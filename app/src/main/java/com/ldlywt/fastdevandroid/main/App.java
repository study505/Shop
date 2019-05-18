package com.ldlywt.fastdevandroid.main;

import com.blankj.utilcode.util.Utils;
import com.ldlywt.base.BaseApp;
import com.zhouyou.http.EasyHttp;

/**
 * author : wutao
 * e-mail : 670831931@qq.com
 * time   : 2019/05/17
 * desc   :
 * version: 1.0
 */
public class App extends BaseApp {
    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);
        EasyHttp.init(this);
        EasyHttp.getInstance()
                .debug("EasyHttp", true);
    }
}
