package com.monthtestdemo.ui.activity;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.monthtestdemo.R;
import com.monthtestdemo.adapter.FragmentAdapter;
import com.monthtestdemo.common.BaseActivity;
import com.monthtestdemo.ui.fragment.CartFragment;
import com.monthtestdemo.ui.fragment.CircleFragment;
import com.monthtestdemo.ui.fragment.HomeFragment;
import com.monthtestdemo.ui.fragment.OrderFragment;
import com.monthtestdemo.ui.fragment.UserFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.radioGroup)
    RadioGroup radioGroup;
    private FragmentAdapter fragmentAdapter;
    private List<Fragment> list;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        list = new ArrayList<>();
        if (list.size() == 0) { //如果为0 添加数据
            list.add(new HomeFragment());
            list.add(new CircleFragment());
            list.add(new CartFragment());
            list.add(new OrderFragment());
            list.add(new UserFragment());
        }
        fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), list);
    }

    @Override
    protected void initData() {
        viewPager.setAdapter(fragmentAdapter);
        viewPager.setOffscreenPageLimit(4);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.home:
                        viewPager.setCurrentItem(0, false);
                        break;
                    case R.id.circle:
                        viewPager.setCurrentItem(1, false);
                        break;
                    case R.id.cart:
                        viewPager.setCurrentItem(2, false);
                        break;
                    case R.id.order:
                        viewPager.setCurrentItem(3, false);
                        break;
                    case R.id.user:
                        viewPager.setCurrentItem(4, false);
                        break;

                }
            }
        });
    }

    @Override
    protected void destoryData() {

    }
}
