package com.oa.mapper;

import com.oa.entity.AccountSubject;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface AccountSubjectMapper {
    List<AccountSubject> selectAll();
    List<AccountSubject> selectTree();
    AccountSubject selectById(Integer id);
    void insert(AccountSubject subject);
    void update(AccountSubject subject);
    void delete(Integer id);
}
