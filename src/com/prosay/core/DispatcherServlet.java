package com.prosay.core;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.log4j.Logger;

import com.prosay.core.annotation.Power;
import com.prosay.core.annotation.ResponseBody;
import com.prosay.core.util.Contants;
import com.prosay.util.SystemUtil;

/**
 * 
* @ClassName: DispatcherServlet 
* @Description: MVC框架的核心控制器 
* @author Jame 
* @date 2017年12月18日 下午9:02:12 
*
 */
@MultipartConfig
public class DispatcherServlet extends HttpServlet {
	private Logger logger = Logger.getLogger(DispatcherServlet.class);
	//框架容器对象实例
	private BeanContext context;
	public void init(){
		//从应用上下文中拿到Bean容器
		context =  (BeanContext)this.getServletContext().getAttribute(Contants.CONTEXT_NAME);
			
	}
	public void service(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("utf-8");
		SystemUtil.initBasePath(request);	
		//拿到请求的路径
		//System.out.println("request.getContextPath():"+request.getContextPath());
		//System.out.println("request.getRequestURI():"+request.getRequestURI());
		//System.out.println("request.getRequestURL():"+request.getRequestURL());
		// /prosay  or /
	
		String contextPath = request.getContextPath();
		///prosay/front/index.do
		String uri = request.getRequestURI();
		if(uri.equals(contextPath+"/")){
			uri = uri+"front/index.do";
		}
		String mappingPath = uri.substring(uri.indexOf(contextPath)+contextPath.length(),uri.indexOf(".do"));
		/**
		 * 1.通过映射路径去获取Method 然后要反射调用
		 * 2.反射调用离不开对象实例，所以要获取控制器的实例 
		 * 3.然后通过反射调用
		 */
		//通过映射路径获取对应的方法
		Method method = context.getExecMethod(mappingPath);//这一步相当于handlerMapping
		if(method==null){
			logger.error("404：请求的路径未映射到MVC框架!path:"+mappingPath);
			request.getRequestDispatcher("/404.jsp").forward(request, response);
			return;
		}
		String powerResult = checkPower(method,request.getSession());
		if(powerResult!=null){
			logger.info("没有权限访问，即将跳转到对应的界面!"+powerResult);
			request.setAttribute("url", powerResult);
			request.getRequestDispatcher("/power.jsp").forward(request, response);
			return;
		}
		BaseController controller = context.getController(method.getDeclaringClass().getName());
		try {
			if(controller instanceof Uploadable){
				List<Part> files = new ArrayList<Part>();
				List<String> fileNames = new ArrayList<String>();
				for(Part file :request.getParts()){
					files.add(file);
					fileNames.add(file.getName());
				}
				((Uploadable)controller).setFileNames(fileNames);
				((Uploadable)controller).setFileParts(files);
			}
				//初始化传递请求对象和响应对象
				controller.init(request, response);
				if(method.isAnnotationPresent(ResponseBody.class)){
					method.invoke(controller);
				}else{
					//执行方法
					CustomView view = (CustomView)method.invoke(controller);
					ReturnType type = view.getReturnType();
					ReturnContent content = view.getContent();
					switch (type) {
					case FORWORD:
						request.getRequestDispatcher(Contants.VIEW_PREFIX+content.getUrl()+Contants.VIEW_SUFIX).forward(request, response);
						break;
					case JSON:
						//从数据库查出数据，没有乱码，但是返回前端中文乱码，增加编码格式
						response.setContentType("text/text;charset=UTF-8");   
						PrintWriter writer = response.getWriter();
						writer.print(content.getJson());
						writer.close();
						break;
					case REDIRECT:
						//这里只考虑重定向到内部资源
						response.sendRedirect(request.getServletContext().getAttribute("basePath").toString()+content.getUrl());
						break;
					case FORWORDCHAIN:
						request.getRequestDispatcher(contextPath+"/"+content.getUrl()).forward(request,response);
						break;
					default:
						break;
					}
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 
	* @Title: checkPower 
	* @Description: 检测是否具备访问权限
	* @param @param method
	* @param @param session
	* @param @return    设定文件 
	* @return boolean    返回类型 
	* @throws
	 */
	private String checkPower(Method method,HttpSession session){
		String result = null;
		Power power = method.getDeclaringClass().getAnnotation(Power.class);
		//类上面设置了权限的时候
		if(power!=null){
			String value = power.value();
			//只需要session中存在就ok user or admin
			if(SystemUtil.USERALL.equals(value)){
				Map<String,Object> obj = (Map<String,Object>)session.getAttribute(SystemUtil.USERADMIN);
				if(obj==null){
					obj = (Map<String,Object>)session.getAttribute(SystemUtil.USERNORMAL);
					if(obj==null){
						//vip登陆界面
						result = SystemUtil.USERLOGIN;
					}
				}
			}else{
				//他是管理员 还是vip 
				Map<String,Object> obj = (Map<String,Object>)session.getAttribute(value);
				if(null==obj){//说明不具备权限
					if(value.equals(SystemUtil.USERADMIN)){
						result = SystemUtil.ADMINLOGIN;//管理员的登陆路径
					}else{
						result = SystemUtil.USERLOGIN;//普通用户的登陆路径（VIP用户）
					}
				}
			}
		}
		//从类 然后从method
		power = method.getAnnotation(Power.class);
		if(power!=null){
			String value = power.value();
			//匿名访问
			if(value.equals(SystemUtil.USERNON)){
				return null;
			}
			//只需要session中存在就ok user or admin
			if(SystemUtil.USERALL.equals(value)){
				Map<String,Object> obj = (Map<String,Object>)session.getAttribute(SystemUtil.USERADMIN);
				if(obj==null){
					obj = (Map<String,Object>)session.getAttribute(SystemUtil.USERNORMAL);
					if(obj==null){
						//vip登陆界面
						result = SystemUtil.USERLOGIN;
					}else{
						result = null;//因为前面的类处理部分会修改result的值
					}
				}
			}else{
				//他是管理员 还是vip 
				Map<String,Object> obj = (Map<String,Object>)session.getAttribute(value);
				if(null==obj){//说明不具备权限
					if(value.equals(SystemUtil.USERADMIN)){
						result = SystemUtil.ADMINLOGIN;//管理员的登陆路径
					}else{
						result = SystemUtil.USERLOGIN;//普通用户的登陆路径（VIP用户）
					}
				}else{
					result = null;//因为前面的类处理部分会修改result的值
				}
			}
		}
		return result;
	}
}
