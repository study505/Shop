package com.ldlywt.fastdevandroid.main.source;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.jeremyliao.liveeventbus.LiveEventBus;
import com.ldlywt.base.base.AbsRepository;
import com.ldlywt.base.bean.HttpResult;
import com.ldlywt.base.util.Constant;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * <pre>
 *     author : wutao
 *     time   : 2018/12/10
 *     desc   :
 * </pre>
 */
public class DataRepository extends AbsRepository {

    String url = "https://www.wanandroid.com/banner/json";

    public MutableLiveData<String> loadArticleRemList(final String lectureLevel, final String lastId, final String rn){
        final MutableLiveData<String> liveData = new MutableLiveData<>();
        OkHttpClient okHttpClient = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(url)
                .get()//默认就是GET请求，可以不写
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("wutao", "onFailure: ");
                e.printStackTrace();
//                liveData.postValue(e.getMessage());
                LiveEventBus.get().with(Constant.PAGE_STATE,HttpResult.class).post(new HttpResult(HttpResult.ERROR_CODE, ""));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String result = response.body().string();
                Log.d("wutao", "onResponse: " + result);
//                liveData.postValue(response.body().toString());
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        LiveEventBus.get().with(Constant.PAGE_STATE,HttpResult.class).post(new HttpResult(HttpResult.SUCCESS_CODE,"FDSFS"));
                    }
                }).start();
            }
        });
        return liveData;
    };

}
