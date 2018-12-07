package com.ldlywt.base.vm;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.ldlywt.base.BaseApp;
import com.ldlywt.base.repository.BaseRepository;

/**
 * <pre>
 *     author : wutao
 *     e-mail : ldlywt@163.com
 *     time   : 2018/12/07
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class BaseViewModel<T extends BaseRepository> extends AndroidViewModel {


    private T repository;

    public BaseViewModel(@NonNull Application application,T repository) {
        super(application);
        this.repository = repository;
    }

    public BaseApp getApp(){
        return getApplication();
    }

    public T getRepository(){
        return repository;
    }

}