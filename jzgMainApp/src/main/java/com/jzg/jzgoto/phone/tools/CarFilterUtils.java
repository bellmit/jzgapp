package com.jzg.jzgoto.phone.tools;


import android.text.TextUtils;
import com.jzg.jzgoto.phone.constant.Constant;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


public class CarFilterUtils {
	private static CarFilterUtils instance;

	public static class CarFilterDataType{
		private int mIndex;
		private String mFilterText;

		public void setIndex(int index){
			mIndex = index;
		}
		public int getIndex(){
			return mIndex;
		}
		public void setFilterText(String text){
			mFilterText = text;
		}
		public String getFilterText(){
			return mFilterText;
		}
	};


	private CarFilterUtils() {
		toInitCarAge();
		toInitCarMileage();
		toInitCarVolume();
	}

	public static CarFilterUtils getInstance() {
		if (instance == null) {
			instance = new CarFilterUtils();
		}
		return instance;
	}

	private Map<Integer,String> mBeginCarAge = new HashMap<Integer,String>();
	private Map<Integer,String> mEndCarAge = new HashMap<Integer,String>();
	private void toInitCarAge(){
		int year = Calendar.getInstance().get(Calendar.YEAR);
		int month = Calendar.getInstance().get(Calendar.MONTH)+1;
		int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);

		String monthStr = month+"";
		if(month<10)monthStr = "0"+month;

		String dayStr = day+"";
		if(day<10)dayStr = "0"+day;

