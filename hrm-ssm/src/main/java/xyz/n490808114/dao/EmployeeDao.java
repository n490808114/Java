package xyz.n490808114.dao;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import xyz.n490808114.domain.Employee;

import java.util.List;
import java.util.Map;

public interface EmployeeDao {
    @Insert("INSERT INTO employee_inf(id,dept_id,job_id,name,card_id,address,post_code,tel,phone,qq_num,email," +
            "sex,party,birthday,race,education,speciality,hobby,remark,create_date) " +
            "VALUES(#{id},#{dept.id},#{job.id},#{name},#{cardId},#{address},#{postCard},#{tel},#{phone},#{qqNum}," +
            "#{email},#{sex},#{party},#{birthday},#{race},#{education},#{speciality},#{hobby},#{remark},#{createDate})")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    int save(Employee employee);

    @Delete("DELETE * FROM employee_inf WHERE id =#{id}")
    int deleteById(@Param("id") int id);

    @Update("UPDATE employee_inf SET dept_id = #{dept.id}," +
            "job_id = #{job.id}," +
            "name = #{name}," +
            "card_id = #{cardId}," +
            "address = #{address}," +
            "post_code = #{postCard}," +
            "tel = #{tel}," +
            "phone = #{phone}," +
            "qq_num = #{qqNum}," +
            "email = #{email}," +
            "sex = #{sex}," +
            "party = #{party}," +
            "birthday = #{birthday}," +
            "race = #{race}," +
            "education = #{education}," +
            "speciality = #{speciality}," +
            "hobby = #{hobby}," +
            "remark = #{remark}," +
            "create_date = #{createDate} " +
            "WHERE id = #{id}")
    void modify(Employee employee);

    @Select("SELECT * FROM employee_inf WHERE id = #{id}")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "name",property = "name"),
            @Result(column = "card_id",property = "cardId"),
            @Result(column = "address",property = "address"),
            @Result(column = "post_code",property = "postCode"),
            @Result(column = "tel",property = "tel"),
            @Result(column = "phone",property = "phone"),
            @Result(column = "qq_num",property = "qqNum"),
            @Result(column = "email",property = "email"),
            @Result(column = "sex",property = "sex"),
            @Result(column = "party",property = "party"),
            @Result(column = "birthday",property = "birthday"),
            @Result(column = "race",property = "race"),
            @Result(column = "education",property = "education"),
            @Result(column = "speciality",property = "speciality"),
            @Result(column = "hobby",property = "hobby"),
            @Result(column = "remark",property = "remark"),
            @Result(column = "create_date",property = "createDate"),
            @Result(column = "dept_id",property = "dept",
                    one = @One(select = "xyz.n490808114.dao.DeptDao.selectDeptById",
                            fetchType = FetchType.EAGER)),
            @Result(column = "job_id",property = "job",
                    one = @One(select = "xyz.n490808114.dao.JobDao.selectJobById",
                            fetchType = FetchType.EAGER))
    })
    Employee selectById(int id);

    @Select("SELECT * FROM employee_inf")
    List<Employee> selectAll();

    @Select("SELECT * FROM employee_inf WHERE dept_id = #{id}")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "name",property = "name"),
            @Result(column = "card_id",property = "cardId"),
            @Result(column = "address",property = "address"),
            @Result(column = "post_code",property = "postCode"),
            @Result(column = "tel",property = "tel"),
            @Result(column = "phone",property = "phone"),
            @Result(column = "qq_num",property = "qqNum"),
            @Result(column = "email",property = "email"),
            @Result(column = "sex",property = "sex"),
            @Result(column = "party",property = "party"),
            @Result(column = "birthday",property = "birthday"),
            @Result(column = "race",property = "race"),
            @Result(column = "education",property = "education"),
            @Result(column = "speciality",property = "speciality"),
            @Result(column = "hobby",property = "hobby"),
            @Result(column = "remark",property = "remark"),
            @Result(column = "create_date",property = "createDate"),
            @Result(column = "dept_id",property = "dept",
                    one = @One(select = "xyz.n490808114.dao.DeptDao.selectDeptById",
                            fetchType = FetchType.LAZY)),
            @Result(column = "job_id",property = "job",
                    one = @One(select = "xyz.n490808114.dao.JobDao.selectJobById",
                            fetchType = FetchType.LAZY))
    })
    List<Employee> selectEmployeesByDeptId(int id);

    @Select("SELECT * FROM employee_inf WHERE job_id = #{id}")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "name",property = "name"),
            @Result(column = "card_id",property = "cardId"),
            @Result(column = "address",property = "address"),
            @Result(column = "post_code",property = "postCode"),
            @Result(column = "tel",property = "tel"),
            @Result(column = "phone",property = "phone"),
            @Result(column = "qq_num",property = "qqNum"),
            @Result(column = "email",property = "email"),
            @Result(column = "sex",property = "sex"),
            @Result(column = "party",property = "party"),
            @Result(column = "birthday",property = "birthday"),
            @Result(column = "race",property = "race"),
            @Result(column = "education",property = "education"),
            @Result(column = "speciality",property = "speciality"),
            @Result(column = "hobby",property = "hobby"),
            @Result(column = "remark",property = "remark"),
            @Result(column = "create_date",property = "createDate"),
            @Result(column = "dept_id",property = "dept",
                    one = @One(select = "xyz.n490808114.dao.DeptDao.selectDeptById",
                            fetchType = FetchType.LAZY)),
            @Result(column = "job_id",property = "job",
                    one = @One(select = "xyz.n490808114.dao.JobDao.selectJobById",
                            fetchType = FetchType.LAZY))
    })
    List<Employee> selectEmployeesByJobId(int id);

    @SelectProvider(type = EmployeeDynaSqlProvider.class,method = "selectWithParam")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "name",property = "name"),
            @Result(column = "card_id",property = "cardId"),
            @Result(column = "address",property = "address"),
            @Result(column = "post_code",property = "postCode"),
            @Result(column = "tel",property = "tel"),
            @Result(column = "phone",property = "phone"),
            @Result(column = "qq_num",property = "qqNum"),
            @Result(column = "email",property = "email"),
            @Result(column = "sex",property = "sex"),
            @Result(column = "party",property = "party"),
            @Result(column = "birthday",property = "birthday"),
            @Result(column = "race",property = "race"),
            @Result(column = "education",property = "education"),
            @Result(column = "speciality",property = "speciality"),
            @Result(column = "hobby",property = "hobby"),
            @Result(column = "remark",property = "remark"),
            @Result(column = "create_date",property = "createDate"),
            @Result(column = "dept_id",property = "dept",
                    one = @One(select = "xyz.n490808114.dao.DeptDao.selectDeptById",
                            fetchType = FetchType.LAZY)),
            @Result(column = "job_id",property = "job",
                    one = @One(select = "xyz.n490808114.dao.JobDao.selectJobById",
                            fetchType = FetchType.LAZY))
    })
    List<Employee> selectEmployeesWithParam(Map<String,Object> param);
}
