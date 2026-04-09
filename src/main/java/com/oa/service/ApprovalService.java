package com.oa.service;

import com.oa.entity.Approval;
import com.oa.entity.ApprovalType;
import com.oa.mapper.ApprovalMapper;
import com.oa.mapper.ApprovalTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ApprovalService {
    @Autowired
    private ApprovalMapper approvalMapper;
    @Autowired
    private ApprovalTypeMapper approvalTypeMapper;

    public List<Approval> getAllApprovals() {
        return approvalMapper.selectAll();
    }

    public List<Approval> getApprovalsByUserId(Integer userId) {
        return approvalMapper.selectByUserId(userId);
    }

    public Approval getApprovalById(Integer id) {
        return approvalMapper.selectById(id);
    }

    public List<ApprovalType> getAllTypes() {
        return approvalTypeMapper.selectAll();
    }

    public void addApproval(Approval approval) {
        approvalMapper.insert(approval);
    }

    public void updateApproval(Approval approval) {
        approvalMapper.update(approval);
    }

    public void deleteApproval(Integer id) {
        approvalMapper.delete(id);
    }

    public void approve(Integer id, Integer status) {
        Approval approval = new Approval();
        approval.setId(id);
        approval.setStatus(status);
        approvalMapper.update(approval);
    }
}
