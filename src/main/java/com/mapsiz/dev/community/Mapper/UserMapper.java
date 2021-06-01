package com.mapsiz.dev.community.Mapper;

import com.mapsiz.dev.community.Model.User;
import org.apache.ibatis.annotations.*;

/**
 * @Author: DamageeZ
 * @Create: 06-01-2021 19:51
 */
@Mapper
public interface UserMapper {
    @Insert("INSERT into user (name,account_id,token,gmt_create,gmt_modified) values (#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified});")
    void insert(User user);

    @Select("SELECT * FROM user WHERE token = #{token}")
    User findByToken(@Param("token")String token);
}
