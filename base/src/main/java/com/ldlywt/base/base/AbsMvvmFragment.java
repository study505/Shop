package com.ldlywt.base.base;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.NonNull;
import com.ldlywt.base.util.TUtil;


public abstract class AbsMvvmFragment<T extends AbsViewModel> extends BaseFragment {

    protected T mViewModel;

    @Override
    public void initView() {
        mViewModel = VMProviders(this, (Class<T>) TUtil.getInstance(this, 0));
    }

    /**
     * create ViewModelProviders
     *
     * @return ViewModel
     */
    protected <T extends ViewModel> T VMProviders(BaseFragment
                                                          fragment, @NonNull Class<T> modelClass) {
        return ViewModelProviders.of(fragment).get(modelClass);
    }

}
