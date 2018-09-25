/**
 * 
 */
package com.news.security.core;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import com.news.security.core.properties.SecurityProperties;


/**
 * @author zhngtr-mi
 *
 */
@Configuration
@EnableConfigurationProperties(SecurityProperties.class)
public class SecurityCoreConfig {
	
}
