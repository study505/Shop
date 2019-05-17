package com.ldlywt.base.base;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import com.jeremyliao.liveeventbus.LiveEventBus;
import com.ldlywt.base.pagestate.StateConstants;
import com.ldlywt.base.util.TUtil;


public abstract class AbsMvvmActivity<T extends AbsViewModel> extends BaseActivity {

    protected T mViewModel;

    public AbsMvvmActivity() {

    }

    @Override
    public void initView() {
        mViewModel = VMProviders(this, (Class<T>) TUtil.getInstance(this, 0));
        LiveEventBus.get().with(StateConstants.PAGE_STATE).observe(this, pageStateObserver);
    }

    protected <T extends ViewModel> T VMProviders(AppCompatActivity fragment, @NonNull Class modelClass) {
        return (T) ViewModelProviders.of(fragment).get(modelClass);

    }

    protected Observer pageStateObserver = (Observer<String>) state -> {
        if (!TextUtils.isEmpty(state)) {
            if (StateConstants.ERROR_STATE.equals(state)) {
                showErrorView();
            } else if (StateConstants.NET_WORK_STATE.equals(state)) {
                showNoNetView();
            } else if (StateConstants.LOADING_STATE.equals(state)) {
                showLoadingView();
            } else if (StateConstants.SUCCESS_STATE.equals(state)) {
                showContentView();
            } else if (StateConstants.EMPTY_STATE.equals(state)) {
                showEmptyView();
            }
        }
    };

}
