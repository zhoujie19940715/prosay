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
import com.prosay.service.VipService;
import com.prosay.util.SystemUtil;

/**
 * @description: vip会员管理控制器 
 * @author Simple
 * 2018年1月4日下午5:34:48
 */
@Controller
@RequestMapping("/admin")
@Power(SystemUtil.USERADMIN)
public class VipController extends BaseController{
	@Autowired("vipService")
	private VipService vipService;
	@RequestMapping("/list")
	public CustomView list(){
		CustomView cv = new CustomView("vip/list");
		PageData pd= this.getPageData();
		Page<PageData> page = new Page<PageData>();
		page.setPd(pd);
		page = vipService.queryVipList(page);
		put("page", page);
		return cv;
	}
	@RequestMapping("/vipAdd")
	public CustomView vipAdd(){
		CustomView cv = new CustomView("vip/edit");
		return cv;
	}
	@RequestMapping("/vipEdit")
	public CustomView vipEdit(){
		CustomView cv = new CustomView("vip/edit");
		PageData pd = this.getPageData();
		pd = vipService.getPageById(pd);
		put("pd",pd);
		return cv;
	}
	@RequestMapping("/delVip")
	@ResponseBody
	public void delVip(){
		PageData pd = this.getPageData();
		try{
			boolean result = vipService.delVipById(pd);
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
	@RequestMapping("/batchDelVip")
	@ResponseBody
	public void batchDelVip(){
		PageData pd = this.getPageData();
		try{
			boolean result = vipService.delVipByIds(pd);
			
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
	
	@RequestMapping("/editVip")
	public CustomView editVip(){
		PageData pd= this.getPageData();
		CustomView cv = null;
		try{
			boolean result = vipService.saveVip(pd);
			if(result){
				if(pd.getString("vip_id").length()>0){
					put("isClose","true");
				}
				put("msg","保存成功！！");
				cv = new CustomView("vip/edit");
			}else{
				put("msg","保存失败！！");
				put("pd",pd);
				cv = new CustomView("vip/edit");
			}
		}catch(Exception ex){
			put("msg","保存失败！！"+ex.getMessage());
			log.error(ex.getCause());
			put("pd",pd);
			cv = new CustomView("vip/edit");
		}
		return cv;
	}
}
