package com.oa.service;

import com.oa.entity.AccountSubject;
import com.oa.mapper.AccountSubjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AccountSubjectService {
    @Autowired
    private AccountSubjectMapper accountSubjectMapper;

    public List<AccountSubject> getAllSubjects() {
        return accountSubjectMapper.selectAll();
    }

    public List<AccountSubject> getSubjectTree() {
        return accountSubjectMapper.selectTree();
    }

    public AccountSubject getById(Integer id) {
        return accountSubjectMapper.selectById(id);
    }

    public void addSubject(AccountSubject subject) {
        accountSubjectMapper.insert(subject);
    }

    public void updateSubject(AccountSubject subject) {
        accountSubjectMapper.update(subject);
    }

    public void deleteSubject(Integer id) {
        accountSubjectMapper.delete(id);
    }
}
