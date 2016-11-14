/**
 * Project Name:JZGPingGuShi
 * File Name:StringUtil.java
 * Package Name:com.gc.jzgpinggushi.uitls
 * Date:2014-10-8下午2:18:50
 * Copyright (c) 2014, wangyd523@gmail.com All Rights Reserved.
 *
 */

package com.jzg.jzgoto.phone.tools;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Calendar;

import android.text.TextUtils;
import android.util.Log;


public class StringUtil
{
	
	
	
	public static void log(String tag,String msg){
		Log.d(tag, msg);
	}
	

	public static String getStringByUrlUTF8(String str)
			throws UnsupportedEncodingException
	{
		return URLEncoder.encode(str, "utf-8");
	}

	
	
	/**
	 * 2015-12  --> 2015
	 */
	public static int getYear4String(String yearStr) {
		int year = 0;
		try {
			if ("".equals(yearStr) || null == yearStr) {
				year = 0;
			} else {
				if (yearStr.indexOf("-") != -1) {
					year = Integer.parseInt(yearStr.substring(0,
							yearStr.indexOf("-")));
				} else {
					year = 0;
				}
			}
		} catch (Exception e) {
			year = 0;
		}
		return year;
	}
	/**
	 * 2015-03  --> 3
	 */
	public static int getMonth4String(String yearStr) {
		int year = 0;
		try {
			if ("".equals(yearStr) || null == yearStr) {
				year = 0;
			} else {
				if (yearStr.indexOf("-") != -1) {
					year = Integer.parseInt(yearStr.substring(yearStr.indexOf("-")+1));
				} else {
					year = 0;
				}
			}
		} catch (Exception e) {
			year = 0;
		}
		return year;
	}
	
	
	/**
	 * 北京市------》北京
	  * returnShi
	  *
	  * @Title: returnShi
	  * @Description: TODO
	  * @param @param shi
	  * @param @return    
	  * @return String
	  * @throws
	 */
	public static String returnShi(String shi){
		
		if(shi == null || "".equals(shi)){
			return "";
		}else{
			if(shi.indexOf("市")!=-1){
				return shi.substring(0,shi.indexOf("市"));
			}else{
				return shi;
			}
		}
		
		
	}

	/**
	 *
	 * @param regDate	2014年09月
	 * @return 上牌月数
     */
	public static int getMonthFromRegDate(String regDate){
		int months = 0;
		if(!TextUtils.isEmpty(regDate)&&regDate.length()>=8){
			int	year = Integer.parseInt(regDate.substring(0,
					regDate.indexOf("年")));
			int monthOfYear = Integer.parseInt(regDate.substring(regDate.indexOf("年")+1,(regDate.length()-1)));
			int thisYear = Calendar.getInstance().get(Calendar.YEAR);
			int thisMonth = Calendar.getInstance().get(Calendar.MONTH) + 1;
			months = (thisYear - year) * 12 + (thisMonth - monthOfYear);
		}
		return months;
	}
}
