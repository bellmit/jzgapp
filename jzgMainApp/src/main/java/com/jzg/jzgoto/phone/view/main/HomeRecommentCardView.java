package com.jzg.jzgoto.phone.view.main;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.jzg.jzgoto.phone.R;
import com.jzg.jzgoto.phone.models.RecommendCardData;
import com.jzg.jzgoto.phone.tools.ImageRender;


public class HomeRecommentCardView extends RelativeLayout {

    private ImageView cardImageView;
    private RecommendCardData cardData;

    public HomeRecommentCardView(Context context) {
        super(context);
    }

    public HomeRecommentCardView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        initViews();
    }

    private void initViews() {
//        LayoutInflater inflater = LayoutInflater.from(getContext());
//        View rootView = inflater.inflate(R.layout.view_home_recommend_card_layout, this);
        cardImageView = (ImageView) this.findViewById(R.id.recommend_card_imageview);
    }

    private void updateViews() {
        ImageRender.getInstance().setImage(cardImageView, cardData.getImageUrl(), R.drawable.jingzhengu_moren);
    }

    public void setCardHeight(int height){
        ViewGroup.LayoutParams lp = (ViewGroup.LayoutParams) this.getLayoutParams();
        lp.height = height;
        this.setLayoutParams(lp);
    }

    public void setData(RecommendCardData data){
        if (data == null) {
            return;
        }
        cardData = data;
        updateViews();
    }

    public RecommendCardData getData(){
        return cardData;
    }

}
