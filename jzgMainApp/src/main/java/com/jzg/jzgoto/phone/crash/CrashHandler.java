
package com.jzg.jzgoto.phone.crash;

import java.lang.Thread.UncaughtExceptionHandler;
import android.content.Context;


public class CrashHandler implements UncaughtExceptionHandler {

	private Thread.UncaughtExceptionHandler mDefaultHandler;
	private static CrashHandler mInstance;
	private Context mContext;
	public  static final String LINE_ENTER = "\r\n";

	private CrashHandler() {

	}

	public static CrashHandler getInstance() {		
		if (mInstance == null)
			mInstance = new CrashHandler();
		return mInstance;
	}

	@Override
	public void uncaughtException(Thread thread, Throwable throwable) {
		
		if (!handleException(throwable) && mDefaultHandler != null) {
			mDefaultHandler.uncaughtException(thread, throwable);
		}else{
			android.os.Process.killProcess(android.os.Process.myPid());
			System.exit(0);
		}
	}

	private boolean handleException(final Throwable ex) {
		if (ex == null) {
			return true;
		}
		
		try {
			LogFileUtil util = new LogFileUtil(mContext);
			util.writeJavaLog(ex);
		} catch (Exception e) {
		}

		return true;
	}
	
	public void init(Context context){
		if(context==null)return;
		mContext = context;
		mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
		Thread.setDefaultUncaughtExceptionHandler(this);
	}

}