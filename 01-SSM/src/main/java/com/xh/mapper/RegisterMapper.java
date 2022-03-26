package com.xh.mapper;

import com.xh.entity.User;

/**
 * @Description:
 * @Author: xiaohao
 * @Time: 2022/3/20 22:44
 */
public interface RegisterMapper {
    /**
     * 判断当前用户名是否已经被注册
     * @param uname
     * @return
     */
    User findByUname(String uname);

    /**
     * 注册用户，将这条记录插入到数据库
     * @param user
     * @return
     */
    int insertUser(User user);
}
