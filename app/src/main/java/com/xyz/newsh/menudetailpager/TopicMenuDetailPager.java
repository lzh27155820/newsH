package com.xyz.newsh.menudetailpager;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.xyz.newsh.base.MenuDetailBasePager;

public class TopicMenuDetailPager extends MenuDetailBasePager {
    public TopicMenuDetailPager(Context context) {
        super(context);
    }

    @Override
    public View initView() {
        TextView textView = new TextView(content);
        textView.setText("专题的详情页面");
        textView.setTextSize(20);
        textView.setTextColor(Color.RED);
        textView.setGravity(Gravity.CENTER);
        return textView;
    }


    @Override
    public void initData() {
        super.initData();
        System.out.println("专题的详情页面数据初始化了");
    }

}
