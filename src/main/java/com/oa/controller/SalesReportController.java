package com.oa.controller;

import com.oa.service.SalesReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sales/report")
public class SalesReportController {
    @Autowired
    private SalesReportService salesReportService;

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("summary", salesReportService.getDashboardSummary());
        model.addAttribute("customerSales", salesReportService.getSalesByCustomer());
        model.addAttribute("monthlySales", salesReportService.getMonthlySales());
        return "sales/report_dashboard";
    }
}
