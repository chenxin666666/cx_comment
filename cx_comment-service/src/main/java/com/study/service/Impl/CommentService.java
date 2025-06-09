package com.study.service.Impl;

import com.study.dto.CommentInfoDTO;
import com.study.entity.CommentEntity;
import com.study.mapper.CommentMapper;
import com.study.service.ICommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 评论的DTO
 */
@Service
@Slf4j
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


    /**
     * 删除评论
     * @return int
     */
    @Override
    public int deleteComment(CommentInfoDTO dto) {
        log.info("删除评论-入参：{}",dto);
        //TODO 缺少参数校验
        int count = commentMapper.deleteCommentById(dto.getId());
        return count;
    }
}
