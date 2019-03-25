package xyz.n490808114.test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.PropertyConfigurator;
import xyz.n490808114.pojo.Job;
import xyz.n490808114.pojo.User;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

public class MybatisTest {
    static Logger logger = Logger.getLogger(MybatisTest.class.getName());
    public static void main(String[] args){
        String path = MybatisTest.class.getResource("/").getPath();
        System.out.println(path+"log4j.properties");
        PropertyConfigurator.configure(path+"log4j.properties");
        logger.info("test");
        try{
            new MybatisTest().requestMapTest();
        }catch (IOException ex){ex.printStackTrace();}

    }
    public SqlSession getSession()throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        return sqlSessionFactory.openSession();
    }
    public void insert() throws IOException{
        User user = new User(7,"zhao","n490808114","8801668",1,new Date(),"490808114@qq.com");
        SqlSession sqlSession = getSession();
        sqlSession.insert("saveUser",user);
        sqlSession.commit();
        sqlSession.close();
    }
    public void select(int a)throws IOException{
        SqlSession sqlSession = sqlSession = getSession();
        User user = sqlSession.selectOne("selectUser",a);
        logger.warning(user.toString());
        System.out.println(user.toString());
        sqlSession.commit();
        sqlSession.close();
    }
    public void requestMapTest()throws IOException{
        SqlSession sqlSession = getSession();
        List<Job> userList = sqlSession.selectList("selectUser2");
        for(Job job:userList){
            System.out.println(job);
        }
        sqlSession.commit();
        sqlSession.close();
    }
}
