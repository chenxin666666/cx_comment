package com.study.mapper;

import com.study.entity.CommentEntity;
import com.study.entity.CommentParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

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

    /**
     * 分页查询评论
     * @param param 分页查询参数
     * @return List<CommentEntity>
     */
    List<CommentEntity> queryCommentByParam(CommentParam param);

    /**
     * 分页查询评论总数
     * @param param 分页查询参数
     * @return List<CommentEntity>
     */
     int countCommentByParam(CommentParam param);



}
