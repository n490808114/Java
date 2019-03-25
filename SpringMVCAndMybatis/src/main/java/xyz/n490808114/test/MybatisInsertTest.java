package xyz.n490808114.test;

import org.apache.ibatis.session.SqlSession;
import xyz.n490808114.pojo.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MybatisInsertTest {
    private static SqlSession sqlSession;
    private static EmployeeMapper employeeMapper;
    private static JobMapper jobMapper;

    static {
        sqlSession = SSFactory.getSqlSession();
        employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
        jobMapper = sqlSession.getMapper(JobMapper.class);
    }
    public static void main(String[] args){
        Map<String,Object> map = new HashMap<>();
        map.put("party","µ³Ô±");
        List<Employee> employees = employeeMapper.selectEmployeesWithParam(map);
        for(Employee em:employees){
            System.out.println(em);
        }
        sqlSession.commit();
        sqlSession.close();
    }

}
