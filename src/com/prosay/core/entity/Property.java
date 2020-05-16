package com.prosay.core.entity;
/**
 * Bean原型中属性对象
 * @author Jame
 *
 */

import java.lang.reflect.Field;

public class Property {
	private Field field;
	public Field getField() {
		return field;
	}
	public void setField(Field field) {
		this.field = field;
	}
	private String name;//属性名字
	private String value;//属性值
	private String ref;//依赖的对象 
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getRef() {
		return ref;
	}
	public void setRef(String ref) {
		this.ref = ref;
	}

}
