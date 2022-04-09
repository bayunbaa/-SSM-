package com.xh.service.workerService;

import com.github.pagehelper.PageInfo;
import com.xh.entity.student.Repairs;
import com.xh.entity.teacher.Addfacility;

import java.util.List;

/**
 * @Description:
 * @Author: xiaohao
 * @Time: 2022/3/27 13:52
 */
public interface WorkerService {

    /**
     * 查找所有的报修记录
     * @return
     */
    PageInfo<List<Repairs>> findAll(Integer page);

    /**
     * 进来之后表示该条设备问题已被解决
     * @param id
     * @return
     */
    int updateById(Integer id);

    /**
     * 查询已解决的问题
     * @param page
     * @return
     */
    PageInfo<List<Repairs>> selectByPlan(Integer page);

    /**
     * 查询出管理员已经审核通过的设备，维修人员去安装
     * @param page
     * @return
     */
    PageInfo<List<Addfacility>> findAddFacilityByPlan(Integer page);

    /**
     * 根据管理员审核通过的需要安装设备，去安装
     * @param id
     * @return
     */
    int updateAddFacilityByPlan(Integer id);
}
