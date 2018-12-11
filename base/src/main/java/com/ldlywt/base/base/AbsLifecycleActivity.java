package com.ldlywt.base.base;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.ldlywt.base.util.TUtil;


public abstract class AbsLifecycleActivity<T extends AbsViewModel> extends BaseActivity {

    protected T mViewModel;

    public AbsLifecycleActivity() {

    }

    @Override
    protected void initView() {
        mViewModel = VMProviders(this, (Class<T>) TUtil.getInstance(this, 0));
        dataObserver();
    }

    protected <T extends ViewModel> T VMProviders(AppCompatActivity fragment, @NonNull Class modelClass) {
        return (T) ViewModelProviders.of(fragment).get(modelClass);

    }

    protected void dataObserver() {

    }

}
