package com.study.controller;


import com.study.dto.CommentInfoDTO;
import com.study.param.*;
import com.study.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.management.Query;
import java.util.Date;

@RestController
@RequestMapping("/comment")
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
        return null;
    }

}
