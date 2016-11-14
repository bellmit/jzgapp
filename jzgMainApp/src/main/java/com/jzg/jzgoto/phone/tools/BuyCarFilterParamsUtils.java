package com.jzg.jzgoto.phone.tools;

import android.text.TextUtils;

import com.jzg.jzgoto.phone.models.CarConditionData;
import com.jzg.jzgoto.phone.models.business.buy.BuyCarFilterIndexModel;
import com.jzg.jzgoto.phone.models.business.buy.BuyCarListParams;

import java.util.Calendar;

/**
 * Created by WY on 2016/10/12.
 * Function :获取买车列表参数工具类
*/
public class BuyCarFilterParamsUtils {

    private static BuyCarFilterParamsUtils filterParamsUtils = new BuyCarFilterParamsUtils();
    private int beginPrice;

    public BuyCarFilterParamsUtils() {
        super();
    }

    public static synchronized BuyCarFilterParamsUtils getInstance(){
        return filterParamsUtils;
    }

    /**
     * 把订阅条件转为更多筛选参数
     * @param conditionData 订阅结果
     * @return 买车列表请求参数
     */
    public BuyCarFilterIndexModel getFilterIndexModel(CarConditionData conditionData){
        BuyCarFilterIndexModel filterModel = new BuyCarFilterIndexModel();
        filterModel.setParams(new BuyCarListParams());
        if(conditionData!=null){
            filterModel.getParams().CarSourceFrom = String.valueOf(conditionData.CarSourceFrom);
            if(filterModel.getParams().CarSourceFrom==null)filterModel.getParams().CarSourceFrom="0";
            filterModel.getParams().Seats = String.valueOf(conditionData.Seats);
            if(filterModel.getParams().Seats==null)filterModel.getParams().Seats="0";
            filterModel.getParams().CSUserType = String.valueOf(conditionData.CSUserType);
            if(filterModel.getParams().CSUserType==null)filterModel.getParams().CSUserType="0";
            filterModel.getParams().ModelLevel = String.valueOf(conditionData.ModelLevel);
            if(filterModel.getParams().ModelLevel==null)filterModel.getParams().ModelLevel="0";
            filterModel.getParams().MakeID = String.valueOf(conditionData.MakeID);
            if(filterModel.getParams().MakeID==null)filterModel.getParams().MakeID="0";
            ///
            filterModel.getParams().ModelID = String.valueOf(conditionData.ModelID);
            if(filterModel.getParams().ModelID==null)filterModel.getParams().ModelID="0";
            filterModel.getParams().CityID = String.valueOf(conditionData.CityID);
            if(filterModel.getParams().CityID==null)filterModel.getParams().CityID="0";
            filterModel.getParams().CityName = conditionData.CityName;
            if(filterModel.getParams().CityName==null)filterModel.getParams().CityName="";
            filterModel.getParams().BeginSellPrice = conditionData.BeginSellPrice;
            if(filterModel.getParams().BeginSellPrice==null)filterModel.getParams().BeginSellPrice="";
            filterModel.getParams().EndSellPrice = conditionData.EndSellPrice;
            if(filterModel.getParams().EndSellPrice==null)filterModel.getParams().EndSellPrice="";
            filterModel.getParams().BeginCarAge = String.valueOf(conditionData.BeginCarAge);
            if(filterModel.getParams().BeginCarAge==null)filterModel.getParams().BeginCarAge="";
            filterModel.getParams().EndCarAge = String.valueOf(conditionData.EndCarAge);
            if(filterModel.getParams().EndCarAge==null)filterModel.getParams().EndCarAge="";
            filterModel.getParams().BeginMileage = String.valueOf(conditionData.BeginMileage);
            if(filterModel.getParams().BeginMileage==null)filterModel.getParams().BeginMileage="";
            filterModel.getParams().EndMileage = String.valueOf(conditionData.EndMileage);
            if(filterModel.getParams().EndMileage==null)filterModel.getParams().EndMileage="";

            filterModel.getParams().BeginPL = conditionData.BeginPL;
            if(filterModel.getParams().BeginPL==null)filterModel.getParams().BeginPL="";
            filterModel.getParams().EndPL = conditionData.EndPL;
            if(filterModel.getParams().EndPL==null)filterModel.getParams().EndPL="";
            filterModel.getParams().BsqSimpleValue = String.valueOf(conditionData.BsqSimpleValue);
            if(filterModel.getParams().BsqSimpleValue==null)filterModel.getParams().BsqSimpleValue="";
            filterModel.getParams().Countries = String.valueOf(conditionData.Countries);
            if(filterModel.getParams().Countries==null)filterModel.getParams().Countries="0";

            filterModel.setSourceType(conditionData.CSUserType);
            filterModel.setCarStyleIndex(getCarStyleIIndx(conditionData.ModelLevel));
            filterModel.setCarStyleText(conditionData.ModelLevelName);
            filterModel.setBiansuxiang(getBsqSimpleIndex(conditionData.BsqSimpleValue));

            int carAgeIndex = CarFilterUtils.getInstance().getCarAgeFilterTagData(
                    String.valueOf(conditionData.BeginCarAge),String.valueOf(conditionData.EndCarAge)).getIndex();
            filterModel.setCheling(carAgeIndex);
            int mileageIndex = CarFilterUtils.getInstance().getCarMileageFilterTagData(
                    String.valueOf(conditionData.BeginMileage),String.valueOf(conditionData.EndMileage)).getIndex();
            filterModel.setLicheng(mileageIndex);
            int carPLIndex = CarFilterUtils.getInstance().getCarPaiLiangFilterTagData(
                    conditionData.BeginPL,conditionData.EndPL).getIndex();
            filterModel.setPailiang(carPLIndex);
            int priceIndex = CarFilterUtils.getInstance().getCarPriceFilterTagData(
                    conditionData.BeginSellPrice,conditionData.EndSellPrice).getIndex();
            filterModel.setPriceIndex(priceIndex);








            if(priceIndex==0){
                if(!TextUtils.isEmpty(conditionData.BeginSellPrice)
                        &&!TextUtils.isEmpty(conditionData.EndSellPrice)){
                    filterModel.getParams().BeginSellPrice = conditionData.BeginSellPrice;
                    filterModel.getParams().EndSellPrice = conditionData.EndSellPrice;
                    int beginPrice = 0;
                    int endPrice = 0;
                    if(Double.valueOf(conditionData.BeginSellPrice)>10000){
                        beginPrice =  (int) (Double.valueOf(conditionData.BeginSellPrice) / 10000);
                    }
                    if(Double.valueOf(conditionData.EndSellPrice)>10000){
                        endPrice = (int) (Double.valueOf(conditionData.EndSellPrice) / 10000);
                    }
                    if(beginPrice==0&&endPrice>0&&endPrice<9999){
                    //    filterModel.setPriceText("0-"+endPrice+"万");
                    }else if(beginPrice<1&&endPrice==9999){
                        filterModel.setPriceText("0-100万+");
                    }else if(beginPrice==100&&endPrice==9999){
                        filterModel.setPriceText("100万以上");
                    }else if(beginPrice==9999&&endPrice==9999){
                        filterModel.setPriceText("");
                    }else if(beginPrice>0&&beginPrice!=9999&&endPrice==9999){
                        filterModel.setPriceText(beginPrice+"-100万+");
                    }else{
                        filterModel.setPriceText(beginPrice+"-"+endPrice+"万");
                    }
                }
            }
            filterModel.setBrandName(conditionData.MakeName);
            filterModel.setModeName(conditionData.ModelName);
            filterModel.setCarPlatformIndex(getPlatformIndex(conditionData.CarSourceFrom));
            filterModel.setCarSeatIndex(getSeatIndex(conditionData.Seats));
            filterModel.setCountryIndex(getCountryIndex(conditionData.Countries));
        }
        return filterModel;
    }
    private int getCarAgeIndex(){
        int year = Calendar.getInstance().get(Calendar.YEAR);
        int month = Calendar.getInstance().get(Calendar.MONTH)+1;
        int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);

