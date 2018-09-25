/**
 * 
 */
package com.news.security.core.properties;

/**
 * @author zhngtr-mi
 *
 */
public class ImageCodeProperties {
	private int width = 67;
	private int heigth = 23;
	private int length = 4;
	private int expireIn = 60;
	
	private String url ;
	
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeigth() {
		return heigth;
	}
	public void setHeigth(int heigth) {
		this.heigth = heigth;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public int getExpireIn() {
		return expireIn;
	}
	public void setExpireIn(int expireIn) {
		this.expireIn = expireIn;
	}
	public String getUrl() { 
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	

}
