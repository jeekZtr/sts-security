package com.news.web.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * 
 * @author zhngtr-mi
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest // 声明是测试用例的类
public class UserControllerTest {
	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}
	
	@Test
	public void whenUploadSuccess() throws Exception {
		String result = mockMvc.perform(fileUpload("/file")
				.file(new MockMultipartFile("file", "test.txt", "multipart/form-data", "hello upload".getBytes("UTF-8"))))
				.andExpect(status().isOk())
				.andReturn().getResponse().getContentAsString();
		System.out.println(result);
	}
	
	/**
	 *测试 一个请求成功的请求
	 * @throws Exception 
	 */
	@Test
	public void whenQuerySuccess() throws Exception {
	    String result =           
		mockMvc.perform( get("/user") // 模拟 请求  /user
			.param("username","jojo")
			.param("age","18")
			.param("ageTo","60")
			.param("xxx","xxx")
//			.param("size", "15")
//			.param("page", "3")
//			.param("sort", "age,desc")
		       .contentType(MediaType.APPLICATION_JSON_UTF8))  // 请求的contentType
		       .andExpect( status().isOk()) // 结果对比
		       .andExpect( jsonPath("$.length()").value(3))  //结果对比
		       .andReturn().getResponse().getContentAsString();
            System.out.println(result);
	}
	@Test
	public void whenGetInfoSuccess() throws Exception {
	    String result =  mockMvc.perform(get("/user/1")
		    .contentType(MediaType.APPLICATION_JSON_UTF8))
	             .andExpect(status().isOk())
	             .andExpect(jsonPath("$.username").value("tom"))
	             .andReturn().getResponse().getContentAsString();
	    System.out.println(result);
	}
	
	@Test
	public void whenGetInfoIsFail() throws Exception {
	    String result =  mockMvc.perform(get("/user/a")
		   .contentType(MediaType.APPLICATION_JSON_UTF8))
	        .andExpect(status().is4xxClientError())
	   	   .andReturn().getResponse().getContentAsString();
	    System.out.println(result);
	}
	
	@Test
	public void whenCreateSuccess() throws Exception {
		Date date = new Date();
		System.out.println(date.getTime());
		
		String content = "{\"username\":\"tom\",\"password\":\"asdfas\",\"brithday\":"+date.getTime()+"}";
		String result = mockMvc.perform(post("/user")
			   .contentType(MediaType.APPLICATION_JSON_UTF8).content(content))
		       .andExpect(status().isOk())
		       .andExpect(jsonPath("$.id").value("1"))
		       .andReturn().getResponse().getContentAsString();
		System.out.println("whenCreateSuccess--result:"+result);
	}
	
	@Test
	public void whenUpdateSuccess() throws Exception {
		
		Date date = new Date(LocalDateTime.now().plusYears(1).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
		System.out.println("update 测试用例执行开始--"+date.getTime());
		String content = "{\"id\":\"1\", \"username\":\"tom\",\"password\":null,\"birthday\":"+date.getTime()+"}";
		String reuslt = mockMvc.perform(put("/user/1").contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(content))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value("1"))
				.andReturn().getResponse().getContentAsString();
		
		System.out.println("update 测试用例执行结果--"+reuslt);
	}
	
	@Test
	public void whenDeleteSuccess() throws Exception {
		mockMvc.perform(delete("/user/1")
				.contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk());
	}
}
