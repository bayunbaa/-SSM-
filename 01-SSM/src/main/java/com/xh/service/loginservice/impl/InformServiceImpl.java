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
        example.createCriteria().andItypeEqualTo(1);
        return informMapper.selectByExample(example).get(0);
    }

    @Override
    public int editInform(Inform inform, Integer uId, Integer iId) {
        //将上一条记录更新一下为不可读
        //根据编号查出上一条记录
        Inform inform2 = informMapper.selectByPrimaryKey(iId);
        if (inform2 != null){
            Inform inform1 = new Inform();
            inform1.setItype(2);
            inform1.setIid(iId);
            inform1.setUid(inform2.getUid());
            inform1.setIbody(inform2.getIbody());
            informMapper.updateByPrimaryKey(inform1);
        }

        //添加一条公告
        inform.setUid(uId);
        inform.setItype(1);
        int num = informMapper.insertSelective(inform);
//        int num = informMapper.updateByPrimaryKey(inform);
        return num;
    }
}
