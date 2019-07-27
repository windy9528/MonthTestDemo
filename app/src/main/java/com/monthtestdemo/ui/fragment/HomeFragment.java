package com.monthtestdemo.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.monthtestdemo.R;
import com.monthtestdemo.adapter.HomeTypeAdapter;
import com.monthtestdemo.common.BaseFragment;
import com.monthtestdemo.constract.ImplView;
import com.monthtestdemo.entity.HomeType;
import com.monthtestdemo.entity.Result;
import com.monthtestdemo.presenter.HomePresenter;
import com.stx.xhb.xbanner.XBanner;

import butterknife.BindView;

/**
 * date:2019/7/27
 * name:windy
 * function:
 */
public class HomeFragment extends BaseFragment implements ImplView<HomeType> {

    @BindView(R.id.xBanner)
    XBanner xBanner;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private HomePresenter homePresenter;
    private HomeTypeAdapter homeTypeAdapter;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {
        homePresenter = new HomePresenter(this);
        homeTypeAdapter = new HomeTypeAdapter(getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL,false));
    }

    @Override
    protected void initData() {
        homePresenter.requestData();
        recyclerView.setAdapter(homeTypeAdapter);
    }

    @Override
    protected void destoryData() {

    }

    /**
     * 请求首页数据成功的回调
     *
     * @param result
     */
    @Override
    public void success(Result result) {
        HomeType homeType = (HomeType) result.getResult();
        // mlss 1003    pzsh 1004   rxxp 1002
        homeTypeAdapter.addList(homeType);
        homeTypeAdapter.notifyDataSetChanged();
    }

    /**
     * 请求首页数据失败的回调
     *
     * @param result
     */
    @Override
    public void fail(Result result) {
        showToast(result.getMessage());
    }
}
