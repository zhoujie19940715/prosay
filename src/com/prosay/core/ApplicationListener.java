package com.prosay.core;

import com.prosay.core.util.ClassScanner;
import com.prosay.core.util.Contants;
import com.prosay.db.DbUtil;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.Map;
/**
 * 
* @ClassName: ApplicationListener 
* @Description: 监听应用的上下文
* @author Jame 
* @date 2017年12月15日 下午10:12:22 
*
 */
public class ApplicationListener implements ServletContextListener {
	/**
	 *  当Servlet 容器终止Web 应用时调用该方法。在调用该方法之前，容器会先销毁所有的Servlet 和Filter 过滤器。
	 * @param servletContextEvent
	 */

	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		// 应用程序被卸载的时候 释放数据库连接池
		DbUtil.release();
	}

	/**
	 * 当Servlet 容器启动Web 应用时调用该方法。在调用完该方法之后，容器再对Filter 初始化，
	 * @param servletContextEvent
	 */

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		// 应用程序被加载的时候（应用启动的时候）
		String basePackage = (String)servletContextEvent.getServletContext().getInitParameter(Contants.PACKAGE_PARAM_NAME);
		//通过基础包路径 扫描下面的所有类
		Map<String,Class<?>> result = ClassScanner.scannerClass(basePackage);
		//初始化Bean容器
		BeanContext b = new BeanContext(result);
		//将Bean容器存放到整个上下文的中
		servletContextEvent.getServletContext().setAttribute(Contants.CONTEXT_NAME, b);
	}

}
