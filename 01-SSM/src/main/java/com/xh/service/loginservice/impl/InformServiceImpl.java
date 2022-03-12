package com.xh.service.loginservice.impl;

import com.xh.entity.Inform;
import com.xh.entity.InformExample;
import com.xh.mapper.InformMapper;
import com.xh.service.loginservice.InformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InformServiceImpl implements InformService {
    //注入
    @Autowired
    private InformMapper informMapper;
    @Override
    public Inform findInform() {
        InformExample example = new InformExample();
        return informMapper.selectByExample(example).get(0);
    }

    @Override
    public int editInform(Inform inform) {
        //只编译第一条内容
        inform.setIid(1);
        int num = informMapper.updateByPrimaryKey(inform);
        return num;
    }
}
