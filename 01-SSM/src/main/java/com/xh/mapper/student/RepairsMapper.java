package com.xh.mapper.student;

import com.xh.entity.student.Repairs;
import com.xh.entity.student.RepairsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RepairsMapper {
    int countByExample(RepairsExample example);

    int deleteByExample(RepairsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Repairs record);

    int insertSelective(Repairs record);

    List<Repairs> selectByExample(RepairsExample example);

    Repairs selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Repairs record, @Param("example") RepairsExample example);

    int updateByExample(@Param("record") Repairs record, @Param("example") RepairsExample example);

    int updateByPrimaryKeySelective(Repairs record);

    int updateByPrimaryKey(Repairs record);
}