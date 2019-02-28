package com.how2java.test;

public class TestReflection {
    public static void main(String[] args){
        String className = "com.how2java.test.Hero";
        try{

            Class test1 = Class.forName(className);
            //Class test2 = Hero.class;
            //Class test3 = new Hero().getClass();
            Hero hero = new Hero();
        }catch (Exception ex){ex.printStackTrace();}
    }
}
class Hero{
    String name;
    static int a = 1;
    static {
        System.out.println("静态初始化块");
    }
    {
        System.out.println("普通初始化块");
    }
    Hero(){
        System.out.println("构造器");
    }
    String getName(){
        return name;
    }
}
