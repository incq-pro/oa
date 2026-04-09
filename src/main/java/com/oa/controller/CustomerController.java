package com.oa.controller;

import com.oa.entity.Customer;
import com.oa.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("customers", customerService.getAll());
        return "sales/customer_list";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("customer", new Customer());
        return "sales/customer_edit";
    }

    @PostMapping("/save")
    public String save(Customer customer) {
        if (customer.getId() == null) {
            customerService.add(customer);
        } else {
            customerService.update(customer);
        }
        return "redirect:/customer/list";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("customer", customerService.getById(id));
        return "sales/customer_edit";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        customerService.delete(id);
        return "redirect:/customer/list";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Integer id, Model model) {
        model.addAttribute("customer", customerService.getById(id));
        return "sales/customer_detail";
    }
}
