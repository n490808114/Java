package com.how2java.service;

import org.springframework.stereotype.Component;

@Component("productService")
public class ProductService {
    public void doSomeService(){
        System.out.println("doSomeService.do");
    }
}
