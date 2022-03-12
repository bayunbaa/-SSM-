package com.xh.service.adminService.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xh.entity.Delivery;
import com.xh.entity.Facility;
import com.xh.entity.FacilityExample;
import com.xh.mapper.admin.DeliveryMapper;
import com.xh.mapper.admin.FacilityMapper;
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
}
