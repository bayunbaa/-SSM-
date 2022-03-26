package com.xh.service.studentService;

import com.github.pagehelper.PageInfo;
import com.xh.entity.User;
import com.xh.entity.student.Repairs;

import java.util.List;

/**
 * @Description: 用来操作学生这个角色
 * @Author: xiaohao
 * @Time: 2022/3/26 14:31
 */
public interface StudentService {

    /**
     * 提交设备报修信息
     * @param repairs
     */
    int insert(Repairs repairs);

    /**
     * 查看当前用户设备报修信息
     * @param uid
     * @return
     */
    PageInfo<List<Repairs>> findByUid(Integer page, Integer uid);

    /**
     * 修改密码
     * @param user
     * @return
     */
    int updateByUid(User user);
}
