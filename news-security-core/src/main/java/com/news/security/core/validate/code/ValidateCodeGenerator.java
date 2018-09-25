package com.news.security.core.validate.code;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.ServletWebRequest;

import com.news.security.core.validate.code.image.ImageCode;

public interface ValidateCodeGenerator {
	
	 ImageCode generate(ServletWebRequest request);

}
