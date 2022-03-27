package com.xh.service.workerService.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xh.entity.student.Repairs;
import com.xh.entity.student.RepairsExample;
import com.xh.mapper.student.RepairsMapper;
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
    /**
     * 查询出所有设备报修表
     * @return
     */
    @Override
    public PageInfo<List<Repairs>> findAll(Integer page) {
        //分页
        PageHelper.startPage(page, 5);
        RepairsExample example = new RepairsExample();
        //查找未处理的
        example.createCriteria().andPlanEqualTo("1");
        List<Repairs> repairsList = repairsMapper.selectByExample(example);

        for (int i = 0; i < repairsList.size(); i++) {
            if (repairsList.get(i).getPlan().equals("1")){
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
        repairs.setPlan("2");
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
        PageHelper.startPage(page, 5);
        RepairsExample example = new RepairsExample();
        //查找未处理的
        example.createCriteria().andPlanEqualTo("2");
        List<Repairs> repairsList = repairsMapper.selectByExample(example);

        for (int i = 0; i < repairsList.size(); i++) {
            if (repairsList.get(i).getPlan().equals("2")){
                repairsList.get(i).setPlan("已处理");
            }
        }

        PageInfo<List<Repairs>> pageInfo = new PageInfo(repairsList);

        return pageInfo;
    }
}
