/**
 * 
 */
package com.news.web.filter;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.stereotype.Component;

/**
 * 过滤器
 * 优点,可以拿到原始的 request和Response的值
 * 
 * 缺点,不能够拿到具体的方法的信息
 * @author zhngtr-mi
 *
 */
//@Component
public class TimeFilter implements Filter {

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
//		System.out.println("time filter init");
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
//		System.out.println("time filter start");
		long start = System.currentTimeMillis();
		chain.doFilter(request, response);
//		System.out.println("time filter 耗时:"+ (System.currentTimeMillis() - start));
//		System.out.println("time filter finish");
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#destroy()
	 */
	@Override
	public void destroy() {
//		System.out.println("time filter destroy");
	}

}
