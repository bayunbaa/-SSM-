package com.xh.service.loginservice;

import com.xh.entity.Inform;

public interface InformService {
    //查询第一条公告
    Inform findInform();
    //编辑公告
    int editInform(Inform inform, Integer uId, Integer iId);
}
