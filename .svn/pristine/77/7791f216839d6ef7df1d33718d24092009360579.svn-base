package com.prosay.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.prosay.core.annotation.Autowired;
import com.prosay.core.annotation.Bean;
import com.prosay.db.BaseDao;

@Bean("menuService")
public class MenuService {
	
	@Autowired("baseDao")
	private BaseDao baseDao;
	
	public List<Map<String,Object>> getMenuData(String userId,String basePath){
		List<Map<String,Object>> menulist = getMenuTree("0", 0,basePath);
		return menulist;
	}
	public List<Map<String,Object>> getMenuData(String userId){
		List<Map<String,Object>> menulist = getMenuTree("0", 0,"");
		return menulist;
	}
	private List<Map<String,Object>> getMenuTree(String parentId,int cycleCount,String basePath){
		List<Map<String,Object>> menulist = null;
		//只支持3级菜单（防止递归死循环）
		if(cycleCount<4){
			String sql = "select a.id,a.parent_id as parentId,a.text,a.is_header as isHeader,a.is_open as isOpen,a.icon,a.url,a.target_type as targetType "
					+ "from t_sys_menu a "
					+ "where a.del_flag ='0' and a.parent_id =? "
					+ "order by a.sort";
			List<Object> params = new ArrayList<Object>();
			params.add(parentId);
			menulist = baseDao.queryForList(sql, params);
			if(menulist!=null&&menulist.size()>0){
				cycleCount++;
				for (Map<String, Object> map : menulist) {
					if("1".equals(map.get("isHeader"))){
						map.put("isHeader", true);
					}else{
						map.put("isHeader", false);
					}
					if("1".equals(map.get("isOpen"))){
						map.put("isOpen", true);
					}else{
						map.put("isOpen", false);
					}
					if(map.get("url")!=null&&!"".equals(map.get("url"))&&basePath!=null){
						map.put("url", basePath+map.get("url").toString());
					}
					String id = map.get("id").toString();
					List<Map<String,Object>> childlist = getMenuTree(id,cycleCount,basePath);
					if(childlist!=null&&childlist.size()>0){
						map.put("children", childlist);
					}
				}
			}
		}
		return menulist;
	}
}
