package com.ldlywt.base.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.blankj.utilcode.util.ToastUtils;
import com.gyf.immersionbar.ImmersionBar;
import com.jeremyliao.liveeventbus.LiveEventBus;
import com.kingja.loadsir.callback.Callback;
import com.kingja.loadsir.callback.SuccessCallback;
import com.kingja.loadsir.core.Convertor;
import com.kingja.loadsir.core.LoadService;
import com.kingja.loadsir.core.LoadSir;
import com.ldlywt.base.bean.BaseResult;
import com.ldlywt.base.pagestate.EmptyCallback;
import com.ldlywt.base.pagestate.ErrorCallback;
import com.ldlywt.base.pagestate.LoadingCallback;
import com.ldlywt.base.util.Constant;

/**
 * <pre>
 *     author : wutao
 *     e-mail : ldlywt@163.com
 *     time   : 2018/12/07
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public abstract class BaseActivity extends AppCompatActivity implements IUiCallback {


    private LoadService loadService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        setStatusBar();
        handleIntent();
        initView();
        initData(savedInstanceState);
        registerPageState();
        LiveEventBus.get().with(Constant.PAGE_STATE, BaseResult.class).observe(this,
                httpResult -> loadService.showWithConvertor(httpResult));
    }

    protected void setStatusBar() {
        ImmersionBar.with(this)
                .transparentStatusBar()
                .init();
    }

    protected void handleIntent() {
    }

    private void registerPageState() {
        loadService = LoadSir.getDefault().register(this, (Callback.OnReloadListener) v -> {
            loadService.showCallback(LoadingCallback.class);
            retryClick();
        }, (Convertor<BaseResult>) httpResult -> {
            Class<? extends Callback> resultCode;
            switch (httpResult.getCode()) {
                case BaseResult.SUCCESS_CODE:
                    if (httpResult.getData() != null) {
                        resultCode = SuccessCallback.class;
                    } else {
                        resultCode = EmptyCallback.class;
                    }
                    break;
                case BaseResult.ERROR_CODE:
                    resultCode = ErrorCallback.class;
                    break;
                default:
                    resultCode = ErrorCallback.class;
            }
            return resultCode;
        });
    }



    protected void retryClick() {
        ToastUtils.showShort("重新请求");
    }

    /**
     * 获取一个 Context 对象
     */
    public Context getContext() {
        return getBaseContext();
    }


    /**
     * 获取当前 Activity 对象
     */
    public <A extends BaseActivity> A getActivity() {
        return (A) this;
    }

    /**
     * 跳转到其他 Activity 并销毁当前 Activity
     */
    public void startActivityFinish(Class<? extends Activity> cls) {
        startActivity(cls);
        finish();
    }

    /**
     * 跳转到其他 Activity
     */
    public void startActivity(Class<? extends Activity> cls) {
        startActivity(new Intent(this, cls));
    }
}
