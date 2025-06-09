package com.study.controller;


import com.study.param.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.management.Query;

@RestController
@RequestMapping("/comment")
public class CommentController {


    /**
     * 增加评论
     * @param param 请求参数
     * @return boolean
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public BaseResult<Boolean> addComment(AddCommentRequestParam param){
        return null;
    }

    /**
     * 删除评论
     * @param param 请求参数
     * @return boolean
     */
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public BaseResult<Boolean> deleteComment(DelCommentRequestParam param){
        return null;
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
