package com.monthtestdemo.model;

import com.monthtestdemo.common.ApiService;
import com.monthtestdemo.constract.ImplView;
import com.monthtestdemo.entity.Result;
import com.monthtestdemo.util.RetrofitUtil;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * date:2019/7/27
 * name:windy
 * function:
 */
public abstract class BaseModel {

    private ImplView implView;

    public BaseModel(ImplView implView) {
        this.implView = implView;
    }

    public void requestData(Object... args) {

        ApiService apiService = RetrofitUtil.getmInstance().create(ApiService.class);

        getModel(apiService, args)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Result>() {
                    @Override
                    public void accept(Result result) throws Exception {
                        if ("0000".equals(result.getStatus())) {
                            implView.success(result);
                        } else {
                            implView.fail(result);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                    }
                });
    }

    protected abstract Observable getModel(ApiService apiService, Object... args);

    public void unBind() {
        if (implView != null) {
            implView = null;
        }
    }
}
