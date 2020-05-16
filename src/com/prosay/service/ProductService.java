package com.prosay.service;
/**
 * 
* @ClassName: ProductService 
* @Description: 商品服务类
* @author Jame 
* @date 2017年12月25日 下午8:53:02 
*
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.prosay.core.annotation.Autowired;
import com.prosay.core.annotation.Bean;
import com.prosay.core.entity.Page;
import com.prosay.core.entity.PageData;
import com.prosay.db.BaseDao;
import com.prosay.util.SQLUtil;
@Bean("productService")
public class ProductService {
	@Autowired("baseDao")
	private BaseDao baseDao;
	public Page<PageData> queryProducts(Page<PageData> page){
		PageData pd = page.getPd();//拿到页面提交的参数
		String sql="SELECT p.product_id,type_id,p.product_name,p.product_storage,p.product_price,p.product_dealamount,p.product_outline,p.product_storetime,p.product_pic FROM t_product_info p";
		String condition=" where del_flag=0 ";
		List<Object> params =new  ArrayList<Object>();
		if(pd.hasKey("PRODUCT_NAME")){
			condition+=" and product_name like ?";
			params.add("%"+pd.get("PRODUCT_NAME")+"%");
		}
		//查询条件里面 时间一个范围
		if(pd.hasKey("STARTTIME")){
			condition+=" and product_storetime >= ?";
			params.add(pd.get("STARTTIME"));
		}
		if(pd.hasKey("ENDTIME")){
			condition+=" and product_storetime < ?";
			params.add(pd.get("ENDTIME"));
		}
		int total = baseDao.getTotal("select count(1) from t_product_info"+condition, params);
		
		page.setTotal(total);
		List<PageData> results = baseDao.queryForPage(sql+condition+page.getPageSql(), params);
		page.setData(results);
		return page;
	}
	/**
	 * 
	* @Title: queryProductType 
	* @Description: 查询所有的商品类型
	* @param @return    设定文件 
	* @return List<PageData>    返回类型 
	* @throws
	 */
	public List<Map<String,Object>> queryProductType(PageData pd){
		String sql="SELECT type_id,type_name from t_product_type where del_flag=0 and type_parent=?";
		ArrayList<Object> param = new ArrayList<Object>();
		param.add(pd.getString("type_id"));
		List<Map<String,Object>> results = baseDao.queryForList(sql, param);
		return results;
	}
	public boolean delProductById(PageData pd){
		String sql = "update t_product_info set del_flag = 1 where product_id = ?";
		boolean result = false;
		int r = baseDao.excuteUpdate(sql, pd, "id");
		if(r>0){
			result =true;
		}
		return result;
	}
	public boolean delProductByIds(PageData pd){
		String ids = pd.getString("ids");
		String[] id = ids.split(",");
		List params = new ArrayList();
		String sql = "update t_product_info set del_flag = 1 where product_id in (";
		for(int i = 0 ; i < id.length ; i++){
			if(i>0){
				sql+=",";
			}
			sql+="?";
			params.add(id[i]);
		}
		sql+= ")";
		boolean result = false;
		int r = baseDao.excuteUpdate(sql, params);
		if(r>0){
			result =true;
		}
		return result;
	}
	public boolean saveProduct(PageData pd){
		boolean result = false;
		if(pd.getString("product_id").length()>0){
			String sql = "update t_product_info set type_id=?,type_pid=?,product_name=?,product_storage=?,product_price=?,product_discount=?,product_outline=?,product_pic=?,product_detail=?  where product_id=?";
			int r = baseDao.excuteUpdate(sql, pd, "type_id,type_pid,product_name,product_storage,product_price,product_discount,product_outline,product_pic,product_detail,product_id");
			if(r>0){
				result = true;
			}
		}else{
			String sql = "INSERT INTO t_product_info(type_id,type_pid,product_name,product_storage,product_price,product_discount,product_outline,product_storetime,product_pic,product_detail)"
	+"VALUES(?,?,?,?,?,?,?,NOW(),?,?)";
			int r = baseDao.excuteUpdate(sql, pd, "type_id,type_pid,product_name,product_storage,product_price,product_discount,product_outline,product_pic,product_detail");
			if(r>0){
				result = true;
			}
		}
		return result;
	}
	/**
	 * 
	* @Title: getPageById 
	* @Description:根据ID查询商品信息
	* @param @param pd
	* @param @return    设定文件 
	* @return PageData    返回类型 
	* @throws
	 */
	public PageData getPageById(PageData pd){
		String sql = "select * from t_product_info where product_id = ?";
		ArrayList<Object> params = new ArrayList<Object>();
		params.add(pd.getString("id"));
		Map<String,Object> obj = baseDao.queryForObject(sql, params);
		pd.setData(obj);
		return pd;
	}
	/**
	 * 展示列表
	 * @param page
	 * @return
	 */
	public Page<PageData> queryProductTypeList(Page<PageData> page) {
		PageData pd = page.getPd();
		List<Object> params = new ArrayList<>();  // 参数List
		String sql = "select * from t_product_type where del_flag=0 ";
	
		if(pd.hasKey("type_name")){
			
			sql += "and (type_name like ? or type_id in(select type_parent from t_product_type where type_name like ? and del_flag=0))";
			params.add("%"+pd.get("type_name")+"%");
			params.add("%"+pd.get("type_name")+"%");
		}
		
		List<PageData> result = baseDao.queryForPage(sql, params);
		page.setData(result);
		return page;
	}
	public boolean saveType(PageData pd) {
		boolean result = false;
		if(pd.getString("type_id").length()>0){
			String sql = "update t_product_type set type_name=?,type_parent=? where type_id=?";
			int r = baseDao.excuteUpdate(sql, pd, "type_name,type_parent,type_id");
			if(r>0){
				result = true;
			}
		}else{
			String sql = "INSERT INTO t_product_type(type_name,type_parent)"
	+"VALUES(?,?)";
			int r = baseDao.excuteUpdate(sql, pd, "type_name,type_parent");
			if(r>0){
				result = true;
			}
		}
		return result;
	}
	
	public PageData getTypeById(PageData pd){
		String sql = "select * from t_product_type where type_id = ?";
		ArrayList<Object> params = new ArrayList<Object>();
		params.add(pd.getString("type_id"));
		Map<String,Object> obj = baseDao.queryForObject(sql, params);
		pd.setData(obj);
		return pd;
	}
	public boolean delTypeById(PageData pd) {
		String sql = "update t_product_type set del_flag = 1 where type_id = ?";
		boolean result = false;
		int r = baseDao.excuteUpdate(sql, pd, "type_id");
		if(r>0){
			result =true;
		}
		return result;
	}
	public boolean delTypeByIds(PageData pd) {
		String ids = pd.getString("ids");
		String[] id = ids.split(",");
		List params = new ArrayList();
		String sql = "update t_product_type set del_flag = 1 where type_id in (";
		for(int i = 0 ; i < id.length ; i++){
			if(i>0){
				sql+=",";
			}
			sql+="?";
			params.add(id[i]);
		}
		sql+= ")";
		boolean result = false;
		int r = baseDao.excuteUpdate(sql, params);
		if(r>0){
			result =true;
		}
		return result;
	}
	/**
	 * @description: 更新浏览次数 
	 * @author Simple
	 * @param pd
	 * @return
	 * 2018年1月17日下午1:56:20
	 */
	public boolean updateLookAmount(PageData pd) {
		String sql = "update t_product_info set product_lookamount = product_lookamount+1 where product_id=?";
		System.out.println(pd);
		System.out.println(pd.getString("id"));
		int result = baseDao.excuteUpdate(sql,pd, "id");
		return result>0;
	}
}
