package com.xyz.newsh.fragment;

import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.xyz.newsh.R;
import com.xyz.newsh.base.BaseFragment;
import com.xyz.newsh.base.BasePager;
import com.xyz.newsh.pager.GovaffairsPager;
import com.xyz.newsh.pager.HomePager;
import com.xyz.newsh.pager.NewsCenterPager;
import com.xyz.newsh.pager.SettingPager;
import com.xyz.newsh.pager.SmartservicePager;
import com.xyz.newsh.view.NoScrollViewPager;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * 主页面
 */
public class ContentFragment extends BaseFragment {

    @ViewInject(R.id.viewpage)
    private NoScrollViewPager viewpage;


    @ViewInject(R.id.rg_main)//依赖注入
    private RadioGroup rg_main;

    private Integer preNum;

    public List<BasePager> basePagerList=new ArrayList<BasePager>();
    @Override
    protected View initView() {

        View view = View.inflate(context, R.layout.contentfragment, null);

        x.view().inject(ContentFragment.this,view);


        return view;
    }

    @Override
    protected void initData() {
        //添加视图
        basePagerList.add(new HomePager(context));
        basePagerList.add(new NewsCenterPager(context));
        basePagerList.add(new SmartservicePager(context));
        basePagerList.add(new GovaffairsPager(context));
        basePagerList.add(new SettingPager(context));

        viewpage.setAdapter(new MyViewPageAdapter());
        /**
         * 设置RadioGroup 当前选中的值的监听 更换页面
         */
        rg_main.setOnCheckedChangeListener(new MyOnCheckedChangeListener());
        //默认选中主页面
        rg_main.check(R.id.home);
        basePagerList.get(0).initDate();

        /**
         * 1. 默认情况下 viewpage会加载初始化 2至3个page 导致别的page网络请求快速 屏蔽它
         */
        viewpage.addOnPageChangeListener(new MyViewPageOnPageChangeListener());
    }

    /**
     * 得到新闻中心
     * @return
     */
    public NewsCenterPager getNewsCenterPager() {
        return (NewsCenterPager) basePagerList.get(1);
    }

    class MyOnCheckedChangeListener implements RadioGroup.OnCheckedChangeListener{

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId){
                case R.id.home:
                    viewpage.setCurrentItem(0,false);
                    break;
                case R.id.news:
                    viewpage.setCurrentItem(1,false);
                    break;
                case R.id.smart:
                    viewpage.setCurrentItem(2,false);
                    break;
                case R.id.govaffair:
                    viewpage.setCurrentItem(3,false);
                    break;
                case R.id.setting:
                    viewpage.setCurrentItem(4,false);
                    break;
            }
        }
    }

    /**
     *
     */
    class  MyViewPageOnPageChangeListener implements ViewPager.OnPageChangeListener{

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {



            basePagerList.get(position).initDate();
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }
    class MyViewPageAdapter extends PagerAdapter{

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

            container.removeView((View) object);
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {

            BasePager basePager = basePagerList.get(position);

            View view=basePager.rootView;
            container.addView(view);

            return view;
        }

        @Override
        public int getCount() {
            return basePagerList.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view==object;
        }
    }





}
