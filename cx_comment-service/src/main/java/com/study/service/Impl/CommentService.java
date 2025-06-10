package com.study.service.Impl;

import com.study.dto.CommentDetailInfoDTO;
import com.study.dto.CommentInfoDTO;
import com.study.dto.CommentResultInfoDTO;
import com.study.entity.CommentEntity;
import com.study.entity.CommentParam;
import com.study.mapper.CommentMapper;
import com.study.service.ICommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

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

    /**
     * 查询评论
     * @return CommentResultInfoDTO
     */
    @Override
    public CommentResultInfoDTO queryCommentByParam(CommentInfoDTO dto) {
        log.info("查询评论-queryCommentByParam-入参:{}",dto);
        CommentResultInfoDTO resultInfoDTO = new CommentResultInfoDTO();

        //检查参数
        checkParam(dto);

        //构建查询条件
        CommentParam queryParam = buildQueryCommentParam(dto);

        //查询评论总数
        int total = commentMapper.countCommentByParam(queryParam);
        resultInfoDTO.setTotal(Long.valueOf(total+""));
        if(total <= 0){
            return resultInfoDTO;
        }

        //查询评论列表
        List<CommentEntity> commentEntities = commentMapper.queryCommentByParam(queryParam);

        //组装结果集
        List<CommentDetailInfoDTO> list  = buildResultList(commentEntities);
        resultInfoDTO.setList(list);
        log.info("查询评论-queryCommentByParam-出参:{}",resultInfoDTO);
        return resultInfoDTO;
    }

    /**
     * 构建结果集
     * @param commentEntities 评论实体列表
     * @return List<CommentDetailInfoDTO>
     */
    private List<CommentDetailInfoDTO> buildResultList(List<CommentEntity> commentEntities) {
        if(CollectionUtils.isEmpty(commentEntities)){
            return new ArrayList<>();
        }
        List<CommentDetailInfoDTO> resultInfoList = new ArrayList<>();
        for (int i = 0; i < commentEntities.size(); i++) {
            CommentEntity commentEntity = commentEntities.get(i);
            if(commentEntity == null){
                continue;
            }
            CommentDetailInfoDTO target = new CommentDetailInfoDTO();

            BeanUtils.copyProperties(commentEntity,target);
            resultInfoList.add(target);
        }

        return resultInfoList;
    }

    /**
     * 构建查询条件
     * @param dto
     * @return
     */
    private CommentParam buildQueryCommentParam(CommentInfoDTO dto) {
        CommentParam commentParam = new CommentParam();
        commentParam.setModule(dto.getModule());
        commentParam.setResourceId(dto.getResourceId());
        commentParam.setLimit(dto.getPageSize());
        commentParam.setOffset(buildOffset(dto.getPageNum(),dto.getPageSize()));
        if(dto.getOrder() == null){
            //设置一个默认值
            commentParam.setOrderBy("create_time");
            commentParam.setOrderDirection("desc");
        }else {
            if(dto.getOrder() == 2){
                commentParam.setOrderBy("star_num");
                commentParam.setOrderDirection("desc");
            }else {
                commentParam.setOrderBy("create_time");
                commentParam.setOrderDirection("asc");
            }
        }
        return commentParam;
    }

    /**
     * 计算偏移量
     * @param pageNum 页码
     * @param pageSize 页面大小
     * @return Integer
     */
    private Integer buildOffset(Integer pageNum, Integer pageSize) {

        int offset = (pageNum - 1) * pageSize;
        return Math.max(0,offset);
    }

    /**
     * 检查参数
     */
    private void checkParam(CommentInfoDTO dto) {
        Assert.isTrue(dto != null,"参数不能为空");
        Assert.isTrue(dto.getModule() != null,"模块不能为空");
        Assert.isTrue(dto.getResourceId() != null,"资源id不能为空");

        if(dto.getPageNum() == null || dto.getPageSize() == null){
            dto.setPageNum(1);
            dto.setPageSize(10);
        }
    }
}
