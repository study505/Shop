package com.ldlywt.fastdevandroid.main;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.Utils;
import com.ldlywt.base.BaseApp;
import com.ldlywt.base.BuildConfig;

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
        if (BuildConfig.DEBUG) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this); // 尽可能早，推荐在Application中初始化
    }
}
