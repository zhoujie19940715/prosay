package com.prosay.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.prosay.core.annotation.Autowired;
import com.prosay.core.annotation.Bean;
import com.prosay.core.entity.PageData;
import com.prosay.db.BaseDao;

@Bean("carService")
public class CarService {
	@Autowired("baseDao")
	private BaseDao baseDao;
	//查询购物车列表
	public List<Map<String,Object>> queryCarList(PageData pd){
		List<Object> params = new ArrayList<Object>();
		params.add(pd.getString("vip_id"));
		return baseDao.queryForList("select t.*,p.product_pic from t_cart_info t,t_product_info p where p.product_id=t.product_id and vip_id=?", params);
	}
	//将商品添加到购物车
	public boolean saveProductToCar(PageData pd){
		boolean result = true;
		baseDao.excuteUpdate("delete from t_cart_info where product_id=? and vip_id=?", pd, "product_id,vip_id");
		String sql = "INSERT INTO t_cart_info(product_id,vip_id,product_num,product_price,product_total,del_flag)VALUES(?,?,?,?,?,0)";
		int rows = baseDao.excuteUpdate(sql, pd, "product_id,vip_id,product_num,product_price,product_total");
		if(rows<1){
			result = false;
		}
		return result;
	}
	//将商品从购物车中移除
	public boolean delProductToCar(PageData pd){
		boolean result = true;
		int rows =  baseDao.excuteUpdate("delete from t_cart_info where product_id=? and vip_id=?", pd, "product_id,vip_id");
		if(rows<1){
			result = false;
		}
		return result;
	}
}