        String monthStr = month+"";
        if(month<10)monthStr = "0"+month;

        String dayStr = day+"";
        if(day<10)dayStr = "0"+day;

//        mBeginCarAge.put(0,"0");mEndCarAge.put(0,"0");
//        mBeginCarAge.put(1,(year-1)+""+monthStr+dayStr);mEndCarAge.put(1,year+""+monthStr+dayStr);
//        mBeginCarAge.put(2,(year-3)+""+monthStr+dayStr);mEndCarAge.put(2,(year-1)+""+monthStr+dayStr);
//        mBeginCarAge.put(3,(year-5)+""+monthStr+dayStr);mEndCarAge.put(3,(year-3)+""+monthStr+dayStr);
//        mBeginCarAge.put(4,"19900101");mEndCarAge.put(4,(year-5)+""+monthStr+dayStr);
        return 0;
    }
    private int getPlatformIndex(int carSourceFrom){
        int index = 0;
        //  1.精真估  3.易车二手车,4.大搜车,5.58,6.二手车之家,7.看车网
        if(carSourceFrom<2){
            index = carSourceFrom;
        }else if(carSourceFrom<=7){
            index = carSourceFrom-1;
        }
        return index;
    }
    private int getSeatIndex(int seatNumber){
        int index = 0;
        switch (seatNumber){
            case 999:
                index = 4;
                break;
            case 0:
                index = 0;
                break;
            case 2:
                index = 1;
                break;
            case 5:
                index = 2;
                break;
            case 7:
                index = 3;
                break;

        }
        return index;
    }
    private int getCountryIndex(int countryId){
        int index = 0;
        if(countryId==999){
            index = 8;
        }else if(countryId<=7){
            index = countryId;
        }
        return index;
    }
    private int getBsqSimpleIndex(int bsqSimpleId){
        int bsqIndex = 0;
        //	0：全部 2：手动 3：自动
        switch (bsqSimpleId){
            case 0:
                bsqIndex = 0;
                break;
            case 2:
                bsqIndex = 1;
                break;
            case 3:
                bsqIndex = 2;
                break;
        }
        return bsqIndex;
    }
    private int getCarStyleIIndx(int modelLevelId){
        int styleIndex = 0;
        switch (modelLevelId){
            case 0:
                //不限
                styleIndex = 0;
                break;
            case 3:
                //紧凑型
                styleIndex = 1;
                break;
            case 4:
                //中型车
                styleIndex = 2;
                break;
            case 6:
                //大型车
                styleIndex = 3;
                break;
            case 51:
                //SUV
                styleIndex = 4;
                break;
            case 53:
                //MPV
                styleIndex = 5;
                break;
        }
        return styleIndex;
    }
}
