package com.prosay.core;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.prosay.core.entity.PageData;

/**
 * 
* @ClassName: BaseController 
* @Description: 控制起的基类
* @author Jame 
* @date 2017年12月18日 下午9:00:06 
*
 */
public class BaseController {
	protected Logger log = Logger.getLogger(BaseController.class);
	/**
	 * ThreadLocal 
	 * 他会在每一个子线程中 开辟一个内存空间
	 * 他存储的对象声明周期只有当前线程
	 */
	private static ThreadLocal<HttpServletRequest>  request = new ThreadLocal<HttpServletRequest>();
	private static ThreadLocal<HttpServletResponse> response = new ThreadLocal<HttpServletResponse>();
	private static ThreadLocal<PageData> pageData = new ThreadLocal<PageData>();
	
	public void init(HttpServletRequest request ,HttpServletResponse response){
		this.request.set(request);
		this.response.set(response);
		pageData.set(new PageData(request));
	}
	public PageData getPageData(){
		return this.pageData.get();
	}
	public Object getSessionAttr(String attrName){
		return this.request.get().getSession().getAttribute(attrName);
	}
	public void setSessionAttr(String attrName,Object obj){
		this.request.get().getSession().setAttribute(attrName,obj);
	}
	/**
	 * 
	* @Title: getReq 
	* @Description: 拿到当前线程的请求对象
	* @param @return    设定文件 
	* @return HttpServletRequest    返回类型 
	* @throws
	 */
	protected HttpServletRequest getReq(){
		return this.request.get();
	}
	/**
	 * 
	* @Title: getRep 
	* @Description: 拿到当前线程的响应对象
	* @param @return    设定文件 
	* @return HttpServletResponse    返回类型 
	* @throws
	 */
	protected HttpServletResponse getRep(){
		return this.response.get();
	}
	/**
	 * 
	* @Title: put 
	* @Description: 往请求域中存放属性
	* @param @param attrName
	* @param @param attrValue    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	protected void put(String attrName,Object attrValue){
		request.get().setAttribute(attrName, attrValue);
	}
	/**
	 * 
	* @Title: get 
	* @Description: 从请求域中拿属性
	* @param @param attrName
	* @param @param attrValue    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	protected Object get(String attrName){
		return request.get().getAttribute(attrName);
	}
	protected void writeToResponse(String str){
		try {
			PrintWriter writer = getRep().getWriter();
			writer.write(str);
			writer.close();
		} catch (IOException e) {
			log.error("Response输出到前台错误！",e.getCause());
		}
	}
}
