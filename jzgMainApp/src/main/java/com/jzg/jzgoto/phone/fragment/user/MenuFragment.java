package com.jzg.jzgoto.phone.fragment.user;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.jzg.jzgoto.phone.R;
import com.jzg.jzgoto.phone.fragment.shared.BaseFragment;
import com.jzg.jzgoto.phone.view.menu.MenuItemView;

import java.util.HashMap;
import java.util.Map;


public class MenuFragment extends BaseFragment {
    public interface OnMenuClickListener {
        public void onMenuClick(MenuType type);
    }

    public static enum MenuType {
        MENU_MESSAGE_CENTER,
        MENU_LATEST_ACTIVITY,
        MENU_SUBSCRIBE_CAR_SOURCE,
        MENU_FOCUS_ON_CAR_SOURCE,
        MENU_LATEST_NEWS,
        MENU_QUERY_OFFENCE,
        MENU_CAR_REPLACEMENT,
        MENU_CAR_SELL_PROGRESS,
    }

    private MenuItemView menuMessageCenter;
    private MenuItemView menuLatestActivity;
    private MenuItemView menuSubscribeCarSource;
    private MenuItemView menuFocusOnCarSource;
    private MenuItemView menuLatestNews;
    private MenuItemView menuQueryOffence;
    private MenuItemView menuCarReplacement;
    private MenuItemView menuCarSellProgress;

    private MenuType currentMenuType = MenuType.MENU_MESSAGE_CENTER;
    private OnMenuClickListener onMenuClickListener;
    private Map<MenuType, MenuItemView> mMenuItemMap = new HashMap<MenuType, MenuItemView>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_main_menufragment, null);
        this.menuMessageCenter = (MenuItemView) rootView.findViewById(R.id.menu_item_message_center);
        this.menuLatestActivity = (MenuItemView) rootView.findViewById(R.id.menu_item_lastest_activity);
        this.menuSubscribeCarSource = (MenuItemView) rootView.findViewById(R.id.menu_item_subscribe_car_source);
        this.menuFocusOnCarSource = (MenuItemView) rootView.findViewById(R.id.menu_item_focus_on_car_source);
        this.menuLatestNews = (MenuItemView) rootView.findViewById(R.id.menu_item_latest_news);
        this.menuQueryOffence = (MenuItemView) rootView.findViewById(R.id.menu_item_query_offence);
        this.menuCarReplacement = (MenuItemView) rootView.findViewById(R.id.menu_item_car_replacement);
        this.menuCarSellProgress = (MenuItemView) rootView.findViewById(R.id.menu_item_car_sell_progress);

        mMenuItemMap.put(MenuType.MENU_MESSAGE_CENTER, menuMessageCenter);
        mMenuItemMap.put(MenuType.MENU_LATEST_ACTIVITY, menuLatestActivity);
        mMenuItemMap.put(MenuType.MENU_SUBSCRIBE_CAR_SOURCE, menuSubscribeCarSource);
        mMenuItemMap.put(MenuType.MENU_FOCUS_ON_CAR_SOURCE, menuFocusOnCarSource);
        mMenuItemMap.put(MenuType.MENU_LATEST_NEWS, menuLatestNews);
        mMenuItemMap.put(MenuType.MENU_QUERY_OFFENCE, menuQueryOffence);
        mMenuItemMap.put(MenuType.MENU_CAR_REPLACEMENT, menuCarReplacement);
        mMenuItemMap.put(MenuType.MENU_CAR_SELL_PROGRESS, menuCarSellProgress);

        for (MenuType menuItem : mMenuItemMap.keySet()){
            mMenuItemMap.get(menuItem).setOnClickListener(this.onClickListener);
        }

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroy();
    }


    public void setOnMenuClickListener(OnMenuClickListener listener) {
        this.onMenuClickListener = listener;
    }

    public void setMenuItemNumberIndicator(MenuType type, int number){
        MenuItemView itemView = mMenuItemMap.get(type);
        if (itemView != null){
            itemView.showNewIcon(number);
        }
    }

    public void clearMenuItemNumberIndicator(MenuType type){
        MenuItemView itemView = mMenuItemMap.get(type);
        if (itemView != null){
            itemView.hideNewIcon();
        }
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            int viewId = v.getId();
            handleMenuClick(getConvertMenuId(viewId));
        }
    };

    private void handleMenuClick(MenuType type) {
        if (onMenuClickListener == null) {
            return;
        }
        onMenuClickListener.onMenuClick(type);
        currentMenuType = type;
    }

    private MenuType getConvertMenuId(int menuId){
        MenuType type = MenuType.MENU_MESSAGE_CENTER;
        switch (menuId){
            case R.id.menu_item_message_center:
                type = MenuType.MENU_MESSAGE_CENTER;
                break;
            case R.id.menu_item_lastest_activity:
                type = MenuType.MENU_LATEST_ACTIVITY;
                break;
            case R.id.menu_item_subscribe_car_source:
                type = MenuType.MENU_SUBSCRIBE_CAR_SOURCE;
                break;
            case R.id.menu_item_focus_on_car_source:
                type = MenuType.MENU_FOCUS_ON_CAR_SOURCE;
                break;
            case R.id.menu_item_latest_news:
                type = MenuType.MENU_LATEST_NEWS;
                break;
            case R.id.menu_item_query_offence:
                type = MenuType.MENU_QUERY_OFFENCE;
                break;
            case R.id.menu_item_car_replacement:
                type = MenuType.MENU_CAR_REPLACEMENT;
                break;
            case R.id.menu_item_car_sell_progress:
                type = MenuType.MENU_CAR_SELL_PROGRESS;
                break;
        }
        return type;
    }
}
