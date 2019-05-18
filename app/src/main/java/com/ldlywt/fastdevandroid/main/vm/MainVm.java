package com.ldlywt.fastdevandroid.main.vm;

import android.app.Application;
import android.support.annotation.NonNull;
import android.util.Log;

import com.blankj.utilcode.util.ToastUtils;
import com.jeremyliao.liveeventbus.LiveEventBus;
import com.ldlywt.base.base.AbsViewModel;
import com.ldlywt.base.bean.BaseResult;
import com.ldlywt.base.util.CustomGetRequest;
import com.ldlywt.fastdevandroid.main.bean.BannerBean;
import com.ldlywt.fastdevandroid.main.common.GlobalConstant;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.CallBack;
import com.zhouyou.http.callback.CallBackProxy;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * <pre>
 *     author : wutao
 *     time   : 2018/12/10
 *     desc   :
 * </pre>
 */
public class MainVm extends AbsViewModel {

    String url = "https://www.wanandroid.com/banner/json";

    public MainVm(@NonNull Application application) {
        super(application);
    }

    public void getArticleList() {
//        EasyHttp.get(url)
//        .execute(new CallBackProxy<BaseResult<List<BannerBean>>, List<BannerBean>>(new SimpleCallBack<List<BannerBean>>() {
//            @Override
//            public void onError(ApiException e) {
//                ToastUtils.showShort(e.getMessage());
//            }
//
//            @Override
//            public void onSuccess(List<BannerBean> bannerBeans) {
//                Log.i("wutao", "onSuccess: " + bannerBeans);
//            }
//        }) {
//        });

        new CustomGetRequest(url)
                .execute(new SimpleCallBack<List<BannerBean>>() {
                    @Override
                    public void onError(ApiException e) {

                    }

                    @Override
                    public void onSuccess(List<BannerBean> bannerBeans) {
                        Log.i("wutao", "onSuccess: " + bannerBeans);
                        LiveEventBus.get().with(GlobalConstant.EVENT_KEY, List.class)
                                .post(bannerBeans);
                    }
                });
    }

}
