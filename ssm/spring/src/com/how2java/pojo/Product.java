package com.how2java.pojo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("p")
public class Product {
    private String name;
    private String productName;


    private Category c;

    public void setC(Category c){this.c=c;}
    public Category getC(){
        return c;
    }
    public void setProductName(String productName){
        this.productName = productName;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
    public String getProductName(){return productName;}
}
