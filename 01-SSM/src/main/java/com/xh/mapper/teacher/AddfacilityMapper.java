package com.xh.mapper.teacher;

import com.xh.entity.teacher.Addfacility;
import com.xh.entity.teacher.AddfacilityExample;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface AddfacilityMapper {
    int countByExample(AddfacilityExample example);

    int deleteByExample(AddfacilityExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Addfacility record);

    int insertSelective(Addfacility record);

    List<Addfacility> selectByExample(AddfacilityExample example);
    List<Addfacility> selectDeleteByExample(AddfacilityExample example);


    Addfacility selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Addfacility record, @Param("example") AddfacilityExample example);

    int updateByExample(@Param("record") Addfacility record, @Param("example") AddfacilityExample example);

    int updateByPrimaryKeySelective(Addfacility record);

    int updateByPrimaryKey(Addfacility record);

    @Update("UPDATE addfacility SET plan = #{plan} WHERE id = #{id}")
    void deleteById(@Param("id") Integer id, @Param("plan") String plan);
    @Update("UPDATE deletefacility SET plan = #{plan} WHERE id = #{id}")
    void deleteFacility(@Param("id") Integer id, @Param("plan") String plan);

    @Update("UPDATE repairs SET plan = #{plan} WHERE id = #{id}")
    void repairFacility(@Param("id") Integer id, @Param("plan") String plan);

    @Insert(" insert into deletefacility (id, uid, fname, \n" +
            "      location, createTime, plan,\n" +
            "      test)\n" +
            "    values (#{id,jdbcType=INTEGER}, #{uid,jdbcType=VARCHAR}, #{fname,jdbcType=VARCHAR}, \n" +
            "      #{location,jdbcType=VARCHAR}, #{createtime,jdbcType=VARCHAR}, #{plan,jdbcType=VARCHAR}, \n" +
            "      #{test,jdbcType=VARCHAR})")
    int insertDelete(Addfacility addfacility);

    @Delete("DELETE FROM addfacility WHERE id = #{id}")
    void deleteAddFacility(Integer id);

    List<Addfacility> selectAllDeleteFacility(Integer uid);


}