package com.xh.controller;

import com.xh.entity.User;
import com.xh.service.registerService.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description:  注册
 * @Author: xiaohao
 * @Time: 2022/3/20 21:44
 */
@Controller
@RequestMapping("/register")
public class RegisterController {
    //注册，判断当前注册的用户是否存在
    @Autowired
    private RegisterService registerService;


    /**
     * 判断用户名是否重复
     * @return
     */
    @ResponseBody
    @RequestMapping("/ajaxByName.action")
    public User ajaxByName(String uname){
        System.out.println(uname);
        User user = registerService.findByUname(uname);
        System.out.println(user);
        return user;
    }
}
