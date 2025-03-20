package com.xh.service.teacherService.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xh.entity.teacher.Addfacility;
import com.xh.entity.teacher.AddfacilityExample;
import com.xh.mapper.teacher.AddfacilityMapper;
import com.xh.service.teacherService.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Description:  操作设备表的
 * @Author: xiaohao
 * @Time: 2022/3/26 21:22
 */
@Service
public class TeacherServiceImpl implements TeacherService {
    //添加设备表
    @Autowired
    private AddfacilityMapper addfacilityMapper;

    /**
     * 老师申请添加设备
     * @param addfacility
     * @return
     */
    @Override
    public int insert(Addfacility addfacility) {
        //格式化日期
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format1 = format.format(date);
        addfacility.setCreatetime(format1);
        //设备维修的进度
        addfacility.setPlan("1");
        int num = addfacilityMapper.insertSelective(addfacility);
        return num;
    }


    @Override
    public int insertDeleteFacility(Addfacility addfacility) {
        //格式化日期
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format1 = format.format(date);
        addfacility.setCreatetime(format1);
        //设备维修的进度
        addfacility.setPlan("1");
        int num = addfacilityMapper.insertDelete(addfacility);
        return num;
    }

    /**
     * 查看老师申请设备的进度
     *
     * @param page
     * @param uid
     * @return
     */
    public PageInfo<Addfacility> findByUid(Integer page, Integer uid) {
        PageHelper.startPage(page, 10);
        // 将当前登录老师的id封装进去
        AddfacilityExample example = new AddfacilityExample();
//        example.createCriteria().andUidEqualTo(uid + "");
        List<Addfacility> addfacilityList = addfacilityMapper.selectByExample(example);
        // 给查出来的报修记录信息设置编号，并对新创建的报修记录显示处理中
        setRepairIdsAndUpdatePlanStatus(addfacilityList);
        // 使用 PageHelper 分页后的结果来构造 PageInfo
        PageInfo<Addfacility> pageInfo = new PageInfo<>(addfacilityList);
        return pageInfo;
    }

    private void setRepairIdsAndUpdatePlanStatus(List<Addfacility> addfacilityList) {
        for (int i = 0; i < addfacilityList.size(); i++) {
            Addfacility repair = addfacilityList.get(i);
            repair.setId(i);
            // 更新处理状态
            switch (repair.getPlan()) {
                case "1":
                    repair.setPlan("处理中");
                    break;
                case "2":
                    repair.setPlan("已处理");
                    break;
                case "3":
                    repair.setPlan("已安装");
                    break;
            }
        }
    }


    @Override
    public void deleteById(Integer id) {
        String plan="1";
        //改变plan的值
        addfacilityMapper.deleteById(id,plan);
        Addfacility addfacility = addfacilityMapper.selectByPrimaryKey(id);
        addfacilityMapper.insertDelete(addfacility);
        addfacilityMapper.deleteAddFacility(id);

    }


}
