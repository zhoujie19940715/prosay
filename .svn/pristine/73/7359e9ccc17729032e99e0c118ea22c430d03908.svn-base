package com.prosay.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
* @ClassName: Bean 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author Jame 
* @date 2017年12月15日 下午9:37:44 
*
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Bean {
	/**
	 * 
	* @Title: value 
	* @Description: Bean的名字 
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	String value();
	/**
	 * 
	* @Title: single 
	* @Description: 是否是单例
	* @param @return    设定文件 
	* @return boolean    返回类型 
	* @throws
	 */
	boolean single() default true;
}
