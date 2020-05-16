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
import com.prosay.service.ProductSalesService;
@Controller
@RequestMapping("/front")
public class FrontController extends BaseController{
	@Autowired("productSalesService")
	private ProductSalesService productSalesService;
	@RequestMapping("/index")
	public CustomView index(){
		CustomView cv = new CustomView("front/index");
		//新品推荐
		PageData pd = this.getPageData();
		pd.put("type",0);
		List<Map<String,Object>> results = productSalesService.getSalesProductList(pd);
		put("newSales",results);		
		//促销商品
		pd.put("type",1);
		results = productSalesService.getSalesProductList(pd);
		put("quickSales",results);
		//热销商品
		pd.put("type",2);
		results = productSalesService.getSalesProductList(pd);
		put("hotSales",results);
		return cv;
	}
	@RequestMapping("/getBanner")
	@ResponseBody
	public void getBanner(){
		PageData pd = this.getPageData();
		pd.put("type",4);
		List<Map<String,Object>> results = productSalesService.getSalesList(pd);
		this.writeToResponse(JSON.toJSONString(results));
	}
}
