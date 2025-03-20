package com.xh.mapper.admin;

import com.xh.entity.Facility;
import com.xh.entity.FacilityExample;
import com.xh.entity.admin.AdminVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

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

    @Select("select * from facility where fname=#{fname} ")
   Facility selectByFname(String fname);
// @Select("select * from facility where fname=#{fname} ")
//   Facility selectByFname(String fname);

    @Update("UPDATE facility SET fnum = fnum - 1 WHERE id = ${id} ")
    void updateFacilityById(@Param("id") Integer id);

    void updateFacility(Facility facility);
}