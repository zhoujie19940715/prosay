package com.prosay.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import com.prosay.core.BaseController;
import com.prosay.core.CustomView;
import com.prosay.core.ReturnType;
import com.prosay.core.annotation.Autowired;
import com.prosay.core.annotation.Controller;
import com.prosay.core.annotation.RequestMapping;
import com.prosay.core.annotation.ResponseBody;
import com.prosay.core.entity.PageData;
import com.prosay.service.AdminService;
import com.prosay.util.Md5Util;
import com.prosay.util.SystemUtil;
import com.prosay.util.ValidateCodeUtil;
/**
 * 
* @ClassName: AdminController 
* @Description: 商城后台的控制器 
* @author Jame 
* @date 2017年12月20日 下午8:41:08 
*
 */
@Controller
@RequestMapping("/admin")
public class AdminController extends BaseController{
	@Autowired("adminService")
	private AdminService adminService;
	/**
	 * 
	* @Title: login 
	* @Description:进入登陆界面
	* @param @return    设定文件 
	* @return CustomView    返回类型 
	* @throws
	 */
	@RequestMapping("/login")
	public CustomView login(){
		log.debug("进入登陆界面");
		return new CustomView("admin/login");
	}
	/**
	 * 
	* @Title: doLogin 
	* @Description: 校验登陆
	* @param @return    设定文件 
	* @return CustomView    返回类型 
	* @throws
	 */
	@RequestMapping("/doLogin")
	public CustomView doLogin(){
		log.debug("进行登陆校验");
		PageData pd = this.getPageData();
		String username = pd.getString("username");
		String password =pd.getString("password");
		String code = pd.getString("code");
		
		
		CustomView view = null;
		Object codetmp = getSessionAttr("code");
		if(null==codetmp){
			view =  new CustomView("admin/login");
			return view;
		}
		String codeInSession = codetmp.toString();
		if(!code.equalsIgnoreCase(codeInSession)){
			put("username",username);
			put("password",password);
			put("msg","验证码填写错误！");
			view =  new CustomView("admin/login");
			return view;
		}
		Map<String,Object> user = adminService.doLogin(username, Md5Util.encodeMd5(username+SystemUtil.USERENCODE+password));
		if(user.isEmpty()){
			put("username",username);
			put("password",password);
			put("msg","用户名或者密码错误！");
			view =  new CustomView("admin/login");
		}else{
			SystemUtil.setAdmin(getReq(), user);
			view = new CustomView("admin/index");
		}
		log.debug("登陆校验结束");
		return view;
	}
	@RequestMapping("/logout")
	public CustomView logout(){
		getReq().getSession().invalidate();
		return new CustomView("admin/login");
	}
	
	@RequestMapping("/code")
	@ResponseBody
	public void getCode(){
		BufferedImage img = ValidateCodeUtil.getValidateCode(getReq());
		OutputStream out ;
		try {
			out = getRep().getOutputStream();
			ImageIO.write(img, "JPEG",out);
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.error("验证码声称失败",e.getCause());
		}
	}
	@RequestMapping("/validateCode")
	@ResponseBody
	public void validateCode(){
		PageData pd = this.getPageData();
		String code = pd.getString("code");
		String codeInSession = this.getSessionAttr("code").toString();
		boolean result = false;
		if(code.equalsIgnoreCase(codeInSession)){
			result = true;
		}
		writeToResponse(result+"");
	}
}
