package com.study.controller;


import com.alibaba.fastjson.JSON;
import com.study.dto.CommentDetailInfoDTO;
import com.study.dto.CommentInfoDTO;
import com.study.dto.CommentResultInfoDTO;
import com.study.param.*;
import com.study.service.ICommentService;
import com.study.utils.BaseResultUtils;
import com.study.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.apache.commons.lang3.StringUtils;
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
        try{
            log.info("增加评论-controller层-addComment-入参:{}", JSON.toJSONString(param));
            //入参校验
            checkParam(param);
            CommentInfoDTO dto = buildCommentInfoDTO(param);
            int count = commentService.addComment(dto);
            log.info("增加评论-controller层-addComment-出参:{}", count);
            return BaseResultUtils.generateSuccess(count>0);
        }catch (Exception e){
            log.error("增加评论-controller层-addComment-异常",e);
            return BaseResultUtils.generateFail("增加评论异常");
        }

    }

    /**
     * 入参校验
     */
    private void checkParam(AddCommentRequestParam param) {
        Assert.isTrue(param != null,"入参不能为空");
        Assert.isTrue(StringUtils.isNotBlank(param.getUserId()),"用户id不能为空");
        Assert.isTrue(param.getModule() != null,"模块不能为空");
        Assert.isTrue(StringUtils.isNotBlank(param.getResourceId()),"资源id不能为空");
        Assert.isTrue(StringUtils.isNotBlank(param.getContent()),"评论内容不能为空");
    }

    private static CommentInfoDTO buildCommentInfoDTO(AddCommentRequestParam param) {
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
        return dto;
    }

    /**
     * 删除评论
     * @param param 请求参数
     * @return boolean
     */
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public BaseResult<Boolean> deleteComment(@RequestBody DelCommentRequestParam param){
        try {
            log.info("增加评论-controller层-deleteComment-入参:{}", JSON.toJSONString(param));
            CommentInfoDTO dto = buildCommentInfoDTO(param);
            int count = commentService.deleteComment(dto);

            log.info("增加评论-controller层-deleteComment-出参:{}",count);

            return BaseResultUtils.generateSuccess(count > 0);
        }catch (Exception e){
            log.error("增加评论-controller层-deleteComment-异常",e);
            return BaseResultUtils.generateFail("删除评论失败");
        }
    }

    private static CommentInfoDTO buildCommentInfoDTO(DelCommentRequestParam param) {
        CommentInfoDTO dto = new CommentInfoDTO();
        dto.setId(Long.valueOf(param.getCommentId()));
        dto.setUserId(Long.valueOf(param.getUserId()));
        dto.setModule(param.getModule());
        dto.setResourceId(Long.valueOf(param.getResourceId()));
        dto.setUpdateTime(new Date());
        return dto;
    }


    /**
     * 查询评论
     * @param param 请求参数
     * @return boolean
     */
    @RequestMapping(value = "/query",method = RequestMethod.GET)
    public BaseResult<CommentResultParam> queryComment(QueryCommentRequestParam param){

        try{
            log.info("查询评-controller层-论queryComment-入参:{}", JSON.toJSONString(param));
            CommentInfoDTO commentInfoDTO = buildCommentInfoDto(param);
            CommentResultInfoDTO resultInfoDTO = commentService.queryCommentByParam(commentInfoDTO);
            CommentResultParam resultParam = buildCommentResultParam(resultInfoDTO);
            log.info("查询评论-controller层-queryComment-出参:{}", JSON.toJSONString(resultParam));
            return BaseResultUtils.generateSuccess(resultParam);
        }catch (Exception e){
            log.error("查询评论-controller层-queryComment-异常",e);
            return BaseResultUtils.generateFail("查询评论失败");
        }
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
