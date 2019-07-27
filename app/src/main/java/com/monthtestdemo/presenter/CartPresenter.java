package com.monthtestdemo.presenter;


import com.monthtestdemo.common.ApiService;
import com.monthtestdemo.constract.ImplView;
import com.monthtestdemo.model.BaseModel;

import io.reactivex.Observable;

/**
 * Author: 杨高峰(windy)
 * Date: 2019/6/16 18:35
 * Description:展示购物车列表
 */
public class CartPresenter extends BaseModel {

    public CartPresenter(ImplView implView) {
        super(implView);
    }

    @Override
    protected Observable getModel(ApiService apiService, Object... args) {
        return apiService.shopCar(
                String.valueOf(args[0]),
                String.valueOf(args[1])
        );
    }
}
