package com.monthtestdemo.common;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * date:2019/7/27
 * name:windy
 * function:
 */
public abstract class BaseFragment extends Fragment {

    private Unbinder bind;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(setLayoutId(), container, false);

        bind = ButterKnife.bind(this, view);

        initView();

        initData();

        return view;
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
    public void onDestroy() {
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
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }
}
