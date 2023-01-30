package com.xyz.newsh.pager;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import androidx.drawerlayout.widget.DrawerLayout;

import com.xyz.newsh.MainActivity;
import com.xyz.newsh.base.BasePager;


public class GovaffairsPager extends BasePager {
    public GovaffairsPager(Context context) {
        super(context);
    }


    @Override
    public void initDate() {

        MainActivity context = (MainActivity) this.context;
        //禁止滑动
        context.dl_layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

        tv_title.setText("网络");
        img_menu_btn.setVisibility(View.GONE);
        TextView child = new TextView(context);
        child.setText("网络");
        child.setTextColor(Color.RED);
        child.setTextSize(25);
        child.setGravity(Gravity.CENTER);
        fl_content.addView(child);
    }
}
