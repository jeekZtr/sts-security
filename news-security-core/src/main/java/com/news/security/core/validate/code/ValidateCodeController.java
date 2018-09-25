/**
 * 
 */
package com.news.security.core.validate.code;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import com.news.security.core.properties.SecurityConstants;
import com.news.security.core.properties.SecurityProperties;
import com.news.security.core.validate.code.image.ImageCode;

/**
 * @author zhngtr-mi
 *
 */
@RestController
public class ValidateCodeController {

	public static final String SESSION_KEY = "SESSION_KEY_IMAGE_CODE";
	//@Autowired
	//private ValidateCodeProcessorHolder validateCodeProcessorHolder;

	private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy(); 

	@Autowired
	private ValidateCodeGenerator imageCoderGenerator;
	/**
	 * 创建验证码，根据验证码类型不同，调用不同的 {@link ValidateCodeProcessor}接口实现
	 * 
	 * @param request
	 * @param response
	 * @param type
	 * @throws Exception
	 */
	@GetMapping("/code/image")
	public void createCode(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ImageCode imageCode = imageCoderGenerator.generate( new ServletWebRequest(request));
		sessionStrategy.setAttribute(new ServletWebRequest(request),SESSION_KEY,imageCode);
		//validateCodeProcessorHolder.findValidateCodeProcessor(type).create(new ServletWebRequest(request, response));
		ImageIO.write(imageCode.getImage(), "JPEG", response.getOutputStream());
	}

	
}
