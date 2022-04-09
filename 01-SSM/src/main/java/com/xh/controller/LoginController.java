package com.xh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    /**
     * 启动页面跳转到登录页面
     * @return
     */
    @RequestMapping("/login.action")
    public String login(){
        return "forward:/login.jsp";
    }
}
