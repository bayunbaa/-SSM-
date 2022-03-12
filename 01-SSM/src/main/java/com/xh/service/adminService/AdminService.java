package com.xh.service.adminService;

import com.github.pagehelper.PageInfo;
import com.xh.entity.Facility;

import java.util.List;

/**
 * @Description:
 * @Author: xiaohao
 * @Time: 2022/3/12 0:07
 */
public interface AdminService {
    //添加设备
    int add(Facility facility);
    //查询出所有的设备，设备出库前的操作
    List<Facility> info();
    //分页查询设备
    PageInfo<List<Facility>> findAll(Integer page, Integer size);
    //根据设备id查询，查询商品信息
    Facility findById(Integer id);
    //根据设备id,出库设备
    int updateById(Facility facility);
}
