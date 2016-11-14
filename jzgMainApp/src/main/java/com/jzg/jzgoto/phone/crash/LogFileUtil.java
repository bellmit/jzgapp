
package com.jzg.jzgoto.phone.crash;

import java.io.File;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Environment;
import android.text.format.DateFormat;


public class LogFileUtil {

	public interface WriteLogListener {
		public void onSucceed();
		public void onFailure();
	}

	private static final SimpleDateFormat dataFormat = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	private static final SimpleDateFormat todayFormat = new SimpleDateFormat(
			"yyyyMMdd");
	private static final String LINE_ENTER = "\r\n";
	
	private Context mContext = null;
	private PackageInfo info = null;
	private String javaLogPath = null;


	private FilenameFilter logFileFilter = new FilenameFilter() {
		
		@Override
		public boolean accept(File dir, String filename) {
			return filename.endsWith(".log");
		}
	};
	
	public LogFileUtil(Context context) {
		this.mContext = context;

		try {
			info = mContext.getPackageManager().getPackageInfo(
					mContext.getPackageName(), 0);
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		if (sdcardExist()) {
			javaLogPath = getLogPath() + "/jzg/crash/log/";
			checkFolder();
		}
	}

	private boolean sdcardExist(){
		return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
	}
	
	private String getLogPath(){
		return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED) ? Environment.getExternalStorageDirectory().toString() : "";
	}

	private void checkFolder() {
		boolean r = false;
		try {
			File folder = new File(javaLogPath);
			if (!folder.exists()) {
				r = folder.mkdirs();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void writeJavaLogAsyn(final Throwable ex, final WriteLogListener listener) {
		if (ex == null) {
			return;
		}
		new Thread(new Runnable() {

			@Override
			public void run() {
				boolean result = writeJavaLog(ex);
				if (listener != null) {
					if (result) {
						listener.onSucceed();
					} else {
						listener.onFailure();
					}
				}
			}
		}).start();
	}

	public boolean writeJavaLog(final Throwable ex) {
		String logName = getLogName(javaLogPath);
		if (logName == null) {
			return false;
		}
		String filePath = javaLogPath + logName;
		try {
			File file = new File(filePath);  
		    if (file.isFile() && file.exists()) {  
		        file.delete();
		    }
		        
			writeToLog(filePath, ex);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	private String getLogName(String path) {
		String filePath = "crash" + DateFormat.format("yyyy-MM-dd-hh-mm-ss", new Date()) + ".txt";
		return filePath;
	}

	private void wirteLogHead(FileWriter writer) throws IOException {
		writer.write(dataFormat.format(new Date()));
		writer.write(LINE_ENTER);
		
		if (info != null) {
			writer.write("VersionName : " + info.versionName);
			writer.write(LINE_ENTER);
			writer.write("VersionCode : " + info.versionCode);
			writer.write(LINE_ENTER);
		}
		
		writer.write("Manufacturer : " + android.os.Build.MANUFACTURER);
		writer.write(LINE_ENTER);
		writer.write("Model : " + android.os.Build.MODEL);
		writer.write(LINE_ENTER);
		writer.write("AndroidVersion : " + android.os.Build.VERSION.RELEASE);
		writer.write(LINE_ENTER);
		writer.write("SDKVersion : " + android.os.Build.VERSION.SDK);
		writer.write(LINE_ENTER);
		writer.write(LINE_ENTER);
	}
	
	public void writeToLog(String filePath, Throwable ex) throws IOException {
		FileWriter writer = new FileWriter(filePath, false);
		PrintWriter pWriter = new PrintWriter(writer);
		wirteLogHead(writer);
		writer.write(LINE_ENTER);
		ex.printStackTrace(pWriter);
		writer.write(LINE_ENTER);
		writer.close();
		pWriter.close();
	}

	public void writeToLog(String filePath, String context) throws IOException {
		FileWriter writer = new FileWriter(filePath, false);
		wirteLogHead(writer);
		writer.write(LINE_ENTER);
		writer.write(context);
		writer.write(LINE_ENTER);
		writer.close();
	}
}
