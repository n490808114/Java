package xyz.n490808114.pojo;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

public interface DeptMapper {
    @Select("SELECT * FROM dept_inf WHERE id = #{id}")
    @Results(value = {
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "name",property = "name"),
            @Result(column = "remark",property = "remark"),
            @Result(column = "id",property = "employees",
                    many=@Many(select = "xyz.n490808114.pojo.EmployeeMapper.selectEmployeesByDeptId",
                    fetchType = FetchType.LAZY
            ))
    })
    Dept selectDeptById( int id);
}
