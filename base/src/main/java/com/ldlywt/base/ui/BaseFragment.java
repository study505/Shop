package com.ldlywt.base.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
public abstract class BaseFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       return inflater.inflate(getLayoutId(),container,false);
    }

    protected abstract @LayoutRes int getLayoutId();

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        handIntent();
        initView();
        initData();
    }

    protected void handIntent() {
    }

    protected abstract void initData();

    protected abstract void initView();

    public abstract class OnCallback<T> implements Resource.OnHandleCallback<T>{

        @Override
        public void onLoading() {

        }

        @SuppressLint("MissingPermission")
        @Override
        public void onError(Throwable e) {
            if(!NetworkUtils.isConnected()){
                ToastUtils.showShort(R.string.result_network_unavailable_error);
                return;
            }
            if(e instanceof ConnectException){
                ToastUtils.showShort( R.string.result_connect_failed_error);
            }else if(e instanceof SocketTimeoutException){
                ToastUtils.showShort( R.string.result_connect_timeout_error);
            }else{
                ToastUtils.showShort( R.string.result_empty_error);
            }
        }

        @Override
        public void onFailure(String msg) {
            ToastUtils.showShort(msg);
        }

        @Override
        public void onCompleted() {

        }
    }
}