		mBeginCarAge.put(0,"0");mEndCarAge.put(0,"0");
		mBeginCarAge.put(1,(year-1)+""+monthStr+dayStr);mEndCarAge.put(1,year+""+monthStr+dayStr);
		mBeginCarAge.put(2,(year-3)+""+monthStr+dayStr);mEndCarAge.put(2,(year-1)+""+monthStr+dayStr);
		mBeginCarAge.put(3,(year-5)+""+monthStr+dayStr);mEndCarAge.put(3,(year-3)+""+monthStr+dayStr);
		mBeginCarAge.put(4,"19900101");mEndCarAge.put(4,(year-5)+""+monthStr+dayStr);
		//不限、1年以内、1-3年、3-5年、5年以上
	}

	public int getYearDelta(String beginAge, String endAge){
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		long milliseconds = 0;
		long yearDelta = 0;
		try {
			milliseconds = dateFormat.parse(endAge).getTime() - dateFormat.parse(beginAge).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		yearDelta =  milliseconds / (1000 * 60 * 60 * 24 * 365);
		return (int)yearDelta;
	}

	public CarFilterDataType getCarAgeFilterTagData(String beginAge, String endAge){
		CarFilterDataType filterData = new CarFilterDataType();
		String tagString = "";
		int index = 0;
		int yearDelta =  0;
		int itemYearDelta = 0;
		if (!TextUtils.isEmpty(beginAge) && !TextUtils.isEmpty(endAge) && beginAge.length() == 8 && endAge.length() == 8) {
			yearDelta = getYearDelta(beginAge, endAge);
			for (int i = 0; i < mBeginCarAge.size(); i++) {
				itemYearDelta = getYearDelta(mBeginCarAge.get(i), mEndCarAge.get(i));
				if (yearDelta == itemYearDelta) {
					index = i;
					break;
				}
			}
		}
		tagString = Constant.BUY_CAR_FILTER_CARAGR[index];
		filterData.setIndex(index);
		filterData.setFilterText(tagString);
		return filterData;
	}

	private Map<Integer,String> mBeginMileage = new HashMap<Integer,String>();
	private Map<Integer,String> mEndMileage = new HashMap<Integer,String>();
	private void toInitCarMileage(){
		mBeginMileage.put(0,"0");mEndMileage.put(0,"0");
		mBeginMileage.put(1,"0");mEndMileage.put(1,"10000");
		mBeginMileage.put(2,"0");mEndMileage.put(2,"30000");
		mBeginMileage.put(3,"0");mEndMileage.put(3,"50000");
		mBeginMileage.put(4,"0");mEndMileage.put(4,"100000");
		mBeginMileage.put(5,"100000");mEndMileage.put(5,"990000");
		//	不限、1万公里以下、3万公里以下、5万公里以下、10万公里以下、10万公里以上
	}
	public CarFilterDataType getCarMileageFilterTagData(String beginMileage, String endMileage){
		CarFilterDataType filterData = new CarFilterDataType();
		String tagString = "";
		int index = 0;
		if (!TextUtils.isEmpty(endMileage)) {
			for (int i = 0; i < mEndMileage.size(); i++) {
				if (Integer.valueOf(endMileage) <= Integer.valueOf(mEndMileage.get(i))) {
					index = i;
					break;
				}
			}
		}
		tagString = Constant.BUY_CAR_FILTER_MILEAGE[index];
		filterData.setIndex(index);
		filterData.setFilterText(tagString);
		return filterData;
	}


	private Map<Integer,String> mBeginPailiang = new HashMap<Integer,String>();
	private Map<Integer,String> mEndPailiang = new HashMap<Integer,String>();
	private void toInitCarVolume(){
		mBeginPailiang.put(0,"0");mEndPailiang.put(0,"0");
		mBeginPailiang.put(1,"0");mEndPailiang.put(1,"1.0");
		mBeginPailiang.put(2,"1.0");mEndPailiang.put(2,"1.6");
		mBeginPailiang.put(3,"1.7");mEndPailiang.put(3,"2.0");
		mBeginPailiang.put(4,"2.1");mEndPailiang.put(4,"2.5");
		mBeginPailiang.put(5,"2.6");mEndPailiang.put(5,"3.5");
		mBeginPailiang.put(6,"3.5");mEndPailiang.put(6,"100");
	}

	public CarFilterDataType getCarPaiLiangFilterTagData(String beginPaiLiang, String endPaiLiang){
		CarFilterDataType filterData = new CarFilterDataType();
		String tagString = "";
		int index = 0;
		if (!TextUtils.isEmpty(beginPaiLiang) && !TextUtils.isEmpty(endPaiLiang)) {
			for (int i = 0; i < mBeginPailiang.size(); i++) {
				if (Float.valueOf(beginPaiLiang) >= Float.valueOf(mBeginPailiang.get(i))
						&& Float.valueOf(endPaiLiang) <= Float.valueOf(mEndPailiang.get(i))) {
					index = i;
					break;
				}
			}
		}
		tagString = Constant.BUY_CAR_FILTER_PAILIANG[index];
		filterData.setIndex(index);
		filterData.setFilterText(tagString);
		return filterData;
	}

	public CarFilterDataType getCarPriceFilterTagData(String beginPrice, String endPrice){
		CarFilterDataType filterData = new CarFilterDataType();
		String tagString = "";
		int index = 0;
		if (TextUtils.isEmpty(beginPrice) || TextUtils.isEmpty(endPrice)){
			//不限
			tagString = Constant.BUY_CAR_FILTER_PRICE[0];
		}else {
			for (int i = 1; i < Constant.BUY_CAR_FILTER_PRICE_END.length; i++) {
				if (endPrice.equals(Constant.BUY_CAR_FILTER_PRICE_END[i])
						&& beginPrice.equals(Constant.BUY_CAR_FILTER_PRICE_END[i-1])) {
					index = i;
					break;
				}
			}
			if (index == 0) {
				int beginValue = 0;
				int endValue = 0;
				if (endPrice.equals(Constant.BUY_CAR_FILTER_PRICE_END[Constant.BUY_CAR_FILTER_PRICE_END.length - 1])) {
					beginValue = Integer.valueOf(beginPrice) / 10000;
					tagString = beginValue + "-100万+";
				} else {
					beginValue = Integer.valueOf(beginPrice) / 10000;
					endValue = Integer.valueOf(endPrice) / 10000;
					if (endValue > 100){
						tagString = beginValue + "-100万+";
					}else if(beginValue == 0 && endValue == 0){
						tagString = Constant.BUY_CAR_FILTER_PRICE[index];
					} else {
						tagString = beginValue + "-" + endValue + "万";
					}
				}
			}else {
				tagString = Constant.BUY_CAR_FILTER_PRICE[index];
			}
		}
		filterData.setIndex(index);
		filterData.setFilterText(tagString);
		return filterData;
	}

	public CarFilterDataType getCarTypeFilterTagData(String carType){
		CarFilterDataType filterData = new CarFilterDataType();
		String tagString = "";
		int index = 0;
		if (!TextUtils.isEmpty(carType)) {
			int tempIndex = Integer.valueOf(carType);
			if (tempIndex >= 0 && tempIndex < Constant.BUY_CAR_FILTER_TYPE.length){
				index = tempIndex;
			}
		}
		tagString = Constant.BUY_CAR_FILTER_TYPE[index];
		filterData.setIndex(index);
		filterData.setFilterText(tagString);
		return filterData;
	}

	public CarFilterDataType getCarSeatsFilterTagData(String carSeats){
		CarFilterDataType filterData = new CarFilterDataType();
		String tagString = "";
		int index = 0;
		if (!TextUtils.isEmpty(carSeats)) {
			for (int i = 0; i < Constant.BUY_CAR_FILTER_SEATS_VALUES.length; i++) {
				if (Integer.valueOf(carSeats) <= Integer.valueOf(Constant.BUY_CAR_FILTER_SEATS_VALUES[i])) {
					index = i;
					break;
				}
			}
		}
		tagString = Constant.BUY_CAR_FILTER_SEATS[index];
		filterData.setIndex(index);
		filterData.setFilterText(tagString);
		return filterData;
	}

	public String getCarSourceFromTagString(String sourceType){
		String tagString = "";
		int type = Integer.valueOf(sourceType);
		switch(type){
			case 1:
				tagString = "精真估";
				break;
			case 3:
				tagString = "易车二手车";
				break;
			case 4:
				tagString = "大搜车";
				break;
			case 5:
				tagString = "58";
				break;
			case 6:
				tagString = "二手车之家";
				break;
			case 7:
				tagString = "7";
				break;
		}
		return tagString;
	}

	public String getCarUserTypeTagString(String userType){
		String tagString = "";
		int type = Integer.valueOf(userType);
		switch(type){
			case 1:
				tagString = "个人";
				break;
			case 2:
				tagString = "商家";
				break;
		}
		return tagString;
	}

}