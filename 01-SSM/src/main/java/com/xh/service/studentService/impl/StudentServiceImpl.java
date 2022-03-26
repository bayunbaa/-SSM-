package com.xh.service.studentService.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xh.entity.User;
import com.xh.entity.student.Repairs;
import com.xh.entity.student.RepairsExample;
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
        repairs.setPlan("1");
        int num = repairsMapper.insertSelective(repairs);

        return num;
    }

    /**
     * 当前用户提交的设备报修信息
     * @param uid
     * @return
     */
    @Override
    public PageInfo<List<Repairs>> findByUid(Integer page, Integer uid) {
        //分页
        PageHelper.startPage(page, 5);
        RepairsExample example = new RepairsExample();
        //将当前用户id封装里面
        example.createCriteria().andUidEqualTo(uid);
        List<Repairs> repairsList = repairsMapper.selectByExample(example);
        //给查出来的报修记录信息设置编号
        for (int i = 0; i < repairsList.size(); i++) {
            repairsList.get(i).setId(i+1);
            if (repairsList.get(i).getPlan().equals("1")){
                repairsList.get(i).setPlan("处理中");
            }
            if (repairsList.get(i).getPlan().equals("2")){
                repairsList.get(i).setPlan("已处理");
            }
        }
        PageInfo<List<Repairs>> pageInfo = new PageInfo(repairsList);

        return pageInfo;
    }


    /**
     * 修改密码
     * @param user
     * @return
     */
    @Override
    public int updateByUid(User user) {
        int num = userMapper.updateByPrimaryKey(user);
        return num;
    }
}
