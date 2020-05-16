package com.prosay.core;
/**
 * bean容器类
 * @author Jame
 *
 */

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;

import com.prosay.core.entity.BeanEntity;
import com.prosay.core.entity.Property;
import com.prosay.core.util.Contants;
import com.prosay.core.util.User;
import com.prosay.core.util.XmlUtil;

public class ApplicationContext {
	//存放单例的bean对象
	private Map<String,Object> beans = new HashMap<String,Object>();
	//存放多例bean原型 多例
	private Map<String,BeanEntity> prototypes = new HashMap<String,BeanEntity>();
	
	public ApplicationContext(String xml){
		XmlUtil.log("********Bean容器框架开始初始化*****");
		Document doc = XmlUtil.getDocFromStream(ApplicationContext.class.getClassLoader().getResourceAsStream(xml));
		Element root = XmlUtil.getRootElement(doc);
		List<Element> beas = XmlUtil.getChildElements(root);
		try {
			for(Element  be : beas){//迭代所有的bean
			
				String id = be.attributeValue("id");
				String scope = be.attributeValue("scope");
				String className = be.attributeValue("class");
				System.out.println(id+"解析………………");
				BeanEntity b = null;//如果多例 存储原型
				Class<?> clazz = Class.forName(className);
				Object instance = null;//单例的实例对象
				if(scope!=null&&scope.equals(Contants.PROTO_STR)){
					b = new BeanEntity();
					b.setId(id);
					b.setScope(scope);
					b.setClassName(className);
					prototypes.put(id, b);
				}else{
					//这个bean容器管理的都是空构造
					instance = clazz.newInstance();
					beans.put(id, instance);
				}
				
				//迭代Bean的所有属性列表
				for(Object obj : be.elements()){
					Element prop = (Element)obj;
					String name = prop.attributeValue("name");
					String value = prop.attributeValue("value");
					String ref = prop.attributeValue("ref");
					if(b!=null){//代表多例的 prototype
						Property pro = new Property();
						pro.setName(name);
						pro.setValue(value);
						pro.setRef(ref);
						b.addProperty(pro);
					}else{//代表单例，初始化的时候就应该创建好实例
						//拿到所有声明的属性
						Field field = clazz.getDeclaredField(name);
						//开放修改权限 （针对私有变量）
						field.setAccessible(true);
						if(value==null){
							field.set(instance, getBean(ref));
						}else{
							field.set(instance, value);
						}
					}
				}
				
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		XmlUtil.log("********Bean容器框架初始化完成*****");
	}
	/**
	 * 通过id拿到一个Java实例
	 * @param id
	 * @return
	 */
	public Object getBean(String id){
		//先通过单例拿
		Object result = beans.get(id);
		if(result==null){
			//通过原型去new
			BeanEntity b = prototypes.get(id);
			try {
				Class<?> clazz = Class.forName(b.getClassName());
				result = clazz.newInstance();//单例的实例对象
				for(Property prop : b.getProps()){
					Field field = clazz.getDeclaredField(prop.getName());
					//开放修改权限 （针对私有变量）
					field.setAccessible(true);
					if(prop.getValue()==null){
						field.set(result, getBean(prop.getRef()));
					}else{
						field.set(result, prop.getValue());
					}
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		return result;
	}
	public static void main(String [] args){
		ApplicationContext ctx = new ApplicationContext("beans.xml");
		User u1 = (User)ctx.getBean("user");
		u1.query();
		User u2 = (User)ctx.getBean("user");
		
		System.out.println(u1.getUserName()+":"+u1.getPassword());
		System.out.println(u2.getUserName()+":"+u2.getPassword());
		System.out.println(u1+":"+u2);
	}
}
