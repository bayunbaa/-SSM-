package com.xh.service.workerService.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xh.entity.student.Repairs;
import com.xh.entity.student.RepairsExample;
import com.xh.entity.teacher.Addfacility;
import com.xh.entity.teacher.AddfacilityExample;
import com.xh.mapper.student.RepairsMapper;
import com.xh.mapper.teacher.AddfacilityMapper;
import com.xh.service.workerService.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @Author: xiaohao
 * @Time: 2022/3/27 13:56
 */
@Service
public class WorkerServiceImpl  implements WorkerService {
    //注入
    @Autowired
    private RepairsMapper repairsMapper;
    //注入,操作安装设备表，
    @Autowired
    private AddfacilityMapper addfacilityMapper;
    /**
     * 查询出所有设备报修表
     * @return
     */
    @Override
    public PageInfo<List<Repairs>> findAll(Integer page) {
        //分页
        PageHelper.startPage(page, 10);
        RepairsExample example = new RepairsExample();
        //查找未处理的
        example.createCriteria().andPlanEqualTo("2");
        List<Repairs> repairsList = repairsMapper.selectByExample(example);

        for (int i = 0; i < repairsList.size(); i++) {
            if (repairsList.get(i).getPlan().equals("2")){
                repairsList.get(i).setPlan("未处理");
            }
        }

        PageInfo<List<Repairs>> pageInfo = new PageInfo(repairsList);

        return pageInfo;
    }

    @Override
    public int updateById(Integer id) {
        Repairs repairs = new Repairs();
        repairs.setId(id);
        repairs.setPlan("3");
        int num = repairsMapper.updateByPrimaryKeySelective(repairs);
        return num;
    }

    /**
     * 查询已解决的问题
     * @param page
     * @return
     */
    @Override
    public PageInfo<List<Repairs>> selectByPlan(Integer page) {
        //分页
        PageHelper.startPage(page, 10);
        RepairsExample example = new RepairsExample();
        //查找未处理的
        example.createCriteria().andPlanEqualTo("3");
        List<Repairs> repairsList = repairsMapper.selectByExample(example);
        for (int i = 0; i < repairsList.size(); i++) {
            if (repairsList.get(i).getPlan().equals("3")){
                repairsList.get(i).setPlan("已维修");
            }
        }

        PageInfo<List<Repairs>> pageInfo = new PageInfo(repairsList);

        return pageInfo;
    }

    /**
     * 查询出管理员已经审核通过的设备，维修人员去安装
     * @param page
     * @return
     */
    @Override
    public PageInfo<List<Addfacility>> findAddFacilityByPlan(Integer page) {
        PageHelper.startPage(page, 10);
        AddfacilityExample example = new AddfacilityExample();
        example.createCriteria().andPlanEqualTo("2");
        List<Addfacility> addfacilityList = addfacilityMapper.selectByExample(example);
        return new PageInfo(addfacilityList);
    }

    /**
     * 根据管理员审核通过的需要安装设备，去安装
     * @param id
     * @return
     */
    @Override
    public int updateAddFacilityByPlan(Integer id) {
        //根据要安装设备的这条id，先查询出这条记录,然后修改状态，修改其安装状态
        Addfacility facility = addfacilityMapper.selectByPrimaryKey(id);
        facility.setPlan("3");
        int num = addfacilityMapper.updateByPrimaryKeySelective(facility);
        return num;
    }

    @Override
    public PageInfo<List<Addfacility>> findByUid(Integer page, Integer uid) {
        // 分页
        PageHelper.startPage(page, 10);
        AddfacilityExample example = new AddfacilityExample();
        // 将当前用户id封装里面
//        example.createCriteria().andUidEqualTo(String.valueOf(uid));
//        List<Addfacility> repairsList = repairsMapper.selectAllDeleteFacility(uid);
        example.createCriteria().andPlanEqualTo("2");
        List<Addfacility> repairsList =  addfacilityMapper.selectDeleteByExample(example);

        PageInfo<List<Addfacility>> pageInfo = new PageInfo(repairsList);
        return pageInfo;
    }

    @Override
    public void deleteById(Integer id) {
        String plan="3";
        addfacilityMapper.deleteFacility(id,plan);
    }


    @Override
    public PageInfo<List<Addfacility>> findDelete(Integer page, Integer uid) {
        // 分页
        PageHelper.startPage(page, 10);
        AddfacilityExample example = new AddfacilityExample();
        // 将当前用户id封装里面
        example.createCriteria().andUidEqualTo(String.valueOf(uid));
        List<Addfacility> repairsList =repairsMapper.selectAllDeleteFacility(uid);
        for (int i = 0; i < repairsList.size(); i++) {
            Addfacility repair = repairsList.get(i);
            if ("1".equals(repair.getPlan())) {
                repair.setPlan("申请中");
            } else if ("2".equals(repair.getPlan())) {
                repair.setPlan("申请通过");
            }else if ("3".equals(repair.getPlan())) {
                repair.setPlan("已报废");
            }
        }
        PageInfo<List<Addfacility>> pageInfo = new PageInfo(repairsList);
        return pageInfo;
    }
}
