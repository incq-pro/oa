package com.oa.controller;

import com.oa.entity.Approval;
import com.oa.entity.ApprovalType;
import com.oa.service.ApprovalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/approval")
public class ApprovalController {
    @Autowired
    private ApprovalService approvalService;

    @GetMapping("/list")
    public String list(Model model) {
        List<Approval> approvals = approvalService.getAllApprovals();
        model.addAttribute("approvals", approvals);
        return "approval/list";
    }

    @GetMapping("/my")
    public String my(Model model) {
        List<Approval> approvals = approvalService.getApprovalsByUserId(1);
        model.addAttribute("approvals", approvals);
        return "approval/my";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("approval", new Approval());
        model.addAttribute("types", approvalService.getAllTypes());
        return "approval/edit";
    }

    @PostMapping("/save")
    public String save(Approval approval) {
        approval.setUserId(1);
        approval.setStatus(0);
        approvalService.addApproval(approval);
        return "redirect:/approval/my";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("approval", approvalService.getApprovalById(id));
        model.addAttribute("types", approvalService.getAllTypes());
        return "approval/edit";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        approvalService.deleteApproval(id);
        return "redirect:/approval/my";
    }

    @GetMapping("/approve/{id}/{status}")
    public String approve(@PathVariable Integer id, @PathVariable Integer status) {
        approvalService.approve(id, status);
        return "redirect:/approval/list";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Integer id, Model model) {
        model.addAttribute("approval", approvalService.getApprovalById(id));
        return "approval/approval_detail";
    }
}
