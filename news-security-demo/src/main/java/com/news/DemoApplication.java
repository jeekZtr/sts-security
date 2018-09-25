package com.news;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 
 * @author zhngtr-mi 
 *
 */
@SpringBootApplication
@RestController
@EnableSwagger2
public class DemoApplication {
	
	public static void main(String args[]) {
		SpringApplication.run(DemoApplication.class,args);
		System.out.println("------系统已经启动-----");
	}
	@GetMapping("/hello")
	public String hello() {
		System.out.println("------hello------");
		return "hello spring security";
	}

}
