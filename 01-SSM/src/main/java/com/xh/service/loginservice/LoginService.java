package com.xh.service.loginservice;

import com.xh.entity.User;

public interface LoginService {
    //根据用户名密码，类别判断当前用户是否存在
    User findUser(User user);
}
