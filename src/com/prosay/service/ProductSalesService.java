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
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.prosay.core.annotation.Autowired;
import com.prosay.core.annotation.Bean;
import com.prosay.core.entity.Page;
import com.prosay.core.entity.PageData;
import com.prosay.db.BaseDao;
import com.prosay.util.SQLUtil;
@Bean("productSalesService")
public class ProductSalesService {
	@Autowired("baseDao")
	private BaseDao baseDao;
	/**
	 * @description: 查询营销列表 
	 * @author Simple
	 * @param page
	 * @return
	 * 2018年1月4日下午12:10:44
	 */
	public Page<PageData> querySalesList(Page<PageData> page){
		String sql = "select * from t_product_sales ";
		PageData pd = page.getPd();
		//商品名称 模糊查询
		//TODO 应该把商品名称也显示出来
		//String productSql = "select product_name where product_id = ?";
		//strb = SQLUtil.sqlState(sb, pd, true, str);
		//商品编号 营销类型 精确查询
		String condition=" where del_flag=0 ";
		List<Object> params =new  ArrayList<Object>();
		if(pd.hasKey("sales_type")){
			condition+=" and sales_type = ?";
			params.add(pd.get("sales_type"));
		}
		//查询条件里面 时间一个范围
		//开始时间 结束时间
		if(pd.hasKey("chosed_time")){
			condition+=" and sales_starttime <= ?";
			condition+=" and sales_endtime >= ?";
			params.add(pd.get("chosed_time"));
			params.add(pd.get("chosed_time"));
		}
		int total = baseDao.getTotal("select count(1) from t_product_sales "+condition, params);
		page.setTotal(total);
		List<PageData> result = baseDao.queryForPage(sql+condition+page.getPageSql(), params);
		page.setData(result);
		return page;
	}
	/**
	 * @description: 删除商品营销信息
	 * @author Simple
	 * @param pd
	 * @return
	 * 2018年1月4日下午12:07:30
	 */
	public boolean delProductSalesByIds(PageData pd){
		String ids = pd.getString("ids");
		String[] id = ids.split(",");
		List params = new ArrayList();
		String sql = "update t_product_sales set del_flag = 1 where sales_id in (";
		for(int i = 0 ; i < id.length ; i++){
			if(i>0){
				sql+=",";
			}
			sql+="?";
			params.add(id[i]);
		}
		sql+= ")";
		int result = baseDao.excuteUpdate(sql, params);
		return result>0;
	}
	/**
	 * @description: 删除单个商品 
	 * @author Simple
	 * @param pd
	 * @return
	 * 2018年1月4日下午2:51:46
	 */
	public boolean delProductById(PageData pd){
		String sql = "update t_product_sales set del_flag = 1 where sales_id = ?";
		int result = baseDao.excuteUpdate(sql, pd, "sales_id");
		return result>0;
	}

	/**
	 * @description: 根据id查询商品
	 * @author Simple
	 * @param pd
	 * @return
	 * 2018年1月4日下午12:04:16
	 */
	public PageData getPageById(PageData pd){
		String sql = "select * from t_product_sales where sales_id = ?";
		Map<String, Object> map = baseDao.queryForObject(sql, SQLUtil.createList(pd, "sales_id"));
		pd.setData(map);
		return pd;
	}
	/**
	 * @description: 新增或者修改 
	 * @author Simple
	 * @param pd
	 * @return
	 * 2018年1月4日下午5:26:59
	 */
	public boolean saveProductSales(PageData pd){
		if(pd.getString("sales_id").length()>0){
			String sql = "update t_product_sales set product_id=?,sales_mark=?,sales_type=?,sales_pic=?,sales_starttime=?,sales_endtime=? where sales_id=?";
			int result = baseDao.excuteUpdate(sql, pd, "product_id,sales_mark,sales_type,sales_pic,sales_starttime,sales_endtime,sales_id");
			return result>0;
		}else{
			String sql = "insert into t_product_sales(product_id,sales_mark,sales_type,sales_pic,sales_starttime,sales_endtime)"
	+"VALUES(?,?,?,?,?,?)";
			int result = baseDao.excuteUpdate(sql, pd, "product_id,sales_mark,sales_type,sales_pic,sales_starttime,sales_endtime");
			return result>0;
		}
	}
	/**
	 * @description: 如果商品已经在营销信息中,则返回true 
	 * @author Simple
	 * @param pd
	 * @return
	 * 2017年12月29日下午7:26:53
	 */
	public boolean isSalesExits(PageData pd){
		String colName1 = pd.getString("colName1");
		String colName2 = pd.getString("colName2");
		String sql = "select count(1) from t_product_sales where "+colName1+" = ? and "+colName2+" = ?";
		int result = baseDao.getTotal(sql,SQLUtil.createList(pd, "colValue1","colValue2"));
		return result>0;
	}
	public List<Map<String,Object>> getSalesList(PageData pd){
		String sql = "SELECT * FROM t_product_sales WHERE sales_starttime<=NOW() AND sales_endtime>=NOW() AND del_flag=0 and sales_type=?";
		ArrayList<Object> params = new ArrayList<Object>();
		params.add(pd.get("type"));
		return baseDao.queryForList(sql, params);
	}//
	public List<Map<String,Object>> getSalesProductList(PageData pd){
		String sql = "SELECT * FROM t_product_info info WHERE EXISTS( SELECT product_id FROM t_product_sales sales WHERE sales_starttime<=NOW() AND sales_endtime>=NOW() AND del_flag=0 and sales_type=? and sales.product_id=info.product_id)";
		ArrayList<Object> params = new ArrayList<Object>();
		params.add(pd.get("type"));
		return baseDao.queryForList(sql, params);
	}
}
