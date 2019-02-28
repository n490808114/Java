package com.how2java.test;

public class TestInit4 {
    public static void main(String[] args) {
        InitDemo4 id = new InitDemo4();
        System.out.println("------------");
        InitDemo4 id1 = new InitDemo4();
    }
}
class InitDemo4{
    public InitDemo4(){
        System.out.println("我是构造器");
    }
    String a=msg("普通属性初始化1");
    public static String msg(String info){
        System.out.println(info);
        return info;
    }
    static{
        System.out.println("静态初始化块2");
    }
    static{
        System.out.println("静态初始化块1");
    }
    static String b=msg("静态属性初始化1");
    {
        System.out.println("普通初始化块1");
    }
    String c=msg("普通属性初始化2");
    {
        System.out.println("普通初始化块2");
    }
    static String d=msg("静态属性初始化2");

}
