package com.jzg.jzgoto.phone.global;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.app.Application;
import android.content.Context;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.util.Log;
import cn.jpush.android.api.JPushInterface;

import com.android.volley.Network;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HttpStack;
import com.android.volley.toolbox.HurlStack;
import com.jzg.jzgoto.phone.R;
import com.jzg.jzgoto.phone.crash.CrashHandler;
import com.jzg.jzgoto.phone.models.business.buy.BuyCarItemModel;
import com.jzg.jzgoto.phone.models.business.login.LoginResultModels;
import com.jzg.jzgoto.phone.models.business.valuation.sell.RequestValFragmentHistory;
import com.jzg.jzgoto.phone.models.business.valuation.sell.RequestValuationBuyParams;
import com.jzg.jzgoto.phone.tools.ImageRender;
import com.jzg.pricechange.phone.JzgCarChooseStyle;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.umeng.analytics.MobclickAgent;
import com.yiche.ycanalytics.YCPlatform;

/**
 * 自定义应用类
 * 
 * @author jzg
 * @Date 2015-05-05
 */
public class AppContext extends Application {
	public static final String TAG = "PhoneJzgApplication";

	public static Context mAppContext = null;
	/**
	 * 线程ID
	 */
	private int mAppThreadId = -1;

	private static final String DEFAULT_CACHE_DIR = "cache_directory";

	// Default maximum disk usage in bytes
	private static final int DEFAULT_DISK_USAGE_BYTES = 25 * 1024 * 1024;

	private static RequestQueue mVolleyQueue;
	
	/**
	 * 估价模块车辆信息索引后，最终车型
	 */
	public static JzgCarChooseStyle mQueryCarStyle = null;
	
	/**
	 * 卖车选择最终车型
	 */
	public static JzgCarChooseStyle mSellQueryCarStyle = null;
	
	public static RequestValuationBuyParams mValuationBuyParams = null;

	public static RequestQueue getRequestQueue() {
		return mVolleyQueue;
	}
	
	public static String cityName = "全国"; 
	public static String provinceName = "全国";

	private static Handler mHandler;
	private NetworkStatusReceiver mNetworkStatusReceiver;
	
	/**
	 * 最近浏览
	 */
	public static List<BuyCarItemModel> recentlyList = new ArrayList<BuyCarItemModel>();	//最近浏览
	
	//最后一次点击的历史记录
	public static RequestValFragmentHistory requestValFragmentHistory = null;
	
	public static void addRecently(BuyCarItemModel buyCarItemModel){
		if(recentlyList.size()>19){
			recentlyList.remove(0);
			recentlyList.add(buyCarItemModel);
		}else{
			recentlyList.add(buyCarItemModel);
		}
	}
	
	
	public static DisplayImageOptions mOptions;
	public static DisplayImageOptions mUserImgOptions;
	public static LoginResultModels.PersonalInfo mLoginResultModels;
	public static boolean isLogin(){
		if(mLoginResultModels == null)return false;
		return true;
	}
	@Override
	public void onCreate() {
		super.onCreate();
		mAppContext = this;
		// 初始化imageload库
		initImageLoader(getApplicationContext());
	//	JPushInterface.setDebugMode(true);
	    JPushInterface.init(this);
	    MobclickAgent.onResume(mAppContext);
	    MobclickAgent.onPause(mAppContext);
		mOptions = new DisplayImageOptions.Builder()
		.showImageOnLoading(R.drawable.jingzhengu_moren)
		.showImageForEmptyUri(R.drawable.jingzhengu_moren)
		.showImageOnFail(R.drawable.jingzhengu_moren).cacheInMemory(true)
		.cacheOnDisk(true)
		.considerExifParams(true)
		.build();		
		mUserImgOptions = new DisplayImageOptions.Builder()
		.showImageOnLoading(R.drawable.touxiang)
		.showImageForEmptyUri(R.drawable.touxiang)
		.showImageOnFail(R.drawable.touxiang).cacheInMemory(true)
		.cacheOnDisk(true)
		.considerExifParams(true)
		.build();		
		// 初始化volley请求队列
		mVolleyQueue = newParallelRequestQueue(this);
		mAppThreadId = android.os.Process.myPid();
//		initException();
		mHandler = new Handler();
		//add crash log support
		CrashHandler.getInstance().init(this);

		//易车统计用
		YCPlatform.init(this);

		mNetworkStatusReceiver = new NetworkStatusReceiver();
		registerNetworkReceiver();
	}

	@Override
	public void onTerminate() {
		unRegisterNetworkReceiver();
		super.onTerminate();
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
		ImageRender.getInstance().init(context);
	}

	public static RequestQueue newParallelRequestQueue(Context context) {
		// define cache folder
		File rootCache = context.getExternalCacheDir();
		if (rootCache == null) {
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
	 * 检测网络是否可用
	 * 
	 * @return
	 */
	public static boolean isNetworkConnected() {
		if (mAppContext == null) {
			return false;
		}
		ConnectivityManager cm = (ConnectivityManager) mAppContext
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo ni = cm.getActiveNetworkInfo();
		return ni != null && ni.isConnectedOrConnecting();
	}

	private void initException() {
		Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
			@Override
			public void uncaughtException(Thread thread, final Throwable ex) {
				Log.e("phoneJzgApp","phoneJzgAppException------->" + ex.toString());
			}
		});
	}

	private static SharedPreferences getAppState(String name) {
		if (mAppContext != null) {
			return mAppContext.getSharedPreferences(name, MODE_PRIVATE);
		}
		return null;
	}

	public static Handler getHandler() {
		return mHandler;
	}
	public static Context getContext() {
		return mAppContext;
	}

	private void registerNetworkReceiver() {
		IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
		this.registerReceiver(mNetworkStatusReceiver, filter);
	}

	private void unRegisterNetworkReceiver() {
		this.unregisterReceiver(mNetworkStatusReceiver);
	}
}
