package com.ldlywt.fastdevandroid.main.common;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;
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
public class DataRepository extends BaseRepository {

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
                liveData.postValue(e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d("wutao", "onResponse: " + response.body().string());
//                sendData("wutao",response.body().string());
                liveData.postValue(response.body().toString());
            }
        });
        return liveData;
    };

}
