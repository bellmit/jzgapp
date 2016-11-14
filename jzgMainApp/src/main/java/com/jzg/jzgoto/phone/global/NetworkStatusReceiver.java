package com.jzg.jzgoto.phone.global;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.net.NetworkInfo.State;
import com.jzg.jzgoto.phone.event.NetworkStatusEvent;

import de.greenrobot.event.EventBus;

public class NetworkStatusReceiver extends BroadcastReceiver {
	
	@Override
	public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        if(bundle == null)return;
		
        State wifiState = null;  
        State mobileState = null;  
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        wifiState = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();  
        mobileState = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();  
        if (wifiState != null && mobileState != null  
                && State.CONNECTED != wifiState  
                && State.CONNECTED == mobileState) {  
            // 手机网络连接成功
            EventBus.getDefault().post(NetworkStatusEvent.build(true));
        } else if (wifiState != null && mobileState != null  
                && State.CONNECTED != wifiState  
                && State.CONNECTED != mobileState) {  
            // 手机没有任何的网络
            EventBus.getDefault().post(NetworkStatusEvent.build(false));
        } else if (wifiState != null && State.CONNECTED == wifiState) {  
            // 无线网络连接成功
            EventBus.getDefault().post(NetworkStatusEvent.build(true));
        }  
	}


}
