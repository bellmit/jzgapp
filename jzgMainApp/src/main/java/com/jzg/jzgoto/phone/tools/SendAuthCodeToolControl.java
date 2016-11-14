package com.jzg.jzgoto.phone.tools;

import java.util.HashMap;
import java.util.Map;

import android.os.CountDownTimer;

/**
 * @Description: 发送验证码控制类
 * @Package com.jzg.jzgoto.phone.tools SendAuthCodeToolControl.java
 * @author gf
 * @date 2015-12-23 上午10:51:27
 */
public class SendAuthCodeToolControl {
	
	private static final Map<String, SelfCountDownTimer> mCountDownTimes = new HashMap<String, SelfCountDownTimer>();
	private static final Map<String, TimerCallBack> mCountDownCallback = new HashMap<String, TimerCallBack>();
	
	public static SelfCountDownTimer newCountDownTimes(final String phoneNum ,final TimerCallBack callback){
		clearCallback();
		mCountDownCallback.put(phoneNum, callback);
		if(mCountDownTimes.get(phoneNum)!=null){
			return mCountDownTimes.get(phoneNum);
		}
		SelfCountDownTimer mCountDownTime = new SelfCountDownTimer(62*1000, 1000) {
			@Override
			public void onTick(long millisUntilFinished) {
				if(mCountDownCallback.get(phoneNum)!=null)
				mCountDownCallback.get(phoneNum).onTick(Integer.toString(mMaxNum));
				if(mMaxNum!=0){
					mMaxNum--;
				}
			}
			@Override
			public void onFinish() {
				mMaxNum = 60;
				if(mCountDownCallback.get(phoneNum)!=null)
					mCountDownCallback.get(phoneNum).onFinish();
				mCountDownTimes.remove(phoneNum);
				mCountDownCallback.remove(phoneNum);
			}
		};
		mCountDownTime.start();
		mCountDownTimes.put(phoneNum, mCountDownTime);
		return mCountDownTime;
	}
	public static SelfCountDownTimer getCountDownTimer(String phoneNum){
		return mCountDownTimes.get(phoneNum);
	}
	public static TimerCallBack getCallback(String phoneNum){
		if(mCountDownCallback.get(phoneNum)!=null){
			return mCountDownCallback.get(phoneNum);
		}
		return null;
	}
	public static void clearCallback(){
		mCountDownCallback.clear();
	}
	public static void setCallback(final String phoneNum ,final TimerCallBack callback){
		mCountDownCallback.put(phoneNum, callback);
	}
	public static abstract class SelfCountDownTimer extends CountDownTimer{
		public SelfCountDownTimer(long millisInFuture, long countDownInterval) {
			super(millisInFuture, countDownInterval);
		}
		int mMaxNum = 60;
		public int getMaxNum(){
			return mMaxNum;
		}
	}
	public interface TimerCallBack{
		public void onTick(String time);
		public void onFinish();
	}
}
