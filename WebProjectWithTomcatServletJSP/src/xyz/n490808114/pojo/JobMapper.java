package xyz.n490808114.pojo;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface JobMapper {
    @Select("SELECT * FROM job_inf WHERE id = #{id}")
    Job selectJobById(@Param("id") int id);
}
