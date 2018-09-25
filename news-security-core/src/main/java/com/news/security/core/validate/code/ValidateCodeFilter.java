/**
 * 
 */
package com.news.security.core.validate.code;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import com.news.security.core.properties.SecurityProperties;
import com.news.security.core.validate.code.image.ImageCode;


/**
 * @author zhngtr-mi
 *
 */
@Component("validateCodeFilter")
public class ValidateCodeFilter extends OncePerRequestFilter implements InitializingBean {

	private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy(); 
	@Autowired
	private AuthenticationFailureHandler authenticationFailureHandler;	
	
	private Set<String> urls = new HashSet<>();
	
	private AntPathMatcher pathMatcher = new AntPathMatcher();
	/**
	 * 系统配置信息
	 */
	@Autowired
	private SecurityProperties securityProperties;
	
	/**
	 * 初始化要拦截的url配置信息
	 */
	@Override
	public void afterPropertiesSet() throws ServletException {
		super.afterPropertiesSet();

		String[] configUrls = StringUtils.splitByWholeSeparatorPreserveAllTokens(securityProperties.getCode().getImage().getUrl(),",");
		for(String configUrl:configUrls) {
			urls.add(configUrl); 
		}
		urls.add("/authentication/form");
	}

	
	/*
	 * 
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		
		boolean action = false;
		for(String url:urls) {
			if(pathMatcher.match(url, request.getRequestURI())) {
				action = true;
			}
			
		}
		
		if( action  ) {
			
			try {
				validate(new ServletWebRequest(request));
			} catch (ValidateCodeException e) { 
				authenticationFailureHandler.onAuthenticationFailure(request, response, e);
				return;
			}
		}
		chain.doFilter(request, response);
		
	}

	private void validate(ServletWebRequest request) throws ServletRequestBindingException {
		
		ImageCode codeInSession = (ImageCode) sessionStrategy.getAttribute(request, ValidateCodeController.SESSION_KEY);
		
		String codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(), "imageCode");
		
		if(StringUtils.isBlank(codeInRequest)) {
			throw new ValidateCodeException("验证码不能为空值");
		}
		if(codeInSession==null) {
			throw new ValidateCodeException("验证码不存在");
		}
		if(codeInSession.isExpried()) {
			sessionStrategy.removeAttribute(request, ValidateCodeController.SESSION_KEY);
			throw new ValidateCodeException("验证码已过期");
		}
		if(!StringUtils.equals(codeInSession.getCode(),codeInRequest) ) {
			throw new ValidateCodeException("验证码不匹配");
		}
		sessionStrategy.removeAttribute(request, ValidateCodeController.SESSION_KEY);
	}

	public AuthenticationFailureHandler getAuthenticationFailureHandler() {
		return authenticationFailureHandler;
	}

	public void setAuthenticationFailureHandler(AuthenticationFailureHandler authenticationFailureHandler) {
		this.authenticationFailureHandler = authenticationFailureHandler;
	}


	public SecurityProperties getSecurityProperties() {
		return securityProperties;
	}


	public void setSecurityProperties(SecurityProperties securityProperties) {
		this.securityProperties = securityProperties;
	}


}
