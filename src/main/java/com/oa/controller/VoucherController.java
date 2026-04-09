package com.oa.controller;

import com.oa.entity.Voucher;
import com.oa.entity.VoucherItem;
import com.oa.entity.VoucherSummary;
import com.oa.entity.SubjectSummary;
import com.oa.entity.AccountSubject;
import com.oa.entity.User;
import com.oa.service.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/voucher")
public class VoucherController {
    @Autowired
    private VoucherService voucherService;

    @GetMapping("/list")
    public String list(Model model,
                      @RequestParam(required = false) String voucherNo,
                      @RequestParam(required = false) String startDate,
                      @RequestParam(required = false) String endDate,
                      @RequestParam(required = false) String auditStatus,
                      @RequestParam(required = false) String postingStatus,
                      @RequestParam(defaultValue = "1") int page,
                      @RequestParam(defaultValue = "15") int pageSize) {

        Integer auditStatusInt = (auditStatus == null || auditStatus.isEmpty()) ? null : Integer.parseInt(auditStatus);
        Integer postingStatusInt = (postingStatus == null || postingStatus.isEmpty()) ? null : Integer.parseInt(postingStatus);

        List<VoucherSummary> list = voucherService.getVoucherList(voucherNo, startDate, endDate, auditStatusInt, postingStatusInt, page, pageSize);
        int total = voucherService.getVoucherCount(voucherNo, startDate, endDate, auditStatusInt, postingStatusInt);

        int pendingCount = voucherService.getVoucherCount(voucherNo, startDate, endDate, 0, null);
        int postedCount = voucherService.getVoucherCount(voucherNo, startDate, endDate, null, 1);

        java.math.BigDecimal totalAmount = list.stream()
            .map(VoucherSummary::getTotalAmount)
            .filter(java.math.BigDecimal.class::isInstance)
            .map(java.math.BigDecimal.class::cast)
            .reduce(java.math.BigDecimal.ZERO, java.math.BigDecimal::add);

        model.addAttribute("list", list);
        model.addAttribute("total", total);
        model.addAttribute("pendingCount", pendingCount);
        model.addAttribute("postedCount", postedCount);
        model.addAttribute("totalAmount", totalAmount);
        model.addAttribute("page", page);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("totalPages", (total + pageSize - 1) / pageSize);
        model.addAttribute("voucherNo", voucherNo);
        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);
        model.addAttribute("auditStatus", auditStatus);
        model.addAttribute("postingStatus", postingStatus);

        return "voucher/list";
    }

    @GetMapping("/add")
    public String add(Model model) {
        List<AccountSubject> subjects = voucherService.getAllSubjects();
        Voucher voucher = new Voucher();
        voucher.setVoucherDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        model.addAttribute("subjects", subjects);
        model.addAttribute("voucher", voucher);
        return "voucher/edit";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        Voucher voucher = voucherService.getVoucherById(id);
        List<AccountSubject> subjects = voucherService.getAllSubjects();
        model.addAttribute("voucher", voucher);
        model.addAttribute("subjects", subjects);
        return "voucher/edit";
    }

    @PostMapping("/save")
    public String save(@RequestParam(required = false) Integer id,
                       @RequestParam String voucherDate,
                       @RequestParam(defaultValue = "0") Integer attachmentCount,
                       @RequestParam(required = false) String remark,
                       @RequestParam("subjectId") Integer[] subjectIds,
                       @RequestParam("direction") String[] directions,
                       @RequestParam("amount") BigDecimal[] amounts,
                       @RequestParam("summary") String[] summaries,
                       HttpSession session) {

        List<VoucherItem> items = new ArrayList<>();
        for (int i = 0; i < subjectIds.length; i++) {
            VoucherItem item = new VoucherItem();
            item.setSubjectId(subjectIds[i]);
            item.setDirection(directions[i]);
            item.setAmount(amounts[i]);
            item.setSummary(summaries[i]);
            items.add(item);
        }

        User loginUser = (User) session.getAttribute("loginUser");
        int makerId = loginUser != null ? loginUser.getId() : 1;

        Voucher voucher = new Voucher();
        voucher.setVoucherDate(voucherDate);
        voucher.setAttachmentCount(attachmentCount);
        voucher.setRemark(remark);
        voucher.setMakerId(makerId);

        if (id == null) {
            voucherService.addVoucher(voucher, items);
        } else {
            voucher.setId(id);
            voucherService.updateVoucher(voucher, items);
        }

        return "redirect:/voucher/list";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        voucherService.deleteVoucher(id);
        return "redirect:/voucher/list";
    }

    @GetMapping("/audit/{id}/{status}")
    public String audit(@PathVariable Integer id, @PathVariable Integer status, HttpSession session) {
        User loginUser = (User) session.getAttribute("loginUser");
        int auditorId = loginUser != null ? loginUser.getId() : 1;
        voucherService.auditVoucher(id, auditorId, status);
        return "redirect:/voucher/list";
    }

    @GetMapping("/post/{id}")
    public String post(@PathVariable Integer id, HttpSession session) {
        User loginUser = (User) session.getAttribute("loginUser");
        int posterId = loginUser != null ? loginUser.getId() : 1;
        voucherService.postVoucher(id, posterId);
        return "redirect:/voucher/list";
    }

    @GetMapping("/summary")
    public String summary(Model model,
                          @RequestParam(required = false) String startDate,
                          @RequestParam(required = false) String endDate) {

        if (startDate == null || startDate.isEmpty()) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Calendar cal = java.util.Calendar.getInstance();
            cal.set(java.util.Calendar.DAY_OF_MONTH, 1);
            startDate = sdf.format(cal.getTime());
        }
        if (endDate == null || endDate.isEmpty()) {
            endDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        }

        List<SubjectSummary> summaryList = voucherService.getSubjectSummary(startDate, endDate);

        model.addAttribute("summaryList", summaryList);
        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);

        return "voucher/summary";
    }

    @GetMapping("/subject/list")
    public String subjectList(Model model) {
        List<AccountSubject> subjects = voucherService.getAllSubjects();
        model.addAttribute("subjects", subjects);
        return "voucher/subject_list";
    }
}
