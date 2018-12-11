package com.ldlywt.fastdevandroid.main;

import com.blankj.utilcode.util.Utils;
import com.ldlywt.base.BaseApp;

/**
 * <pre>
 *     author : wutao
 *     time   : 2018/12/11
 *     desc   :
 * </pre>
 */
public class App extends BaseApp {
    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);
    }
}
