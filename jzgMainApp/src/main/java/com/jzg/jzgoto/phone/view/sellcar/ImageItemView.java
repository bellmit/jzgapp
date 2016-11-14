package com.jzg.jzgoto.phone.view.sellcar;


import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.jzg.jzgoto.phone.R;
import com.jzg.jzgoto.phone.tools.ImageRender;

/**
 * author: guochen
 * date: 2016/9/29 16:04
 * email: 
 */
public class ImageItemView extends LinearLayout{

    private ImageView imageView;
    private ImageView imageselect;

    public void setImageselect(ImageView imageselect) {
        this.imageselect = imageselect;
    }

    public boolean isSelect = true;
    public int position;
    public int itemPosition;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getItemPosition() {
        return itemPosition;
    }

    public void setItemPosition(int itemPosition) {
        this.itemPosition = itemPosition;
    }

    public ImageItemView(Context context) {
        this(context,null);

    }

    public ImageItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    public ImageItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = LayoutInflater.from(context).inflate(R.layout.image_item, this, true);
        imageView = (ImageView) view.findViewById(R.id.iv_image);
        imageselect = (ImageView) view.findViewById(R.id.iv_imageselect);
//        this.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(imageselect.getVisibility()==View.GONE){
//                    imageselect.setVisibility(View.VISIBLE);
//                    isSelect = true;
//                }else {
//                    imageselect.setVisibility(View.GONE);
//                    isSelect= false;
//                }
//            }
//        });
    }

    public ImageView getImageView() {
        return imageView;
    }


    public ImageView getImageselect() {

        return imageselect;
    }


    public void setUri(String uri,String id){
        ImageRender.getInstance().setImage( imageView, uri, R.drawable.jingzhengu_moren);
        this.setTag(id);
    }



}
