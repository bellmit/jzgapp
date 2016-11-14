package com.jzg.jzgoto.phone.services.contentprovider;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.content.Context;
import android.os.Environment;

/**
 * @Description: 从数据库查询
 * @Package com.jzg.jzgoto.phone.services.contentprovider CarListTool.java
 * @author gf
 * @date 2016-6-12 下午4:46:32
 */
public abstract class GetDataFromDBToolBase {
	private final String DATABASE_NAME = "jzg.db";
	private final int DATABASE_VERSION = 1;
	public DBOpenHelper mDBOpenHelper = null;
	public void initDb(Context context){
		if(mDBOpenHelper == null)
		mDBOpenHelper = DBOpenHelper.getInstance(context, DATABASE_NAME, null, DATABASE_VERSION, null);
	}
	
	private static final String DATABASE_NAME_SELF = "car_style.db";
	private final int DATABASE_VERSION_SELF = 1;
	public static String getSelfDatabaseName(){
		return DATABASE_NAME_SELF;
	}
	public SelfDbOpenHelper mSelfDbOpenHelper = null;
	public void initSelfDb(Context context){
		if(mSelfDbOpenHelper == null)
			mSelfDbOpenHelper = SelfDbOpenHelper.getInstance(context, DATABASE_NAME_SELF, null, DATABASE_VERSION_SELF, null);
	}

	public String getSelfDataPath(){
		String dbPath = Environment.getExternalStorageDirectory().getAbsolutePath();
		File file = new File(dbPath + "/jzg/db/");
		if(!file.exists()){
			file.mkdirs();
		}
		File dbSd = new File(file, "jzg.db");
		if(!dbSd.exists()){
			try {
				dbSd.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return dbSd.getAbsolutePath();
	}
	
	public void copyDbToSd(File db,File sdFile){
		if(!db.exists())return;
		FileInputStream input = null;
		try {
			input = new FileInputStream(db);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		if(null == input)return;
		
		FileOutputStream output = null;
		try {
			output = new FileOutputStream(sdFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		if( null == output)return;
		
		byte[] buffer = new byte[1024];
		int length = 0;
		try {
			while((length = input.read(buffer)) > 0){
				output.write(buffer, 0, length);
			}
			output.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			try {
				input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				output.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
}
