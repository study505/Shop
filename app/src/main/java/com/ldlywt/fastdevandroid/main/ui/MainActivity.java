package com.ldlywt.fastdevandroid.main.ui;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jeremyliao.liveeventbus.LiveEventBus;
import com.ldlywt.base.base.AbsMvvmActivity;
import com.ldlywt.fastdevandroid.R;
import com.ldlywt.fastdevandroid.main.common.Constant;
import com.ldlywt.fastdevandroid.main.vm.MainVm;

public class MainActivity extends AbsMvvmActivity<MainVm> implements View.OnClickListener {

    private Button mBtn;
    private TextView mTv;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        LiveEventBus.get().with(Constant.EVENT_KEY, String.class).observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                Log.i("wutao", "onChanged: " + s);
                mTv.setText(s);
            }
        });

    }

    @Override
    public void initView() {
        super.initView();
        mBtn = (Button) findViewById(R.id.btn);
        mBtn.setOnClickListener(this);
        mTv = (TextView) findViewById(R.id.tv);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.btn:
                mViewModel.getArticleList("1", "1");
                break;
        }
    }
}
