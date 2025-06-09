package com.study.service.Impl;

import com.study.dto.CommentInfoDTO;
import com.study.entity.CommentEntity;
import com.study.mapper.CommentMapper;
import com.study.service.ICommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 评论的DTO
 */
@Service
public class CommentService implements ICommentService {

    @Autowired
    private CommentMapper commentMapper;

    /**
     * 新增评论
     * @return int
     */
    @Override
    public int addComment(CommentInfoDTO dto) {
        CommentEntity commentEntity = new CommentEntity();
        BeanUtils.copyProperties(dto,commentEntity);
        int count = commentMapper.addComment(commentEntity);
        return count;
    }
}
