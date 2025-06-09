package com.study.service;

import com.study.dto.CommentInfoDTO;

public interface ICommentService {

    /**
     * 新增评论
     * @return int
     */
    int addComment(CommentInfoDTO dto);
}
