package com.prosay.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.prosay.core.BaseController;
import com.prosay.core.CustomView;
import com.prosay.core.annotation.Autowired;
import com.prosay.core.annotation.Controller;
import com.prosay.core.annotation.Power;
import com.prosay.core.annotation.RequestMapping;
import com.prosay.core.annotation.ResponseBody;
import com.prosay.core.entity.PageData;
import com.prosay.service.OrderService;
import com.prosay.util.AlipayConfig;
import com.prosay.util.SystemUtil;

/**
 * 
* @ClassName: OrderController 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author Jame 
* @date 2018年1月19日 下午8:40:12 
*
 */
@Controller
@RequestMapping("/order")
@Power(SystemUtil.USERNORMAL)
public class OrderController extends BaseController{
	@Autowired("orderService")
	private OrderService orderService;
	@RequestMapping("/goOrder")
	public CustomView goOrder(){
		//从会话中（session）拿到当前登陆的用户
		Map<String,Object> user = (Map<String,Object>)this.getSessionAttr(SystemUtil.USERNORMAL);
		PageData pd = new PageData();
		pd.setData(user);
		CustomView cv = new CustomView("front/order/orderInfo");
		//生成数据
		List<PageData> products = orderService.queryCartProductInfo(pd);
		List<PageData> address  = orderService.queryAddresssInfo(pd);
		put("products",products);
		put("address",address);
		return cv;
	}
	@RequestMapping("/doOrder")
	@ResponseBody
	public void doOrder(){
		try {
			//从会话中（session）拿到当前登陆的用户
			Map<String,Object> user = (Map<String,Object>)this.getSessionAttr(SystemUtil.USERNORMAL);
			PageData pd =this.getPageData();
			pd.put("vip_id",user.get("vip_id"));
			pd.put("vip_name",user.get("vip_name"));
			pd.put("vip_tel",user.get("vip_tel"));
			//生成数据
			boolean result = orderService.genOrder(pd);
			if(!result){
				put("errorMsg","订单生成失败！！！");
			}
			Map<String,Object> orderInfo = orderService.getOrderInfo(pd);
			//查询数据
			put("orderInfo",orderInfo);
			//获得初始化的AlipayClient
			AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
			HttpServletRequest request = this.getReq();
			//设置请求参数
			AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
			alipayRequest.setReturnUrl(AlipayConfig.return_url);
			alipayRequest.setNotifyUrl(AlipayConfig.notify_url);
			
			//商户订单号，商户网站订单系统中唯一订单号，必填
			String out_trade_no;
	
			out_trade_no = new String(pd.getString("order_no"));
		
		//付款金额，必填
		String total_amount = new String(pd.getString("total_money"));
		//订单名称，必填
		String subject = "猿商购物支付"+pd.getString("order_no");
		//商品描述，可空
		String body = "";
		
		alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\"," 
				+ "\"total_amount\":\""+ total_amount +"\"," 
				+ "\"subject\":\""+ subject +"\"," 
				+ "\"body\":\""+ body +"\"," 
				+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
		System.out.println(alipayRequest.getBizContent());
		//若想给BizContent增加其他可选请求参数，以增加自定义超时时间参数timeout_express来举例说明
		//alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\"," 
		//		+ "\"total_amount\":\""+ total_amount +"\"," 
		//		+ "\"subject\":\""+ subject +"\"," 
		//		+ "\"body\":\""+ body +"\"," 
		//		+ "\"timeout_express\":\"10m\"," 
		//		+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
		//请求参数可查阅【电脑网站支付的API文档-alipay.trade.page.pay-请求参数】章节
		
		//请求
		String html;
	
			html = alipayClient.pageExecute(alipayRequest).getBody();
			this.getRep().setContentType("text/html;charset=utf-8");
			//输出
			this.writeToResponse(html);
		} catch (AlipayApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
}
