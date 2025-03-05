package com.xh.mapper.student;

import com.xh.entity.student.Repairs;
import com.xh.entity.student.RepairsExample;
import com.xh.entity.teacher.Addfacility;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RepairsMapper {
    int countByExample(RepairsExample example);

    int deleteByExample(RepairsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Repairs record);

    int insertSelective(Repairs record);


    List<Repairs> selectByExample(RepairsExample example);
    List<Repairs> selectByExample2(RepairsExample example);
    List<Addfacility> selectDeleteFacility(Integer uid);
    List<Addfacility> selectAllDeleteFacility(Integer uid);
    Repairs selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Repairs record, @Param("example") RepairsExample example);

    int updateByExample(@Param("record") Repairs record, @Param("example") RepairsExample example);

    int updateByPrimaryKeySelective(Repairs record);

    int updateByPrimaryKey(Repairs record);



}