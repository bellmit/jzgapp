package com.jzg.jzgoto.phone.tools;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import android.provider.MediaStore;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.text.format.DateFormat;

public class FileUtil {

    //private static String SDCARD_FOLDER_CACHE = Environment.getExternalStorageDirectory() + "/Android/data/%s/files/";
    private static String SDCARD_FOLDER_CACHE = Environment.getExternalStorageDirectory() + "/%s/files/";

    public static File getTempFile(Context context) {
        final File path = getAppCacheDir(context);
        if (!path.exists()) {
            path.mkdirs();
        }
        File f = new File(path,
                String.valueOf(DateFormat.format("yyyy-MM-dd-hh-mm-ss", new Date()) + "_temp_image.png"));
        if (!f.exists()) {
            try {
                f.createNewFile();
            } catch (IOException e) {
            }
        }
        return f;
    }
    
    public static File getTempImageFile(Context context, String fileType) {
        final File path = getAppCacheDir(context);
        if (!path.exists()) {
            path.mkdirs();
        }
        String ext = "";
        if (fileType.toLowerCase().endsWith("jpg")){
        	ext = ".jpg";
        }else if (fileType.toLowerCase().endsWith("png")){
        	ext = ".png";
        }
        	
        File f = new File(path,
                String.valueOf("image" + System.currentTimeMillis() + ext));
        if (!f.exists()) {
            try {
                f.createNewFile();
            } catch (IOException e) {
            }
        }
        return f;
    }

    public static String getTempImageFilePath(Context context, String fileType) {
        String path = String.format(SDCARD_FOLDER_CACHE, context.getPackageName());
        String ext = "";
        if (fileType.toLowerCase().endsWith("jpg")){
            ext = ".jpg";
        }else if (fileType.toLowerCase().endsWith("png")){
            ext = ".png";
        }
        path += ("image" + System.currentTimeMillis() + ext);
        return path;
    }

    private static File getAppCacheDir(Context context) {
        return new File(String.format(SDCARD_FOLDER_CACHE, context.getPackageName()));
    }

    public static void copyFile(String oldPath, String newPath) {
        try {
            int bytesum = 0;
            int byteread = 0;
            File oldfile = new File(oldPath);
            if (oldfile.exists()) { //文件存在时
                InputStream inStream = new FileInputStream(oldPath); //读入原文件
                FileOutputStream outStream = new FileOutputStream(newPath);
                byte[] buffer = new byte[1444];
                int length;
                while ( (byteread = inStream.read(buffer)) != -1) {
                    bytesum += byteread; //字节数 文件大小
                    outStream.write(buffer, 0, byteread);
                }
                inStream.close();
                outStream.flush();
                outStream.close();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

}
