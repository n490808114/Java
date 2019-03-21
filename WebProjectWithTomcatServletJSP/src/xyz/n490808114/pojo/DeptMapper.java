package xyz.n490808114.pojo;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface DeptMapper {
    @Select("SELECT * FROM dept_inf WHERE id = #{id}")
    Dept selectDeptById(@Param("id") int id);
}
