package com.prosay.util.mail;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
/**
 * 
* @ClassName: MailUtil 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author Jame 
* @date 2018年1月6日 下午10:12:55 
*
 */
public class MailUtil {

	public static void main(String [] args) throws Exception{
		MailAuth auth = new MailAuth("79196052@qq.com","atibsjlwgepocbch");
		MailInfo mailInfo = new MailInfo();
		Session session = Session.getDefaultInstance(mailInfo.getPropertes(),auth);
		//创建消息实体
		Message message = new MimeMessage(session);
		Address from = new InternetAddress("79196052@qq.com","Jame老师");
		message.setFrom(from);
		Address to = new InternetAddress("79196052@qq.com");
		message.setRecipient(Message.RecipientType.TO, to);
		message.setSubject("Ar同学");
		message.setSentDate(Calendar.getInstance().getTime());
		message.setText("Jame老师，你真帅，我爱死你了！！");
		Transport.send(message);
		
		
	}
	/**
	 * 
	* @Title: sendEmail 
	* @Description: 发送文本邮件
	* @param     设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public static boolean sendTextEmail(String email,String subject,String content){
		boolean result = true;
		MailAuth auth = new MailAuth("79196052@qq.com","atibsjlwgepocbch");
		MailInfo mailInfo = new MailInfo();
		Session session = Session.getDefaultInstance(mailInfo.getPropertes(),auth);
		//创建消息实体
		Message message = new MimeMessage(session);
		Address from;
		try {
			from = new InternetAddress("79196052@qq.com","猿商");
			message.setFrom(from);
			Address to = new InternetAddress(email);
			message.setRecipient(Message.RecipientType.TO, to);
			message.setSubject(subject);
			message.setSentDate(Calendar.getInstance().getTime());
			message.setText(content);
			Transport.send(message);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = false;
		}
		return result;
	}
}
