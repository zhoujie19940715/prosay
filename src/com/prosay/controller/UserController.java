package com.prosay.controller;

import java.util.Map;

import com.prosay.core.BaseController;
import com.prosay.core.CustomView;
import com.prosay.core.ReturnContent;
import com.prosay.core.ReturnType;
import com.prosay.core.annotation.Autowired;
import com.prosay.core.annotation.Controller;
import com.prosay.core.annotation.RequestMapping;
import com.prosay.core.annotation.ResponseBody;
import com.prosay.core.entity.PageData;
import com.prosay.service.VipService;
import com.prosay.util.SystemUtil;
import com.prosay.util.ValidateCodeUtil;
import com.prosay.util.mail.MailUtil;
import com.sun.org.apache.bcel.internal.generic.PUTSTATIC;

/**
 * 
* @ClassName: UserController 
* @Description: VIP用户的控制器
* @author Jame 
* @date 2018年1月6日 下午8:33:46 
*
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController{
	@Autowired("vipService")
	private VipService vipService;
	@RequestMapping("/reg")
	public CustomView reg(){
		return new CustomView("front/user/reg");
	}
	//提供前台验证框架的后台验证接口约定
	//提交的参数名固定 value , 返回的内容{result:true or false}
	@RequestMapping("/checkAccount")
	@ResponseBody
	public void checkAccount(){
		PageData pd = this.getPageData();
		boolean result = vipService.checkAccount(pd);
		this.writeToResponse("{\"result\":"+result+"}");
	}
	@RequestMapping("/sendEmailCode")
	@ResponseBody
	public void sendEmailCode(){
		PageData pd = this.getPageData();
		String email = pd.getString("email");
		boolean result = MailUtil.sendTextEmail(email, "[猿商]注册校验",getEmailCode());
		this.writeToResponse("{\"result\":"+result+"}");
	}
	@RequestMapping("/checkEmailCode")
	@ResponseBody
	public void checkEmailCode(){
		PageData pd = this.getPageData();
		String code = pd.getString("value");
		boolean result = true;
		Object sessionCode = getSessionAttr("usercode");
		if(null==sessionCode || !sessionCode.toString().equalsIgnoreCase(code)){
			result = false;
		}
		this.writeToResponse("{\"result\":"+result+"}");
	}
	@RequestMapping("/saveUser")
	public CustomView saveUser(){
		PageData pd = this.getPageData();
		CustomView cv = null;
		Object sessionCode = getSessionAttr("usercode");
		
		try{
			if(!pd.getString("validateCode").equalsIgnoreCase(sessionCode.toString())){
				cv = new CustomView("front/user/reg");
				this.put("pd",pd);
				this.put("errorMsg", "注册失败！邮箱验证码错误！");
			}
					
		boolean result = vipService.saveUser(pd);
			if(result){
				cv = new CustomView("front/index");
				this.put("regMsg", "注册成功！请登录！");
			}else{
				cv = new CustomView("front/user/reg");
				this.put("pd",pd);
				this.put("errorMsg", "注册失败！");
			}
		}catch(Exception ex){
			log.error(ex.getStackTrace());
			cv = new CustomView("front/user/reg");
			this.put("pd",pd);
			this.put("errorMsg", "注册失败！");
		}
		return cv;
	}
	@RequestMapping("/goLogin")
	public CustomView goLogin(){
		CustomView cv = new CustomView("front/user/login");
		return cv;
	}
	@RequestMapping("/logout")
	public CustomView logout(){
		CustomView cv = new CustomView(new ReturnContent("/front/index.do"),ReturnType.REDIRECT);
		this.getReq().getSession().invalidate();
		return cv;
	}
	@RequestMapping("/login")
	@ResponseBody
	public void login(){
		PageData pd = this.getPageData();
		Map<String,Object> user = vipService.doLogin(pd);
		if(user.size()>0){
			this.setSessionAttr(SystemUtil.USERNORMAL, user);
			this.writeToResponse("{\"result\":true,\"headerImg\":\""+user.get("vip_header").toString()+"\",\"userName\":\""+user.get("vip_name")+"\"}");
		}else{
			this.writeToResponse("{\"result\":false}");
		}
		
	}
	private String getEmailCode(){
		String code = ValidateCodeUtil.getCodeString(this.getReq());
		String emailContent = "您好，感谢您注册我们的商城，本次的注册验证码为"+code;
		return emailContent;
	}
}
