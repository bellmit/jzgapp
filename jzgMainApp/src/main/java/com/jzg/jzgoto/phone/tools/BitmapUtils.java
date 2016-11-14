package com.jzg.jzgoto.phone.tools;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class BitmapUtils {

	/**
     * 获取网络图片资源
     * 
     * @param url
     * @return
     */
    public static Bitmap getHttpBitmap(String url) {
    	
            URL myFileURL;
            Bitmap bitmap = null;
          try {
				myFileURL = new URL(url);
				HttpURLConnection conn = (HttpURLConnection) myFileURL
                              .openConnection();
			//	conn.connect();
                conn.setConnectTimeout(5000);
                conn.setDoInput(true);
                InputStream is =  conn.getInputStream();
                bitmap = BitmapFactory.decodeStream(is);
                is.close();
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
                    
            return bitmap;
    }
}
