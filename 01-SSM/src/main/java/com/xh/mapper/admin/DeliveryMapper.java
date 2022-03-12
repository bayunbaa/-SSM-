package com.xh.mapper.admin;

import com.xh.entity.Delivery;
import com.xh.entity.DeliveryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DeliveryMapper {
    int countByExample(DeliveryExample example);

    int deleteByExample(DeliveryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Delivery record);

    int insertSelective(Delivery record);

    List<Delivery> selectByExample(DeliveryExample example);

    Delivery selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Delivery record, @Param("example") DeliveryExample example);

    int updateByExample(@Param("record") Delivery record, @Param("example") DeliveryExample example);

    int updateByPrimaryKeySelective(Delivery record);

    int updateByPrimaryKey(Delivery record);
}