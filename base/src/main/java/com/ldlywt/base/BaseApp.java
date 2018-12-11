package com.ldlywt.base;

import android.app.Application;


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

    private static BaseApp sMBaseApp = null;

    public static BaseApp getApp() {
        return sMBaseApp;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
