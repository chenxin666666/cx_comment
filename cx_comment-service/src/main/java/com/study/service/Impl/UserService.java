package com.study.service.Impl;

import com.study.mapper.UserMapper;
import com.study.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService implements IUserService {


    @Autowired
    private UserMapper userMapper;

    /**
     * 查询用户总人数
     * @return int
     */
    @Override
    public int countUserTotal() {
        return userMapper.countUserTotal();
    }
}
