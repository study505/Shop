package com.ldlywt.base.ui;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * <pre>
 *     author : wutao
 *     e-mail : ldlywt@163.com
 *     time   : 2018/12/07
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public abstract class BaseMVVMFragment<VM extends ViewModel> extends BaseFragment {


    protected VM mViewModel;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = createViewModel();
    }

    protected abstract VM createViewModel();

    public <T extends ViewModel> T getViewModel(@NonNull Class<T> modelClass){
        return ViewModelProviders.of(this).get(modelClass);
    }
}
