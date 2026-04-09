package com.oa.service;

import com.oa.entity.SalesOrder;
import com.oa.entity.SalesOrderItem;
import com.oa.mapper.SalesOrderMapper;
import com.oa.mapper.SalesOrderItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class SalesOrderService {
    @Autowired
    private SalesOrderMapper salesOrderMapper;
    @Autowired
    private SalesOrderItemMapper salesOrderItemMapper;

    public List<SalesOrder> getAll() {
        return salesOrderMapper.selectAll();
    }

    public List<SalesOrder> getOrdersByPage(int page, int size) {
        int offset = (page - 1) * size;
        return salesOrderMapper.selectList(offset, size);
    }

    public int getTotalCount() {
        return salesOrderMapper.count();
    }

    public SalesOrder getById(Integer id) {
        return salesOrderMapper.selectById(id);
    }

    public List<SalesOrderItem> getItemsByOrderId(Integer orderId) {
        return salesOrderItemMapper.selectByOrderId(orderId);
    }

    @Transactional
    public void add(SalesOrder order, List<SalesOrderItem> items) {
        order.setOrderNo(generateOrderNo());
        order.setCreateTime(new Date());
        order.setStatus(0);
        salesOrderMapper.insert(order);

        BigDecimal total = BigDecimal.ZERO;
        for (SalesOrderItem item : items) {
            item.setOrderId(order.getId());
            item.setSubtotal(item.getPrice().multiply(new BigDecimal(item.getQuantity())));
            salesOrderItemMapper.insert(item);
            total = total.add(item.getSubtotal());
        }
        order.setTotalAmount(total);
        salesOrderMapper.update(order);
    }

    @Transactional
    public void update(SalesOrder order, List<SalesOrderItem> items) {
        salesOrderMapper.update(order);

        salesOrderItemMapper.deleteByOrderId(order.getId());
        BigDecimal total = BigDecimal.ZERO;
        for (SalesOrderItem item : items) {
            item.setOrderId(order.getId());
            item.setSubtotal(item.getPrice().multiply(new BigDecimal(item.getQuantity())));
            salesOrderItemMapper.insert(item);
            total = total.add(item.getSubtotal());
        }
        order.setTotalAmount(total);
        salesOrderMapper.update(order);
    }

    public void delete(Integer id) {
        salesOrderItemMapper.deleteByOrderId(id);
        salesOrderMapper.delete(id);
    }

    public void approve(Integer id) {
        SalesOrder order = salesOrderMapper.selectById(id);
        if (order != null) {
            order.setStatus(1);
            salesOrderMapper.update(order);
        }
    }

    private String generateOrderNo() {
        return "SO" + System.currentTimeMillis();
    }
}
