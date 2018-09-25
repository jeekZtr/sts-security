/**
 * 
 */
package com.news.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.news.service.HelloService;

/**
 * @author zhngtr-mi
 *
 */
public class MyConstraintValidator implements ConstraintValidator<MyConstraint, Object> {

	@Autowired
	private HelloService helloservice;
	
	@Override
	public void initialize(MyConstraint constraintAnnotation) {
//		System.out.println("my validator init");
	} 

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
//		System.out.println("isValid---"+value);
		helloservice.greeting("tom");
		return false;
	}

}
