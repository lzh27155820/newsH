package com.xyz.newsh.base;

import android.content.Context;
import android.view.View;

import com.xyz.newsh.domain.NewsCenterBean;

public abstract class MenuDetailBasePager {

    public Context content;
    public View rootView;
    public NewsCenterBean.DataEntity dataEntity;
   public NewsCenterBean.Children children;
    public MenuDetailBasePager(Context context){
        this.content = context;

        rootView = initView();
    }

    public MenuDetailBasePager(Context context,NewsCenterBean.DataEntity dataEntity){
        this.content = context;
        this.dataEntity=dataEntity;
        rootView = initView();
    }

    public MenuDetailBasePager(Context content, NewsCenterBean.Children children) {
        this.content = content;

        this.children = children;
        rootView = initView();
    }

    /**
     * 当前的页面的布局，有于抽取的页面布局都不一样，子类必须实现此方法，必须创建自己的布局，并且返回
     * @return
     */
    public  abstract View initView() ;

    /**
     * 子类覆盖此方法，实现自己的数据初始化
     */
    public void initData(){

    }

}
