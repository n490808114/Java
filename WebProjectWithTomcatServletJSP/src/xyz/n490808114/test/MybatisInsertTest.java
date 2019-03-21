package xyz.n490808114.test;

import org.apache.ibatis.session.SqlSession;
import xyz.n490808114.pojo.*;

import java.util.List;

public class MybatisInsertTest {
    private static SqlSession sqlSession;
    private static EmployeeMapper employeeMapper;

    static {
        sqlSession = SSFactory.getSqlSession();
        employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
    }
    public static void main(String[] args){
        Employee employee = employeeMapper.selectEmployeeById(1);
        System.out.println(employee);
        sqlSession.commit();
        sqlSession.close();
    }

}
