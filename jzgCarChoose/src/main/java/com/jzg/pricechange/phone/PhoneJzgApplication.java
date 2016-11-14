package com.jzg.pricechange.phone;

import java.io.File;
import java.util.ArrayList;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.android.volley.Network;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HttpStack;
import com.android.volley.toolbox.HurlStack;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

/**
 * �Զ���Ӧ����
 * 
 * @author jzg
 * @Date 2015-05-05
 */
public class PhoneJzgApplication extends Application
{
	public static final String TAG = "PhoneJzgApplication";

	public static Context mAppContext = null;
	private static SharedPreferences mSharedPreferences = null;
	private static SharedPreferences mUserSharedPreferences = null;
	/**
	 * 线程ID
	 */
	private int mAppThreadId = -1;

	private static final String DEFAULT_CACHE_DIR = "cache_directory";

	// Default maximum disk usage in bytes
	private static final int DEFAULT_DISK_USAGE_BYTES = 25 * 1024 * 1024;

	/**
	 * 历史车型json解析数据封装
	 */
	public static ArrayList<JzgCarChooseStyleCategory> mHistoryCarStyles = new ArrayList<JzgCarChooseStyleCategory>();

	/**
	 * 历史品牌列表所有数据
	 */
	public static ArrayList<JzgCarChooseMake> mHistoryMakes = new ArrayList<JzgCarChooseMake>();

	/**
	 * 历史解析后车系json数据封装
	 */
	public static ArrayList<JzgCarChooseModelCategory> mHistoryModelCategorys = new ArrayList<JzgCarChooseModelCategory>();

	/**
	 * 车系历史列表被点击的位置（用于车系列表字体颜色改变）
	 */
	public static int mModelHistoryPosition = -1;

	/**
	 * 车型历史列表被点击的位置（用于车系列表字体颜色改变）
	 */
	public static int mStyleHistoryPosition = -1;

	/**
	 * 估价模块车辆信息索引后，最终车的年款
	 */
	public static int mQueryCarYear = -1;

	/**
	 * 品牌历史列表被点击的位置（用于品牌列表字体颜色改变）
	 */
	public static int mMakeHistoryPosition = -1;
	
	/**
	 * 上次选择的车型id
	 */
	public static int publicStyleId = -1;
	public static int maxYear = 0;
	public static int MaxMonth = 0;
	public static int minYear = 0;
	public static int MinMonth = 0;
	

	private static RequestQueue mVolleyQueue;

	public static RequestQueue getRequestQueue()
	{
		return mVolleyQueue;
	}

	@Override
	public void onCreate()
	{
		super.onCreate();
		mAppContext = this;

		// 初始化imageload库
		initImageLoader(getApplicationContext());
		// 初始化volley请求队列
		mVolleyQueue = newParallelRequestQueue(this);
		mAppThreadId = android.os.Process.myPid();

		// initCrashHandler();
	}

	public static RequestQueue newParallelRequestQueue(Context context)
	{
		// define cache folder
		File rootCache = context.getExternalCacheDir();
		if (rootCache == null)
		{
			Log.i("AppContext", "Can't find External Cache Dir, "

			+ "switching to application specific cache directory");
			rootCache = context.getCacheDir();
		}

		File cacheDir = new File(rootCache, DEFAULT_CACHE_DIR);
		cacheDir.mkdirs();

		HttpStack stack = new HurlStack();
		Network network = new BasicNetwork(stack);
		DiskBasedCache diskBasedCache = new DiskBasedCache(cacheDir,
				DEFAULT_DISK_USAGE_BYTES);
		RequestQueue queue = new RequestQueue(diskBasedCache, network);
		queue.start();

		return queue;
	}

	/**
	 * 初始化ImageLoader initImageLoader: 使用imageload库必须加<br/>
	 * 
	 * @author wang
	 * @param context
	 * @since JDK 1.6
	 */
	public static void initImageLoader(Context context)
	{
		// This configuration tuning is custom. You can tune every option, you
		// may tune some of them,
		// or you can create default configuration by
		// ImageLoaderConfiguration.createDefault(this);
		// method.
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
				context).threadPriority(Thread.NORM_PRIORITY - 2)
				.denyCacheImageMultipleSizesInMemory()
				.diskCacheFileNameGenerator(new Md5FileNameGenerator())
				.tasksProcessingOrder(QueueProcessingType.LIFO)
				.writeDebugLogs() // Remove for release app
				.build();
		// Initialize ImageLoader with configuration.
		ImageLoader.getInstance().init(config);
	}

	/**
	 * 检测网络是否可用
	 * 
	 * @return
	 */
	public static boolean isNetworkConnected()
	{
		if (mAppContext == null)
		{
			return false;
		}
		ConnectivityManager cm = (ConnectivityManager) mAppContext
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo ni = cm.getActiveNetworkInfo();
		return ni != null && ni.isConnectedOrConnecting();
	}

	
}
