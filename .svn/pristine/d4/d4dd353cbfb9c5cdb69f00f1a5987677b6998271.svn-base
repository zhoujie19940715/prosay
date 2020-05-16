package com.prosay.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

/**
 * 
* @ClassName: SystemUtil 
* @Description: 系统常见公用方法
* @author Jame 
* @date 2017年12月20日 下午8:50:20 
*
 */
public class SystemUtil {
	public static final String USERENCODE = "[prosay]";
	public static final String USERADMIN = "admin";
	public static final String USERNORMAL = "user";
	public static final String USERALL = "all";
	public static final String USERNON = "non";
	public static final String ADMINLOGIN="/admin/login.do";
	public static final String USERLOGIN="/user/login.do";
	public static void initBasePath(HttpServletRequest request){
		//http://localhost:8080/prosay
		ServletContext application = request.getServletContext();
		if(application.getAttribute("basePath")==null){
			application.setAttribute("basePath", request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath());
		}
	}
	public static void setAdmin(HttpServletRequest request,Map<String,Object> admin){
		request.getSession().setAttribute(SystemUtil.USERADMIN,admin);
	}
	public static Map<String,Object> getAdmin(HttpServletRequest request){
		return (Map<String,Object>)request.getSession().getAttribute(SystemUtil.USERADMIN);
	}
	public static String getBasePath(HttpServletRequest request){
		return request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
	}
	public static String getCurrentDateStr(String format){
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(new Date());
	}
}
