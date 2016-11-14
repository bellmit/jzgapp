package com.jzg.jzgoto.phone.tools;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;

import com.jzg.jzgoto.phone.R;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.utils.StorageUtils;

public class ImageRender {
	public final static String imageLoaderPath = "/jzg/image/cache/";
    private static ImageRender sInstance = new ImageRender();
    private boolean mIsInited;
    private DisplayImageOptions defaultOptions = null;
	
    private ImageRender() {
    }

    public static ImageRender getInstance() {
        return sInstance;
    }

    public void init(Context context) {
        if (mIsInited) {
            return;
        }
        // This configuration tuning is custom. You can tune every option, you
        // may tune some of them,
        // or you can create default configuration by
        // ImageLoaderConfiguration.createDefault(this);
        // method.
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                .threadPriority(Thread.NORM_PRIORITY - 2).denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .diskCacheSize(50 * 1024 * 1024)
                // 50 Mb
                .tasksProcessingOrder(QueueProcessingType.LIFO)
//                .diskCache(
//                        new UnlimitedDiskCache(StorageUtils.getOwnCacheDirectory(context,
//                                imageLoaderPath)))// 自定义缓存路径
                // .writeDebugLogs() // Remove for release app
                .build();
        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config);
        defaultOptions = new DisplayImageOptions.Builder().showImageOnLoading(R.color.transparent)
                .showImageForEmptyUri(R.color.transparent).showImageOnFail(R.color.transparent)
                .cacheInMemory(true).cacheOnDisk(true).considerExifParams(true).build();
        mIsInited = true;
    }

    public void clear() {
        ImageLoader.getInstance().clearMemoryCache();
        ImageLoader.getInstance().clearDiskCache();
    }

    public void setImage(ImageView imageView, String urlStr){
        setImage(imageView, urlStr, R.color.transparent, R.color.transparent, R.color.transparent, 0, true,
                null);
        if (imageView == null) {
            return;
        }
        if (TextUtils.isEmpty(urlStr)) {
            return;
        }
        ImageLoader.getInstance().displayImage(urlStr, imageView, defaultOptions, null);
    }

    public void setImage(ImageView imageView, String urlStr, int defaultImageResourceID) {
        setImage(imageView, urlStr, defaultImageResourceID, defaultImageResourceID, defaultImageResourceID, 0, true,
                null);
    }

    public void setImage(ImageView imageView, String urlStr, int lodingImageResourceID, int defaultImageResourceID) {
        setImage(imageView, urlStr, defaultImageResourceID, lodingImageResourceID, defaultImageResourceID, 0, true,
                null);
    }

    public void setImage(ImageView imageView, String urlStr, int defaultImageResourceID, int cornerRadiusPixels,
            boolean cache, ImageLoadingListener listener) {
        setImage(imageView, urlStr, defaultImageResourceID, defaultImageResourceID, defaultImageResourceID,
                cornerRadiusPixels, cache, listener);
    }

    public void setImage(ImageView imageView, String urlStr, int defaultImageResourceID, int lodingImageResourceID,
            int errorImageResoureID, int cornerRadiusPixels, boolean cache, ImageLoadingListener listener) {
        if (imageView == null) {
            return;
        }
        if (TextUtils.isEmpty(urlStr)) {
            if (defaultImageResourceID > 0) {
                imageView.setImageResource(defaultImageResourceID);
            }
            return;
        }
        DisplayImageOptions options = null;
        if (cornerRadiusPixels > 0) {
            options = new DisplayImageOptions.Builder().showImageOnLoading(lodingImageResourceID)
                    .showImageForEmptyUri(defaultImageResourceID).showImageOnFail(errorImageResoureID)
                    .cacheInMemory(cache).cacheOnDisk(cache).considerExifParams(true)
                    .displayer(new RoundedBitmapDisplayer(cornerRadiusPixels)).build();
        } else {
            options = new DisplayImageOptions.Builder().showImageOnLoading(lodingImageResourceID)
                    .showImageForEmptyUri(defaultImageResourceID).showImageOnFail(errorImageResoureID)
                    .cacheInMemory(cache).cacheOnDisk(cache).considerExifParams(true).build();
        }
        ImageLoader.getInstance().displayImage(urlStr, imageView, options, listener);
    }

    public void loadImage(String uri, ImageLoadingListener listener) {
        DisplayImageOptions options = new DisplayImageOptions.Builder().showImageOnLoading(0).showImageForEmptyUri(0)
                .showImageOnFail(0).cacheInMemory(true).cacheOnDisk(true).considerExifParams(true).build();
        ImageLoader.getInstance().loadImage(uri, options, listener);
    }
}