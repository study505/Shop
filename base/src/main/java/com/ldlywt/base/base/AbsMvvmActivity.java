package com.ldlywt.base.base;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.ldlywt.base.util.TUtil;


public abstract class AbsMvvmActivity<T extends AbsViewModel> extends BaseActivity {

    protected T mViewModel;

    public AbsMvvmActivity() {

    }

    @Override
    public void initView() {
        mViewModel = VMProviders(this, (Class<T>) TUtil.getInstance(this, 0));
    }

    protected <T extends ViewModel> T VMProviders(AppCompatActivity fragment, @NonNull Class modelClass) {
        return (T) ViewModelProviders.of(fragment).get(modelClass);

    }

}
