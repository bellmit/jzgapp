package com.jzg.jzgoto.phone.constant;

import java.util.ArrayList;
import java.util.List;

import com.jzg.jzgoto.phone.R;
import com.jzg.jzgoto.phone.models.business.buy.PopListDataModel;


public class Constant {

	public static final int SUCCESS = 100;//请求成功
	public static final int QUERYCAR_REGION = 0x00000010;	//选择地区

	/**
	 * 估值上牌时间
	 */
	public static final int Valuation_Time_MSG = 0x00000020;

	public static final String[] SHIGU_STR= {
			"未发生任何事故","事故仅造成外观部件受损","事故造成外观及内部部件受损，但未伤至车辆骨架","事故造成车辆骨架受损"
	};
	public static final String[] WAIGUAN_STR= {
			"车身漆面未涉及任何修复","存在轻微划痕；车身喷漆未涉及钣金修复","全车部分划伤；车身喷漆涉及部分钣金修复","全身大量划伤；车身喷漆涉及大量钣金修复"
	};
	public static final String[] NEISHI_STR= {
			"无任何磨损、脏污","仅1-2处磨损、脏污","仅3-5处磨损、脏污","大于5处磨损、脏污"
	};
	public static final String[] FADONGJI_STR= {
			"发动机、变速器性能良好，无任何异样及修理情况","发动机、变速器性能良好，有过轻微修理情况","发动机、变速器性能良好，有过重大修理情况","发动机、变速器需要重大维修或更换"
	};
	public static final String[] YIBIAO_STR= {
			"无任何电子、电器、按键故障","仅1处电子、电器、按键故障","部分电子、电器、按键故障","大量电子、电器、按键故障"
	};
	public static final String[] BAOYANG_STR= {
			"全部4S店保养且按厂家要求保养","全部4S店保养部分按厂家要求保养","部分4S店保养","非4S店保养"
	};

	public static final String[] BUY_CAR_FILTER_PRICE_BEGIN={
			"0","0",	"50000", "100000","150000","200000","250000","300000","500000"
	};
	public static final String[] BUY_CAR_FILTER_PRICE_END={
			"0","50000","100000","150000","200000","250000","300000","500000","99990000"
	};
	public static final String[] BUY_CAR_FILTER_SOUYCE_TYPE ={
			"全部","个人","商家"
	};
	public static final String[] BUY_CAR_FILTER_PLATFORM_TYPE={
			"全部","精真估","易车二手车","大搜车","58","二手车之家","看车网"
	};
	public static final String[] BUY_CAR_FILTER_BIANSU_TYPE={
			"不限","手动","自动"
	};
	public static final String[] BUY_CAR_FILTER_CARAGR={
			"不限","1年以内","1-3年","3-5年","5年以上"
	};
	public static final String[] BUY_CAR_FILTER_MILEAGE={
			"不限","1万以下","3万以下","5万以下","10万以下","10万以上"
	};
	public static final String[] BUY_CAR_FILTER_PAILIANG={
			"不限","1.0及以下","1.0-1.6","1.7-2.0","2.1-2.5","2.6-3.5","3.5及以上"
	};
	public static final String[] BUY_CAR_FILTER_SEATS={
			"不限","2座","5座","7座","7座以上"
	};
	public static final String[] BUY_CAR_FILTER_COUNTRY={
			"不限","国产","美","日","韩","德","英","法","其他"
	};
	//0全部，1中国，2美国，3日本，4韩国，5德国，6英国，7法国，999其他；
	/**
	 * 筛选Tag标记
	 */
	public static final String TAG_BRAND = "tag_car_brand";
	public static final String TAG_MODE = "tag_car_mode";
	public static final String TAG_PRICE = "tag_car_price";
	public static final String TAG_STYPE = "tag_source_type";
	public static final String TAG_TYPE = "tag_car_type";
	public static final String TAG_BIANSU = "tag_car_biansu";
	public static final String TAG_CARAGE = "tag_car_age";
	public static final String TAG_MILEAGE = "tag_car_mileage";
	public static final String TAG_PAILIANG = "tag_car_pailiang";
	public static final String TAG_PLATFORM = "tag_car_platform";
	public static final String TAG_CAESEATS = "tag_car_seats";
	public static final String TAG_COUNTRY = "tag_car_country";


