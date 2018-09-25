package com.news.security.core.validate.code;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.news.security.core.properties.SecurityProperties;
import com.news.security.core.validate.code.image.ImageCodeGenerator;

@Configuration
public class ValidateBeanConfig {
	
	@Autowired
	private SecurityProperties securityProperties;
	
	@Bean
	@ConditionalOnMissingBean(name = "imageCodeGenerator")
	public ValidateCodeGenerator imageCodeGenerator(){
		ImageCodeGenerator codeGenertor = new ImageCodeGenerator();
		codeGenertor.setSecurityProperties(securityProperties);
		return codeGenertor;
	}
}
