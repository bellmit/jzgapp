package com.jzg.jzgoto.phone.tools;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;

/**
 * @Description: 点击控制类
 * @Package com.jzg.jzgoto.phone.tools ClickControlTool.java
 * @author gf
 * @date 2015-12-11 下午2:53:37
 */
public class ClickControlTool {
	private static long mLastClickTime = -1L;
	/**
	 * 返回true为可以点击。false为不可以点击
	 * @return
	 */
	public static final boolean isCanToClick(){
		long nowTime = System.currentTimeMillis();
		if(nowTime-mLastClickTime > 1000L){
			mLastClickTime = nowTime;
			return true;
		}
		return false;
	}
	/**
	 * 返回true为可以点击。false为不可以点击    间隔为 500 
	 * @return
	 */
	public static final boolean isCanToClickFor500(){
		long nowTime = System.currentTimeMillis();
		if(nowTime-mLastClickTime > 500L){
			mLastClickTime = nowTime;
			return true;
		}
		return false;
	}
	public static final boolean isCanToClickFor200(){
		long nowTime = System.currentTimeMillis();
		if(nowTime-mLastClickTime > 200L){
			mLastClickTime = nowTime;
			return true;
		}
		return false;
	}
	
	public synchronized static final String getUserIp(Context context){
		if(context == null)return null;
		WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
		if(wifiManager.isWifiEnabled()){
			int ipAddress = wifiManager.getConnectionInfo().getIpAddress();
			return (ipAddress & 0xFF ) + "." +
            ((ipAddress >> 8 ) & 0xFF) + "." +
            ((ipAddress >> 16 ) & 0xFF) + "." +
            ( ipAddress >> 24 & 0xFF) ;
		}
		ConnectivityManager connectivityMana = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    	NetworkInfo netIn = connectivityMana.getActiveNetworkInfo();
    	if(netIn==null){
    		return null;
    	}
		String ipAddress = null;
		try {
			Enumeration<NetworkInterface> networkInterface = NetworkInterface.getNetworkInterfaces();
			while(networkInterface.hasMoreElements()){
				NetworkInterface network = networkInterface.nextElement();
				Enumeration<InetAddress> address = network.getInetAddresses();
				while(address.hasMoreElements()){
					InetAddress inetAddress = address.nextElement();
					if(!inetAddress.isLoopbackAddress()){
						ipAddress = inetAddress.getHostAddress();
					}
				}
			}
		} catch (SocketException e) {
			e.printStackTrace();
		}
		return ipAddress;
	}
	public synchronized static final boolean checkStringIsChina(String str){
		final char[] chars = str.toCharArray();
		for(char ch:chars){
			if(!checkCharIsChina(ch)){
				return false;
			}
		}
		return true;
	}
	private synchronized static final boolean checkCharIsChina(char c){
		final Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
		if(ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS||
				ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS||
				ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A||
				ub == Character.UnicodeBlock.GENERAL_PUNCTUATION||
				ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION||
				ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS){
			return true;
		}
		
		return false;
	}
}
