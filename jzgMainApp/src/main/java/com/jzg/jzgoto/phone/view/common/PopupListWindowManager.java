package com.jzg.jzgoto.phone.view.common;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.jzg.jzgoto.phone.R;

public class PopupListWindowManager implements PopupWindow.OnDismissListener {
	private  Context mContext = null;
	private PopupWindow mPopWindow;
	private RelativeLayout mPopContainer;
	private ListView mListView;
	private View mContentContainer;
	private static PopupListWindowManager mInstance = new PopupListWindowManager();

	private PopupListWindowManager() {
	}

	public static  PopupListWindowManager getInstance(){
		return mInstance;
	}

	public void prepare(Context context){
		if (context == null){
			return;
		}
		mContext = context;
		initPopWindow();
		initContentView();
		mPopContainer.addView(mContentContainer);
	}

	public ListView getListView(){
		return mListView;
	}

	public void setAdapter(BaseAdapter listAdapter){
		if (mListView != null){
			mListView.setAdapter(listAdapter);
		}
	}

	public void showPopupListWindow(View anchorView){
		if (anchorView != null){
			if (mPopWindow.isShowing()) {
				mPopWindow.dismiss();
			} else {
				mPopWindow.showAsDropDown(anchorView);
			}
		}
	}

	public void hidePopupListWindow(){
		if (mPopWindow != null) {
			if (mPopWindow.isShowing()) {
				mPopWindow.dismiss();
			}
		}
	}

	private void initPopWindow() {
		if (mPopWindow == null) {
			mPopWindow = new PopupWindow(mContext);
			View contentView = LayoutInflater.from(mContext).inflate(
					R.layout.view_shared_popup_window_layout, null);
			mPopContainer = (RelativeLayout) contentView
					.findViewById(R.id.view_popup_content_container);
			contentView.findViewById(R.id.view_popup_outside_container)
					.setOnClickListener(new View.OnClickListener() {

						@Override
						public void onClick(View v) {
							mPopWindow.dismiss();
						}
					});
			mPopWindow.setContentView(contentView);
			mPopWindow.setWindowLayoutMode(ViewGroup.LayoutParams.MATCH_PARENT,
					ViewGroup.LayoutParams.WRAP_CONTENT);
			mPopWindow.setBackgroundDrawable(new ColorDrawable(mContext.getResources()
					.getColor(android.R.color.transparent)));
		}
		mPopWindow.setOnDismissListener(this);
		mPopWindow.setFocusable(true);
		mPopWindow.setOutsideTouchable(true);
	}

	private View initContentView() {
		View view = LayoutInflater.from(mContext).inflate(
				R.layout.view_shared_popup_list_view_layout, null);
		mListView = (ListView) view
				.findViewById(R.id.view_content_listview);
		mContentContainer = view;
		return view;
	}

	@Override
	public void onDismiss() {

	}
}
