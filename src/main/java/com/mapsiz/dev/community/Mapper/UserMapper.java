package com.mapsiz.dev.community.Mapper;

import com.mapsiz.dev.community.Model.User;
import org.apache.ibatis.annotations.*;

/**
 * @Author: DamageeZ
 * @Create: 06-01-2021 19:51
 */
@Mapper
public interface UserMapper {
    @Insert("INSERT into user (name,accountId,token,gmtCreate,gmtModified,avatarUrl) values (#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified},#{avatarUrl});")
    void insert(User user);

    @Select("SELECT * FROM user WHERE token = #{token}")
    User findByToken(@Param("token")String token);

    @Select("SELECT * FROM user WHERE accountId = #{accountId}")
    User findByAccountId(@Param("accountId")String accountId);

    @Update("UPDATE user SET token = #{token},gmtModified = #{gmtModified} WHERE accountId = #{accountId}")
    void updateTokenById(@Param("accountId") String accountId,@Param("token") String token,@Param("gmtModified") Long gmtModified);
}
