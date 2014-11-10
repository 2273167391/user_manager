package com.tenghu.user_manager.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;

public class StringUtil {
	private StringUtil(){}
	
	/**
	 * MD5加密
	 * @param needStr 需要加密的字符串
	 * @return
	 */
	public static String md5Encryption(String needStr){
		MessageDigest md=null;
		String encryStr=null;
		try {
			md=MessageDigest.getInstance("MD5");
			//进行SHA-1加密
			byte[] bytes=md.digest(needStr.getBytes());
			encryStr=byteToStr(bytes);//将字节数组转为字符串
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return encryStr;
	}
	
	/**
	 * sha—1加密
	 * @param waitEncryption 需要加密的字符串
	 * @return
	 */
	public static String sha1Encryption(String needStr){
		MessageDigest md=null;
		String encryStr=null;
		try {
			md=MessageDigest.getInstance("SHA-1");
			//进行SHA-1加密
			byte[] bytes=md.digest(needStr.getBytes());
			encryStr=byteToStr(bytes);//将字节数组转为字符串
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return encryStr;
	}
	
	/**
	 * 将字节数组转为十六进制字符串
	 * @param bytes
	 * @return
	 */
	public static String byteToStr(byte[] bytes){
		StringBuffer sb=new StringBuffer();
		for (byte b : bytes) {
			sb.append(byteToHexStr(b));
		}
		return sb.toString().toLowerCase();
	}
	
	/**
	 * 将字节码转为16进制字符串
	 * @param mByte
	 * @return
	 */
	public static String byteToHexStr(byte mByte){
		char[] digit={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
		char[] tmpArr=new char[2];
		tmpArr[0]=digit[(mByte>>>4)&0X0F];
		tmpArr[1]=digit[mByte&0X0F];
		return new String(tmpArr).toString().toLowerCase();
	}
	
	/**
	 * 获取项目全路径
	 * @param httpRequest
	 * @return
	 */
	public static String getContextPath(HttpServletRequest httpRequest){
		StringBuffer sb=new StringBuffer("http://");
		sb.append(httpRequest.getRemoteHost());
		sb.append(":").append(httpRequest.getServerPort());
		sb.append(httpRequest.getContextPath());
		return sb.toString();
	}
	
	/**
	 * 判断字符串是否为空
	 * @param str
	 * @return
	 */
	public static boolean isNUll(String str){
		if(null==str||str.toString().equals("")){
			return true;
		}
		return false;
	}
}
