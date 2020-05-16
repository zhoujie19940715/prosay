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
import com.prosay.service.ProductService;
import com.prosay.util.SystemUtil;

/**
 * 
* @ClassName: ProductController 
* @Description: 商品管理的控制器
* @author Jame 
* @date 2017年12月25日 下午8:40:30 
*
 */
@Controller
@RequestMapping("/product")
@Power(SystemUtil.USERADMIN)
public class ProductController extends BaseController{
	@Autowired("productService")
	private ProductService productService;
	@RequestMapping("/list")
	@Power(SystemUtil.USERALL)
	public CustomView list(){
		CustomView cv = new CustomView("product/list");
		PageData pd= this.getPageData();//前台界面提交的参数
		Page<PageData> page = new Page<PageData>();
		page.setPd(pd);//将前台参数提交进去
		page = productService.queryProducts(page);
		put("page", page);
		return cv;
	}

	@RequestMapping("/productAdd")
	public CustomView productAdd(){
		CustomView cv = new CustomView("product/edit");
		//put("types",productService.queryProductType());
		return cv;
	}
	@RequestMapping("/productEdit")
	public CustomView productEdit(){
		CustomView cv = new CustomView("product/edit");
		PageData pd = this.getPageData();//必须传递ID
		pd = productService.getPageById(pd);
		put("pd",pd);
		return cv;
	}
	@RequestMapping("/queryType")
	@Power(SystemUtil.USERNON)
	public CustomView queryType(){
		PageData pd = this.getPageData();
		List<Map<String,Object>> results = productService.queryProductType(pd);
		ReturnContent content = new ReturnContent(results);
		CustomView cv = new CustomView(content);
		return cv;
	}
	@RequestMapping("/delProduct")
	@ResponseBody
	public void delProduct(){
		PageData pd = this.getPageData();
		try{
			boolean result = productService.delProductById(pd);
			
			if(result){
				this.writeToResponse("sucess|删除成功！");
			}else{
				this.writeToResponse("error|删除失败！");
			}
		}catch(Exception ex){
			log.error(ex.getCause());
			this.writeToResponse("error|删除失败！");
		}
	}
	@RequestMapping("/batchDel")
	@ResponseBody
	public void batchDel(){
		PageData pd = this.getPageData();
		try{
			boolean result = productService.delProductByIds(pd);
			
			if(result){
				this.writeToResponse("sucess|删除成功！");
			}else{
				this.writeToResponse("error|删除失败！");
			}
		}catch(Exception ex){
			log.error(ex.getCause());
			this.writeToResponse("error|删除失败！");
		}
	}
	@RequestMapping("/editProduct")
	public CustomView editProduct(){
		PageData pd= this.getPageData();
		CustomView cv = null;
		try{
			boolean result = productService.saveProduct(pd);
			if(result){
				if(pd.getString("product_id").length()>0){
					put("isClose","true");
				}
				put("msg","保存成功！！");
				cv = new CustomView("product/edit");
			}else{
				put("msg","保存失败！！");
				put("pd",pd);
				cv = new CustomView("product/edit");
			}
		}catch(Exception ex){
			put("msg","保存失败！！"+ex.getMessage());
			log.error(ex.getCause());
			put("pd",pd);
			cv = new CustomView("product/edit");
		}
		return cv;
	}
	
	/**
	 * 列表展示
	 * @return
	 */
	@RequestMapping("/type")
	public CustomView type(){
		PageData pd = this.getPageData();
		Page<PageData> page = new Page<PageData>();
		page.setPd(pd);
		page = productService.queryProductTypeList(page);
		put("page", page);
		return new CustomView("/product/type");
	}
	
	@RequestMapping("/typeAdd")
	public CustomView typetAdd(){
		CustomView cv = new CustomView("product/type_edit");
		//put("types",productService.queryProductType());
		return cv;
	}
	
	@RequestMapping("/typeEdit")
	public CustomView editType(){
		PageData pd= this.getPageData();
		CustomView cv = null;
		try{
			boolean result = productService.saveType(pd);
			if(result){
				if(pd.getString("type_id").length()>0){
					put("isClose","true");
				}
				put("msg","保存成功！！");
			}else{
				put("msg","保存失败！！");
				put("pd",pd);
			}
		}catch(Exception ex){
			put("msg","保存失败！！"+ex.getMessage());
			log.error(ex.getCause());
			put("pd",pd);
		}
		cv = new CustomView("product/type_edit");
		return cv;
	}
	
	@RequestMapping("/typeEditShow")
	public CustomView typeEditShow(){
		CustomView cv = new CustomView("product/type_edit");
		PageData pd = this.getPageData();//必须传递ID
		pd = productService.getTypeById(pd);
		put("pd",pd);
		return cv;
	}
	
	@RequestMapping("/delType")
	@ResponseBody
	public void delType(){
		PageData pd = this.getPageData();
		try{
			boolean result = productService.delTypeById(pd);
			
			if(result){
				this.writeToResponse("sucess|删除成功！");
			}else{
				this.writeToResponse("error|删除失败！");
			}
		}catch(Exception ex){
			log.error(ex.getCause());
			this.writeToResponse("error|删除失败！");
		}
	}
	
	/**
	 * 批量删除
	 */
	
	@RequestMapping("/batchTypeDel")
	@ResponseBody
	public void batchTypeDel(){
		PageData pd = this.getPageData();
		try{
			boolean result = productService.delTypeByIds(pd);
			
			if(result){
				this.writeToResponse("sucess|删除成功！");
			}else{
				this.writeToResponse("error|删除失败！");
			}
		}catch(Exception ex){
			log.error(ex.getCause());
			this.writeToResponse("error|删除失败！");
		}
	}
}
