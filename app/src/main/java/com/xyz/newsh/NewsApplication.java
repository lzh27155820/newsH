package com.xyz.newsh;

import android.app.Application;

import org.xutils.x;

public class NewsApplication extends Application {

    // 在application的onCreate中初始化
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG); // 是否输出debug日志, 开启debug会影响性能.

    }
}
