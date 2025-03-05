package com.xh.service.studentService.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xh.entity.User;
import com.xh.entity.student.Repairs;
import com.xh.entity.student.RepairsExample;
import com.xh.entity.teacher.Addfacility;
import com.xh.entity.teacher.AddfacilityExample;
import com.xh.mapper.UserMapper;
import com.xh.mapper.student.RepairsMapper;
import com.xh.service.studentService.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @Author: xiaohao
 * @Time: 2022/3/26 14:32
 */
@Service
public class StudentServiceImpl implements StudentService {
    //注入，用来操作设备报修表
    @Autowired
    private RepairsMapper repairsMapper;
    //注入，修改密码
    @Autowired
    private UserMapper userMapper;

    @Override
    public int insert(Repairs repairs) {
        //学生一开始提交的设备报修时间
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(date);
        repairs.setCreatetime(format);
        //提交之后，显示处理中。
        repairs.setPlan("1");
        int num = repairsMapper.insertSelective(repairs);

        return num;
    }


    /**
     * 当前用户提交的设备报修信息
     *
     * @param uid
     * @return
     */
    @Override
    public PageInfo<List<Repairs>> findByUid(Integer page, Integer uid) {
        // 分页
        PageHelper.startPage(page, 10);
        RepairsExample example = new RepairsExample();
        // 将当前用户id封装里面
        example.createCriteria().andUidEqualTo(uid);
        List<Repairs> repairsList = repairsMapper.selectByExample(example);

        // 给查出来的报修记录信息设置编号，并对新创建的报修记录显示处理中
        setRepairIdsAndUpdatePlanStatus(repairsList);

        PageInfo<List<Repairs>> pageInfo = new PageInfo(repairsList);
        return pageInfo;
    }

    private void setRepairIdsAndUpdatePlanStatus(List<Repairs> repairsList) {
        for (int i = 0; i < repairsList.size(); i++) {
            Repairs repair = repairsList.get(i);
            repair.setId(1 + i); // Assuming you need a sequential number for display purposes
            updatePlanStatus(repair);
        }
    }

    private void updatePlanStatus(Repairs repair) {
        if ("1".equals(repair.getPlan())) {
            repair.setPlan("申请中");
        } else if ("2".equals(repair.getPlan())) {
            repair.setPlan("申请通过");
        }else if ("3".equals(repair.getPlan())) {
            repair.setPlan("已维修");
        }
    }


    /**
     * 修改密码
     *
     * @param user
     * @return
     */
    @Override
    public int updateByUid(User user) {
        int num = userMapper.updateByPrimaryKey(user);
        return num;
    }

    //查看报废进度
    @Override
    public PageInfo<List<Addfacility>> findByUid2(Integer page, Integer uid) {
        // 分页
        PageHelper.startPage(page, 10);
        AddfacilityExample example = new AddfacilityExample();
        // 将当前用户id封装里面
        example.createCriteria().andUidEqualTo(String.valueOf(uid));

        List<Addfacility> repairsList = repairsMapper.selectDeleteFacility(uid);

        // 转换状态并过滤
        for (int i = 0; i < repairsList.size(); i++) {
            Addfacility repair = repairsList.get(i);
            repair.setId(i);
            updatePlanStatus(repair);
        }


        PageInfo<List<Addfacility>> pageInfo = new PageInfo(repairsList);
        return pageInfo;
    }

    private void updatePlanStatus(Addfacility repair) {
        switch (repair.getPlan()) {
            case "1":
                repair.setPlan("申请中");
                break;
            case "2":
                repair.setPlan("申请通过");
                break;
            case "3":
                repair.setPlan("已报废");
                break;
        }
    }
}