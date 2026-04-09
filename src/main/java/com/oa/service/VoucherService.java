package com.oa.service;

import com.oa.entity.Voucher;
import com.oa.entity.VoucherItem;
import com.oa.entity.VoucherSummary;
import com.oa.entity.SubjectSummary;
import com.oa.entity.AccountSubject;
import com.oa.mapper.VoucherMapper;
import com.oa.mapper.VoucherItemMapper;
import com.oa.mapper.AccountSubjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class VoucherService {
    @Autowired
    private VoucherMapper voucherMapper;
    @Autowired
    private VoucherItemMapper voucherItemMapper;
    @Autowired
    private AccountSubjectMapper accountSubjectMapper;

    public List<VoucherSummary> getVoucherList(String voucherNo, String startDate, String endDate,
                                               Integer auditStatus, Integer postingStatus,
                                               int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        return voucherMapper.selectList(voucherNo, startDate, endDate, auditStatus, postingStatus, offset, pageSize);
    }

    public int getVoucherCount(String voucherNo, String startDate, String endDate,
                                Integer auditStatus, Integer postingStatus) {
        return voucherMapper.countList(voucherNo, startDate, endDate, auditStatus, postingStatus);
    }

    public Voucher getVoucherById(Integer id) {
        Voucher voucher = voucherMapper.selectById(id);
        if (voucher != null) {
            voucher.setItems(voucherItemMapper.selectByVoucherId(id));
        }
        return voucher;
    }

    @Transactional
    public void addVoucher(Voucher voucher, List<VoucherItem> items) {
        validateBalance(items);

        String voucherNo = generateVoucherNo();
        voucher.setVoucherNo(voucherNo);
        voucher.setAuditStatus(0);
        voucher.setPostingStatus(0);

        BigDecimal totalAmount = calculateTotalAmount(items);
        voucher.setTotalAmount(totalAmount);

        voucherMapper.insert(voucher);

        for (int i = 0; i < items.size(); i++) {
            VoucherItem item = items.get(i);
            item.setVoucherId(voucher.getId());
            item.setSeq(i + 1);
            voucherItemMapper.insert(item);
        }
    }

    @Transactional
    public void updateVoucher(Voucher voucher, List<VoucherItem> items) {
        Voucher existing = voucherMapper.selectById(voucher.getId());
        if (existing == null) {
            throw new RuntimeException("凭证不存在");
        }
        if (existing.getPostingStatus() == 1) {
            throw new RuntimeException("已过账的凭证不能修改");
        }

        validateBalance(items);

        BigDecimal totalAmount = calculateTotalAmount(items);
        voucher.setTotalAmount(totalAmount);
        voucherMapper.update(voucher);

        voucherItemMapper.deleteByVoucherId(voucher.getId());
        for (int i = 0; i < items.size(); i++) {
            VoucherItem item = items.get(i);
            item.setVoucherId(voucher.getId());
            item.setSeq(i + 1);
            voucherItemMapper.insert(item);
        }
    }

    @Transactional
    public void deleteVoucher(Integer id) {
        Voucher voucher = voucherMapper.selectById(id);
        if (voucher == null) {
            throw new RuntimeException("凭证不存在");
        }
        if (voucher.getPostingStatus() == 1) {
            throw new RuntimeException("已过账的凭证不能删除");
        }
        voucherItemMapper.deleteByVoucherId(id);
        voucherMapper.delete(id);
    }

    @Transactional
    public void auditVoucher(Integer voucherId, Integer auditorId, Integer status) {
        Voucher voucher = voucherMapper.selectById(voucherId);
        if (voucher == null) {
            throw new RuntimeException("凭证不存在");
        }
        if (voucher.getPostingStatus() == 1) {
            throw new RuntimeException("已过账的凭证不能审核");
        }
        if (voucher.getAuditStatus() == 1) {
            throw new RuntimeException("该凭证已审核，不能重复审核");
        }

        voucher.setCheckerId(auditorId);
        voucher.setAuditStatus(status);
        voucherMapper.update(voucher);
    }

    @Transactional
    public void postVoucher(Integer voucherId, Integer posterId) {
        Voucher voucher = voucherMapper.selectById(voucherId);
        if (voucher == null) {
            throw new RuntimeException("凭证不存在");
        }
        if (voucher.getAuditStatus() != 1) {
            throw new RuntimeException("未审核通过的凭证不能过账");
        }
        if (voucher.getPostingStatus() == 1) {
            throw new RuntimeException("该凭证已过账，不能重复过账");
        }

        voucher.setPosterId(posterId);
        voucher.setPostingStatus(1);
        voucherMapper.update(voucher);
    }

    public List<SubjectSummary> getSubjectSummary(String startDate, String endDate) {
        return voucherMapper.selectSubjectSummary(startDate, endDate);
    }

    public List<AccountSubject> getAllSubjects() {
        return accountSubjectMapper.selectAll();
    }

    private void validateBalance(List<VoucherItem> items) {
        BigDecimal debitTotal = BigDecimal.ZERO;
        BigDecimal creditTotal = BigDecimal.ZERO;

        for (VoucherItem item : items) {
            if ("1".equals(item.getDirection())) {
                debitTotal = debitTotal.add(item.getAmount());
            } else {
                creditTotal = creditTotal.add(item.getAmount());
            }
        }

        if (debitTotal.compareTo(creditTotal) != 0) {
            throw new RuntimeException("借贷不平衡！借方合计：" + debitTotal + "，贷方合计：" + creditTotal);
        }
    }

    private String generateVoucherNo() {
        String dateStr = new SimpleDateFormat("yyyyMMdd").format(new java.util.Date());
        int seq = voucherMapper.countTodayVouchers(dateStr) + 1;
        return "V" + dateStr + String.format("%03d", seq);
    }

    private BigDecimal calculateTotalAmount(List<VoucherItem> items) {
        BigDecimal total = BigDecimal.ZERO;
        for (VoucherItem item : items) {
            if ("1".equals(item.getDirection())) {
                total = total.add(item.getAmount());
            }
        }
        return total;
    }
}
