package com.prosay.core;

import java.util.List;

import javax.servlet.http.Part;

/**
 * 
* @ClassName: Uploadable 
* @Description: 控制器要能够上传文件
* @author Jame 
* @date 2017年12月27日 下午9:33:39 
*
 */
public interface Uploadable {
	/**
	 * 
	* @Title: setFileNames 
	* @Description: 提供给DispacherServlet来反射调用时传递文件的参数
	* @param @param fileNames    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void setFileNames(List<String> fileNames);
	/**
	 * 
	* @Title: setFileNames 
	* @Description: 提供给DispacherServlet来反射调用时传递文件的参数
	* @param @param fileNames    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void setFileParts(List<Part> files);
}
