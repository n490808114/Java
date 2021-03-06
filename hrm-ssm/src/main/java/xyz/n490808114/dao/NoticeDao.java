package xyz.n490808114.dao;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import xyz.n490808114.domain.Notice;

import java.util.List;

public interface NoticeDao {

    @Select("SELECT * FROM notice_inf")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "title",property = "title"),
            @Result(column = "content",property = "content"),
            @Result(column = "create_date",property = "createDate"),
            @Result(column = "user_id",property = "user",
                    one = @One(select = "xyz.n490808114.dao.UserDao.selectById",
                    fetchType = FetchType.EAGER))
    })
    List<Notice> selectAll();

    @Select("SELECT * FROM notice_inf WHERE id = #{id}")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "title",property = "title"),
            @Result(column = "content",property = "content"),
            @Result(column = "create_date",property = "createDate"),
            @Result(column = "user_id",property = "user",
                    one = @One(select = "xyz.n490808114.dao.UserDao.selectById",
                            fetchType = FetchType.EAGER))
    })
    Notice selectById(int id);

    @Delete("DELETE * FROM notice_inf WHERE id = #{id}")
    void deleteById(int id);

    @Insert("INSERT INTO notice_inf(id,title,content,create_date,user_id) " +
                            "VALUES(#{id},#{title},#{content},#{createDate},#{user.id})")
    void save(Notice notice);

    @Update("UPDATE notice_inf SET title = #{title}," +
                                    "content = #{content}," +
                                    "create_date = #{createDate}," +
                                    "user_id = #{user.id} " +
                                    "WHERE id = #{id}")
    void modify(Notice notice);
}
