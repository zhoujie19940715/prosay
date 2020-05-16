package com.prosay.util.mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
/**
 * 
* @ClassName: MailAuth 
* @Description: 邮箱认证的实体类
* @author Jame 
* @date 2018年1月6日 下午10:23:04 
*
 */
public class MailAuth extends Authenticator{
	String userName = null;
	String password = null;
	public MailAuth(){
		
	}
	public MailAuth(String userName,String password){
		this.userName = userName;
		this.password = password;
	}
	protected PasswordAuthentication getPasswordAuthentication() {
			return  new PasswordAuthentication(userName, password);
	 } 
}
