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
import com.prosay.util.Md5Util;
import com.prosay.util.SQLUtil;
@Bean("vipService")
public class VipService {
	@Autowired("baseDao")
	private BaseDao baseDao;
	/**
	 * @description: 查询会员列表 
	 * @author Simple
	 * @param page
	 * @return
	 * 2018年1月4日下午5:37:05
	 */
	//SELECT vip_name,vip_account,vip_password,vip_sex,vip_realname,vip_tel,vip_birthday,vip_level,vip_score,vip_email,vip_regtime,vip_loginip,vip_logintime FROM t_vip_info WHERE vip_id=1;
	public Page<PageData> queryVipList(Page<PageData> page){
		String sql = "select vip_id,vip_name,vip_account,vip_password,vip_sex,vip_realname,vip_tel,vip_birthday,vip_level,vip_score,vip_email,vip_regtime,vip_loginip,vip_logintime from t_vip_info ";
		PageData pd = page.getPd();
		String condition=" where del_flag=0 ";
		List<Object> params =new  ArrayList<Object>();
		if(pd.hasKey("vip_name")){
			condition+=" and vip_name like ?";
			params.add("%"+pd.get("vip_name")+"%");
		}
		if(pd.hasKey("vip_starttime")){
			condition+=" and vip_logintime >= ?";
			params.add(pd.get("vip_starttime"));
		}
		if(pd.hasKey("vip_endtime")){
			condition+=" and vip_logintime < ?";
			params.add(pd.get("vip_endtime"));
		}
		int total = baseDao.getTotal("select count(1) from t_vip_info "+condition, params);
		page.setTotal(total);
		List<PageData> result = baseDao.queryForPage(sql+condition+page.getPageSql(), params);
		page.setData(result);
		return page;
	}	
	/**
	 * @description: 删除vip(多) 
	 * @author Simple
	 * @param pd
	 * @return
	 * 2018年1月4日下午6:04:19
	 */
	public boolean delVipByIds(PageData pd){
		String ids = pd.getString("ids");
		String[] id = ids.split(",");
		List params = new ArrayList();
		String sql = "update t_vip_info set del_flag = 1 where vip_id in (";
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
	public boolean delVipById(PageData pd){
		String sql = "update t_vip_info set del_flag = 1 where vip_id = ?";
		int result = baseDao.excuteUpdate(sql, pd, "vip_id");
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
		String sql = "select * from t_vip_info where vip_id = ?";
		Map<String, Object> map = baseDao.queryForObject(sql, SQLUtil.createList(pd, "vip_id"));
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
	public boolean saveVip(PageData pd){
		if(pd.getString("vip_id").length()>0){
			String sql = "update t_vip_info set vip_name=?,vip_account=?,vip_password=?,vip_sex=?,vip_realname=?,vip_tel=?,vip_birthday=?,vip_level=?,vip_score=?,vip_email=? where vip_id=?";
			int result = baseDao.excuteUpdate(sql, pd, "vip_name,vip_account,vip_password,vip_sex,vip_realname,vip_tel,vip_birthday,vip_level,vip_score,vip_email,vip_id");
			return result>0;
		}
		return false;
	}
	/**
	 * 
	* @Title: checkAccount 
	* @Description: 校验帐号是否存在
	* @param @param pd
	* @param @return    设定文件 
	* @return boolean    返回类型 
	* @throws
	 */
	public boolean checkAccount(PageData pd){
		String account = pd.getString("value");
		boolean result = true;
		ArrayList<Object> params = new ArrayList<Object>();
		params.add(account);
		int rows = baseDao.getTotal("select count(1) from t_vip_info WHERE vip_account=?", params);
		if(rows>0){
			result = false;
		}
		return result;
	}
	/**
	 * 
	* @Title: saveUser 
	* @Description: 保存VIP用户的注册信息
	* @param @param pd
	* @param @return    设定文件 
	* @return boolean    返回类型 
	* @throws
	 */
	public boolean saveUser(PageData pd){
		boolean returnValue = true;
		String sql = "INSERT INTO t_vip_info(vip_name,vip_account,vip_password,vip_sex,vip_realname,vip_tel,vip_birthday,vip_email,vip_regtime,vip_header)VALUES(?,?,?,?,?,?,?,?,now(),?);";
		String vip_password = pd.getString("vip_password");
		vip_password = Md5Util.encodeMd5(pd.getString("vip_account")+vip_password);
		pd.put("vip_password", vip_password);
		int result = baseDao.excuteUpdate(sql, pd, "vip_name,vip_account,vip_password,vip_sex,vip_realname,vip_tel,vip_birthday,vip_email,vip_header");
		if(result==0){
			returnValue =false;
		}
		return returnValue;
	}
	/**
	 * 
	* @Title: doLogin 
	* @Description: 用户登陆服务
	* @param @param pd
	* @param @return    设定文件 
	* @return PageData    返回类型 
	* @throws
	 */
	public Map<String,Object> doLogin(PageData pd){
		String sql = "select * from t_vip_info where vip_account=? and vip_password=?";
		List<Object> params = new ArrayList<Object>();
		params.add(pd.getString("userName"));
		params.add(Md5Util.encodeMd5(pd.getString("userName")+pd.getString("password")));
		Map<String,Object> user = baseDao.queryForObject(sql, params);
		return user;
	}
}
