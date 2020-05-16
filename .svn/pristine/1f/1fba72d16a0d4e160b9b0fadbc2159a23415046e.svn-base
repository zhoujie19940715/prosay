package com.prosay.controller;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.prosay.core.BaseController;
import com.prosay.core.CustomView;
import com.prosay.core.annotation.Autowired;
import com.prosay.core.annotation.Controller;
import com.prosay.core.annotation.RequestMapping;
import com.prosay.core.annotation.ResponseBody;
import com.prosay.core.entity.PageData;
import com.prosay.service.CarService;
import com.prosay.util.SystemUtil;
@Controller
@RequestMapping("/car")
public class CarController extends BaseController{
	@Autowired("carService")
	private CarService carService;
	@RequestMapping("/carList")
	@ResponseBody
	public void carList(){
		PageData pd = this.getPageData();
		Map<String,Object> user = (Map<String,Object>)this.getSessionAttr(SystemUtil.USERNORMAL);
		
		if(user==null||user.size()==0){
			return;
		}else{
			pd.put("vip_id",user.get("vip_id").toString());
			List<Map<String,Object>> products = carService.queryCarList(pd);
			this.writeToResponse(JSON.toJSONString(products));
		}
	}
	@RequestMapping("/add")
	@ResponseBody
	public void add(){
		PageData pd = this.getPageData();
		Map<String,Object> user = (Map<String,Object>)this.getSessionAttr(SystemUtil.USERNORMAL);
		if(user != null){
			pd.put("vip_id",user.get("vip_id").toString());
		}
		boolean result = carService.saveProductToCar(pd);
		this.writeToResponse(result+"");
	}
	@RequestMapping("/delete")
	@ResponseBody
	public void delete(){
		PageData pd = this.getPageData();
		Map<String,Object> user = (Map<String,Object>)this.getSessionAttr(SystemUtil.USERNORMAL);
		if(user != null){
			pd.put("vip_id",user.get("vip_id").toString());
		}
		boolean result = carService.delProductToCar(pd);
		this.writeToResponse(result+"");
	}
}
