package com.xyz.newsh;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.xyz.newsh.domain.NewsCenterBean;
import com.xyz.newsh.fragment.ContentFragment;
import com.xyz.newsh.pager.NewsCenterPager;

import java.util.List;

/**
 *      抽屉页面 包拦了主页面
 */

public class MainActivity extends FragmentActivity implements View.OnClickListener {

    private final static String TAG = "DrawerLayoutActivity";
    public DrawerLayout dl_layout; // 声明一个抽屉布局对象

    public ListView lv_drawer_left; // 声明左侧菜单的列表视图对象
    private ListView lv_drawer_right; // 声明右侧菜单的列表视图对象
    private String[] titleArray = {"首页", "新闻", "娱乐", "博客", "论坛"}; // 左侧菜单项的标题数组
    private String[] settingArray = {"我的", "设置", "关于"}; // 右侧菜单项的标题数组

    private String MAIN_CONTENT_TAG ="MAIN_CONTENT_TAG";
    MyListAdapter myListAdapter;




    List<NewsCenterBean.DataEntity> list;
     int prePosition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         //从布局文件中获取名叫dl_layout的抽屉布局
        dl_layout= findViewById(R.id.dl_layout);

        // 给抽屉布局设置侧滑监听器
        dl_layout.addDrawerListener(new SlidingListener());

        //initListDrawer(); // 初始化侧滑的菜单列表

        initFragment();
    }

    private void initFragment() {
        //获取全局的fragmentManager
        FragmentManager fragmentManager = getSupportFragmentManager();
        //开启事物
        FragmentTransaction transaction = fragmentManager.beginTransaction();
      //  transaction.replace(R.id.main_content,new LeftMenuFragment(),"xxxxx");

        transaction.replace(R.id.main_content,new ContentFragment(),MAIN_CONTENT_TAG);

        transaction.commit();
    }

    // 初始化侧滑的菜单列表
    private void initListDrawer() {
        // 下面初始化左侧菜单的列表视图
        lv_drawer_left = findViewById(R.id.lv_drawer_left);
        ArrayAdapter<String> left_adapter = new ArrayAdapter<String>(this,
                R.layout.item_select, titleArray);
         myListAdapter = new MyListAdapter();
        lv_drawer_left.setAdapter(myListAdapter);
        lv_drawer_left.setOnItemClickListener(new LeftListListener());
        // 下面初始化右侧菜单的列表视图
        lv_drawer_right =findViewById(R.id.lv_drawer_right);
        ArrayAdapter<String> right_adapter = new ArrayAdapter<String>(this,
                R.layout.item_select, settingArray);
        lv_drawer_right.setAdapter(right_adapter);
        lv_drawer_right.setOnItemClickListener(new RightListListener());
    }


    class  MyListAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView inflate =(TextView) View.inflate(MainActivity.this, R.layout.left_menu_itme, null);

            inflate.setText(list.get(position).getTitle());

            if(position==prePosition){
                inflate.setEnabled(true);
            }
            return inflate;
        }
    }
    @Override
    public void onClick(View v) {

    }

    public void setLeftMeun(List<NewsCenterBean.DataEntity> dataEntity) {

        if(dataEntity!=null){
            list=dataEntity;
            initListDrawer();

            FragmentManager fragmentManager = getSupportFragmentManager();

            ContentFragment fragmentByTag = (ContentFragment)fragmentManager.findFragmentByTag(MAIN_CONTENT_TAG);

            NewsCenterPager newsCenterPager =fragmentByTag.getNewsCenterPager();

            newsCenterPager.swichPager(prePosition);
        }

    }
    public void setLeftMenu(int prePosition) {

            FragmentManager fragmentManager = getSupportFragmentManager();

            ContentFragment fragmentByTag = (ContentFragment)fragmentManager.findFragmentByTag(MAIN_CONTENT_TAG);

            NewsCenterPager newsCenterPager =fragmentByTag.getNewsCenterPager();

            newsCenterPager.swichPager(prePosition);


    }

    // 定义一个左侧菜单列表的点击监听器
    private class LeftListListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//            String text = titleArray[position];
