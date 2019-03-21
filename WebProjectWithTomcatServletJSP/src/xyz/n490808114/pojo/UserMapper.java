package xyz.n490808114.pojo;

import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserMapper {
    @Insert("INSERT INTO user_inf(id,username,loginname,password,status,createdate,email) " +
            "VALUES(#{id},#{userName},#{loginName},#{password},#{status},#{createDate},#{email})")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    int saveUser(User user);

    @Delete("DELETE FROM user_inf WHERE id = #{id}")
    int removeUser(@Param("id") int id);

    @Update("UPDATE user_inf SET username = #{userName}," +
                                "loginname = #{loginName}," +
                                "password = #{password}," +
                                "status = #{status}," +
                                "createdate = #{createDate}," +
                                "email = #{email} WHERE id = #{id}")
    void modifyUser(User user);

    @Select("SELECT * FROM user_inf WHERE id = #{id}")
    @Results({
            @Result(id=true,column = "id",property="id"),
            @Result(column = "loginname",property = "loginName"),
            @Result(column = "username",property = "userName"),
            @Result(column = "password",property = "password"),
            @Result(column = "status",property = "status"),
            @Result(column = "createdate",property = "createDate"),
            @Result(column = "email",property = "email")
    })
    User selectUserById(@Param("id") int id);

    @Select("SELECT * FROM user_inf")
    List<User> selectAllUser();
}
