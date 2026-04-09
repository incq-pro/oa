package com.oa.controller;

import com.oa.entity.Product;
import com.oa.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("products", productService.getAll());
        return "sales/product_list";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("product", new Product());
        return "sales/product_edit";
    }

    @PostMapping("/save")
    public String save(Product product) {
        if (product.getId() == null) {
            productService.add(product);
        } else {
            productService.update(product);
        }
        return "redirect:/product/list";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("product", productService.getById(id));
        return "sales/product_edit";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        productService.delete(id);
        return "redirect:/product/list";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Integer id, Model model) {
        model.addAttribute("product", productService.getById(id));
        return "sales/product_detail";
    }
}
