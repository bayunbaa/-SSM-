package com.xh.mapper.teacher;

import com.xh.entity.teacher.Addfacility;
import com.xh.entity.teacher.AddfacilityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AddfacilityMapper {
    int countByExample(AddfacilityExample example);

    int deleteByExample(AddfacilityExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Addfacility record);

    int insertSelective(Addfacility record);

    List<Addfacility> selectByExample(AddfacilityExample example);

    Addfacility selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Addfacility record, @Param("example") AddfacilityExample example);

    int updateByExample(@Param("record") Addfacility record, @Param("example") AddfacilityExample example);

    int updateByPrimaryKeySelective(Addfacility record);

    int updateByPrimaryKey(Addfacility record);
}