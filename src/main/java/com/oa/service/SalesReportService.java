package com.oa.service;

import com.oa.mapper.SalesReportMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SalesReportService {
    @Autowired
    private SalesReportMapper reportMapper;

    public Map<String, Object> getDashboardSummary() {
        Map<String, Object> summary = new HashMap<>();
        Map<String, Object> completed = reportMapper.getCompletedSalesSummary();
        Map<String, Object> pending = reportMapper.getPendingSalesSummary();

        summary.put("completedCount", completed.get("orderCount") != null ? completed.get("orderCount") : 0);
        summary.put("completedAmount", completed.get("totalAmount") != null ? completed.get("totalAmount") : BigDecimal.ZERO);
        summary.put("pendingCount", pending.get("orderCount") != null ? pending.get("orderCount") : 0);
        summary.put("pendingAmount", pending.get("totalAmount") != null ? pending.get("totalAmount") : BigDecimal.ZERO);

        return summary;
    }

    public List<Map<String, Object>> getSalesByCustomer() {
        return reportMapper.getSalesByCustomer();
    }

    public List<Map<String, Object>> getMonthlySales() {
        return reportMapper.getMonthlySales();
    }
}
