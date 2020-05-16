package com.prosay.util.mail;

import java.security.Security;
import java.util.Properties;

public class MailInfo {

	public Properties getPropertes(){
		 Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());  
         final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";          
		Properties p = new Properties();
		p.put("mail.smtp.host","smtp.qq.com");
		 p.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
		p.put("mail.smtp.port","465");
		p.put("mail.smtp.auth","true");
		return p;
	}
}
