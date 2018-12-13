package com.ldlywt.base.base;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.blankj.utilcode.util.NetworkUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.ldlywt.base.R;
import com.ldlywt.base.model.Resource;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

/**
 * <pre>
 *     author : wutao
 *     e-mail : ldlywt@163.com
 *     time   : 2018/12/07
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        handleIntent();
        initView();
        initData();
    }

    protected abstract @LayoutRes
    int getLayoutId();

    protected void handleIntent() {
    }

    protected abstract void initView();

    protected abstract void initData();

    /**
     * 跳转页面
     *
     * @param clz 所跳转的目的Activity类
     */
    public void startActivity(Class<?> clz) {
        startActivity(new Intent(this, clz));
    }

    /**
     * 跳转页面
     *
     * @param clz    所跳转的目的Activity类
     * @param bundle 跳转所携带的信息
     */
    public void startActivity(Class<?> clz, Bundle bundle) {
        Intent intent = new Intent(this, clz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    public abstract class OnCallback<T> implements Resource.OnHandleCallback<T> {

        @Override
        public void onLoading() {

        }

        @Override
        public void onFailure(String msg) {
            ToastUtils.showShort(msg);
        }

        @SuppressLint("MissingPermission")
        @Override
        public void onError(Throwable e) {
            if (!NetworkUtils.isConnected()) {
                ToastUtils.showShort(R.string.result_network_unavailable_error);
                return;
            }
            if (e instanceof ConnectException) {
                ToastUtils.showShort(R.string.result_connect_failed_error);
            } else if (e instanceof SocketTimeoutException) {
                ToastUtils.showShort(R.string.result_connect_timeout_error);
            } else {
                ToastUtils.showShort(R.string.result_empty_error);
            }
        }

        @Override
        public void onCompleted() {

        }
    }

}
