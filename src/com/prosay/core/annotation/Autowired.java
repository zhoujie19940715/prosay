package com.prosay.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
* @ClassName: Autowired 
* @Description: 标记某个属性要从容器中去自动注入 
* @author Jame 
* @date 2017年12月15日 下午9:39:37 
*
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Autowired {
	String value();
}
