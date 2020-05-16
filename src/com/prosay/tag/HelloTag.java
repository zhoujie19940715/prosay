package com.prosay.tag;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class HelloTag extends BodyTagSupport{
	//标签的一个属性
	private String name;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

/**
 *  <C:FOREACH
 * 遇到开始标签就会开始执行的方法
 */
  public int doStartTag(){
	 //获取当前时间
	  Calendar cal = Calendar.getInstance();
	  int hour = cal.get(Calendar.HOUR_OF_DAY);
	  String msg = null;
	  if(hour<12){
		  msg = name+":上午好";
	  }else if(hour<18){
		  msg = name+"下午好";
	  }else{
		  msg = name+"晚上好";
	  }
	  //通过页面上下文属性（pageContext）获得jsp的字符输出流
	  JspWriter out = this.pageContext.getOut();
	  	try {
		  out.println(msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  return this.EVAL_PAGE;
	  
  }
}
