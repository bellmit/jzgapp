package com.jzg.jzgoto.phone.view.buy;

import android.content.Context;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jzg.jzgoto.phone.R;
import com.jzg.jzgoto.phone.constant.Constant;
import com.jzg.jzgoto.phone.models.business.buy.BuyCarDetailBargainParams;
import com.jzg.jzgoto.phone.models.business.buy.BuyCarDetailBarginResult;
import com.jzg.jzgoto.phone.models.business.buy.BuyCarDetailResult;
import com.jzg.jzgoto.phone.services.business.buy.BuyCarService;
import com.jzg.jzgoto.phone.services.common.OnRequestFinishListener;
import com.jzg.jzgoto.phone.tools.ClickControlTool;
import com.jzg.jzgoto.phone.tools.CountClickTool;
import com.jzg.jzgoto.phone.tools.ShowDialogTool;

/**
 * Created by WY on 2016/9/28.
 * Function :
 */
public class BuyCarDetailBargainDialogView extends RelativeLayout{

    public BuyCarDetailBargainDialogView(Context context, AttributeSet attrs,
                                    int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    public BuyCarDetailBargainDialogView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public BuyCarDetailBargainDialogView(Context context) {
        super(context);
        initView();
    }

    private ImageView mCloseImage;
    private TextView mSellPrice;
    private EditText mExpectedPrice,mPhoneNumber;
    private Button mSubmitBtn;
    public void initView() {
        View view = LayoutInflater.from(getContext()).inflate( R.layout.view_buycar_detail_dialog_bargain_layout, null);
        mCloseImage = (ImageView) view.findViewById(R.id.buycar_detail_dialog_bargain_finish);
        mSellPrice = (TextView) view.findViewById(R.id.buycar_detail_dialog_bargain_price);
        mExpectedPrice = (EditText) view.findViewById(R.id.buycar_detail_dialog_bargain_expectedPrice);
        mPhoneNumber = (EditText) view.findViewById(R.id.buycar_detail_dialog_bargain_phone);
        mSubmitBtn = (Button) view.findViewById(R.id.buycar_detail_dialog_bargain_submit);
        mCloseImage.setOnClickListener(mClickListener);
        mSubmitBtn.setOnClickListener(mClickListener);
        mExpectedPrice.addTextChangedListener(mTextWatcher);
        this.addView(view);
    }

    private BuyCarDetailResult mDetailResult;
    public void setBuyCarDetailResult(BuyCarDetailResult result){
        mSellPrice.setText(result.getSellPrice()+"万");
        this.mDetailResult = result;
    }
    private void toRequestBargain(){
        ShowDialogTool.showLoadingDialog(getContext());
        BuyCarDetailBargainParams params = new BuyCarDetailBargainParams();
        params.CarSourceId = mDetailResult.getCarSourceId();
        params.CarSourceFrom = mDetailResult.getCarSourceFrom();
        params.sellPrice = mDetailResult.getSellPrice();
        params.expectedPrice = mExpectedPrice.getText().toString().trim();
        params.mobile = mPhoneNumber.getText().toString().trim();
        new BuyCarService(getContext(),requestListener).toResuestBuyCarDetailBargain(
                params, BuyCarDetailBarginResult.class,R.id.request_buy_car_detail_bargain);
    }

    private boolean toCheckEmpty(){
        if(TextUtils.isEmpty(mExpectedPrice.getText().toString().trim())){
            ShowDialogTool.showCenterToast(getContext(),"请填写预期价");
            return false;
        }
        if(Double.valueOf(mExpectedPrice.getText().toString().trim())==0){
            ShowDialogTool.showCenterToast(getContext(),"预期价不能为零");
            return false;
        }
        if(TextUtils.isEmpty(mPhoneNumber.getText().toString().trim())){
            ShowDialogTool.showCenterToast(getContext(),"请填写手机号");
            return false;
        }
        if(mPhoneNumber.getText().toString().trim().length()<11){
            ShowDialogTool.showCenterToast(getContext(),"手机号格式不正确");
            return false;
        }
            return true;
    }
    private OnRequestFinishListener requestListener = new OnRequestFinishListener() {
        @Override
        public void onRequestSuccess(Message msg) {
            ShowDialogTool.dismissLoadingDialog();
            switch (msg.what){
                case R.id.request_buy_car_detail_bargain:
                    BuyCarDetailBarginResult result = (BuyCarDetailBarginResult) msg.obj;
                    if(result.getStatus()== Constant.SUCCESS){
                        CountClickTool.onEvent(getContext(), "V5_buyCar_bargain_success");
                        ShowDialogTool.showCenterToast(getContext(),"提交成功！");
                        ShowDialogTool.dismissSelfViewDialog();
                    }else{
                        ShowDialogTool.showCenterToast(getContext(),getResources().getString(R.string.error_net));
                    }
                    break;
            }
        }

        @Override
        public void onRequestFault(Message msg) {
            ShowDialogTool.dismissLoadingDialog();
            ShowDialogTool.showCenterToast(getContext(),getResources().getString(R.string.error_net));
        }
    };
    private OnClickListener mClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if(!ClickControlTool.isCanToClick())return;
                switch (v.getId()){
                    case R.id.buycar_detail_dialog_bargain_finish:
                        ShowDialogTool.dismissSelfViewDialog();
                        break;
                    case R.id.buycar_detail_dialog_bargain_submit:
                        if(toCheckEmpty()){
                            toRequestBargain();
                        }
                        break;
            }
        }
    };

    private TextWatcher mTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            if (s.toString().contains(".")) {
                if (s.length() - 1 - s.toString().indexOf(".") > 2) {
                    s = s.toString().subSequence(0,
                            s.toString().indexOf(".") + 3);
                    mExpectedPrice.setText(s);
                    mExpectedPrice.setSelection(s.length());
                }
            }
            if (s.toString().trim().substring(0).equals(".")) {
                s = "0" + s;
                mExpectedPrice.setText(s);
                mExpectedPrice.setSelection(2);
            }

            if (s.toString().startsWith("0")
                    && s.toString().trim().length() > 1) {
                if (!s.toString().substring(1, 2).equals(".")) {
                    mExpectedPrice.setText(s.subSequence(0, 1));
                    mExpectedPrice.setSelection(1);
                    return;
                }
            }
        }

        @Override
        public void afterTextChanged(Editable s) {
            if(!s.toString().contains(".")){
                String str = mExpectedPrice.toString().trim();
                if(s.length()<=3){
                    return;
                }else{
                    s.delete(3, 4);
                    return;
                }
            }
        }
    };
}
