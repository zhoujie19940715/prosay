package com.prosay.util;

import java.util.ArrayList;
import java.util.List;

import com.prosay.core.entity.PageData;

public class SQLUtil {
	/**
	 * @description: 检查元素,得到list集合 
	 * @author Simple
	 * @param pd
	 * @param str
	 * @return list
	 * 2017年12月29日下午2:18:45
	 */
	public static List createList(PageData pd,String... str) {
		List list = new ArrayList();
		for(String s : str) {
			if(pd.hasKey(s.toLowerCase().trim()) || pd.hasKey(s.toUpperCase().trim())) {
				list.add(pd.getString(s));
			}
		}
		return list;
	}
	//if(s.equalsIgnoreCase(pd.getString(s.toLowerCase().trim())) || s.equalsIgnoreCase(pd.getString(s.toUpperCase().trim()))){
	/**
	 * @description: 如果是带逗号的字符串,可以带个true实现分割
	 * @author Simple
	 * @param pd
	 * @param str
	 * @param flag
	 * @return
	 * 2017年12月29日下午2:21:09
	 */
	public static List createList(PageData pd,String str,boolean flag) {
		List list = new ArrayList();
		for(String s : str.split(",")) {
			if(pd.hasKey(s.toLowerCase().trim()) || pd.hasKey(s.toUpperCase().trim())) {
				list.add(pd.getString(s));
			}
		}
		return list;
	}
	/**
	 * @description: 模糊查询的,得到list集合 
	 * @author Simple
	 * @param pd
	 * @param str
	 * @return list
	 * 2017年12月29日下午2:18:45
	 */
	public static List createListIndistinct(PageData pd,String... str) {
		List list = new ArrayList();
		for(String s : str) {
			if(pd.hasKey(s.toLowerCase().trim()) || pd.hasKey(s.toUpperCase().trim())) {
				list.add("%"+pd.getString(s)+"%");
			}
		}
		return list;
	}
	/**
	 * @description: 模糊查找使用的集合, 如果是带逗号的字符串,可以带个true实现分割
	 * @author Simple
	 * @param pd
	 * @param str
	 * @param flag
	 * @return
	 * 2017年12月29日下午2:21:09
	 */
	public static List createListIndistinct(PageData pd,String str,boolean flag) {
		List list = new ArrayList();
		for(String s : str.split(",")) {
			if(pd.hasKey(s.toLowerCase().trim()) || pd.hasKey(s.toUpperCase().trim())) {
				list.add("%"+pd.getString(s)+"%");
			}
		}
		return list;
	}
	/**
	 * @description:  sql语句的拼接使用  //注意语句在之前必须使用where 1=1 (即是true)
	 * @author Simple
	 * @param sb StringBuilder
	 * @param pd
	 * @param str 
	 * @param 是否是模糊查找,如果是 则输入true
	 * @return
	 * 2017年12月29日下午4:11:42
	 */
	public static StringBuilder sqlState(StringBuilder sb,PageData pd,boolean indistinct,String... str) {
		if(indistinct) {
			for(String s : str) {
				if(pd.hasKey(s.toLowerCase().trim()) || pd.hasKey(s.toUpperCase().trim())) {
					sb.append(" and "+pd.getString(s)+" like ?");
				}
			}
		}else {
			for(String s : str) {
				if(pd.hasKey(s.toLowerCase().trim()) || pd.hasKey(s.toUpperCase().trim())) {
					sb.append(" and "+pd.getString(s)+" = ?");
				}
			}
		}
		return sb;
	}
	/**
	 * @description:  sql语句的拼接使用  //注意语句在之前必须使用where 1=1 (即是true)
	 * @author Simple
	 * @param sb StringBuilder
	 * @param pd
	 * @param str 带逗号的语句
	 * @param 是否是模糊查找,如果是 则输入true
	 * @return
	 * 2017年12月29日下午4:11:42
	 */
	public static StringBuilder sqlState(StringBuilder sb,PageData pd,boolean indistinct,String str,boolean flag) {
		if(indistinct) {
			for(String s : str.split(",")) {
				if(pd.hasKey(s.toLowerCase().trim()) || pd.hasKey(s.toUpperCase().trim())) {
					sb.append(" and "+pd.getString(s)+" like ?");
				}
			}
		}else {
			for(String s : str.split(",")) {
				if(pd.hasKey(s.toLowerCase().trim()) || pd.hasKey(s.toUpperCase().trim())) {
					sb.append(" and "+pd.getString(s)+" = ?");
				}
			}
		}
		return sb;
	}
	/**
	 * @description: 为日期的拼接使用的 
	 * @author Simple
	 * @param 开始日期,允许为null或者""
	 * @param 结束日期,允许为null或者""
	 * @return 
	 * 2017年12月29日下午3:49:45
	 */
	public static StringBuilder sqlDateState(StringBuilder sb,PageData pd,String startTime,String endTime) {
		if(startTime == null) 
			startTime = "";
		if(pd.hasKey(startTime.toLowerCase().trim()) || pd.hasKey(startTime.toUpperCase().trim())) {
			sb.append(" and "+pd.get(startTime)+" >= ?");
		}
		if(endTime == null)
			endTime = "";
		if(pd.hasKey(endTime.toLowerCase().trim()) || pd.hasKey(endTime.toUpperCase().trim())) {
			sb.append(" and "+pd.get(endTime)+" <= ?");
		}
		return sb;
	}
}
