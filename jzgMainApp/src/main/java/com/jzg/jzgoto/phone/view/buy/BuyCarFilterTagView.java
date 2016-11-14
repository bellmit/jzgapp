package com.jzg.jzgoto.phone.view.buy;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jzg.jzgoto.phone.constant.Constant;
import com.jzg.jzgoto.phone.global.AppContext;
import com.jzg.jzgoto.phone.models.business.buy.BuyCarFilterIndexModel;

import java.util.ArrayList;
import java.util.List;
import com.jzg.jzgoto.phone.R;
import com.jzg.jzgoto.phone.models.business.buy.BuyCarListAddConditionsParams;
import com.jzg.jzgoto.phone.models.business.buy.BuyCarListAddConditionsResult;
import com.jzg.jzgoto.phone.services.business.buy.BuyCarService;
import com.jzg.jzgoto.phone.services.common.OnRequestFinishListener;
import com.jzg.jzgoto.phone.tools.CountClickTool;
import com.jzg.jzgoto.phone.tools.DialogManager;
import com.jzg.jzgoto.phone.tools.MessageUtils;
import com.jzg.jzgoto.phone.tools.ShowDialogTool;

/**
 * Created by WY on 2016/9/13.
 * Function : 买车 标签界面
 */
public class BuyCarFilterTagView extends LinearLayout implements OnRequestFinishListener{

    public BuyCarFilterTagView(Context context) {
        super(context);
        initView(context);
    }

