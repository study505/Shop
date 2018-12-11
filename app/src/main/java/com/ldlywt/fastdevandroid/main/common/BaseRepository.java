package com.ldlywt.fastdevandroid.main.common;


import com.ldlywt.base.base.AbsRepository;
import com.ldlywt.base.event.LiveBus;

public class BaseRepository extends AbsRepository {

//    protected ApiService apiService;
//
//    public BaseRepository() {
//        if (null == apiService) {
//            apiService = HttpHelper.getInstance().create(ApiService.class);
//        }
//    }


    protected void sendData(Object eventKey, Object t) {
        sendData(eventKey, null, t);
    }


    protected void showPageState(Object eventKey, String state) {
        sendData(eventKey, state);
    }

    protected void showPageState(Object eventKey, String tag, String state) {
        sendData(eventKey, tag, state);
    }

    protected void sendData(Object eventKey, String tag, Object t) {
        LiveBus.getDefault().postEvent(eventKey, tag, t);
    }

}
