package com.xyz.newsh.menudetailpager;
import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.xyz.newsh.base.MenuDetailBasePager;


public class InteracMenuDetailPager extends MenuDetailBasePager {


    public InteracMenuDetailPager(Context context) {
        super(context);
    }

    @Override
    public View initView() {
        TextView textView = new TextView(content);
        textView.setText("互动的详情页面");
        textView.setTextSize(20);
        textView.setTextColor(Color.RED);
        textView.setGravity(Gravity.CENTER);
        return textView;
    }


    @Override
    public void initData() {
        super.initData();
        System.out.println("互动的详情页面数据初始化了");
    }


}
