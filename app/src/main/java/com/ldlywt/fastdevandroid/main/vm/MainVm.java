package com.ldlywt.fastdevandroid.main.vm;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.ldlywt.base.base.AbsViewModel;
import com.ldlywt.fastdevandroid.main.source.DataRepository;

/**
 * <pre>
 *     author : wutao
 *     time   : 2018/12/10
 *     desc   :
 * </pre>
 */
public class MainVm extends AbsViewModel<DataRepository> {

    public MainVm(@NonNull Application application) {
        super(application);
    }

    public LiveData<String> getArticleList(String lectureLevel1, String lastId) {
       return mRepository.loadArticleRemList(lectureLevel1, lastId, "20");
    }

}
