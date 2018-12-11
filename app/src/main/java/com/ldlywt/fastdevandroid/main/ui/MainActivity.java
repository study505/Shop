package com.ldlywt.fastdevandroid.main.ui;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ldlywt.base.base.AbsLifecycleActivity;
import com.ldlywt.fastdevandroid.R;
import com.ldlywt.fastdevandroid.main.vm.MainVm;

public class MainActivity extends AbsLifecycleActivity<MainVm> implements View.OnClickListener {

    private Button mBtn;
    /**
     * Hello World!
     */
    private TextView mTv;

    @Override
    protected void initData() {
        mViewModel.getArticleList("1", "1").observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                Log.i("wutao", "onChanged: " + s);
                mTv.setText(s);
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        super.initView();
        mBtn = (Button) findViewById(R.id.btn);
        mBtn.setOnClickListener(this);
        mTv = (TextView) findViewById(R.id.tv);
    }

    @Override
    protected void dataObserver() {
        super.dataObserver();

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO:OnCreate Method has been created, run FindViewById again to generate code
        setContentView(R.layout.activity_main);
        initView();
    }
}
