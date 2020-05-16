package com.prosay.util;
/**
 * 
* @ClassName: ValidateCodeUtil 
* @Description:用来生成验证码
* @author Jame 
* @date 2017年12月22日 下午9:04:27 
*
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

public class ValidateCodeUtil {
	private static Random random = new Random();
	private static final String CODE = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	/**
	 * 
	* @Title: getCodeString 
	* @Description: 拿到随即字符串 
	* @param @param request
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	public static String getCodeString(HttpServletRequest request){
		/*生成随即字符串*/
		String code = "";
		char  tmp;
		for(int i = 0 ; i < 4 ;i++){
			tmp = CODE.charAt(random.nextInt(CODE.length()));
			code+= tmp;
			
		}
		//验证码的值存储到当前会话中
		request.getSession().setAttribute("usercode", code);
		return code;
	}
	/**
	 * 
	* @Title: getValidateCode 
	* @Description: 获取一个验证码图片的对象并且验证码的值会存储在session中 
	* @param @param request
	* @param @return    设定文件 
	* @return BufferedImage    返回类型 
	* @throws
	 */
	public static BufferedImage getValidateCode(HttpServletRequest request){
		int width = 120,height = 36;
		BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		/*
		 * 1.验证码的背景色
		 * 2.验证码的干扰线
		 * */
		Graphics2D grap = img.createGraphics();
		//设置画笔的颜色
		grap.setColor(getRandColor(180,255));
		//使用填充的方式，将整个背景填充为这个颜色
		grap.fillRect(0, 0, width, height);
		//产生随即干扰线
		for(int i = 0 ; i < 40 ;i++){
			//设置线条的颜色
			grap.setColor(getRandColor(150,170));
			int x1 = random.nextInt(width);
			int y1 = random.nextInt(height);
			int x2 = random.nextInt(width);
			int y2 = random.nextInt(height);
			grap.drawLine(x1, y1, x2, y2);
		}
		/*生成随即字符串*/
		String code = "";
		char  tmp;
		int x = 10;
		grap.setFont(new Font("微软雅黑",Font.BOLD,25));
		for(int i = 0 ; i < 4 ;i++){
			tmp = CODE.charAt(random.nextInt(CODE.length()));
			code+= tmp;
			grap.setColor(getRandColor(50,140));
			double degree = random.nextInt()%45*Math.PI/180;
			System.out.println(degree);
			grap.rotate(degree,x,30);
			grap.drawString(tmp+"", x, 30);
			grap.rotate(-degree,x,30);
			x+=30;
			
		}
		//验证码的值存储到当前会话中
		request.getSession().setAttribute("code", code);
		return img;
	}
	public static void main(String [] args){
		//getValidateCode();
	}
	/**
	 * 
	* @Title: getRandColor 
	* @Description: 根据区间获取随机颜色
	* @param @param start
	* @param @param end
	* @param @return    设定文件 
	* @return Color    返回类型 
	* @throws
	 */
	private static Color getRandColor(int start,int end){
		//100 - 200 100 +0-99
		int red = start + random.nextInt(end-start);
		int green = start + random.nextInt(end-start);
		int blue = start + random.nextInt(end-start);
		return new Color(red,green,blue);
	}
}
