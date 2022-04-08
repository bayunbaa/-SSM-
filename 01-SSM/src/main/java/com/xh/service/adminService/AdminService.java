package com.xh.service.adminService;

import com.github.pagehelper.PageInfo;
import com.xh.entity.Facility;
import com.xh.entity.User;
import com.xh.entity.admin.AdminVo;
import com.xh.entity.student.Repairs;
import com.xh.entity.teacher.Addfacility;

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
    //出库多条件查询
    PageInfo<List<Facility>>  findByAdminVo(AdminVo adminVo ,Integer size, Integer page);
    //查看教室需要安装的设备
    PageInfo<List<Addfacility>> findAddFacility(Integer page);
    //根据要安装设备的名字查询仓库是否有该设备
    PageInfo<List<Facility>> findByName(Integer page, Integer id);
    //安装设备，库存减1，如果这是最后一件，就删除该商品，并修改申请安装商品的状态
    int updateFacilityById(Integer id, Integer n, String location);

    //查看所有已安装的设备
    PageInfo<List<Addfacility>> findAddfacilityByPlan(Integer page);
    //查询所有未维修设备信息
    PageInfo<List<Repairs>> findServiceByplan(Integer page);
    //查询所有已修的设备信息
    PageInfo<List<Repairs>> findServiceByYiXiuplan(Integer page);
    //ajax判断维修工用户名是否重复
    User findAjaxUname(String uname);
    //添加维修工账号
    int addWorker(User user);
    //查看全部维修工的信息
    PageInfo<List<User>> findWorker(Integer page);
    //查看全部教师的信息
    PageInfo<List<User>> findTeacher(Integer page);
    //查看全部学生的信息
    PageInfo<List<User>> findStudent(Integer page);
    //需要商品采购
    PageInfo<List<Addfacility>> caigou(Integer page);
}
