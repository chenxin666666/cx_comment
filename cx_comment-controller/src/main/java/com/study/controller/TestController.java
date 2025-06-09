package com.study.controller;


import com.study.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 评论Controller
 */
@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {

    //Logger logger= LoggerFactory.getLogger(CommentController.class);
    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public String test(){
        int userTotal = userService.countUserTotal();
        log.info("评论项目可以打印日志了");

        return "用户总人数为："+userTotal;
    }

}
