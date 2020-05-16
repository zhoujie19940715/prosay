package com.prosay.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.prosay.core.annotation.Autowired;
import com.prosay.core.annotation.Bean;
import com.prosay.core.entity.PageData;
import com.prosay.db.BaseDao;
import com.prosay.util.SystemUtil;

/**
 * 
* @ClassName: OrderService 
* @Description: 订单服务类
* @author Jame 
* @date 2018年1月19日 下午8:56:38 
*
 */
@Bean("orderService")
public class OrderService {
	@Autowired("baseDao")
	private BaseDao baseDao;
	/**
	 * 
	* @Title: genOrder 
	* @Description: 根据用户信息生成订单
	* @param @param pd
	* @param @return    设定文件 
	* @return boolean    返回类型 
	* @throws
	 */
	public boolean genOrder(PageData pd){
		try{
		baseDao.openTransaction();
		String sql = "INSERT INTO t_order_info(order_id,order_no,vip_id,order_date,order_amount,recever_name,recever_addr,recever_tel,total_price,order_status)"
+"SELECT ?,?,?,NOW(),SUM(product_num),?,(SELECT addr_info FROM t_vip_address WHERE addr_id=?),?,SUM(product_total),0 FROM t_cart_info WHERE vip_id=?";
		String order_id = UUID.randomUUID().toString();
		String order_no = SystemUtil.getCurrentDateStr("yyyymmddhhmmss");
		pd.put("order_id", order_id);
		pd.put("order_no", order_no);
		//1.生成订单主表信息
		int result = baseDao.excuteUpdate(sql, pd, "order_id,order_no,vip_id,vip_name,address_id,vip_tel,vip_id");
		if(result==0){
			return false;
		}
		//2.生成订单明细表信息
		sql = "INSERT INTO t_order_detail(order_id,order_no,product_id,vip_id,order_amount,post_status,recev_status,sale_totalprice,product_price)"+
"SELECT ?,?,product_id,vip_id,product_num,'未发货','未收货',product_total,product_price FROM t_cart_info WHERE vip_id=?";
		result = baseDao.excuteUpdate(sql, pd,"order_id,order_no,vip_id");
		if(result==0){
			baseDao.rollbackTransaction();
			return false;
		}
		//3.清除掉购物车的信息
		sql = "delete from t_cart_info where vip_id=?";
		result = baseDao.excuteUpdate(sql, pd, "vip_id");
		if(result==0){
			baseDao.rollbackTransaction();
			return false;
		}
		baseDao.commitTransaction();
		}catch(Exception ex){
			baseDao.rollbackTransaction();
			ex.printStackTrace();
			return false;
			
		}
		return true;
		
	}
	/**
	 * 
	* @Title: getOrderInfo 
	* @Description: 查询订单信息
	* @param @param pd
	* @param @return    设定文件 
	* @return Map<String,Object>    返回类型 
	* @throws
	 */
	public Map<String,Object> getOrderInfo(PageData pd){
		String sql = "select * from t_order_info where order_id=?";
		List<Object> params = new ArrayList<Object>();
		params.add(pd.getString("order_id"));
		return baseDao.queryForObject(sql, params);
		 
	}
	public List<PageData> queryCartProductInfo(PageData pd ){
		String sql="SELECT c.product_id,c.vip_id,c.product_num,p.product_price*p.product_discount product_price,p.product_price*p.product_discount*c.product_num product_total,p.product_pic,p.product_name,p.product_price FROM t_cart_info c,t_product_info p WHERE c.product_id=p.product_id AND c.vip_id=?";
		List<Object> params = new ArrayList<Object>();
		params.add(pd.getString("vip_id"));
		return baseDao.queryForPage(sql, params);
	}
	public List<PageData> queryAddresssInfo(PageData pd ){
		String sql="SELECT * from t_vip_address where vip_id=? order by addr_default desc";
		List<Object> params = new ArrayList<Object>();
		params.add(pd.getString("vip_id"));
		return baseDao.queryForPage(sql, params);
	}
}
