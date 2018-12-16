package com.ldlywt.base;

import android.app.Application;

import com.ldlywt.base.pagestate.XPageStateView;


/**
 * <pre>
 *     author : wutao
 *     e-mail : ldlywt@163.com
 *     time   : 2018/12/07
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class BaseApp extends Application {

    private static BaseApp sApp = null;

    public static BaseApp getApp() {
        return sApp;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sApp = this;
        XPageStateView.init();
    }
}