    public BuyCarFilterTagView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public BuyCarFilterTagView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView(context);
    }
    private TagFlowLayout mTagFlowLayout;
    private TextView mClearTags;

    private void initView(Context context){
        View view = LayoutInflater.from(context).inflate(R.layout.view_buycar_filtertag_layout, null);
        mTagFlowLayout = (TagFlowLayout) view.findViewById(R.id.view_buycar_flterTag_view);
        mClearTags = (TextView) view.findViewById(R.id.view_buycar_filtertag_subscribe_TextView);
        mClearTags.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //添加订阅
                addConditionsRequest(mToFilterParams);
            }
        });
        BuyCarFilterTagView.this.setPadding(0, -120, 0, 0);
        this.addView(view);
    }

    public void addConditionsRequest(BuyCarFilterIndexModel toFilterParams){
        CountClickTool.onEvent(getContext(), "V5_buyCar_add_condition");
        if (AppContext.isLogin()) {
            BuyCarListAddConditionsParams params = new BuyCarListAddConditionsParams();
            params.uid = AppContext.mLoginResultModels.getId();
            params.CSUserType = toFilterParams.getParams().CSUserType;
            params.ModelLevel = toFilterParams.getParams().ModelLevel;
            if(!"0".equals(params.ModelLevel)){
                params.ModelLevelName = toFilterParams.getCarStyleText();
            }
            params.MakeID = toFilterParams.getParams().MakeID;
            params.ModelID = toFilterParams.getParams().ModelID;
            params.BeginSellPrice = toFilterParams.getParams().BeginSellPrice;
            params.EndSellPrice = toFilterParams.getParams().EndSellPrice;
            params.BeginCarAge = toFilterParams.getParams().BeginCarAge;
            params.EndCarAge = toFilterParams.getParams().EndCarAge;
            params.BeginMileage = toFilterParams.getParams().BeginMileage;
            params.EndMileage = toFilterParams.getParams().EndMileage;
            params.BsqSimpleValue = toFilterParams.getParams().BsqSimpleValue;
            params.BeginPL = toFilterParams.getParams().BeginPL;
            params.EndPL = toFilterParams.getParams().EndPL;
            params.PlatForm = toFilterParams.getParams().CarSourceFrom;
            params.Countries = toFilterParams.getParams().Countries;
            params.Seats = toFilterParams.getParams().Seats;
            params.CarType = toFilterParams.getParams().CarType;
            params.CityID = toFilterParams.getParams().CityID;
            ShowDialogTool.showLoadingDialog(getContext());
            new BuyCarService(getContext(),this).toResuestBuyCarAddConditions(params,
                    BuyCarListAddConditionsResult.class,R.id.request_buy_car_add_condition);
        } else {
            DialogManager.toLoginDialog(getContext());
        }
    }

    @Override
    public void onRequestSuccess(Message msg) {
        ShowDialogTool.dismissLoadingDialog();
        if(msg.what == R.id.request_buy_car_add_condition){
            BuyCarListAddConditionsResult result =(BuyCarListAddConditionsResult) msg.obj;
            if(result.getStatus() == Constant.SUCCESS){
                ShowDialogTool.showCenterToast(getContext(),"订阅成功");
            }else if(result.getStatus() == 102){
                ShowDialogTool.showCenterToast(getContext(),result.getMsg());
            }else{
                ShowDialogTool.showCenterToast(getContext(),"添加失败");
            }
        }
    }

    @Override
    public void onRequestFault(Message msg) {
        ShowDialogTool.dismissLoadingDialog();
        ShowDialogTool.showCenterToast(getContext(),getResources().getString(R.string.error_net));
    }

    private List<TagItemModel> mTagsList = new ArrayList<>();
    private BuyCarFilterIndexModel mToFilterParams = null;
    public void initTagsShow(BuyCarFilterIndexModel toFilterParams){
        if(toFilterParams!=null){
            mToFilterParams = toFilterParams;
            mTagFlowLayout.removeAllViews();
            mTagsList.clear();
            if("2".equals(toFilterParams.getParams().CarType)){
                //新车
                initTagListNewCar();
                mClearTags.setVisibility(View.GONE);
            }else{
                //全部、二手车
                initTagListAll();
                mClearTags.setVisibility(View.VISIBLE);
            }

            if(mTagsList.size()>0){
                MarginLayoutParams lp = new MarginLayoutParams(
                        LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
                lp.leftMargin = 5;
                lp.rightMargin = 10;
                lp.topMargin = 10;
                lp.bottomMargin = 5;
                for(int i = 0; i < mTagsList.size(); i ++){
                    if(!TextUtils.isEmpty(mTagsList.get(i).getTagText())){
                        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_buycar_filtertag_layout,null);
                        TextView textView = (TextView) view.findViewById(R.id.item_buycar_title_tag_text);
                        TextView delView = (TextView) view.findViewById(R.id.item_buycar_title_tag_del);
                        textView.setText(mTagsList.get(i).getTagText());
                        delView.setOnClickListener(new TagClickListener(mTagsList.get(i)).tagClickListener);
                        mTagFlowLayout.addView(view,lp);
                    }
                }
                BuyCarFilterTagView.this.setPadding(0, 0, 0, 0);
            }else{
                BuyCarFilterTagView.this.setPadding(0, -120, 0,0);
            }
        }
    }
    private Handler mHandler = new Handler(){
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case R.id.del_tag_refresh:
                    TagItemModel model = (TagItemModel) msg.obj;
                    //删除Tag
                    delTagParams(model);
                    break;

                default:
                    break;
            }
        };
    };
    private void delTagParams(TagItemModel model){
        int delIndex = -1;
        for(int i=0;i<mTagsList.size();i++){
            if(mTagsList.get(i).getTagType().equals(model.getTagType())){
                delIndex = i;
            }
        }
        if(delIndex!=-1){
            mTagsList.remove(delIndex);
            mTagFlowLayout.removeViewAt(delIndex);
            delIndex = -1;
            initParamsDel(model.getTagType());
        }
        for(int i=0;i<mTagsList.size();i++){
            //判断是否为品牌，是否有车系
            if(model.getTagType().equals(Constant.TAG_BRAND)){
                if(mTagsList.get(i).tagType.equals(Constant.TAG_MODE)){
                    delIndex = i;
                }
            }
        }
        if(delIndex!=-1){
            mTagsList.remove(delIndex);
            mTagFlowLayout.removeViewAt(delIndex);
            delIndex = -1;
        }
    }
    private void initParamsDel(String delType){
        switch (delType) {
            case Constant.TAG_BRAND:
                mToFilterParams.setBrandName("");
                mToFilterParams.getParams().MakeID = "0";
                mToFilterParams.setModeName("");
                mToFilterParams.getParams().ModelID= "0";
                break;
            case Constant.TAG_MODE:
                mToFilterParams.setModeName("");
                mToFilterParams.getParams().ModelID= "0";
                break;
            case Constant.TAG_PRICE:
                mToFilterParams.setPriceIndex(0);
                mToFilterParams.getParams().BeginSellPrice= "";
                mToFilterParams.getParams().EndSellPrice= "";
                mToFilterParams.setPriceText("");
                break;
            case Constant.TAG_STYPE:
                mToFilterParams.setSourceType(0);
                mToFilterParams.getParams().CSUserType = "0";
                break;
            case Constant.TAG_TYPE:
                mToFilterParams.setCarStyleIndex(0);
                mToFilterParams.setCarStyleText("不限");
                mToFilterParams.getParams().ModelLevel = "";
                break;
            case Constant.TAG_BIANSU:
                mToFilterParams.setBiansuxiang(0);
                mToFilterParams.getParams().BsqSimpleValue = "0";
                break;
            case Constant.TAG_CARAGE:
                mToFilterParams.setCheling(0);
                mToFilterParams.getParams().BeginCarAge = "";
                mToFilterParams.getParams().EndCarAge = "";
                break;
            case Constant.TAG_MILEAGE:
                mToFilterParams.setLicheng(0);
                mToFilterParams.getParams().BeginMileage= "";
                mToFilterParams.getParams().EndMileage = "";
                break;
            case Constant.TAG_PAILIANG:
                mToFilterParams.setPailiang(0);
                mToFilterParams.getParams().BeginPL= "";
                mToFilterParams.getParams().EndPL = "";
                break;
            case Constant.TAG_PLATFORM:
                mToFilterParams.setCarPlatformIndex(0);
                mToFilterParams.getParams().CarSourceFrom= "0";
                break;
            case Constant.TAG_CAESEATS:
                mToFilterParams.setCarSeatIndex(0);
                mToFilterParams.getParams().Seats= "0";
                break;
            case Constant.TAG_COUNTRY:
                mToFilterParams.setCountryIndex(0);
                mToFilterParams.getParams().Countries= "0";
                break;
            default:
                break;
        }
        mToFilterParams.getParams().PageIndex = 1;
        mRequestCallBack.toRequestBuyCarResult(mToFilterParams);
    }
    private void initTagListAll(){
        if(!TextUtils.isEmpty(mToFilterParams.getBrandName())){
            if(!"不限".equals(mToFilterParams.getBrandName()))
                mTagsList.add(new TagItemModel(
                        mToFilterParams.getBrandName(),Constant.TAG_BRAND));
        }
        if(!TextUtils.isEmpty(mToFilterParams.getModeName())){
            mTagsList.add(new TagItemModel(
                    mToFilterParams.getModeName(),Constant.TAG_MODE));
        }
        if(mToFilterParams.getSourceType()!=0){
            mTagsList.add(new TagItemModel(
                    Constant.BUY_CAR_FILTER_SOUYCE_TYPE[mToFilterParams.getSourceType()],
                    Constant.TAG_STYPE));
        }
        if(mToFilterParams.getCarPlatformIndex()!=0){
            mTagsList.add(new TagItemModel(
                    Constant.BUY_CAR_FILTER_PLATFORM_TYPE[mToFilterParams.getCarPlatformIndex()],
                    Constant.TAG_PLATFORM));
        }
        if(!TextUtils.isEmpty(mToFilterParams.getCarStyleText())
                &mToFilterParams.getCarStyleIndex()!=0){
            mTagsList.add(new TagItemModel(
                    mToFilterParams.getCarStyleText(),Constant.TAG_TYPE));
        }
        if(!TextUtils.isEmpty(mToFilterParams.getPriceText())&&mToFilterParams.getPriceIndex()==8){
            mTagsList.add(new TagItemModel(
                    mToFilterParams.getPriceText(),
                    Constant.TAG_PRICE));
            mToFilterParams.setPriceText("");
        }else if((mToFilterParams.getPriceIndex()!=0&&TextUtils.isEmpty(mToFilterParams.getPriceText())
                &&!"不限".equals(Constant.getFilterPriceList().get(mToFilterParams.getPriceIndex()).getText()))){
            mTagsList.add(new TagItemModel(
                    Constant.getFilterPriceList().get(mToFilterParams.getPriceIndex()).getText(),
                    Constant.TAG_PRICE));
            mToFilterParams.setPriceText("");
        }else if(mToFilterParams.getPriceIndex()==0&&!TextUtils.isEmpty(mToFilterParams.getPriceText())){
            mTagsList.add(new TagItemModel(
                    mToFilterParams.getPriceText(),
                    Constant.TAG_PRICE));
        //    mToFilterParams.setPriceText("");
        }
//        if(mToFilterParams.getPriceIndex()!=0
//                &!"不限".equals(Constant.getFilterPriceList().get(mToFilterParams.getPriceIndex()).getText())){
//            mTagsList.add(new TagItemModel(
//                    Constant.getFilterPriceList().get(mToFilterParams.getPriceIndex()).getText(),
//                    Constant.TAG_PRICE));
//            mToFilterParams.setPriceText("");
//        }
//        if(!TextUtils.isEmpty(mToFilterParams.getPriceText())) {
//            mTagsList.add(new TagItemModel(
//                    mToFilterParams.getPriceText(),
//                    Constant.TAG_PRICE));
//        }
        if(mToFilterParams.getCheling()!=0){
            mTagsList.add(new TagItemModel(
                    Constant.BUY_CAR_FILTER_CARAGR[mToFilterParams.getCheling()],
                    Constant.TAG_CARAGE));
        }
        if(mToFilterParams.getLicheng()!=0){
            mTagsList.add(new TagItemModel(Constant.BUY_CAR_FILTER_MILEAGE[mToFilterParams.getLicheng()],
                    Constant.TAG_MILEAGE));
        }
        if(mToFilterParams.getPailiang()!=0){
            mTagsList.add(new TagItemModel(Constant.BUY_CAR_FILTER_PAILIANG[mToFilterParams.getPailiang()],
                    Constant.TAG_PAILIANG));
        }
        if(mToFilterParams.getBiansuxiang()!=0){
            mTagsList.add(new TagItemModel(
                    Constant.BUY_CAR_FILTER_BIANSU_TYPE[mToFilterParams.getBiansuxiang()],
                    Constant.TAG_BIANSU));
        }
        if(mToFilterParams.getCarSeatIndex()!=0){
            mTagsList.add(new TagItemModel(Constant.BUY_CAR_FILTER_SEATS[mToFilterParams.getCarSeatIndex()],
                    Constant.TAG_CAESEATS));
        }
        if(mToFilterParams.getCountryIndex()!=0){
            mTagsList.add(new TagItemModel(Constant.BUY_CAR_FILTER_COUNTRY[mToFilterParams.getCountryIndex()],
                    Constant.TAG_COUNTRY));
        }
    }

    private void initTagListNewCar(){
        if(!TextUtils.isEmpty(mToFilterParams.getBrandName())){
            if(!"不限".equals(mToFilterParams.getBrandName()))
                mTagsList.add(new TagItemModel(
                        mToFilterParams.getBrandName(),Constant.TAG_BRAND));
        }
        if(!TextUtils.isEmpty(mToFilterParams.getModeName())){
            mTagsList.add(new TagItemModel(
                    mToFilterParams.getModeName(),Constant.TAG_MODE));
        }
        if(!TextUtils.isEmpty(mToFilterParams.getCarStyleText())
                &mToFilterParams.getCarStyleIndex()!=0){
            mTagsList.add(new TagItemModel(
                    mToFilterParams.getCarStyleText(),Constant.TAG_TYPE));
        }
//        if(mToFilterParams.getPriceIndex()!=0
//                &!"不限".equals(Constant.getFilterPriceList().get(mToFilterParams.getPriceIndex()).getText())){
//            mTagsList.add(new TagItemModel(
//                    Constant.getFilterPriceList().get(mToFilterParams.getPriceIndex()).getText(),
//                    Constant.TAG_PRICE));
//            mToFilterParams.setPriceText("");
//        }
        if(!TextUtils.isEmpty(mToFilterParams.getPriceText())&&mToFilterParams.getPriceIndex()==8){
            mTagsList.add(new TagItemModel(
                    mToFilterParams.getPriceText(),
                    Constant.TAG_PRICE));
            mToFilterParams.setPriceText("");
        }else if((mToFilterParams.getPriceIndex()!=0&&TextUtils.isEmpty(mToFilterParams.getPriceText())
                &&!"不限".equals(Constant.getFilterPriceList().get(mToFilterParams.getPriceIndex()).getText()))){
            mTagsList.add(new TagItemModel(
                    Constant.getFilterPriceList().get(mToFilterParams.getPriceIndex()).getText(),
                    Constant.TAG_PRICE));
            mToFilterParams.setPriceText("");
        }else if(mToFilterParams.getPriceIndex()==0&&!TextUtils.isEmpty(mToFilterParams.getPriceText())){
            mTagsList.add(new TagItemModel(
                    mToFilterParams.getPriceText(),
                    Constant.TAG_PRICE));
        //    mToFilterParams.setPriceText("");
        }
//        if(!TextUtils.isEmpty(mToFilterParams.getPriceText())){
//            mTagsList.add(new TagItemModel(
//                    mToFilterParams.getPriceText(),
//                    Constant.TAG_PRICE));
//        }
        if(mToFilterParams.getPailiang()!=0){
            mTagsList.add(new TagItemModel(Constant.BUY_CAR_FILTER_PAILIANG[mToFilterParams.getPailiang()],
                    Constant.TAG_PAILIANG));
        }
        if(mToFilterParams.getBiansuxiang()!=0){
            mTagsList.add(new TagItemModel(
                    Constant.BUY_CAR_FILTER_BIANSU_TYPE[mToFilterParams.getBiansuxiang()],
                    Constant.TAG_BIANSU));
        }
        if(mToFilterParams.getCarSeatIndex()!=0){
            mTagsList.add(new TagItemModel(Constant.BUY_CAR_FILTER_SEATS[mToFilterParams.getCarSeatIndex()],
                    Constant.TAG_CAESEATS));
        }
        if(mToFilterParams.getCountryIndex()!=0){
            mTagsList.add(new TagItemModel(Constant.BUY_CAR_FILTER_COUNTRY[mToFilterParams.getCountryIndex()],
                    Constant.TAG_COUNTRY));
        }
    }
    class TagClickListener{
        private TagItemModel model;

        public TagClickListener(TagItemModel model) {
            super();
            this.model = model;
        }

        private OnClickListener tagClickListener = new OnClickListener() {

            @Override
            public void onClick(View v) {
                MessageUtils.sendMessage(mHandler, R.id.del_tag_refresh, model);
            }
        };
    }
    private RequestBuyCarCallBack mRequestCallBack;

    public void setRequestBuyCarCallback(RequestBuyCarCallBack callback) {
        mRequestCallBack = callback;
    }
    public interface RequestBuyCarCallBack{
        public void toRequestBuyCarResult(BuyCarFilterIndexModel toFilterParams);
    }
    class TagItemModel{

        private String tagText;
        private String tagType;

        public TagItemModel(String tagText, String tagType) {
            super();
            this.tagText = tagText;
            this.tagType = tagType;
        }
        public String getTagText() {
            return tagText;
        }
        public void setTagText(String tagText) {
            this.tagText = tagText;
        }
        public String getTagType() {
            return tagType;
        }
        public void setTagType(String tagType) {
            this.tagType = tagType;
        }
    }
}
