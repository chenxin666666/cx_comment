package com.study.controller;


import com.study.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 评论Controller
 */
@RestController
@RequestMapping("/comment")
@Slf4j
public class CommentController {

    //Logger logger= LoggerFactory.getLogger(CommentController.class);
    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/query",method = RequestMethod.GET)
    public String query(){
        int userTotal = userService.countUserTotal();
        log.info("评论项目可以打印日志了");

        return "用户总人数为："+userTotal;
    }

}
