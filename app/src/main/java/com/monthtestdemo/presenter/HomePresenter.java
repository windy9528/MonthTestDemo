package com.monthtestdemo.presenter;

import com.monthtestdemo.common.ApiService;
import com.monthtestdemo.constract.ImplView;
import com.monthtestdemo.model.BaseModel;

import io.reactivex.Observable;

/**
 * date:2019/7/27
 * name:windy
 * function:
 */
public class HomePresenter extends BaseModel {

    public HomePresenter(ImplView implView) {
        super(implView);
    }

    @Override
    protected Observable getModel(ApiService apiService, Object... args) {
        return apiService.showData();
    }
}
