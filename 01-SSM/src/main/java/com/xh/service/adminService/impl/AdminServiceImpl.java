package com.xh.service.adminService.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xh.entity.Delivery;
import com.xh.entity.Facility;
import com.xh.entity.FacilityExample;
import com.xh.entity.admin.AdminVo;
import com.xh.entity.teacher.Addfacility;
import com.xh.entity.teacher.AddfacilityExample;
import com.xh.mapper.admin.DeliveryMapper;
import com.xh.mapper.admin.FacilityMapper;
import com.xh.mapper.teacher.AddfacilityMapper;
import com.xh.service.adminService.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
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
    public int updateFacilityById(Integer id, Integer n) {
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
}
