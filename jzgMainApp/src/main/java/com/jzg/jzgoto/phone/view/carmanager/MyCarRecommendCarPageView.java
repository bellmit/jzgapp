package com.jzg.jzgoto.phone.view.carmanager;


import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import java.util.List;
import com.jzg.jzgoto.phone.R;
import com.jzg.jzgoto.phone.models.business.carmanager.FocusCarData;


public class MyCarRecommendCarPageView extends LinearLayout {

    private List<FocusCarData> mCarDataList;
    private MyCarRecommendCarItemView leftCard;
    private MyCarRecommendCarItemView rightCard;
    private MyCarRecommendCarItemView middleCard;
    private View bottomLine;

    public MyCarRecommendCarPageView(Context context) {
        super(context);
        initViews();
    }

    public MyCarRecommendCarPageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViews();
    }

    private void initViews() {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View rootView = inflater.inflate(R.layout.view_carmanager_recommend_car_page_view_layout, this);
        this.leftCard = (MyCarRecommendCarItemView) this.findViewById(R.id.view_card_left);
        this.middleCard = (MyCarRecommendCarItemView) this.findViewById(R.id.view_card_middle);
        this.rightCard = (MyCarRecommendCarItemView) this.findViewById(R.id.view_card_right);
        this.bottomLine = this.findViewById(R.id.view_item_bottom_line);
    }

    private void updateViews() {

    }

    public void setDivider(boolean isBottom) {
        //this.bottomLine.setVisibility(isBottom ? View.VISIBLE : View.GONE);
    }

    public void setData(List<FocusCarData> dataList){
        mCarDataList = dataList;
        if (mCarDataList != null) {
            int bannerSize = mCarDataList.size();
            switch(bannerSize){
                case 1:
                    leftCard.setData(mCarDataList.get(0));
                    leftCard.setVisibility(View.VISIBLE);
                    middleCard.setVisibility(View.INVISIBLE);
                    rightCard.setVisibility(View.INVISIBLE);
                    break;
                case 2:
                    leftCard.setData(mCarDataList.get(0));
                    middleCard.setData(mCarDataList.get(1));
                    leftCard.setVisibility(View.VISIBLE);
                    middleCard.setVisibility(View.VISIBLE);
                    rightCard.setVisibility(View.INVISIBLE);
                    break;
                case 3:
                    leftCard.setData(mCarDataList.get(0));
                    middleCard.setData(mCarDataList.get(1));
                    rightCard.setData(mCarDataList.get(2));
                    leftCard.setVisibility(View.VISIBLE);
                    middleCard.setVisibility(View.VISIBLE);
                    rightCard.setVisibility(View.VISIBLE);
                    break;
                default:
                    leftCard.setVisibility(View.INVISIBLE);
                    middleCard.setVisibility(View.INVISIBLE);
                    rightCard.setVisibility(View.INVISIBLE);
                    break;
            }
        }
    }

}
