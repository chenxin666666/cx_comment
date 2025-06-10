package com.study.service;

import com.study.dto.CommentInfoDTO;
import com.study.dto.CommentResultInfoDTO;

public interface ICommentService {

    /**
     * 新增评论
     * @return int
     */
    int addComment(CommentInfoDTO dto);


    /**
     * 删除评论
     * @return int
     */
    int deleteComment(CommentInfoDTO dto);

    /**
     *  查询评论
     * @param dto CommentInfoDT
     * @return CommentResultInfoDTO
     */
    CommentResultInfoDTO queryCommentByParam(CommentInfoDTO dto);
}
