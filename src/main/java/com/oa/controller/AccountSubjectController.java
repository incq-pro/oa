package com.oa.controller;

import com.oa.entity.AccountSubject;
import com.oa.service.AccountSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/accountSubject")
public class AccountSubjectController {
    @Autowired
    private AccountSubjectService accountSubjectService;

    @GetMapping("/list")
    public String list(Model model) {
        List<AccountSubject> tree = accountSubjectService.getSubjectTree();
        model.addAttribute("tree", tree);
        return "voucher/subject_list";
    }

    @GetMapping("/treeData")
    @ResponseBody
    public List<AccountSubject> treeData() {
        return accountSubjectService.getSubjectTree();
    }
}
