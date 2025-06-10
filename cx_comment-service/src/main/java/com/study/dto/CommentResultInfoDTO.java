package com.study.dto;

import lombok.Data;

import java.util.List;

/**
 * 评论结果集
 */
@Data
public class CommentResultInfoDTO {

    /**
     * 评论总数
     */
    private Long total;

    private List<CommentDetailInfoDTO> list;
}
