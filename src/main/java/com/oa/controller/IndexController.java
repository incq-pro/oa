package com.oa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.oa.entity.Notice;
import com.oa.service.NoticeService;
import com.oa.service.UserService;

import java.util.List;

@Controller
public class IndexController {
    private final NoticeService noticeService;
    private final UserService userService;

    public IndexController(NoticeService noticeService, UserService userService) {
        this.noticeService = noticeService;
        this.userService = userService;
    }

    @GetMapping("/")
    public String index(Model model) {
        List<Notice> notices = noticeService.getAllNotices();
        model.addAttribute("notices", notices);
        model.addAttribute("userCount", userService.getAllUsers().size());
        return "index";
    }
}
