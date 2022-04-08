package com.xh.service.adminService.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xh.entity.*;
import com.xh.entity.admin.AdminVo;
import com.xh.entity.student.Repairs;
import com.xh.entity.student.RepairsExample;
import com.xh.entity.teacher.Addfacility;
import com.xh.entity.teacher.AddfacilityExample;
import com.xh.mapper.UserMapper;
import com.xh.mapper.admin.DeliveryMapper;
import com.xh.mapper.admin.FacilityMapper;
import com.xh.mapper.student.RepairsMapper;
import com.xh.mapper.teacher.AddfacilityMapper;
import com.xh.service.adminService.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.dc.pr.PRError;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @Author: xiaohao
 * @Time: 2022/3/12 17:19
 */
@Service
public class AdminServiceImpl implements AdminService {
    //注入
    //设备进库表
    @Autowired
    private FacilityMapper facilityMapper;
    //设备出库表
    @Autowired
    private DeliveryMapper deliveryMapper;

    //需要安装设备的表
    @Autowired
    private AddfacilityMapper addfacilityMapper;
    //操作维修记录
    @Autowired
    private RepairsMapper repairsMapper;
    //操作user表
    @Autowired
    private UserMapper userMapper;

    @Override
    public int add(Facility facility) {
        int num = facilityMapper.insertSelective(facility);
        return num;
    }

    /**
     * 查询出所有设备，为出库做准备
     * @return
     */
    @Override
    public List<Facility> info() {
        FacilityExample example = new FacilityExample();

        return facilityMapper.selectByExample(example);
    }

