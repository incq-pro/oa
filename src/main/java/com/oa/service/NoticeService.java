package com.oa.service;

import com.oa.entity.Notice;
import com.oa.mapper.NoticeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class NoticeService {
    @Autowired
    private NoticeMapper noticeMapper;

    public List<Notice> getAllNotices() {
        return noticeMapper.selectAll();
    }

    public List<Notice> getNoticesByPage(int page, int size) {
        int offset = (page - 1) * size;
        return noticeMapper.selectList(offset, size);
    }

    public int getTotalCount() {
        return noticeMapper.count();
    }

    public Notice getNoticeById(Integer id) {
        return noticeMapper.selectById(id);
    }

    public void addNotice(Notice notice) {
        noticeMapper.insert(notice);
    }

    public void updateNotice(Notice notice) {
        noticeMapper.update(notice);
    }

    public void deleteNotice(Integer id) {
        noticeMapper.delete(id);
    }
}
