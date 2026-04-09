package com.oa.mapper;

import com.oa.entity.VoucherItem;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface VoucherItemMapper {
    List<VoucherItem> selectByVoucherId(Integer voucherId);
    void insert(VoucherItem item);
    void deleteByVoucherId(Integer voucherId);
}
