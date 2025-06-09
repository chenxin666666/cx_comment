package com.study.param;

import lombok.Data;

import java.util.List;

/**
 * 评论详情实体
 */
@Data
public class CommentInfoEntity {
    /**
     * 用户id
     */
    private String userId;

    /**
     * 评论id
     */
    private String commentId;


    /**
     * 模块
     * 0:社区模块
     * 1:游戏模块
     * 2:短视频模块
     */
    private Integer module;

    /**
     * 资源id
     */
    private String resourceId;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 评论时间
     */
    private String commentTime;

    /**
     * 点赞数
     */
    private Integer starNum;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 用户名
     */
    private String username;

    /**
     * 回复数量
     */
    private Integer replyNum;


    /**
     * 状态
     * 1：置顶
     * 2 or null：取消置顶
     */
    private Integer status;

    /**
     * 子回复列表
     */
    private List<ReplyInfoEntity> list;

}
