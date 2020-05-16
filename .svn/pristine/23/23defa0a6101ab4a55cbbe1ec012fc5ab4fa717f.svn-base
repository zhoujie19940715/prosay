package com.prosay.core.entity;
/**
 * 
* @ClassName: PageData 
* @Description: 页面数据
* @author Jame 
* @date 2017年12月22日 下午10:08:02 
*
 */

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class PageData extends HashMap implements Map{

	private Map map = null;
	
	private HttpServletRequest request;
	
	public PageData(){
		map = new HashMap<String,Object>();
	}
	public void setData(Map<String,Object> data){
		this.map = data;
	}
	
	public PageData(HttpServletRequest request){
		this.request = request;
		Map reqMaps = request.getParameterMap();
		map = new HashMap<String,Object>();
		Iterator<String> iter  = reqMaps.keySet().iterator();
		while(iter.hasNext()){
			String key = iter.next();
			Object value = reqMaps.get(key);
			if(null==value){
				map.put(key, "");
			}else if(value instanceof String[]){
				String v = "";
				String[] vs = (String[])value;
				for(String tmp : vs){
					v+=tmp+",";
				}
				map.put(key,v.substring(0, v.length()-1));
			}else{
				map.put(key, value.toString());
			}
		}
	}
	/**
	 * 
	* @Title: hasEle 
	* @Description: 判断是否存在这个属性
	* @param @param key
	* @param @return    设定文件 
	* @return boolean    返回类型 
	* @throws
	 */
	public boolean hasKey(String key){
		if(map.containsKey(key)){
			Object o = map.get(key);
			if(o==null||o.toString().equals("")){
				return false;
			}
			return true;
		}
		return false;
	}
	@Override
	public Object get(Object key){
		if(map==null){
			return null;
		}
		return map.get(key);
	}
	public Object get(String key){
		if(map==null){
			return null;
		}
		return map.get(key);
	}
	public Object put(Object key,Object value){
		return map.put(key, value);
	}
	public String getString(String key){
		Object o = map.get(key);
		if(o==null){
			return "";
		}
		return o.toString();
	}
	@Override
	public boolean containsKey(Object key){
		return map.containsKey(key);
	}
	public Iterator keyIterator(){
		return map.keySet().iterator();
	}
	@Override 
	public boolean containsValue(Object value){
		return map.containsValue(value);
	}
	public  Object remove(Object key){
		return map.remove(key);
	}
}
