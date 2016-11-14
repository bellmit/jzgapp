package com.jzg.jzgoto.phone.fragment.user;


import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import com.jzg.jzgoto.phone.R;
import com.jzg.jzgoto.phone.activity.main.HomeActivity;
import com.jzg.jzgoto.phone.fragment.shared.BaseFragment;
import com.jzg.jzgoto.phone.global.AppContext;
import com.jzg.jzgoto.phone.global.HttpServiceHelper;
import com.jzg.jzgoto.phone.global.Statistical;
import com.jzg.jzgoto.phone.tools.CountClickTool;
import com.jzg.jzgoto.phone.tools.DialogManager;
import com.jzg.jzgoto.phone.tools.ImageRender;
import com.jzg.jzgoto.phone.tools.ToastManager;
import com.jzg.jzgoto.phone.view.ViewUtility;
import com.jzg.jzgoto.phone.view.common.CircularImageView;


public class UserCenterFragment extends BaseFragment implements OnClickListener{

	private AppContext mAppContext;
	private MenuFragment mMenuFragment;

	@Bind(R.id.btn_login)
	TextView mLoginBtn;
	@Bind(R.id.btn_setting)
	TextView mSettingBtn;
	@Bind(R.id.user_img)
	CircularImageView mUserImageView;
	@Bind(R.id.user_info_container)
	View mUserInfoContainer;
	@Bind(R.id.user_name_text)
	TextView mUserNameTextView;
	@Bind(R.id.user_mobile_text)
	TextView mUserMobileTextView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mAppContext = (AppContext) getActivity().getApplicationContext();
		updateLoginViews();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_user_center_layout, null);
		ButterKnife.bind(this, view);
		FragmentManager fm = this.getChildFragmentManager();
		mMenuFragment = (MenuFragment) fm.findFragmentById(R.id.menu_fragment);
		mMenuFragment.setOnMenuClickListener(this.onMenuClickListener);
		//mMenuFragment.setMenuItemNumberIndicator(MenuFragment.MenuType.MENU_MESSAGE_CENTER, 1);
		updateLoginViews();
		return view;
	}
	
	@Override
	public void onResume() {
		super.onResume();
		updateLoginViews();
	}

	private void updateLoginViews(){
		if (mLoginBtn != null && mUserImageView != null) {
			if (AppContext.isLogin()) {
				mLoginBtn.setVisibility(View.GONE);
				mUserInfoContainer.setVisibility(View.VISIBLE);
				String imageUrl = AppContext.mLoginResultModels.getAvatorPicPath();
				ImageRender.getInstance().setImage(mUserImageView,
						imageUrl == null ? null : imageUrl, R.drawable.img_user);
				mUserNameTextView.setText(AppContext.mLoginResultModels.getTrueName());
				mUserMobileTextView.setText(AppContext.mLoginResultModels.getMobile());
			} else {
				mLoginBtn.setVisibility(View.VISIBLE);
				mUserInfoContainer.setVisibility(View.GONE);
				mUserImageView.setImageResource(R.drawable.img_user);
			}
		}
	}

	
	@Override
	public void onRequestSuccess(Message msg) {
		
	}

	@Override
	public void onRequestFault(Message msg) {
		
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	@Override
	@OnClick({R.id.btn_login, R.id.btn_setting, R.id.user_img})
	public void onClick(View view) {
		switch (view.getId()) {
			case R.id.btn_login:
				handleLoginClicked();
				break;
			case R.id.btn_setting:
				handleSettingClicked();
				break;
			case R.id.user_img:
				handleUserImageClicked();
				break;
		}
	}

	private MenuFragment.OnMenuClickListener onMenuClickListener = new MenuFragment.OnMenuClickListener() {

		@Override
		public void onMenuClick(MenuFragment.MenuType type) {
			switch(type){
				case MENU_MESSAGE_CENTER:
					handleMessageCenterClicked();
					break;
				case MENU_LATEST_ACTIVITY:
					handleLatestActivityClicked();
					break;
				case MENU_SUBSCRIBE_CAR_SOURCE:
					handleSubscribeCarSourceClicked();
					break;
				case MENU_FOCUS_ON_CAR_SOURCE:
					handleFocusOnCarSourceClicked();
					break;
				case MENU_LATEST_NEWS:
					handleLatestNewsClicked();
					break;
				case MENU_QUERY_OFFENCE:
					handleQueryOffenceClicked();
					break;
				case MENU_CAR_REPLACEMENT:
					handleCarReplacementClicked();
					break;
				case MENU_CAR_SELL_PROGRESS:
					handleCarSellProgressClicked();
					break;
			}
			HomeActivity.self.closeMenu();
		}
	};

	private void handleMessageCenterClicked(){
		if (AppContext.isLogin()) {
			ViewUtility.navigateToUserMessageMainActivity(this.getActivity());
			CountClickTool.onEvent(this.getActivity(), Statistical.KEY_SIDEBAR_MESSAGE_CENTER_CLICK_COUNT);
		} else {
			DialogManager.toLoginDialog(this.getActivity());
		}
	}

	private void handleLatestActivityClicked(){
		String url = HttpServiceHelper.BASE_HTML_URL + "/APPV5/activePage.aspx";
		ViewUtility.navigateToWebView(this.getActivity(), "", url);
		CountClickTool.onEvent(this.getActivity(), Statistical.KEY_SIDEBAR_LATEST_ACTIVITY_CLICK_COUNT);
	}

	private void handleSubscribeCarSourceClicked(){
		if (AppContext.isLogin()) {
			ViewUtility.navigateToSubscribeCarListActivity(this.getActivity());
			CountClickTool.onEvent(this.getActivity(), Statistical.KEY_SIDEBAR_SUBSCRIBE_CAR_CLICK_COUNT);
		} else {
			DialogManager.toLoginDialog(this.getActivity());
		}
	}

	private void handleFocusOnCarSourceClicked(){
		if (AppContext.isLogin()) {
			ViewUtility.navigateToFavoriteCarListActivity(this.getActivity());
			CountClickTool.onEvent(this.getActivity(), Statistical.KEY_SIDEBAR_FAVORITE_CAR_CLICK_COUNT);
		} else {
			DialogManager.toLoginDialog(this.getActivity());
		}
	}

	private void handleLatestNewsClicked(){
		String url = HttpServiceHelper.BASE_HTML_URL + "/APPV5/articallist.aspx";
		ViewUtility.navigateToWebView(this.getActivity(), "", url);
		CountClickTool.onEvent(this.getActivity(), Statistical.KEY_SIDEBAR_LATEST_NEWS_CLICK_COUNT);
	}

	private void handleQueryOffenceClicked(){
		ViewUtility.navigateToOffenceCountQueryActivity(this.getActivity());
		CountClickTool.onEvent(this.getActivity(), Statistical.KEY_SIDEBAR_OFFENCE_QUERY_CLICK_COUNT);
	}

	private void handleCarReplacementClicked(){
		ViewUtility.navigateToReplaceNewCarActivity(this.getActivity());
		CountClickTool.onEvent(this.getActivity(), Statistical.KEY_SIDEBAR_CAR_REPLACE_CLICK_COUNT);
	}

	private void handleCarSellProgressClicked(){

	}

	private void handleLoginClicked(){
		if(AppContext.isLogin()){
			ToastManager.getInstance().showToastCenter(this.getActivity(), "已登陆！");
			return;
		}
		HomeActivity.self.closeMenu();
		ViewUtility.navigateToLoginActivity(this.getActivity());
		CountClickTool.onEvent(this.getActivity(), Statistical.KEY_SIDEBAR_LOGIN_CLICK_COUNT);
	}

	private void handleSettingClicked(){
		HomeActivity.self.closeMenu();
		ViewUtility.navigateToSettingActivity(this.getActivity());
		CountClickTool.onEvent(this.getActivity(), Statistical.KEY_SIDEBAR_SETTING_CLICK_COUNT);
	}

	private void handleUserImageClicked(){
		if(AppContext.isLogin()){
			HomeActivity.self.closeMenu();
			ViewUtility.navigateEditUserInfoActivity(this.getActivity());
		}
	}
}
