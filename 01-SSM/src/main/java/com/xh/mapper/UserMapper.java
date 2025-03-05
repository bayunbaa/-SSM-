package com.xh.mapper;

import com.xh.entity.User;
import com.xh.entity.UserExample;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {
    /**
     * 查询是否有当前用户
     * @param user
     * @return
     */
    List<User> selectByNameAndPassowrdAndRols(User user);


    int countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(Integer uid);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(Integer uid);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);



@Delete("delete from user where uid = #{uid}")
    void delUser(Long uid);


    @Select("select * from user where uname = #{uname}")
    List<User> findUserByName(String uname);
}