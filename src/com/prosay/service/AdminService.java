package com.prosay.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.prosay.core.annotation.Autowired;
import com.prosay.core.annotation.Bean;
/**
 * 
* @ClassName: AdminService 
* @Description:管理员相关服务类
* @author Jame 
* @date 2017年12月20日 下午10:02:21 
*
 */
import com.prosay.db.BaseDao;
@Bean("adminService")
public class AdminService {
	@Autowired("baseDao")
	private BaseDao baseDao;
	/**
	 *
	* @Title: doLogin 
	* @Description: 用户登陆
	* @param @param username
	* @param @param password
	* @param @return    设定文件 
	* @return Map<String,Object>    返回类型 
	* @throws
	 */
	public Map<String, Object> doLogin(String username,String password){
		List<Object> params = new ArrayList<Object>();
		params.add(username);
		params.add(password);
		 Map<String, Object> user = baseDao.queryForObject("select admin_id,admin_name,admin_account,admin_pwd from t_sys_admin where admin_account=? and admin_pwd=?",params );
		 return user;
		
	}
}
