package com.xh.service.teacherService;

import com.github.pagehelper.PageInfo;
import com.xh.entity.teacher.Addfacility;

/**
 * @Description: 老师添加设备
 * @Author: xiaohao
 * @Time: 2022/3/26 21:20
 */
public interface TeacherService {
    /**
     * 老师要添加设备
     * @param addfacility
     * @return
     */
    int insert(Addfacility addfacility);

    /**
     * 老师申请设备的进度
     *
     * @param page
     * @param uid
     * @return
     */
    PageInfo<Addfacility> findByUid(Integer page, Integer uid);

    void deleteById(Integer id);

    int insertDeleteFacility(Addfacility addfacility);
}
