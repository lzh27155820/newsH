package com.xyz.newsh.base;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.xyz.newsh.R;


public class BasePager {


    public Context context;

    public View rootView;

    public TextView tv_title;
    public ImageButton img_menu_btn;

    public FrameLayout fl_content;

    public BasePager(Context context) {
        this.context = context;
        
        rootView=initView();
    }

    public View initView() {

        View view = View.inflate(context, R.layout.base_pager, null);

        tv_title=view.findViewById(R.id.tv_title);
        img_menu_btn= view.findViewById(R.id.img_menu_btn);
        fl_content=view.findViewById(R.id.fl_content);


        return view;
    }

    public void initDate() {
    }



}
