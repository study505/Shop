package com.ldlywt.base.pagestate;

import android.content.Context;
import android.view.View;

import com.kingja.loadsir.callback.Callback;
import com.ldlywt.base.R;


/**
 * author : wutao
 * e-mail : 670831931@qq.com
 * time   : 2019/05/17
 * desc   :
 * version: 1.0
 */
public class PlaceholderCallback extends Callback {

    @Override
    protected int onCreateView() {
        return R.layout.layout_placeholder;
    }

    @Override
    protected boolean onReloadEvent(Context context, View view) {
        return true;
    }
}
