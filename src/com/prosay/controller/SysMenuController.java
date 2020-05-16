package com.prosay.controller;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.prosay.core.BaseController;
import com.prosay.core.CustomView;
import com.prosay.core.ReturnContent;
import com.prosay.core.annotation.Autowired;
import com.prosay.core.annotation.Controller;
import com.prosay.core.annotation.RequestMapping;
import com.prosay.core.entity.PageData;
import com.prosay.service.MenuService;
import com.prosay.util.SystemUtil;

@Controller
@RequestMapping("/menu")
public class SysMenuController extends BaseController {
	@Autowired("menuService")
	private MenuService menuService;
	@RequestMapping("/getData")
	public CustomView getMenuData(){
		PageData pd = this.getPageData();
		String userId = pd.getString("userId");
		String bathPath = SystemUtil.getBasePath(this.getReq());
		List<Map<String,Object>> menulist = menuService.getMenuData(userId,bathPath);
		ReturnContent rc = new ReturnContent(menulist);
		CustomView cv = new CustomView(rc);
		return cv;
	}
	@RequestMapping("/list")
	public CustomView getMenuList(){
		PageData pd = this.getPageData();
		String userId = pd.getString("userId");
		List<Map<String,Object>> menulist = menuService.getMenuData(userId);
		String menulistStr="";
		if(menulist!=null&&menulist.size()>0){
			menulistStr=JSON.toJSONString(menulist); 
		}
		put("menulist",menulistStr);
		CustomView cv =  new CustomView("menu/menuIndex");
		return cv;
	}
	@RequestMapping("/info")
	public CustomView getMenuInfo(){
		return null;
	}
	@RequestMapping("/save")
	public CustomView saveMenu(){
		return null;
	}
	@RequestMapping("/add")
	public CustomView addMenu(){
		return null;
	}
}
