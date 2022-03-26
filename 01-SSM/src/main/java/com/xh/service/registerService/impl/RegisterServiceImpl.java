package com.xh.service.registerService.impl;

import com.xh.entity.User;
import com.xh.mapper.RegisterMapper;
import com.xh.service.registerService.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author: xiaohao
 * @Time: 2022/3/20 22:43
 */
@Service
public class RegisterServiceImpl implements RegisterService {
    //注入,注册验证的
    @Autowired
    private RegisterMapper registerMapper;

    //验证用的，判断当前用户名是否已经存在
    @Override
    public User findByUname(String uname) {
        return registerMapper.findByUname(uname);
    }

    //注册用户
    @Override
    public int inserUser(User user) {
        return registerMapper.insertUser(user);
    }
}
