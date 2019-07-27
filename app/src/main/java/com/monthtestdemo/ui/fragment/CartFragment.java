package com.monthtestdemo.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.monthtestdemo.R;
import com.monthtestdemo.adapter.ShopAdapter;
import com.monthtestdemo.common.BaseFragment;
import com.monthtestdemo.constract.ImplView;
import com.monthtestdemo.entity.Goods;
import com.monthtestdemo.entity.Result;
import com.monthtestdemo.entity.Shop;
import com.monthtestdemo.presenter.CartPresenter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * date:2019/7/27
 * name:windy
 * function:
 */
public class CartFragment extends BaseFragment implements ImplView<List<Shop>> {

    @BindView(R.id.expandableListView)
    ExpandableListView expandableListView;
    @BindView(R.id.checkbox_all)
    CheckBox checkboxAll;
    @BindView(R.id.total_price)
    TextView allPrice;
    @BindView(R.id.btn_go)
    Button btnGo;
    private static CartPresenter cartPresenter;
    private Unbinder bind;
    private ShopAdapter shopAdapter;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_cart;
    }

    /**
     * 初始化控件
     */
    @Override
    protected void initView() {
        cartPresenter = new CartPresenter(this);
        shopAdapter = new ShopAdapter(getContext());
        expandableListView.setAdapter(shopAdapter);
    }

    /**
     * 请求购物车
     */
    @Override
    public void onStart() {
        super.onStart();
        cartPresenter.requestData();
    }

    /**
     * 初始化数据
     */
    @Override
    protected void initData() {
        // CartPresenter.getData("6366", "15605649738176366");  //请求数据

        /**
         * 通过接口回调 回显数据
         */
        shopAdapter.setShowTotalPrice(new ShopAdapter.ShowTotalPrice() {
            @Override
            public void showPrice(double totalPrice, int num, boolean status) {
                allPrice.setText("￥" + totalPrice);
                btnGo.setText("去结算(" + num + ")");
                checkboxAll.setChecked(status);
            }
        });

        fixParent();
    }

    /**
     * 使得父布局不可伸缩,不可点击
     */
    private void fixParent() {
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return true;
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        //当再次回到此页面时，全选框状态默认为false
        checkboxAll.setChecked(false);
    }

    /**
     * 点击全选  实现全选和反选
     */
    @OnClick(R.id.checkbox_all)
    public void clickCheckAll() {
        List<Shop> shops = shopAdapter.getList();
        for (int i = 0; i < shops.size(); i++) {
            Shop shop = shops.get(i);
            shop.shopCheck = checkboxAll.isChecked();  //把当前全选框的状态给父级复选框
            for (int j = 0; j < shop.getShoppingCartList().size(); j++) {
                Goods goods = shop.getShoppingCartList().get(j);
                goods.goodCheck = shop.shopCheck;
            }
        }
        shopAdapter.calculate();
        shopAdapter.notifyDataSetChanged();
    }

    /**
     * 网络请求成功回调
     *
     * @param data
     */
    @Override
    public void success(Result data) {
        List<Shop> result = (List<Shop>) data.getResult();
        shopAdapter.clear();
        //遍历所有父级条目，使其默认展开
        shopAdapter.addList(result);
        for (int i = 0; i < shopAdapter.getGroupCount(); i++) {
            expandableListView.expandGroup(i);
        }
        shopAdapter.notifyDataSetChanged();
    }


    /**
     * 网络请求失败回调
     *
     * @param result
     */
    @Override
    public void fail(Result result) {
        showToast(result.getMessage());
    }

    @Override
    protected void destoryData() {

    }
}
