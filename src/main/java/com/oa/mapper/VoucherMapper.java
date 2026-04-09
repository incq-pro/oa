package com.oa.mapper;

import com.oa.entity.Voucher;
import com.oa.entity.VoucherSummary;
import com.oa.entity.SubjectSummary;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface VoucherMapper {
    List<VoucherSummary> selectList(@Param("voucherNo") String voucherNo,
                                   @Param("startDate") String startDate,
                                   @Param("endDate") String endDate,
                                   @Param("auditStatus") Integer auditStatus,
                                   @Param("postingStatus") Integer postingStatus,
                                   @Param("offset") int offset,
                                   @Param("limit") int limit);

    int countList(@Param("voucherNo") String voucherNo,
                  @Param("startDate") String startDate,
                  @Param("endDate") String endDate,
                  @Param("auditStatus") Integer auditStatus,
                  @Param("postingStatus") Integer postingStatus);

    Voucher selectById(Integer id);
    void insert(Voucher voucher);
    void update(Voucher voucher);
    void delete(Integer id);

    int countTodayVouchers(@Param("dateStr") String dateStr);

    List<SubjectSummary> selectSubjectSummary(@Param("startDate") String startDate,
                                             @Param("endDate") String endDate);
}
