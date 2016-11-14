package com.jzg.jzgoto.phone.view.valuation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.jzg.jzgoto.phone.R;
import com.jzg.jzgoto.phone.tools.ShowDialogTool;
import com.jzg.jzgoto.phone.activity.valuation.ValuationHedgeActivity.ItemBean;
import com.jzg.jzgoto.phone.view.ViewUtility;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Created by jzg on 2016/9/21.
 * Function :
 */
public class ValuationHedgeDialogView extends ValuationBaseView{
    public ValuationHedgeDialogView(Context context, AttributeSet attrs,
                               int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ValuationHedgeDialogView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ValuationHedgeDialogView(Context context) {
        super(context);
    }

    private ImageView mImgClose;
    private ValuationHedgeBarView mSelfHedgeBarView;

    private ImageView mImgCarPic;
    private TextView mTvLevelPic;
    private TextView mTvBrand;
    private TextView mTvModels;
    private Button mBtnCheck;

    @Override
    public void initView() {
        View.inflate(getContext(), R.layout.view_valuation_hedge_dialog_layout, this);
        mImgClose = (ImageView) findViewById(R.id.img_dialog_hedge_show_close);
        mSelfHedgeBarView = (ValuationHedgeBarView) findViewById(R.id.self_dialog_hedge_show_bar);

        mImgCarPic = (ImageView) findViewById(R.id.img_dialog_hedge_show_carpic);
        mTvLevelPic = (TextView) findViewById(R.id.tv_dialog_hedge_show_levelpic);
        mTvBrand = (TextView) findViewById(R.id.tv_dialog_hedge_show_brand);
        mTvModels = (TextView) findViewById(R.id.tv_dialog_hedge_show_models);
        mBtnCheck = (Button) findViewById(R.id.btn_dialog_hedge_show_check);
    }
    private ItemBean mItemBean = null;
    public void setBarValues(ItemBean bean){
        mItemBean = bean;
        mTvBrand.setText(bean.makeName);
        mTvModels.setText(bean.modelName);

        if(TextUtils.isEmpty(bean.modelPic)){
            mImgCarPic.setImageResource(R.drawable.jingzhengu_moren);
        } else {
            ImageLoader.getInstance().displayImage(bean.modelPic, mImgCarPic);
        }

        mSelfHedgeBarView.toShowData(bean, getSizeRatio());
        mBtnCheck.setText("查看在售 " + bean.modelName);
    }
    public void initShowData(final int position){
        switch(position+1){
            case 1:
                mTvLevelPic.setText("");
                mTvLevelPic.setBackgroundResource(R.drawable.img_n1_d);
                break;
            case 2:
                mTvLevelPic.setText("");
                mTvLevelPic.setBackgroundResource(R.drawable.img_n2_d);
                break;
            case 3:
                mTvLevelPic.setText("");
                mTvLevelPic.setBackgroundResource(R.drawable.img_n3_d);
                break;
            default:
                mTvLevelPic.setText(String.valueOf(position+1));
                mTvLevelPic.setBackgroundResource(R.drawable.img_nduo_d);
                break;
        }
    }

    @Override
    public void initListener() {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(v.getId()){
                    case R.id.img_dialog_hedge_show_close:
                        ShowDialogTool.dismissSelfViewDialog();
                        break;
                    case R.id.btn_dialog_hedge_show_check:
//					Toast.makeText(getContext(), "查看在售！", Toast.LENGTH_SHORT).show();
                        Activity act = (Activity) getContext();
                        Intent intent = new Intent();
                        if(null == mItemBean){
                            break;
                        }
                        ShowDialogTool.dismissSelfViewDialog();
                        // 车系 ，城市
                        ViewUtility.navigateToBuyCarActivity(getContext(),"0",mItemBean.cityId,mItemBean.cityName,
                                mItemBean.makeId,mItemBean.makeName,mItemBean.modelId,mItemBean.modelName,"","","");
                        break;
                }
            }
        };
        mImgClose.setOnClickListener(listener);
        mBtnCheck.setOnClickListener(listener);

        mSelfHedgeBarView.toShowData(null, getSizeRatio());

    }
}
