package kh.spring.controllers;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import kh.spring.interfaces.TV;

public class Main {
	public static void main(String[] args) {
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("context.xml");
		TV tv = (TV)ctx.getBean("tv");
		tv.volDown();
		//TV tv = ctx.getBean(TV.class);
		
//		ctx.getBean("tv");
//		ctx.getBean("tv");
//		ctx.getBean("tv");
//		ctx.getBean("tv");
//		ctx.getBean("tv");
	}
}
