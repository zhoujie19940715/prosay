package com.prosay.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Encoder;

/**
 * 
* @ClassName: Md5Util 
* @Description: MD5密码加密
* @author Jame 
* @date 2017年12月22日 下午10:26:47 
*
 */
public class Md5Util {

	public static String encodeMd5(String str){
		MessageDigest md5;
		String result = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
			byte[] strResult = md5.digest(str.getBytes());
			BASE64Encoder encoder = new BASE64Encoder();
			result = new String(encoder.encode(strResult));
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	public static void main(String [] args){
		encodeMd5("jame[prosay]123456");
	}
}
