package com.ldlywt.base.util;

import com.ldlywt.base.bean.BaseResult;
import com.zhouyou.http.callback.CallBack;
import com.zhouyou.http.callback.CallBackProxy;
import com.zhouyou.http.callback.CallClazzProxy;
import com.zhouyou.http.request.GetRequest;

import java.lang.reflect.Type;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * author : wutao
 * e-mail : 670831931@qq.com
 * time   : 2019/05/17
 * desc   :
 * version: 1.0
 */
public class CustomGetRequest extends GetRequest {

    public CustomGetRequest(String url) {
        super(url);
    }

    @Override
    public <T> Observable<T> execute(Type type) {
        return execute(new CallClazzProxy<BaseResult<T>, T>(type) {
        });
    }

    @Override
    public <T> Disposable execute(CallBack<T> callBack) {
        return execute(new CallBackProxy<BaseResult<T>, T>(callBack) {
        });
    }
}
