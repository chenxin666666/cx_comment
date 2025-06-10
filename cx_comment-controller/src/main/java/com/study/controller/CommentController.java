package com.study.controller;


import com.study.dto.CommentDetailInfoDTO;
import com.study.dto.CommentInfoDTO;
import com.study.dto.CommentResultInfoDTO;
import com.study.param.*;
import com.study.service.ICommentService;
import com.study.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.management.Query;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/comment")
@Slf4j
public class CommentController {


    @Autowired
    private ICommentService commentService;

    /**
     * 增加评论
     * @param param 请求参数
     * @return boolean
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public BaseResult<Boolean> addComment(@RequestBody AddCommentRequestParam param){
        CommentInfoDTO dto = new CommentInfoDTO();
        dto.setUserId(Long.valueOf(param.getUserId()));
        dto.setModule(param.getModule());
        dto.setResourceId(Long.valueOf(param.getResourceId()));
        dto.setContent(param.getContent());
        dto.setStatus(0);
        dto.setScore(param.getScore());
        dto.setStarNum(0);
        dto.setIsDelete(0);
        dto.setCreateTime(new Date());
        dto.setUpdateTime(new Date());
        int count = commentService.addComment(dto);

        return new BaseResult<>(200,true,"添加成功",count > 0);
    }

    /**
     * 删除评论
     * @param param 请求参数
     * @return boolean
     */
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public BaseResult<Boolean> deleteComment(@RequestBody DelCommentRequestParam param){
        CommentInfoDTO dto = new CommentInfoDTO();
        dto.setId(Long.valueOf(param.getCommentId()));
        dto.setUserId(Long.valueOf(param.getUserId()));
        dto.setModule(param.getModule());
        dto.setResourceId(Long.valueOf(param.getResourceId()));
        dto.setUpdateTime(new Date());

        int count = commentService.deleteComment(dto);

        return new BaseResult<>(200,true,"删除成功",count > 0);
    }


    /**
     * 查询评论
     * @param param 请求参数
     * @return boolean
     */
    @RequestMapping(value = "/query",method = RequestMethod.GET)
    public BaseResult<CommentResultParam> queryComment(QueryCommentRequestParam param){
        log.info("查询评论-queryComment-入参:{}",param);
        CommentInfoDTO commentInfoDTO =buildCommentInfoDto(param);

        CommentResultInfoDTO resultInfoDTO = commentService.queryCommentByParam(commentInfoDTO);

        CommentResultParam resultParam = buildCommentResultParam(resultInfoDTO);
        log.info("查询评论-queryComment-出参:{}",resultParam);
        return new BaseResult<>(200,true,"查询成功",resultParam);
    }

    private CommentResultParam buildCommentResultParam(CommentResultInfoDTO resultInfoDTO) {
        if(resultInfoDTO == null){
            return null;
        }
        CommentResultParam resultParam = new CommentResultParam();
        resultParam.setTotal(resultInfoDTO.getTotal());
        resultParam.setList(buildCommentInfoEntityList(resultInfoDTO.getList()));

        return resultParam;
    }

    /**
     * 构建结果集
     */
    private List<CommentInfoEntity> buildCommentInfoEntityList(List<CommentDetailInfoDTO> list) {
        if(CollectionUtils.isEmpty(list)){
            return new ArrayList<>();
        }

        List<CommentInfoEntity> resultList = new ArrayList<>();
        for (CommentDetailInfoDTO source : list) {
            if (source == null) {
                continue;
            }
            CommentInfoEntity target = new CommentInfoEntity();
            target.setUserId(source.getUserId()+"");
            target.setCommentId(source.getId()+"");
            target.setModule(source.getModule());
            target.setResourceId(source.getResourceId()+"");
            target.setContent(source.getContent());
            target.setContentTime(DateUtils.date2Str(source.getCreateTime(),DateUtils.dateFormat));
            target.setStarNum(source.getStarNum());
            target.setAvatar(null);
            target.setUsername(null);
            target.setReplyNum(null);
            target.setStatus(source.getStatus());
            target.setReplyList(null);
            resultList.add(target);
        }
        return resultList;
    }

    /**
     * 构建查询条件
     */
    private CommentInfoDTO buildCommentInfoDto(QueryCommentRequestParam param) {
        if(param == null){
            return null;
        }
        CommentInfoDTO commentInfoDTO = new CommentInfoDTO();
        commentInfoDTO.setUserId(param.getUserId() != null?Long.valueOf(param.getUserId()):null);
        commentInfoDTO.setModule(param.getModule());
        commentInfoDTO.setResourceId(param.getResourceId() != null? Long.valueOf(param.getResourceId()):null);
        commentInfoDTO.setScore(param.getScore());
        commentInfoDTO.setOrder(param.getOrder());
        commentInfoDTO.setPageNum(param.getPageNum());
        commentInfoDTO.setPageSize(param.getPageSize());

        return commentInfoDTO;
    }

}