	public static List<PopListDataModel> getFilterPriceList(){
		List<PopListDataModel > list = new ArrayList<>();
		list.add(new PopListDataModel("不限", "0",
				R.color.text_blue));
		list.add(new PopListDataModel("5万以下", "1",
				R.color.text_item_lightgrey));
		list.add(new PopListDataModel("5-10万", "2",
				R.color.text_item_lightgrey));
		list.add(new PopListDataModel("10-15万", "3",
				R.color.text_item_lightgrey));
		list.add(new PopListDataModel("15-20万", "4",
				R.color.text_item_lightgrey));
		list.add(new PopListDataModel("20-25万", "5",
				R.color.text_item_lightgrey));
		list.add(new PopListDataModel("25-30万", "6",
				R.color.text_item_lightgrey));
		list.add(new PopListDataModel("30-50万", "7",
				R.color.text_item_lightgrey));
		list.add(new PopListDataModel("50万以上", "8",
				R.color.text_item_lightgrey));
		return list;
	}
	public static final String[] BUY_CAR_FILTER_PRICE={
			"不限","5万以下","5-10万","10-15万","15-20万","20-25万","25-30万","30-50万","50万以上"
	};

	public static final String[] BUY_CAR_FILTER_TYPE = {
			"不限","紧凑型","中型车","大型车","SUV","MPV"
	};
	public static final String[] BUY_CAR_FILTER_SEATS_VALUES = {
			"0","2","5","7","999"
	};

//	private final String[] mStyleStr = new String[]{
//			"微型车","小型车","紧凑型车","中型车","中大型车","大型车",
//			"小型SUV","紧凑型SUV","中型SUV","中大型SUV","全尺寸SUV",
//			"入门级跑车","中级跑车","超级跑车","小型MPV","大型MPV"};
	public static List<PopListDataModel> getCarStyleList(){
		List<PopListDataModel > list = new ArrayList<>();
		list.add(new PopListDataModel("微型车", "1",
				R.color.text_item_lightgrey));
		list.add(new PopListDataModel("小型车", "2",
				R.color.text_item_lightgrey));
		list.add(new PopListDataModel("紧凑型车", "3",
				R.color.text_item_lightgrey));
		list.add(new PopListDataModel("中型车", "4",
				R.color.text_item_lightgrey));
		list.add(new PopListDataModel("中大型车", "5",
				R.color.text_item_lightgrey));
		list.add(new PopListDataModel("大型车", "6",
				R.color.text_item_lightgrey));
		list.add(new PopListDataModel("小型SUV", "7",
				R.color.text_item_lightgrey));
		list.add(new PopListDataModel("紧凑型SUV", "8",
				R.color.text_item_lightgrey));
		list.add(new PopListDataModel("中型SUV", "9",
				R.color.text_item_lightgrey));
		list.add(new PopListDataModel("中大型SUV", "10",
				R.color.text_item_lightgrey));
		list.add(new PopListDataModel("全尺寸SUV", "11",
				R.color.text_item_lightgrey));
		list.add(new PopListDataModel("入门级跑车", "12",
				R.color.text_item_lightgrey));
		list.add(new PopListDataModel("中级跑车", "13",
				R.color.text_item_lightgrey));
		list.add(new PopListDataModel("超级跑车", "14",
				R.color.text_item_lightgrey));
		list.add(new PopListDataModel("小型MPV", "15",
				R.color.text_item_lightgrey));
		list.add(new PopListDataModel("大型MPV", "16",
				R.color.text_item_lightgrey));
		return list;
	}
}