//
//            dl_layout.closeDrawers(); // 关闭所有抽屉

            prePosition=position;

//            MyListAdapter adapter =(MyListAdapter) parent.getAdapter();
//            adapter.getView()
             myListAdapter.notifyDataSetChanged();

            //switchMenuDetailPager(position);


            setLeftMenu(position);

        }
    }



    // 定义一个右侧菜单列表的点击监听器
    private class RightListListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String text = settingArray[position];

            dl_layout.closeDrawers(); // 关闭所有抽屉
        }
    }

    // 定义一个抽屉布局的侧滑监听器
    private class SlidingListener implements DrawerLayout.DrawerListener {
        // 在拉出抽屉的过程中触发
        public void onDrawerSlide(View drawerView, float slideOffset) {}

        // 在侧滑抽屉打开后触发
        public void onDrawerOpened(View drawerView) {
            if (drawerView.getId() ==R.id.lv_drawer_left) {

            } else {

            }
        }

        // 在侧滑抽屉关闭后触发
        public void onDrawerClosed(View drawerView) {
            if (drawerView.getId() == R.id.lv_drawer_left) {

            } else {

            }
        }

        // 在侧滑状态变更时触发
        public void onDrawerStateChanged(int paramInt) {

            Log.e("liu","在侧滑状态变更时触发");
        }
    }


//    public void processData(List<NewsCenterBean.DataEntity> list){
//
//        this.list=list;
//        initListDrawer();
//        for (int i = 0; i < list.size(); i++) {
//            System.out.println(list.get(i));
//        }
//    }
//
//    public void processData(List<NewsCenterBean.DataEntity> list){
//
//        this.list=list;
//        initListDrawer();
//        for (int i = 0; i < list.size(); i++) {
//            System.out.println(list.get(i));
//        }
//    }
//
//    private void switchMenuDetailPager(int position) {
//
//        FragmentManager supportFragmentManager = getSupportFragmentManager();
//        ContentFragment fragment =(ContentFragment) supportFragmentManager.findFragmentByTag(MAIN_CONTENT_TAG);
//
//        fragment.sss(position);
//
//
//    }


//    private void getDataFromNet() {
//
//        RequestParams params = new RequestParams(Constant.NEWS_CENTER_URI);
//        x.http().get(params, new Callback.CommonCallback<String>() {
//            @Override
//            public void onSuccess(String result) {
//                Log.e(TAG,"请求成功...result=="+result);
//
//                // processData(result);
//                processDataJSON=result;
//                CacheUitls.putString("processDataJSON",result);
//
//                processData(result);
//            }
//
//            @Override
//            public void onError(Throwable ex, boolean isOnCallback) {
//                Log.e(TAG, "请求onError....isOnCallback==" + isOnCallback+ex.getMessage());
//            }
//
//            @Override
//            public void onCancelled(CancelledException cex) {
//                Log.e(TAG,"请求onCancelled....");
//            }
//
//            @Override
//            public void onFinished() {
//                Log.e(TAG,"请求完成....");
//            }
//
//        });
//    }
//
//    protected void processData(String json) {
//        if(json==null){
//            json=CacheUitls.getString("processDataJSON");
//        }
//        Gson gson = new Gson();
//        NewsCenterBean bean = gson.fromJson(json, NewsCenterBean.class);
//        System.out.println(bean.getDataEntity().get(0).getChildren().get(0).getTitle());
//
//        MainActivity mainActivity = (MainActivity) this.context;
//        mainActivity.processData(bean.getDataEntity());
//
//
//        pagerList  = new ArrayList<MenuDetailBasePager>();
//        newsMenuDetailPager = new NewsMenuDetailPager(context, bean.getDataEntity().get(0).getChildren());
//        pagerList.add(newsMenuDetailPager);
//        pagerList.add(new TopicMenuDetailPager(context));
//        pagerList.add(new PhotosMenuDetailPager(context));
//        pagerList.add(new InteracMenuDetailPager(context));
//
//
//    }
}