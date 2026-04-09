package com.oa.controller;

import com.oa.entity.Customer;
import com.oa.entity.Product;
import com.oa.entity.SalesOrder;
import com.oa.entity.SalesOrderItem;
import com.oa.service.CustomerService;
import com.oa.service.ProductService;
import com.oa.service.SalesOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/sales")
public class SalesOrderController {
    @Autowired
    private SalesOrderService salesOrderService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private ProductService productService;

    @GetMapping("/list")
    public String list(@RequestParam(defaultValue = "1") int page,
                      @RequestParam(defaultValue = "10") int size,
                      Model model) {
        model.addAttribute("orders", salesOrderService.getOrdersByPage(page, size));
        model.addAttribute("currentPage", page);
        model.addAttribute("totalCount", salesOrderService.getTotalCount());
        model.addAttribute("pageSize", size);
        return "sales/order_list";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("order", new SalesOrder());
        model.addAttribute("customers", customerService.getAll());
        model.addAttribute("products", productService.getAll());
        return "sales/order_edit";
    }

    @PostMapping("/save")
    public String save(@RequestParam Integer customerId,
                      @RequestParam String itemsJson,
                      @RequestParam(required = false) Integer id) {
        SalesOrder order = new SalesOrder();
        order.setCustomerId(customerId);
        order.setUserId(1);

        List<SalesOrderItem> items = parseItems(itemsJson);
        salesOrderService.add(order, items);
        return "redirect:/sales/list";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Integer id, Model model) {
        SalesOrder order = salesOrderService.getById(id);
        List<SalesOrderItem> items = salesOrderService.getItemsByOrderId(id);
        order.setItems(items);
        model.addAttribute("order", order);
        return "sales/order_detail";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        salesOrderService.delete(id);
        return "redirect:/sales/list";
    }

    @GetMapping("/approve/{id}")
    public String approve(@PathVariable Integer id) {
        salesOrderService.approve(id);
        return "redirect:/sales/list";
    }

    private List<SalesOrderItem> parseItems(String itemsJson) {
        List<SalesOrderItem> items = new ArrayList<>();
        if (itemsJson == null || itemsJson.isEmpty()) return items;

        String[] pairs = itemsJson.split(";");
        for (String pair : pairs) {
            String[] parts = pair.split(",");
            if (parts.length == 3) {
                SalesOrderItem item = new SalesOrderItem();
                item.setProductId(Integer.parseInt(parts[0]));
                item.setPrice(new BigDecimal(parts[1]));
                item.setQuantity(Integer.parseInt(parts[2]));
                items.add(item);
            }
        }
        return items;
    }
}
