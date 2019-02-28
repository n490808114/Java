package com.how2java.test;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.concurrent.TimeUnit;

public class TestConcurency {
    public static void main(String[] args){
        Superhero hero0 = new Superhero("hero0",10000);
        new Thread(()->{
            for(int i=0;i<10000;i++ ){
                new Thread(()->{hero0.recover();}).start();
            }
        }).start();
        new Thread(()->{
            for(int i=0;i<10000;i++ ){
                new Thread(()->{hero0.hit();}).start();
            }
        }).start();
        try{
            TimeUnit.SECONDS.sleep(10);
        }catch (InterruptedException ex){ex.printStackTrace();}
        System.out.println(hero0.hp);
    }

}
class Superhero {
    public String name;
    public float hp;

    public int damage;
    Superhero(String name,float hp){
        this.name = name;
        this.hp = hp;
    }
    public void recover(){
        float a = hp;
        a += 1;
        hp = a;
    }
    public void hit(){
        float a = hp;
        a -= 1;
        hp = a;
    }
    public void attack(Superhero h){
        h.hp -=  damage;
        System.out.println(name +" is hitting "+h.name);
        if(h.isDead()){System.out.println(h.name + " is dead!");}
    }
    public Boolean isDead(){
        return hp <= 0;
    }
}
