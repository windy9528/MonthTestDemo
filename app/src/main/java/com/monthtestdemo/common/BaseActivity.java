package com.monthtestdemo.common;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * date:2019/7/27
 * name:windy
 * function:
 */
public abstract class BaseActivity extends AppCompatActivity {

    private Unbinder bind;

    @Nullable
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(setLayoutId());

        bind = ButterKnife.bind(this);

        initView();

        initData();
    }

    /**
     * 设置布局
     *
     * @return
     */
    protected abstract int setLayoutId();

    /**
     * 初始化控件
     */
    protected abstract void initView();


    /**
     * 初始化数据
     */
    protected abstract void initData();


    /**
     * 销毁数据 避免内存泄露
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (bind != null) {
            bind.unbind();
            bind = null;
        }
        destoryData();
        System.gc();
    }

    /**
     * 销毁数据
     */
    protected abstract void destoryData();

    /**
     * 封装吐司功能
     *
     * @param msg
     */
    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
