package com.prosay.controller;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
/**
 * 前台业务处理类
 */
import com.prosay.core.BaseController;
import com.prosay.core.CustomView;
import com.prosay.core.annotation.Autowired;
import com.prosay.core.annotation.Controller;
import com.prosay.core.annotation.RequestMapping;
import com.prosay.core.annotation.ResponseBody;
import com.prosay.core.entity.PageData;
import com.prosay.service.FrontService;
import com.prosay.service.ProductService;
@Controller
public class FrontBussinessController extends BaseController{
	@Autowired("frontService")
	private FrontService frontService;
	@Autowired("productService")
	private ProductService productService;
	@RequestMapping("/productList")
	public CustomView productList(){
		CustomView cv = new CustomView("front/productList");
		PageData pd = this.getPageData();
		List<PageData> types = frontService.querySecondType(pd);
		//直接拿
		put("pname",types.get(0).get("pname"));
		List<PageData> products = frontService.queryProductList(pd);
		//request中的param是无法通过el表达式取到
		put("typeId",pd.get("typeId"));
		put("sTypeId",pd.get("sTypeId"));
		/**
		 * 1.一级类型ID
		 * 2.二级类型ID
		 */
		put("types",types);
		put("products",products);
		return cv;
	}
	@RequestMapping("/getDescription")
	@ResponseBody
	public void getDescription() {
		PageData pd = this.getPageData();
		productService.updateLookAmount(pd);
		PageData results = productService.getPageById(pd);
		Iterator iterator = results.keyIterator();
		Map<String,Object> map = new HashMap<String,Object>();
		while(iterator.hasNext()) {
			String key = (String) iterator.next();
			map.put(key, results.get(key));
		}
		this.writeToResponse(JSON.toJSONString(map));
	}
}
