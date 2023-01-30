package com.xyz.newsh.menudetailpager;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.xyz.newsh.base.MenuDetailBasePager;
import com.xyz.newsh.domain.NewsCenterBean;
import com.xyz.newsh.menudetailpager.tabdetail.TabDetailPager;

import java.util.ArrayList;
import java.util.List;

public class NewsMenuDetailPager extends MenuDetailBasePager {

    ViewPager viewPager;
    public List<NewsCenterBean.Children> dataEntityList;

    List<TabDetailPager> tabDetailPagers;
    public NewsMenuDetailPager(Context context) {
        super(context);
    }



    public NewsMenuDetailPager(Context context, NewsCenterBean.DataEntity dataEntity) {

        super(context,dataEntity);

    }

    public NewsMenuDetailPager(Context context, List<NewsCenterBean.DataEntity> dataEntity) {
        super(context);

    }

    @Override
    public View initView() {
        viewPager=new ViewPager(content);

        tabDetailPagers=new ArrayList<>();
        List<NewsCenterBean.Children> children = dataEntity.getChildren();
        for (int i = 0; i < children.size(); i++) {
          tabDetailPagers.add(new TabDetailPager(content, children.get(i)));
        }
        viewPager.setAdapter(new MyPagerAdapter());
        TextView textView = new TextView(content);
        textView.setText("新闻的详情页面");
        textView.setTextSize(20);
        textView.setTextColor(Color.RED);
        textView.setGravity(Gravity.CENTER);
        return viewPager;
    }
    @Override
    public void initData() {
        super.initData();
        System.out.println("新闻的详情页面数据初始化了");
    }


    class MyPagerAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return tabDetailPagers.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view==object;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {

            TabDetailPager tabDetailPager = tabDetailPagers.get(position);
            tabDetailPager.initData();
            container.addView(tabDetailPager.rootView);

            return tabDetailPager.rootView;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }
    }
}
