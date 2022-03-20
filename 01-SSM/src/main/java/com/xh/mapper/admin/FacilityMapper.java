package com.xh.mapper.admin;

import com.xh.entity.Facility;
import com.xh.entity.FacilityExample;
import java.util.List;

import com.xh.entity.admin.AdminVo;
import org.apache.ibatis.annotations.Param;

public interface FacilityMapper {
    int countByExample(FacilityExample example);

    int deleteByExample(FacilityExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Facility record);

    int insertSelective(Facility record);

    List<Facility> selectByExample(FacilityExample example);

    Facility selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Facility record, @Param("example") FacilityExample example);

    int updateByExample(@Param("record") Facility record, @Param("example") FacilityExample example);

    int updateByPrimaryKeySelective(Facility record);

    int updateByPrimaryKey(Facility record);

    /**
     * 多条件查询出库商品
     * @param adminVo
     * @return
     */
    List<Facility> findByAdminVo(AdminVo adminVo);
}