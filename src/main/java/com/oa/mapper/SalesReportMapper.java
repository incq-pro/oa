package com.oa.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;
import java.util.Map;

@Mapper
public interface SalesReportMapper {
    @Select("SELECT COUNT(*) as orderCount, SUM(total_amount) as totalAmount FROM sales_order WHERE status = 1")
    Map<String, Object> getCompletedSalesSummary();

    @Select("SELECT COUNT(*) as orderCount, SUM(total_amount) as totalAmount FROM sales_order WHERE status = 0")
    Map<String, Object> getPendingSalesSummary();

    @Select("SELECT c.name as customerName, SUM(o.total_amount) as totalAmount, COUNT(o.id) as orderCount " +
            "FROM sales_order o LEFT JOIN customer c ON o.customer_id = c.id " +
            "WHERE o.status = 1 GROUP BY c.id ORDER BY totalAmount DESC")
    List<Map<String, Object>> getSalesByCustomer();

    @Select("SELECT strftime('%Y-%m', create_time) as month, COUNT(*) as orderCount, SUM(total_amount) as totalAmount " +
            "FROM sales_order WHERE status = 1 GROUP BY month ORDER BY month DESC LIMIT 12")
    List<Map<String, Object>> getMonthlySales();
}
