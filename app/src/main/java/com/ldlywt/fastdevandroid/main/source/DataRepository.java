package com.ldlywt.fastdevandroid.main.source;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.jeremyliao.liveeventbus.LiveEventBus;
import com.ldlywt.base.base.AbsRepository;
import com.ldlywt.base.pagestate.StateConstants;
import com.ldlywt.fastdevandroid.main.common.Constant;

import java.io.IOException;
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

    String url = "http://www.wanandroid.com/banner/json";

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
//                liveData.postValue(e.getMessage());
                LiveEventBus.get().with(Constant.EVENT_KEY,String.class).post(e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String result = response.body().string();
                Log.d("wutao", "onResponse: " + result);
//                liveData.postValue(response.body().toString());
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        LiveEventBus.get().with(StateConstants.PAGE_STATE).post(StateConstants.NET_WORK_STATE);
//                        LiveDataBus.get().with(Constant.EVENT_KEY,String.class).postValue(result);
                    }
                }).start();
            }
        });
        return liveData;
    };

}
