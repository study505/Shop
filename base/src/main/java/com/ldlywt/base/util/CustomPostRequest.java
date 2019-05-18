package com.ldlywt.base.util;

import com.ldlywt.base.bean.BaseResult;
import com.zhouyou.http.callback.CallBack;
import com.zhouyou.http.callback.CallBackProxy;
import com.zhouyou.http.callback.CallClazzProxy;
import com.zhouyou.http.request.GetRequest;
import com.zhouyou.http.request.PostRequest;

import java.lang.reflect.Type;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * <pre>
 *     @author : wutao
 *     e-mail : wutao@himango.cn
 *     time   : 2019/05/18
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class CustomPostRequest extends PostRequest {

    public CustomPostRequest(String url) {
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
