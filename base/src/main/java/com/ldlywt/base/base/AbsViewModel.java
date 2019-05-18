package com.ldlywt.base.base;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.ldlywt.base.util.TUtil;

/**
 * author : wutao
 * e-mail : 670831931@qq.com
 * time   : 2019/05/17
 * desc   :
 * version: 1.0
 */
public class AbsViewModel extends AndroidViewModel {

    public AbsViewModel(@NonNull Application application) {
        super(application);
    }

}
