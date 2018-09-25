/**
 * 
 */
package com.news.web.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * 
 * 切面
 * 
 * 优点,  能够拿到具体的方法的信息  能方法参数的值 
 * 缺点,  不可以拿到原始的 request和Response的值
 * 
 *     
 * 
 * @author zhngtr-mi
 *
 */
@Aspect
@Component
public class TimeAspect {
	
	@Around("execution(* com.news.web.controller.UserController.*(..))")
	public Object handleControllerMethod(ProceedingJoinPoint pjp) throws Throwable {
		
//		System.out.println("time aspect start");
		
		Object[] args = pjp.getArgs();
		for (Object arg : args) {
			System.out.println("arg is "+arg);
		}
		
		long start = System.currentTimeMillis();
		
		Object object = pjp.proceed();
		
//		System.out.println("time aspect 耗时:"+ (System.currentTimeMillis() - start));
		
//		System.out.println("time aspect end");
		
		return object;
	}
}