    /**
     * 分页查询设备
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageInfo<List<Facility>> findAll(Integer page, Integer size) {
        PageHelper.startPage(page, size);
        FacilityExample example = new FacilityExample();
        List<Facility> facilityList = facilityMapper.selectByExample(example);
        PageInfo pageInfo = new PageInfo(facilityList);
        return pageInfo;
    }



    /**
     * 根据设备Id查询设备信息
     * @param id
     * @return
     */
    @Override
    public Facility findById(Integer id) {
        return facilityMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据设备id出库设备
     * @param facility
     * @return
     */
    @Transactional
    @Override
    public int updateById(Facility facility) {
        int num = 0;
        Delivery delivery = new Delivery();
        delivery.setDtid(facility.getFtid());
        delivery.setDname(facility.getFname());
        delivery.setDnum(facility.getFnum());
        delivery.setDtype(facility.getFtype());
        delivery.setDfctory(facility.getFfctory());
        delivery.setDtrange(facility.getFtrange());
        //当前时间
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyy-MM-dd");
        String time = simpleDateFormat.format(date);
        delivery.setDtime(time);
        System.out.println(delivery);
        //将出库的设备添加到出库表里面
        deliveryMapper.insertSelective(delivery);

        //根据id先把这个设备查出来
        Facility f1 = facilityMapper.selectByPrimaryKey(facility.getId());
        //根据传进来的设备数量和原来的数量相减，得出更新后的数量
        //如果设备数量为0就将她删除
       if (f1.getFnum() - facility.getFnum() != 0){
           facility.setFnum(f1.getFnum() - facility.getFnum());
            num = facilityMapper.updateByPrimaryKey(facility);
       }else
       {
           facility.setFnum(f1.getFnum() - facility.getFnum());
            num = facilityMapper.updateByPrimaryKey(facility);
            //如果商品数量为0，就直接把这条设备信息删除
            facilityMapper.deleteByPrimaryKey(facility.getId());
       }

        return num;
    }

    /**
     * 出库多条件查询
     * @param adminVo
     * @return
     */
    @Override
    public PageInfo<List<Facility>> findByAdminVo(AdminVo adminVo, Integer size, Integer page) {
        PageHelper.startPage(page, size);
        List<Facility> list = facilityMapper.findByAdminVo(adminVo);
        PageInfo<List<Facility>> pageInfo = new PageInfo(list);
        return pageInfo;
    }

    /**
     * 查看教室需要安装的设备
     * @param page
     * @return
     */
    @Override
    public PageInfo<List<Addfacility>> findAddFacility(Integer page) {
        PageHelper.startPage(page, 5);
        //封装条件
        AddfacilityExample example = new AddfacilityExample();
        example.createCriteria().andPlanEqualTo("1");
        List<Addfacility> addfacilityList = addfacilityMapper.selectByExample(example);
       //分页
        for (int i = 0; i < addfacilityList.size(); i++) {
            if (addfacilityList.get(i).getPlan().equals("1")){
                addfacilityList.get(i).setPlan("审核中");
            }
            if (addfacilityList.get(i).getPlan().equals("2")){
                addfacilityList.get(i).setPlan("已安装");
            }
        }
        PageInfo<List<Addfacility>> info = new PageInfo(addfacilityList);
        return info;
    }

    /**
     * 查询仓库是否有该设备
     * @param id
     * @return
     */
    @Override
    public PageInfo<List<Facility>> findByName(Integer page, Integer id) {
        PageHelper.startPage(page, 5);
        //查询出需要安装的设备
        Addfacility addfacility = addfacilityMapper.selectByPrimaryKey(id);
        //封装条件，进行模糊查找仓库是否有该物品
        FacilityExample example = new FacilityExample();
        example.createCriteria().andFnameLike(addfacility.getFname());
        List<Facility> facilityList = facilityMapper.selectByExample(example);
        PageInfo<List<Facility>> info = new PageInfo(facilityList);
        return info;
    }

    /**
     * 安装设备，库存减1，如果这是最后一件，就删除该商品，并修改申请安装商品的状态
     * @param id
     * @return
     */
    @Override
    @Transactional
    public int updateFacilityById(Integer id, Integer n, String location) {
        int num = 0;
        //需要安装这个设备的信息
        Facility facility = facilityMapper.selectByPrimaryKey(id);
        //将出库的这个商品添加到出库表中
        Delivery delivery = new Delivery();
        delivery.setDtid(facility.getFtid());
        delivery.setDname(facility.getFname());
        delivery.setDnum(1);
        delivery.setDtype(facility.getFtype());
        delivery.setDfctory(facility.getFfctory());
        delivery.setDtrange(facility.getFtrange());
        delivery.setDcollege(Integer.valueOf(location));
        //当前时间
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyy-MM-dd");
        String time = simpleDateFormat.format(date);
        delivery.setDtime(time);
        System.out.println(delivery);
        //将出库的设备添加到出库表里面
        deliveryMapper.insertSelective(delivery);

        //根据传进来的设备数量和原来的数量相减，得出更新后的数量
        //如果设备数量为0就将她删除
        if (facility.getFnum() - 1!= 0){
            facility.setFnum(facility.getFnum() - 1);
            num = facilityMapper.updateByPrimaryKey(facility);
        }else
        {
            facility.setFnum(facility.getFnum() - 1);
            num = facilityMapper.updateByPrimaryKey(facility);
            //如果商品数量为0，就直接把这条设备信息删除
            facilityMapper.deleteByPrimaryKey(facility.getId());
        }

        //将老师申请的需要安装的状态修改为已安装
        Addfacility addfacility = new Addfacility();
        addfacility.setId(n);
        addfacility.setPlan("2");
        int update = addfacilityMapper.updateByPrimaryKeySelective(addfacility);

        return update;
    }

    /**
     * 查看所有已安装的设备
     * @return
     */
    @Override
    public PageInfo<List<Addfacility>> findAddfacilityByPlan(Integer page) {
        PageHelper.startPage(page, 5);
        AddfacilityExample example = new AddfacilityExample();
        example.createCriteria().andPlanEqualTo("2");
        List<Addfacility> addfacilityList = addfacilityMapper.selectByExample(example);
        for (int i = 0; i < addfacilityList.size(); i++) {
            if (addfacilityList.get(i).getPlan().equals("1")){
                addfacilityList.get(i).setPlan("审核中");
            }
            if (addfacilityList.get(i).getPlan().equals("2")){
                addfacilityList.get(i).setPlan("已安装");
            }
        }
        PageInfo<List<Addfacility>> info = new PageInfo(addfacilityList);
        return info;
    }

    /**
     * 查看未维修设备的信息
     * @param page
     * @return
     */
    @Override
    public PageInfo<List<Repairs>> findServiceByplan(Integer page) {
        PageHelper.startPage(page, 5);
        //封装条件
        RepairsExample example = new RepairsExample();
        example.createCriteria().andPlanEqualTo("1");
        List<Repairs> repairsList = repairsMapper.selectByExample(example);
        PageInfo<List<Repairs>> info = new PageInfo(repairsList);

        for (int i = 0; i < repairsList.size(); i++) {
            if (repairsList.get(i).getPlan().equals("1")){
                repairsList.get(i).setPlan("处理中");
            }
            if (repairsList.get(i).getPlan().equals("2")){
                repairsList.get(i).setPlan("已处理");
            }
        }
        return info;
    }

    /**
     * 查看已修设备记录
     * @param page
     * @return
     */
    @Override
    public PageInfo<List<Repairs>> findServiceByYiXiuplan(Integer page) {
        PageHelper.startPage(page, 5);
        //封装条件
        RepairsExample example = new RepairsExample();
        example.createCriteria().andPlanEqualTo("2");
        List<Repairs> repairsList = repairsMapper.selectByExample(example);
        PageInfo<List<Repairs>> info = new PageInfo(repairsList);

        for (int i = 0; i < repairsList.size(); i++) {
            if (repairsList.get(i).getPlan().equals("1")){
                repairsList.get(i).setPlan("处理中");
            }
            if (repairsList.get(i).getPlan().equals("2")){
                repairsList.get(i).setPlan("已处理");
            }
        }
        return info;
    }

    /**
     * ajax判断维修工用户名是否重复
     * @param uname
     * @return
     */
    @Override
    public User findAjaxUname(String uname) {
        UserExample example = new UserExample();
        example.createCriteria().andUnameEqualTo(uname);
        List<User> users = userMapper.selectByExample(example);
        return users.get(0);
    }

    /**
     * 添加维修工账号
     * @param user
     * @return
     */
    @Override
    public int addWorker(User user) {
        user.setRols(2);
        int num = userMapper.insertSelective(user);

        return num;
    }

    //查看所有维修工的信息
    @Override
    public PageInfo<List<User>> findWorker(Integer page) {
        PageHelper.startPage(page, 5);
        UserExample example = new UserExample();
        example.createCriteria().andRolsEqualTo(2);
        List<User> users = userMapper.selectByExample(example);
        PageInfo<List<User>> info = new PageInfo(users);
        return info;
    }

    /**
     * 查看所有老师的信息
     * @param page
     * @return
     */
    @Override
    public PageInfo<List<User>> findTeacher(Integer page) {
        PageHelper.startPage(page, 5);
        UserExample example = new UserExample();
        example.createCriteria().andRolsEqualTo(3);
        List<User> users = userMapper.selectByExample(example);
        PageInfo<List<User>> info = new PageInfo(users);
        return info;
    }

    /**
     * 查看所有学生的信息
     * @param page
     * @return
     */
    @Override
    public PageInfo<List<User>> findStudent(Integer page) {
        PageHelper.startPage(page, 5);
        UserExample example = new UserExample();
        example.createCriteria().andRolsEqualTo(4);
        List<User> users = userMapper.selectByExample(example);
        PageInfo<List<User>> info = new PageInfo(users);
        return info;
    }

    /**
     * 需要采购的设备
     * @param page
     * @return
     */
    @Override
    public PageInfo<List<Addfacility>> caigou(Integer page) {
        PageHelper.startPage(page, 5);
        AddfacilityExample example = new AddfacilityExample();
        example.createCriteria().andPlanEqualTo("1");
        //查询出学院没有安装的设备
        List<Addfacility> addfacilityList = addfacilityMapper.selectByExample(example);
        //用来存储库里面没有的商品，需要采购的商品
        List<Addfacility> addfacilities = new ArrayList<>();
        for (Addfacility a: addfacilityList) {
            //用来封装条件
            FacilityExample facilityExample = new FacilityExample();
            //查询出还没有安装设备，将商品名字封装进去
            facilityExample.createCriteria().andFnameLike(a.getFname());
            //开始进行模糊查询
            List<Facility> list = facilityMapper.selectByExample(facilityExample);
            //如果没有查询出，就表示这个商品库里面没有，就需要采购
            if (list.size()<=0){
                addfacilities.add(a);
            }
        }
        return new PageInfo(addfacilities);

    }
}
