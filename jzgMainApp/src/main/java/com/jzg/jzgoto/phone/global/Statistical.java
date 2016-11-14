package com.jzg.jzgoto.phone.global;



public interface Statistical {

    /** 首页统计 **/
    /** 启动次数 **/
    public static final String KEY_APP_START_COUNT = "v5_app_start_count";
    /** banner点击次数（分广告，按编号） **/
    public static final String KEY_HOME_BANNER_CLICK_COUNT = "v5_home_banner_click_count";
    /** 估值按钮点击次数 **/
    public static final String KEY_HOME_VALUATION_CLICK_COUNT = "v5_home_valuation_click_count";
    /** 全款/贷款按钮点击次数 **/
    public static final String KEY_HOME_BUY_CAR_CLICK_COUNT = "v5_home_buy_car_click_count";
    /** 卡片点击次数（按编号区分卡片） **/
    public static final String KEY_HOME_RECOMMEND_CARD_CLICK_COUNT = "v5_home_card_click_count";

    /** 右侧边栏统计 **/
    /** 登录按钮， 点击次数 **/
    public static final String KEY_SIDEBAR_LOGIN_CLICK_COUNT = "v5_sidebar_login_click_count";
    /** 消息中心， 点击次数 **/
    public static final String KEY_SIDEBAR_MESSAGE_CENTER_CLICK_COUNT = "v5_sidebar_message_center_click_count";
    /** 最新活动， 点击次数 **/
    public static final String KEY_SIDEBAR_LATEST_ACTIVITY_CLICK_COUNT = "v5_sidebar_latest_activity_click_count";
    /** 最新资讯， 点击次数 **/
    public static final String KEY_SIDEBAR_LATEST_NEWS_CLICK_COUNT = "v5_sidebar_latest_news_click_count";
    /** 订阅车源， 点击次数 **/
    public static final String KEY_SIDEBAR_SUBSCRIBE_CAR_CLICK_COUNT = "v5_sidebar_subscribe_car_click_count";
    /** 关注车源， 点击次数 **/
    public static final String KEY_SIDEBAR_FAVORITE_CAR_CLICK_COUNT = "v5_sidebar_favorite_car_click_count";
    /** 违章查询， 点击次数 **/
    public static final String KEY_SIDEBAR_OFFENCE_QUERY_CLICK_COUNT = "v5_sidebar_offence_query_click_count";
    /** 车辆置换， 点击次数 **/
    public static final String KEY_SIDEBAR_CAR_REPLACE_CLICK_COUNT = "v5_sidebar_car_replace_click_count";
    /** 设置按钮， 点击次数 **/
    public static final String KEY_SIDEBAR_SETTING_CLICK_COUNT = "v5_sidebar_setting_click_count";

    /** 车管家统计 **/
    /** 下次打开App直接显示车管家提示，点“是”的次数 **/
    public static final String KEY_CAR_MANAGER_SHOW_YES_CLICK_COUNT = "v5_car_manager_show_yes_click_count";
    /** 下次打开App直接显示车管家提示，点“否”的次数 **/
    public static final String KEY_CAR_MANAGER_SHOW_NO_CLICK_COUNT = "v5_car_manager_show_no_click_count";

}
