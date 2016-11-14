package com.jzg.jzgoto.phone.activity.common;


import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.jzg.jzgoto.phone.R;
import com.jzg.jzgoto.phone.activity.shared.BaseActivity;
import com.jzg.jzgoto.phone.view.ViewUtility;
import com.jzg.jzgoto.phone.view.shared.HeadBar;
import com.jzg.jzgoto.phone.view.shared.HeadBar.ClickType;
import com.jzg.jzgoto.phone.view.webview.VideoEnabledWebChromeClient;
import com.jzg.jzgoto.phone.view.webview.VideoEnabledWebView;

import java.util.ArrayList;
import java.util.List;


/**
 * 通用webview界面。
 * <p/>
 * loadUrl两种方式：1、 互联网网址：http://www.google.com 2、
 * 本地文件：file:///android_asset/XX.html
 */
public class CustomWebViewActivity extends BaseActivity {
    private final static String webviewCachePath = "/jzg/webview/cache/";

    private HeadBar headBar;
    private VideoEnabledWebView customWebview;
    private ViewGroup fullVideoView;
    private VideoEnabledWebChromeClient chromeClient;
    private boolean isShowRightBtn = false;
    private boolean isReceivedError = false;

    public static final String BUNDLE_CUSTOM_WEBKIT_TITLE = "BUNDLE_CUSTOM_WEBKIT_TITLE";
    public static final String BUNDLE_CUSTOM_WEBKIT_URL = "BUNDLE_CUSTOM_WEBKIT_URL";
    public static final String BUNDLE_CUSTOM_USE_CUSTOM_TITLE = "BUNDLE_CUSTOM_USE_CUSTOM_TITLE";
    public static final String SHOW_RIGHT_BTN = "SHOW_RIGHT_BTN";
   
    private String url;
    private String mWebTitleStr;
    private boolean mUseCustomTitle = false;
    private List<String> mTitleStrList = new ArrayList<String>();
    private String mCurrWebTitle = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_webview_layout);
        headBar = (HeadBar) this.findViewById(R.id.custom_webview_headbar);
        customWebview = (VideoEnabledWebView) this.findViewById(R.id.custom_webview);
        fullVideoView = (ViewGroup) this.findViewById(R.id.custom_full_video_view);
        mWebTitleStr = getIntent().getStringExtra(BUNDLE_CUSTOM_WEBKIT_TITLE);
        mUseCustomTitle = getIntent().getBooleanExtra(BUNDLE_CUSTOM_USE_CUSTOM_TITLE, false);
        url = getIntent().getStringExtra(BUNDLE_CUSTOM_WEBKIT_URL);
        isShowRightBtn = getIntent().getBooleanExtra(SHOW_RIGHT_BTN, true);
        if (!isShowRightBtn && headBar.getRightBtnContainer() != null) {
            headBar.getRightBtnContainer().setVisibility(View.GONE);
        }

        WebSettings webSettings = customWebview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }


        headBar.setTitle(mWebTitleStr);
        customWebview.setWebViewClient(new WebViewClient() {

            public void onPageFinished(WebView view, String url) {
                handleLoadPageFinished(view, url, isReceivedError);
                if (headBar != null && !mUseCustomTitle){
                    String title = view.getTitle();
                    headBar.setTitle(title);
                }
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                isReceivedError = true;
                handleLoadPageError();
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                isReceivedError = false;
            }
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if(url.endsWith(".apk")){
                    Uri uri = Uri.parse(url);
                    Intent viewIntent = new Intent(Intent.ACTION_VIEW,uri);
                    CustomWebViewActivity.this.startActivity(viewIntent);
                    return true;
                }
                return false;
            }
        });
        chromeClient = new VideoEnabledWebChromeClient(customWebview, fullVideoView, customWebview) {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                if (headBar != null && !isReceivedError) {
                    if (!TextUtils.isEmpty(title) && !mUseCustomTitle) {
                        headBar.setTitle(title);
                    }
                }
            }

            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                return super.onJsAlert(view, url, message, result);
            }
        };

        // 设置setWebChromeClient对象
        customWebview.setWebChromeClient(chromeClient);
        headBar.setOnHeadBarClickListener(onHeadBarClickListener);
        startLoading();
        headBar.getRightTextView().setVisibility(View.GONE);
        headBar.setOnHeadBarClickListener(new HeadBar.OnHeadBarClickListener() {
            @Override
            public void onHeadBarClick(HeadBar.ClickType type) {
                switch (type) {
                    case Left:
                    case Back:
                        handleBack();
                        break;
                    default:
                        break;
                }
            }
        });
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            handleBack();
            return true;
        }
        return super.onKeyDown(keyCode,event);
    }

    private void handleBack(){
        if(customWebview.canGoBack()){
            customWebview.goBack();
//            if (mTitleStrList.size() > 1){
//                mTitleStrList.remove(mTitleStrList.size() - 1);
//                if (headBar != null){
//                    headBar.setTitle(mTitleStrList.get(mTitleStrList.size() - 1));
//                }
//            }

        }else{
            this.finish();;
        }
    }

    private void startLoading() {
        customWebview.stopLoading();
        customWebview.loadUrl(url);
    }

    private void handleLoadPageFinished(WebView view, String url, boolean isError) {
    }

    private void handleLoadPageError() {
    }

    private HeadBar.OnHeadBarClickListener onHeadBarClickListener = new HeadBar.OnHeadBarClickListener() {

        @Override
        public void onHeadBarClick(ClickType type) {
            switch (type) {
            case Back:
                onBackPressed();
                break;
            case Right:
            case Share:
                break;
            default:
                break;
            }
        }
    };


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    /**
     * Activity 设置为 launchMode="singleTask或singleTop 若想再次Intent到此页面，回调此方法
     * 
     * @param intent
     */
    @Override
    protected void onNewIntent(Intent intent) {
        setIntent(intent);
        url = intent.getStringExtra(BUNDLE_CUSTOM_WEBKIT_URL);
        customWebview.stopLoading();
        customWebview.loadUrl(url);
        headBar.getRightTextView().setVisibility(View.GONE);
        super.onNewIntent(intent);
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        if (customWebview != null) {
            customWebview.onPause();
        }
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        if (customWebview != null) {
            customWebview.onResume();
        }
    }

    @Override
    protected void onDestroy() {
        if (customWebview != null) {
            customWebview.clearCache(true);
            customWebview.destroy();
        }
        super.onDestroy();
    }

}
