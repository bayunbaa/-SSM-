package com.xh.service.registerService;

import com.xh.entity.User;

/**
 * @Description:  注册验证
 * @Author: xiaohao
 * @Time: 2022/3/20 22:42
 */
public interface RegisterService {
    /**
     * 判断当前用户名是否已经被注册
     * @param uname
     * @return
     */
    User findByUname(String uname);

    /**
     * 将记录插入到数据库
     * @param user
     * @return
     */
    int inserUser(User user);
}
