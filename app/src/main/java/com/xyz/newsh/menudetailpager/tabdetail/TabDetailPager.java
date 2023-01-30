package com.xyz.newsh.menudetailpager.tabdetail;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.xyz.newsh.base.MenuDetailBasePager;
import com.xyz.newsh.domain.NewsCenterBean;


public class TabDetailPager extends MenuDetailBasePager {

    TextView textView;
   // NewsCenterBean.Children children;
    public TabDetailPager(Context context) {
        super(context);
    }


    public TabDetailPager(Context context, NewsCenterBean.Children children) {
        super(context,children);

    }

    @Override
    public View initView() {
        textView = new TextView(content);

        textView.setTextSize(20);
        textView.setTextColor(Color.RED);
        textView.setGravity(Gravity.CENTER);
        return textView;
    }

    @Override
    public void initData() {
        textView.setText(children.getTitle());
    }
}
