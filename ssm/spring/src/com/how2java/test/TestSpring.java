package com.how2java.test;

import com.how2java.jdbc.ConnectJDBC;
import com.how2java.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.test.context.ContextConfiguration;

import java.util.LinkedList;

@ContextConfiguration("classpath:applicationContext.xml")
public class TestSpring {
	@Autowired
	ProductService service;
	@Autowired
    ConnectJDBC jdbcTest;

	public static void main(String[] args){
		new TestSpring().test();
	}

	private void test() {
		LinkedList<String[]> checkResult = jdbcTest.executeQuery("SELECT * FROM tickets;");
		for(String[] list:checkResult){
			System.out.println(new Order(list).toString());
		}
		service.doSomeService();

	}

}