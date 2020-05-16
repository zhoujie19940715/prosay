package com.prosay.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.prosay.core.annotation.Autowired;
import com.prosay.core.annotation.Bean;
import com.prosay.core.entity.Page;
import com.prosay.core.entity.PageData;
import com.prosay.db.BaseDao;

@Bean("codeAreaService")
public class CodeAreaService {

	@Autowired(value = "baseDao")
	private BaseDao baseDao;

	public Page<PageData> list(Page<PageData> page) {
		PageData pd =page.getPd();
		
		String sql = "select * from t_code_area where del_flag = 0";
		String condition="";
		List<Object> params =new  ArrayList<Object>();
		if(pd.hasKey("area_name")){
			condition+=" and area_name like ?";
			params.add("%"+pd.get("area_name")+"%");
		}
		String countSql = "select count(1) from t_code_area where del_flag = 0 ";
		List<PageData>  list = baseDao.queryForPage(sql+condition+page.getPageSql(), params);
		int count = baseDao.getTotal(countSql+condition, params);
		page.setTotal(count);
		page.setData(list);
		return page;
	}

	public boolean saveArea(PageData pd) {
		boolean result = false;
		String delsql = "delete from t_code_area where area_id=?";
		baseDao.excuteUpdate(delsql, pd, "area_id_bak");
		String sql = "INSERT INTO t_code_area(area_id,parent_id,area_name,area_sort)VALUES(?,?,?,?)";
		int r = baseDao.excuteUpdate(sql, pd, "area_id,parent_id,area_name,area_sort");
		if(r>0){
			result = true;
		}
		return result;
		
	}

	public PageData getPageByAreaId(PageData pd) {		
		String sql = "select area_id, parent_id,area_name,area_sort from t_code_area where area_id=?";
		ArrayList<Object> params = new ArrayList<Object>();
		params.add(pd.getString("area_id"));
		Map<String,Object> obj = baseDao.queryForObject(sql, params);
		pd.setData(obj);
		return pd;
	}

	public boolean delByAreaId(PageData pd) {
		String delSql = "delete from t_code_area where area_id=?";
		ArrayList<Object> params = new ArrayList<Object>();
		params.add(pd.getString("area_id"));
		int r = baseDao.excuteUpdate(delSql, params);
		return r==1;
	}

	public List<Map<String, Object>> queryArea(PageData pd) {
		String sql="SELECT area_id,area_name from t_code_area where del_flag=0 and parent_id=?";
		ArrayList<Object> param = new ArrayList<Object>();
		param.add(pd.getString("parent_id"));
		List<Map<String,Object>> results = baseDao.queryForList(sql, param);
		return results;
	}
}
