package com.jzg.jzgoto.phone.view.menu;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jzg.jzgoto.phone.R;

public class MenuItemView extends RelativeLayout {
    private ImageView menuIcon;
    private TextView menuTitle;
    private View newIconContainer;
    private TextView newIconNumTextView;
    private int menuIconResId;
    private String menuTitleStr;

    public MenuItemView(Context context) {
        super(context);
        init(context, null);
    }

    public MenuItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public MenuItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public void showNewIcon(int num) {
        this.newIconContainer.setVisibility(View.VISIBLE);
        if (num > 0){
            newIconNumTextView.setText(Integer.toString(num));
        }
    }

    public void hideNewIcon() {
        this.newIconContainer.setVisibility(View.INVISIBLE);
    }


    private void init(Context context, AttributeSet attrs) {
        if (attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MenuItemView);
            this.menuIconResId = a.getResourceId(R.styleable.MenuItemView_menuIcon, 0);
            this.menuTitleStr = a.getString(R.styleable.MenuItemView_menuTitle);
            a.recycle();
        }
        LayoutInflater.from(context).inflate(R.layout.view_menu_menuitemview, this);
        this.menuIcon = (ImageView) this.findViewById(R.id.view_menu_menuitemview_icon);
        this.menuTitle = (TextView) this.findViewById(R.id.view_menu_menuitemview_title);
        this.newIconContainer = this.findViewById(R.id.view_menu_menuitemview_new_tip_container);
        this.newIconNumTextView = (TextView) this.findViewById(R.id.view_menu_menuitemview_new_number_textview);
        if (this.menuIconResId > 0) {
            this.menuIcon.setBackgroundResource(this.menuIconResId);
        }
        this.menuTitle.setText(this.menuTitleStr);
    }
}
