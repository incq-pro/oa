package com.oa.controller;

import com.oa.entity.Notice;
import com.oa.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/notice")
public class NoticeController {
    @Autowired
    private NoticeService noticeService;

    @GetMapping("/list")
    public String list(@RequestParam(defaultValue = "1") int page,
                      @RequestParam(defaultValue = "10") int size,
                      Model model) {
        model.addAttribute("notices", noticeService.getNoticesByPage(page, size));
        model.addAttribute("currentPage", page);
        model.addAttribute("totalCount", noticeService.getTotalCount());
        model.addAttribute("pageSize", size);
        return "notice/list";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("notice", new Notice());
        return "notice/edit";
    }

    @PostMapping("/save")
    public String save(Notice notice) {
        notice.setAuthorId(1);
        if (notice.getId() == null) {
            noticeService.addNotice(notice);
        } else {
            noticeService.updateNotice(notice);
        }
        return "redirect:/notice/list";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("notice", noticeService.getNoticeById(id));
        return "notice/edit";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        noticeService.deleteNotice(id);
        return "redirect:/notice/list";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Integer id, Model model) {
        model.addAttribute("notice", noticeService.getNoticeById(id));
        return "notice/notice_detail";
    }
}
