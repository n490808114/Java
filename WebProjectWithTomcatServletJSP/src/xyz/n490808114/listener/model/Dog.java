package xyz.n490808114.listener.model;

import java.io.Serializable;

public class Dog implements Serializable {
    private String name;
    private int size;
    private MyContextListener contextListener;
    Dog(String name,int size){
        this.name = name;
        this.size = size;
    }
    public void setContextListener(MyContextListener listener){
        this.contextListener = listener;
    }
    public MyContextListener getContextListener(){
        return this.contextListener;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setSize(int size){
        this.size = size;
    }
    public String getName(){
        return name;
    }
    public int getSize(){
        return size;
    }

}