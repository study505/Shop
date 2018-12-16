package com.ldlywt.base.base;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.NetworkUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.ldlywt.base.R;
import com.ldlywt.base.model.Resource;
import com.ldlywt.base.pagestate.XPageStateView;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

public abstract class BaseFragment extends Fragment implements IUiCallback {
    protected FragmentActivity activity;
    protected boolean mIsFirstVisible = true;
    protected XPageStateView mPageStateView;

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            onVisible();
        } else {
            onInVisible();
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            onVisible();
        } else {
            onInVisible();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.activity = (FragmentActivity) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle state) {
        if (getLayoutId() > 0) {
            return inflater.inflate(getLayoutId(), container, false);
        } else {
            return super.onCreateView(inflater, container, state);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initPageState();
        handleIntent();
        initView();
        needLazy();
        initData(savedInstanceState);
    }

    private void initPageState() {
        mPageStateView = XPageStateView.wrap(this);
        mPageStateView.setOnRetryClickListener(view -> retryClick());
    }

    protected void retryClick() {
        ToastUtils.showShort("重新请求");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.activity = null;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.activity = null;
    }

    protected void handleIntent() {
    }

    private void needLazy() {
        boolean isVis = isHidden() || getUserVisibleHint();
        if (isVis && mIsFirstVisible) {
            lazyLoad();
            mIsFirstVisible = false;
        }
    }

    /**
     * 当界面可见时的操作
     */
    protected void onVisible() {
        if (mIsFirstVisible && isResumed()) {
            lazyLoad();
            mIsFirstVisible = false;
        }
    }

    /**
     * 当界面不可见时的操作
     */
    protected void onInVisible() {

    }

    /**
     * 数据懒加载
     */
    protected void lazyLoad() {

    }

    @Override
    public void showEmptyView() {
        mPageStateView.showEmpty();
    }

    @Override
    public void showErrorView() {
        mPageStateView.showError();
    }

    @Override
    public void showLoadingView() {
        mPageStateView.showLoading();
    }

    @Override
    public void showNoNetView() {
        mPageStateView.showNoNetwork();
    }

    @Override
    public void showContentView() {
        mPageStateView.showContent();
    }

    /**
     * 跳转到其他 Activity 并销毁当前 Activity
     *
     * @param cls 目标Activity的Class
     */
    public void startActivityFinish(Class<? extends Activity> cls) {
        startActivity(cls);
        activity.finish();
    }

    /**
     * 跳转到其他Activity
     *
     * @param cls 目标Activity的Class
     */
    public void startActivity(Class<? extends Activity> cls) {
        startActivity(new Intent(getContext(), cls));
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
