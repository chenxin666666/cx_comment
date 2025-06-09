package com.study.mapper;

import com.study.entity.CommentEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentMapper {

    /**
     * 新增评论
     * @param entity 评论实体
     * @return int
     */
    int addComment(CommentEntity entity);


    /**
     * 删除评论by主键id
     * @param id 主键id
     * @return int
     */
    int deleteCommentById(Long id);


}
