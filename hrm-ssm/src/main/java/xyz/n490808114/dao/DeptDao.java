package xyz.n490808114.dao;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import xyz.n490808114.domain.Dept;

import java.util.List;

public interface DeptDao {

    @Select("SELECT * FROM dept_inf")
    @Results({
            @Result(id = true,column = "id" ,property = "id"),
            @Result(column = "name",property = "name"),
            @Result(column = "remark",property = "remark"),
            @Result(column = "id",property = "employees",
                many = @Many(select = "xyz.n490808114.dao.EmployeeDao.selectEmployeesByDeptId",
                            fetchType = FetchType.LAZY))
    })
    List<Dept> selectAll();

    @Select("SELECT * FROM dept_inf WHERE id = #{id}")
    @Results({
            @Result(id = true,column = "id" ,property = "id"),
            @Result(column = "name",property = "name"),
            @Result(column = "remark",property = "remark"),
            @Result(column = "id",property = "employees",
                    many = @Many(select = "xyz.n490808114.dao.EmployeeDao.selectEmployeesByDeptId",
                            fetchType = FetchType.LAZY))
    })
    Dept selectById(int id);

    @Delete("DELETE * FROM dept_inf WHERE id = #{id}")
    void deleteById(int id);

    @Insert("INSERT INTO dept_inf(id,name,remark) VALUES(#{id},#{name},#{remark}) ")
    void save(Dept dept);

    @Update("UPDATE dept_inf SET name = #{name},remark = #{remark} WHERE id = #{id}")
    void modify(Dept dept);


}
