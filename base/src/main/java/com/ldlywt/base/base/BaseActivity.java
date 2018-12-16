package com.ldlywt.base.base;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.blankj.utilcode.util.NetworkUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.ldlywt.base.R;
import com.ldlywt.base.model.Resource;
import com.ldlywt.base.pagestate.XPageStateView;

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
public abstract class BaseActivity extends AppCompatActivity implements ICallback{

    protected XPageStateView mPageStateView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        handleIntent();
        mPageStateView = XPageStateView.wrap(this);
        mPageStateView.setOnRetryClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtils.showShort("重新请求");
            }
        });
        initView();
        initData(savedInstanceState);
    }
    protected void handleIntent() {
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
     * 跳转到其他 Activity
     * @param cls       目标Activity的Class
     */
    public void startActivity(Class<? extends Activity> cls) {
        startActivity(new Intent(this, cls));
    }

    /**
     * 跳转到其他 Activity 并销毁当前 Activity
     * @param cls       目标Activity的Class
     */
    public void startActivityFinish(Class<? extends Activity> cls) {
        startActivity(cls);
        finish();
    }


    protected void showEmptyView() {
        mPageStateView.showEmpty();
    }

    protected void showErrorView() {
        mPageStateView.showError();
    }

    protected void showLoadingView() {
        mPageStateView.showLoading();
    }

    protected void showNoNetView() {
        mPageStateView.showNoNetwork();
    }

    protected void showContentView() {
        mPageStateView.showContent();
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
