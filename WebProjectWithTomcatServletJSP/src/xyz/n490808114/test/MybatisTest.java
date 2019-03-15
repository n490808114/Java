package xyz.n490808114.test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import xyz.n490808114.po.User;

import java.io.InputStream;
import java.util.Date;

public class MybatisTest {
    public static void main(String[] args)throws Exception{
        User user = new User(2,"’‘Ω≠∫¿","n490808114","8801668",1,new Date(),"490808114@qq.com");
        InputStream inputStream = Resources.getResourceAsStream("xyz/n490808114/test/mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        sqlSession.insert("xyz.n490808114.test.UserMapper.save",user);
        sqlSession.commit();
        sqlSession.close();
    }
}
