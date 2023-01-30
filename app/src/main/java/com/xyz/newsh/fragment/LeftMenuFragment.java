package com.xyz.newsh.fragment;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.drawerlayout.widget.DrawerLayout;

import com.xyz.newsh.R;
import com.xyz.newsh.base.BaseFragment;


public class LeftMenuFragment extends BaseFragment {

    private final static String TAG = "LeftMenuFragment";
    private DrawerLayout dl_layout; // 声明一个抽屉布局对象

    private ListView lv_drawer_left; // 声明左侧菜单的列表视图对象
    private ListView lv_drawer_right; // 声明右侧菜单的列表视图对象
    private String[] titleArray = {"首页", "新闻", "娱乐", "博客", "论坛"}; // 左侧菜单项的标题数组
    private String[] settingArray = {"我的", "设置", "关于"}; // 右侧菜单项的标题数组
    View view;

    @Override
    protected View initView() {

        view= View.inflate(context, R.layout.sideslip, null);
        dl_layout=view.findViewById(R.id.dl_layout);
        // 下面初始化左侧菜单的列表视图
        lv_drawer_left =view.findViewById(R.id.lv_drawer_left);
        lv_drawer_right =view.findViewById(R.id.lv_drawer_right);
        return view;
    }

    @Override
    protected void initData() {
        dl_layout.addDrawerListener(new SlidingListener());
        initListDrawer(); // 初始化侧滑的菜单列表
    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        // 从布局文件中获取名叫dl_layout的抽屉布局
//        dl_layout= findViewById(R.id.dl_layout);
//
//        // 给抽屉布局设置侧滑监听器
//
//
//
//
//        initFragment();
//    }



    // 初始化侧滑的菜单列表
    private void initListDrawer() {

        ArrayAdapter<String> left_adapter = new ArrayAdapter<String>(context,R.layout.item_select, titleArray);
        lv_drawer_left.setAdapter(left_adapter);
        lv_drawer_left.setOnItemClickListener(new LeftListListener());
        // 下面初始化右侧菜单的列表视图

        ArrayAdapter<String> right_adapter = new ArrayAdapter<String>(context,
                R.layout.item_select, settingArray);
        lv_drawer_right.setAdapter(right_adapter);
        lv_drawer_right.setOnItemClickListener(new RightListListener());
    }

    // 定义一个左侧菜单列表的点击监听器
    private class LeftListListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String text = titleArray[position];

            dl_layout.closeDrawers(); // 关闭所有抽屉
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
}
