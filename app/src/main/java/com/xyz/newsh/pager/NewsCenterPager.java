package com.xyz.newsh.pager;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.drawerlayout.widget.DrawerLayout;

import com.google.gson.Gson;
import com.xyz.newsh.MainActivity;
import com.xyz.newsh.base.BasePager;
import com.xyz.newsh.base.MenuDetailBasePager;
import com.xyz.newsh.domain.NewsCenterBean;
import com.xyz.newsh.menudetailpager.InteracMenuDetailPager;
import com.xyz.newsh.menudetailpager.NewsMenuDetailPager;
import com.xyz.newsh.menudetailpager.PhotosMenuDetailPager;
import com.xyz.newsh.menudetailpager.TopicMenuDetailPager;
import com.xyz.newsh.utlis.CacheUitls;
import com.xyz.newsh.utlis.Constant;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

public class NewsCenterPager extends BasePager {


   public List<MenuDetailBasePager> detailBasePagers;


    private static final String TAG = NewsCenterPager.class.getName();
 // public   NewsMenuDetailPager newsMenuDetailPager;
    public String processDataJSON;
    private List<NewsCenterBean.DataEntity> data;

    public NewsCenterPager(Context context) {
        super(context);
        //getDataFromNet();
    }



    @Override
    public void initDate() {
        tv_title.setText("新闻中心");
        img_menu_btn.setVisibility(View.GONE);
        TextView child = new TextView(context);
        child.setText("新闻中心");
        child.setTextColor(Color.RED);
        child.setTextSize(25);
        child.setGravity(Gravity.CENTER);
        //网络请求数据
        MainActivity context = (MainActivity) this.context;

        fl_content.addView(child);
        //开启滑动
        context.dl_layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNDEFINED);





        img_menu_btn.setVisibility(View.VISIBLE);

        img_menu_btn.setOnClickListener((v)->{

            ListView lv_drawer_left = context.lv_drawer_left;
            context.dl_layout.openDrawer(context.lv_drawer_left);;
        });
        processDataJSON=CacheUitls.getString("processDataJSON");
        if(processDataJSON==null||processDataJSON==""){
            getDataFromNet();
        }else {
            processData(processDataJSON);
        }

    }

    /**
     * 请求网络数据
     */
    public void getDataFromNet() {

        RequestParams params = new RequestParams(Constant.NEWS_CENTER_URI);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e(TAG,"请求成功...result=="+result);

               // processData(result);
                processDataJSON=result;
                CacheUitls.putString("processDataJSON",result);

                processData(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e(TAG, "请求onError....isOnCallback==" + isOnCallback+ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Log.e(TAG,"请求onCancelled....");
            }

            @Override
            public void onFinished() {
                Log.e(TAG,"请求完成....");
            }

        });
    }
//
    protected void processData(String json) {

        Gson gson = new Gson();
        NewsCenterBean bean = gson.fromJson(json, NewsCenterBean.class);


        Log.e("请求的数据",bean.toString());


        data = bean.getDataEntity();
        NewsCenterBean.DataEntity dataEntity = data.get(0);


        detailBasePagers  = new ArrayList<MenuDetailBasePager>();
//        newsMenuDetailPager = new NewsMenuDetailPager(context, bean.getDataEntity().get(0).getChildren());
        detailBasePagers.add(new NewsMenuDetailPager(context,data.get(0)));
        detailBasePagers.add(new TopicMenuDetailPager(context));
        detailBasePagers.add(new PhotosMenuDetailPager(context));
        detailBasePagers.add(new InteracMenuDetailPager(context));

        MainActivity mainActivity = (MainActivity) this.context;
        mainActivity.setLeftMeun(data);
    }

    public void swichPager(int position) {

        if(position<detailBasePagers.size()){

            tv_title.setText(data.get(position).getTitle());
            //2.移除之前内容
            fl_content.removeAllViews();//移除之前的视图
            //3.添加新内容
            MenuDetailBasePager detaliBasePager = detailBasePagers.get(position);//
            View rootView = detaliBasePager.rootView;
            detaliBasePager.initData();//初始化数据

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            rootView.setLayoutParams(params);
            fl_content.addView(rootView);

        }else{
            Toast.makeText(context, "该页面还没有启用", Toast.LENGTH_SHORT).show();
        }
    }

}
