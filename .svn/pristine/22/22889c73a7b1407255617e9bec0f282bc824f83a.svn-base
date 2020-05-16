package com.prosay.core;

import com.alibaba.fastjson.JSON;

/**
 * 
* @ClassName: ReturnContent 
* @Description: 返回的内容实体
* @author Jame 
* @date 2017年12月18日 下午9:55:06 
*
 */
public class ReturnContent {

	/**
	 * 请求转发或者重定向的url
	 */
	private String url;
	/**
	 * 需要返回的json对象
	 */
	private Object obj;
	
	public ReturnContent(String url){
		this.url = url;
	}
	public ReturnContent(Object obj){
		this.obj = obj;
	}
	public String getUrl() {
		return url;
	}
	public Object getObj() {
		return obj;
	}
	public String getJson(){
		return JSON.toJSONString(obj);
	}
	
}
