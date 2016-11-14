package com.jzg.jzgoto.phone.view.main;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import java.util.List;
import com.jzg.jzgoto.phone.R;
import com.jzg.jzgoto.phone.global.Statistical;
import com.jzg.jzgoto.phone.models.RecommendCardData;
import com.jzg.jzgoto.phone.tools.CountClickTool;
import com.jzg.jzgoto.phone.view.ViewUtility;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HomeRecommentListItemView extends LinearLayout {
    public static enum CardShowMode {
        SHOW_MODE_ONE_CARD,
        SHOW_MODE_TWO_CARD,
        SHOW_MODE_THREE_CARD
    }

    private List<RecommendCardData> bannerDataList;
    private HomeRecommentCardView leftCard;
    private HomeRecommentCardView rightCard;
    private HomeRecommentCardView middleCard;
    private View bottomLine;
    private CardShowMode mCardShowMode = CardShowMode.SHOW_MODE_ONE_CARD;
    private int mPosition = 0;

    @Bind(R.id.view_card_divider_left)
    View mLeftDividerView;
    @Bind(R.id.view_card_divider_right)
    View mRightDividerView;

    public HomeRecommentListItemView(Context context) {
        super(context);
        initViews();
    }

    public HomeRecommentListItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViews();
    }

    private void initViews() {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View rootView = inflater.inflate(R.layout.view_home_recommend_item_view_layout, this);
        ButterKnife.bind(this);
        this.leftCard = (HomeRecommentCardView) this.findViewById(R.id.view_card_left);
        this.middleCard = (HomeRecommentCardView) this.findViewById(R.id.view_card_middle);
        this.rightCard = (HomeRecommentCardView) this.findViewById(R.id.view_card_right);
        this.bottomLine = this.findViewById(R.id.view_item_bottom_line);
        this.leftCard.setOnClickListener(onClickListener);
        this.middleCard.setOnClickListener(onClickListener);
        this.rightCard.setOnClickListener(onClickListener);
    }

    private void updateViews() {

    }

    public void setDivider(boolean isBottom) {
        //this.bottomLine.setVisibility(isBottom ? View.VISIBLE : View.GONE);
    }

    private void setCardShowMode(CardShowMode showMode){
        mCardShowMode = showMode;
        int dimensResId = R.dimen.home_fragment_recommend_list_one_card_item_height;
        int height = 0;
        switch(showMode){
            case SHOW_MODE_ONE_CARD:
                dimensResId = R.dimen.home_fragment_recommend_list_one_card_item_height;
                break;
            case SHOW_MODE_TWO_CARD:
                dimensResId = R.dimen.home_fragment_recommend_list_two_card_item_height;
                break;
            case SHOW_MODE_THREE_CARD:
                dimensResId = R.dimen.home_fragment_recommend_list_three_card_item_height;
                break;
        }
        height = getResources().getDimensionPixelOffset(dimensResId);
        leftCard.setCardHeight(height);
        middleCard.setCardHeight(height);
        rightCard.setCardHeight(height);
    }

    public void setPosition(int position){
        mPosition = position;
    }

    public void setData(List<RecommendCardData> dataList){
        bannerDataList = dataList;
        if (bannerDataList != null) {
            int bannerSize = bannerDataList.size();
            switch(bannerSize){
                case 1:
                    leftCard.setData(bannerDataList.get(0));
                    leftCard.setVisibility(View.VISIBLE);
                    middleCard.setVisibility(View.GONE);
                    rightCard.setVisibility(View.GONE);
                    mLeftDividerView.setVisibility(View.GONE);
                    mRightDividerView.setVisibility(View.GONE);
                    setCardShowMode(CardShowMode.SHOW_MODE_ONE_CARD);
                    break;
                case 2:
                    leftCard.setData(bannerDataList.get(0));
                    middleCard.setData(bannerDataList.get(1));
                    leftCard.setVisibility(View.VISIBLE);
                    middleCard.setVisibility(View.VISIBLE);
                    rightCard.setVisibility(View.GONE);
                    mLeftDividerView.setVisibility(View.VISIBLE);
                    mRightDividerView.setVisibility(View.GONE);
                    setCardShowMode(CardShowMode.SHOW_MODE_TWO_CARD);
                    break;
                case 3:
                    leftCard.setData(bannerDataList.get(0));
                    middleCard.setData(bannerDataList.get(1));
                    rightCard.setData(bannerDataList.get(2));
                    leftCard.setVisibility(View.VISIBLE);
                    middleCard.setVisibility(View.VISIBLE);
                    rightCard.setVisibility(View.VISIBLE);
                    mLeftDividerView.setVisibility(View.VISIBLE);
                    mRightDividerView.setVisibility(View.VISIBLE);
                    setCardShowMode(CardShowMode.SHOW_MODE_THREE_CARD);
                    break;
                default:
                    leftCard.setVisibility(View.GONE);
                    middleCard.setVisibility(View.GONE);
                    rightCard.setVisibility(View.GONE);
                    mLeftDividerView.setVisibility(View.GONE);
                    mRightDividerView.setVisibility(View.GONE);
                    break;
            }
        }
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.view_card_left:
                    if (leftCard != null && leftCard.getData() != null) {
                        ViewUtility.navigateToWebView(getContext(), "", leftCard.getData().getWebUrl());
                        CountClickTool.onEvent(getContext(), Statistical.KEY_HOME_RECOMMEND_CARD_CLICK_COUNT + "-" + (mPosition+1) + "-1");
                    }
                    break;
                case R.id.view_card_middle:
                    if (middleCard != null && middleCard.getData() != null) {
                        ViewUtility.navigateToWebView(getContext(), "", middleCard.getData().getWebUrl());
                        CountClickTool.onEvent(getContext(), Statistical.KEY_HOME_RECOMMEND_CARD_CLICK_COUNT + "-" + (mPosition+1) + "-2");
                    }
                    break;
                case R.id.view_card_right:
                    if (rightCard != null && rightCard.getData() != null) {
                        ViewUtility.navigateToWebView(getContext(), "", rightCard.getData().getWebUrl());
                        CountClickTool.onEvent(getContext(), Statistical.KEY_HOME_RECOMMEND_CARD_CLICK_COUNT + "-" + (mPosition+1) + "-3");
                    }
                    break;
            }
        }
    };

}
