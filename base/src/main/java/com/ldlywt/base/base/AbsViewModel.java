package com.ldlywt.base.base;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.ldlywt.base.util.TUtil;


public class AbsViewModel extends AndroidViewModel {

    public AbsViewModel(@NonNull Application application) {
        super(application);
    }

}
