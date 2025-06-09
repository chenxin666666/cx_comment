package com.study.entity;


import lombok.Data;

import java.util.Date;

@Data
public class CommentEntity {

    /**
     * 主键id
     */
    private Long id;

    /**
     * 用户id
     */
    private Long userId;


    /**
     * 模块
     */
    private Integer module;

    /**
     * 资源id
     */
    private Long resourceId;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 评分
     */
    private Integer score;


    /**
     * 点赞数量
     */
    private Integer starNum;

    /**
     * 是否删除
     */
    private Integer isDelete;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

}
