package com.prosay.controller;

import java.util.List;
import java.util.Map;

import com.prosay.core.BaseController;
import com.prosay.core.CustomView;
import com.prosay.core.ReturnContent;
import com.prosay.core.annotation.Autowired;
import com.prosay.core.annotation.Controller;
import com.prosay.core.annotation.RequestMapping;
import com.prosay.core.annotation.ResponseBody;
import com.prosay.core.entity.Page;
import com.prosay.core.entity.PageData;
import com.prosay.service.CodeAreaService;

@Controller
@RequestMapping("/codearea")
public class CodeAreaController extends BaseController{
	
	@Autowired(value = "codeAreaService")
	private CodeAreaService codeAreaService;
	
	@RequestMapping("/list")
	public CustomView list(){
		CustomView cv = new CustomView("codearea/list");
		PageData pd = this.getPageData();
		Page<PageData> page = new Page<PageData>();
		page.setPd(pd);
		
		page = codeAreaService.list(page);
		put("page", page);
		return cv;
	}
	@RequestMapping("/areaAdd")
	public CustomView add(){
		CustomView cv = new CustomView("codearea/edit");		
		return cv;
	}
	@RequestMapping("/areaEdit")
	public CustomView edit(){
		PageData pd = this.getPageData();//必须传递ID
		pd = codeAreaService.getPageByAreaId(pd);
		put("pd",pd);
		CustomView cv = new CustomView("codearea/edit");		
		return cv;
	}
	@RequestMapping("/editArea")
	public CustomView editArea(){
		PageData pd= this.getPageData();
		CustomView cv = null;
		try{
			boolean result = codeAreaService.saveArea(pd);
			if(result){
				if(pd.getString("area_id").length()>0){
					put("isClose","true");
				}
				put("msg","保存成功！！");
				cv = new CustomView("codearea/edit");
			}else{
				put("msg","保存失败！！");
				put("pd",pd);
				cv = new CustomView("codearea/edit");
			}
		}catch(Exception ex){
			put("msg","保存失败！！"+ex.getMessage());
			log.error(ex.getCause());
			put("pd",pd);
			cv = new CustomView("codearea/edit");
		}
		return cv;
	}
	@RequestMapping("/delArea")
	@ResponseBody
	public void del(){
		PageData pd = this.getPageData();
		boolean result = codeAreaService.delByAreaId(pd);
		if(result){
			this.writeToResponse("sucess|删除成功！");
		}else{
			this.writeToResponse("error|删除失败！");
		}
		
	}
	
	@RequestMapping("/queryArea")
	public CustomView queryArea(){
		PageData pd = this.getPageData();
		List<Map<String,Object>> results = codeAreaService.queryArea(pd);
		ReturnContent content = new ReturnContent(results);
		CustomView cv = new CustomView(content);
		return cv;
	}
}
