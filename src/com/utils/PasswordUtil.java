package com.utils;

import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 *Created by pxc on 2017年2月25日 下午12:43:01
 */

public class PasswordUtil {

	public static String EncoderByMd5(String str){
        //确定计算方法
        MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Base64 base64en = new Base64();
        //加密后的字符串
        byte[] newstr = null;
		try {
			newstr = base64en.encode(md5.digest(str.getBytes("utf-8")));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return new String(newstr);
    }
	
	 /*public static void main(String args[]) {
	        
	        System.out.println( PasswordUtil.EncoderByMd5("123"));
	    }
*/}
