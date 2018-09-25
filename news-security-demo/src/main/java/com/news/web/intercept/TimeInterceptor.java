/**
 * 
 */
package com.news.web.intercept;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 拦截器
 * 
 * 优点,可以拿到原始的 request和Response的值
 * 
 *     能够拿到具体的方法的信息
 * 缺点, 不能方法参数的值
 * 
 * @author zhngtr-mi
 *
 */
@Component
public class TimeInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
//		System.out.println("preHandle");
//		
//		System.out.println(((HandlerMethod)handler).getBean().getClass().getName());
//		System.out.println(((HandlerMethod)handler).getMethod().getName());
		
		request.setAttribute("startTime", System.currentTimeMillis());
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
//		System.out.println("postHandle");
		Long start = (Long) request.getAttribute("startTime");
//		System.out.println("time interceptor 耗时:"+  (System.currentTimeMillis() - start));
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
//		System.out.println("afterCompletion");
		Long start = (Long) request.getAttribute("startTime");
//		System.out.println("time interceptor 耗时:"+ (System.currentTimeMillis() - start));
//		System.out.println("ex is "+ex);
	}

}
