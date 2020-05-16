package com.prosay.service;

import java.util.ArrayList;
import java.util.List;

import com.prosay.core.annotation.Autowired;
import com.prosay.core.annotation.Bean;
import com.prosay.core.entity.PageData;
import com.prosay.db.BaseDao;

@Bean("frontService")
public class FrontService {
	@Autowired("baseDao")
	private BaseDao baseDao;
	/**
	 * 
	* @Title: querySecondType 
	* @Description: 根据一级类型id查询出二级类型列表 
	* @param @param pd
	* @param @return    设定文件 
	* @return List<PageData>    返回类型 
	* @throws
	 */
	public List<PageData> querySecondType(PageData pd){
		String sql = "SELECT  type_id,type_name,(SELECT type_name FROM t_product_type WHERE type_id=t.type_parent) pname,type_parent FROM t_product_type t WHERE type_parent=?";
		List<Object> params= new ArrayList<Object>();
		params.add(pd.getString("typeId"));
		return baseDao.queryForPage(sql, params);
	}
	/**
	 * 
	* @Title: queryProductList 
	* @Description: 根据商品类别查询商品列表
	* @param @param pd
	* @param @return    设定文件 
	* @return List<PageData>    返回类型 
	* @throws
	 */
	public List<PageData> queryProductList(PageData pd){
		String sql = "SELECT * FROM t_product_info WHERE type_pid=? ";
		List<Object> params = new ArrayList<Object>();
		params.add(pd.getString("typeId"));
		if(pd.containsKey("sTypeId")){
			params.add(pd.getString("sTypeId"));
			sql += " and type_id=?";
		}
		return baseDao.queryForPage(sql, params);
	}
}
