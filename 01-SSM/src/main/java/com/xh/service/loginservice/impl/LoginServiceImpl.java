package com.xh.service.loginservice.impl;

import com.xh.entity.User;
import com.xh.entity.UserExample;
import com.xh.mapper.UserMapper;
import com.xh.service.loginservice.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public User findUser(User user) {
        UserExample example = new UserExample();
        //将条件封装在里面
        example.createCriteria().andRolsEqualTo(user.getRols());
        example.createCriteria().andUnameEqualTo(user.getUname());
        example.createCriteria().andUpasswordEqualTo(user.getUpassword());
        //查询是否有当前用户
        List<User> userList = userMapper.selectByExample(example);
        System.out.println("userList:"+userList);
        //判断查询的集合里面是否有用户
        if (userList.size() > 0){
            //有就返回
            return userList.get(0);
        }
        //没有返回null
        return null;
    }
}
