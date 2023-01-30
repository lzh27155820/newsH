package com.xyz.newsh.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.xyz.newsh.MainActivity;
import com.xyz.newsh.R;
import com.xyz.newsh.utlis.CacheUitls;
import com.xyz.newsh.utlis.DensityUtil;


import java.util.ArrayList;
import java.util.List;

/**
 *  引导页面
 */
public class Guide extends AppCompatActivity {

    ViewPager viewPager;
   private Button btn_start_main;
    LinearLayout ll_point_group;
    ImageView iv_point_red;
    List<ImageView> imageViews=new ArrayList<>();

    private Integer leftMax=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        viewPager=findViewById(R.id.viewpage);

        btn_start_main= findViewById(R.id.btn_start_main);
        ll_point_group = findViewById(R.id.ll_point_group);
        iv_point_red=findViewById(R.id.iv_point_red);
        int[] imgs={R.drawable.guide_1,R.drawable.guide_2,R.drawable.guide_3};

        for (int i = 0; i < imgs.length; i++) {
            //加载page资源
            ImageView imageView = new ImageView(this);
            imageView.setBackgroundResource(imgs[i]);
            imageViews.add(imageView);

            //创建点
            ImageView view = new ImageView(this);
            view.setBackgroundResource(R.drawable.point_normal);
            int dip = DensityUtil.dip2px(this, 10);
            LinearLayout.MarginLayoutParams params = new LinearLayout.MarginLayoutParams(dip, dip);

            if(i!=0){
                params.leftMargin=20;
            }
            view.setLayoutParams(params);

            ll_point_group.addView(view);
        }

        viewPager.setAdapter(new MyViewPageAdapter());

        //iv_point_red.getViewTreeObserver().addOnGlobalLayoutListener(new MyGlobalLayoutListener());
        iv_point_red.getViewTreeObserver().addOnGlobalLayoutListener(new MyGlobalLayoutListener());
        viewPager.addOnPageChangeListener(new MyOnPageChangeListener());

        //绑架点击事件跳转主页面

        btn_start_main.setOnClickListener((v)->{

            Boolean s = CacheUitls.putBoolean("startMain");

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
         //   finish();

        });
    }

    class MyViewPageAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return imageViews.size();
        }
        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {

            container.addView(imageViews.get(position));

            return imageViews.get(position);
        }

        /**
         *
         * @param view
         * @param object  instantiateItem 的返回值
         * @return 判断两个是否相等
         */
        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view==object;
        }

        /**
         * 销毁
         * @param container
         * @param position
         * @param object
         */
        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

            container.removeView((View) object);
        }
    }

    class MyGlobalLayoutListener implements ViewTreeObserver.OnGlobalLayoutListener{

        @Override
        public void onGlobalLayout() {
            //默认执行两次 添加后执行一次
            iv_point_red.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            //获取点的间距
            leftMax=ll_point_group.getChildAt(1).getLeft()-ll_point_group.getChildAt(0).getLeft();
        }

    }

    class MyOnPageChangeListener implements ViewPager.OnPageChangeListener{

        /**
         *  当页面滑动的时候
         * @param position 第几个页面
         * @param positionOffset 滑动的百分比
         * @param positionOffsetPixels 滑动的橡树
         */
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            //两点的间距=屏幕滑动的百分比*间距
            //滑动的总距离=屏幕滑动的百分比*两点的间距
            /**
             * leftMax 两点的距离
             * positionOffset 滑动的百分比
             * position 第几个页面
             */
            Integer left =(int) (positionOffset * leftMax);
            Integer leftMargin =(int)(position*leftMax+(positionOffset * leftMax));
            Log.e("liu","leftMax"+leftMax+"\tleft"+left+"\tposition 第一个"+position+"\tpositionOffset 屏幕的"+positionOffset+"\t两点的间距"+leftMargin);

            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)iv_point_red.getLayoutParams();
            params.leftMargin=leftMargin;
            iv_point_red.setLayoutParams(params);


        }

        /**
         * 当前停止的页面
         * @param position 第几个页面
         */
        @Override
        public void onPageSelected(int position) {
            if (position==imageViews.size()-1){
                btn_start_main.setVisibility(View.VISIBLE);
            }else {
                btn_start_main.setVisibility(View.GONE);
            }

        }
        /**
         *
         * @param state
         */
        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }
}