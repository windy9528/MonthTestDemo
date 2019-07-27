package com.monthtestdemo.ui.fragment;

import android.graphics.Color;
import android.widget.RelativeLayout;

import com.monthtestdemo.R;
import com.monthtestdemo.common.BaseFragment;

import butterknife.BindView;

/**
 * date:2019/7/27
 * name:windy
 * function:
 */
public class CircleFragment extends BaseFragment {

    @BindView(R.id.background_layout)
    RelativeLayout backgroundLayout;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_others;
    }

    @Override
    protected void initView() {
        System.out.println("圈子执行");

    }

    @Override
    protected void initData() {
        backgroundLayout.setBackgroundColor(Color.BLUE);
    }

    @Override
    protected void destoryData() {

    }
}
