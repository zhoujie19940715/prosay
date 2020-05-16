package com.prosay.tag;

import java.util.List;

import javax.servlet.jsp.tagext.BodyTagSupport;
/**
 * 自定义循环标签
 * @author jame
 *
 */
public class ForEachTag extends BodyTagSupport{
	private List items;
	private String var;
	private int index = 0;//循环变量
	public List getItems() {
		return items;
	}
	public void setItems(List items) {
		this.items = items;
	}
	public String getVar() {
		return var;
	}
	public void setVar(String var) {
		this.var = var;
	}
	/**
	 * 标签开始的时候执行 是不是获取变量
	 */
	public int doStartTag(){
		//当传递的集合不符合要求，忽略标签体
		if(items==null||items.size()==0){
			return this.SKIP_BODY;
		}
		//从集合中拿元素 第一次我们自己拿对象
		Object obj = items.get(index);
		//设置到pageContext中 var代表我们循环的对象临时标识符
		this.pageContext.setAttribute(var, obj);
		//包含标签体进行执行 
		return this.EVAL_BODY_INCLUDE;
		
	}
	//标签包含的内容执行完成以后
	public int doAfterBody(){
		index++;
		//当循环到列表的最后，跳过标签体的执行
		if(index==items.size()){
			index = 0;
			return this.SKIP_BODY;
		}
		Object obj = items.get(index);
		this.pageContext.setAttribute(var, obj);
		//再一次执行标签体
		return this.EVAL_BODY_AGAIN;
	}
	
}
