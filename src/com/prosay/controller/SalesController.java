package com.prosay.controller;

import java.util.List;
import java.util.Map;

import com.prosay.core.BaseController;
import com.prosay.core.CustomView;
import com.prosay.core.ReturnContent;
import com.prosay.core.annotation.Autowired;
import com.prosay.core.annotation.Controller;
import com.prosay.core.annotation.Power;
import com.prosay.core.annotation.RequestMapping;
import com.prosay.core.annotation.ResponseBody;
import com.prosay.core.entity.Page;
import com.prosay.core.entity.PageData;
import com.prosay.service.ProductSalesService;
import com.prosay.service.ProductService;
import com.prosay.util.SystemUtil;

/**
 * @description: 营销控制器 
 * @author Simple
 * 2018年1月3日下午11:52:22
 */
@Controller
@RequestMapping("/sales")
@Power(SystemUtil.USERADMIN)
public class SalesController extends BaseController{
	@Autowired("productSalesService")
	private ProductSalesService productSalesService;
	/**
	 * @description: 获取营销列表 
	 * @author Simple
	 * @return
	 * 2018年1月3日下午11:58:36
	 */
	@RequestMapping("/list")
	public CustomView list(){
		CustomView cv = new CustomView("sales/list");
		PageData pd= this.getPageData();//前台界面提交的参数
		Page<PageData> page = new Page<PageData>();
		page.setPd(pd);//将前台参数提交进去
		page = productSalesService.querySalesList(page);
		put("page", page);
		return cv;
	}
	/**
	 * @description: 增加 
	 * @author Simple
	 * @return
	 * 2018年1月3日下午11:59:10
	 */
	@RequestMapping("/productSalesAdd")
	public CustomView productSalesAdd(){
		CustomView cv = new CustomView("sales/edit");
		return cv;
	}
	/**
	 * @description: 修改 
	 * @author Simple
	 * @return
	 * 2018年1月3日下午11:59:19
	 */
	@RequestMapping("/productSalesEdit")
	public CustomView productSalesEdit(){
		CustomView cv = new CustomView("sales/edit");
		PageData pd = this.getPageData();//必须传递ID
 		pd = productSalesService.getPageById(pd);
		put("pd",pd);
		return cv;
	}
	/**
	 * @description: 删除单个 
	 * @author Simple
	 * 2018年1月3日下午11:59:38
	 */
	@RequestMapping("/delProductSales")
	@ResponseBody
	public void delProductaSales(){
		PageData pd = this.getPageData();
		try{
			boolean result = productSalesService.delProductById(pd);
			
			if(result){
				this.writeToResponse("success|删除成功！");
			}else{
				this.writeToResponse("error|删除失败！");
			}
		}catch(Exception ex){
			log.error(ex.getCause());
			this.writeToResponse("error|删除失败！");
		}
	}
	/**
	 * @description: 删除多个 
	 * @author Simple
	 * 2018年1月3日下午11:59:50
	 */
	@RequestMapping("/batchDelSales")
	@ResponseBody
	public void batchDelSales(){
		PageData pd = this.getPageData();
		try{
			boolean result = productSalesService.delProductSalesByIds(pd);
			if(result){
				this.writeToResponse("success|删除成功！");
			}else{
				this.writeToResponse("error|删除失败！");
			}
		}catch(Exception ex){
			log.error(ex.getCause());
			this.writeToResponse("error|删除失败！");
		}
	}
	@RequestMapping("/editProductSales")
	public CustomView editProductSales(){
		PageData pd= this.getPageData();
		CustomView cv = null;
		try{
			boolean result = productSalesService.saveProductSales(pd);
			if(result){
				if(pd.getString("sales_id").length()>0){
					put("isClose","true");
				}
				put("msg","保存成功！！");
				cv = new CustomView("sales/edit");
			}else{
				put("msg","保存失败！！");
				put("pd",pd);
				cv = new CustomView("sales/edit");
			}
		}catch(Exception ex){
			put("msg","保存失败！！"+ex.getMessage());
			log.error(ex.getCause());
			put("pd",pd);
			cv = new CustomView("sales/edit");
		}
		return cv;
	}
}

