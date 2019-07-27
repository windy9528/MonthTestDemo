package com.monthtestdemo.common;

import com.monthtestdemo.entity.HomeType;
import com.monthtestdemo.entity.Result;
import com.monthtestdemo.entity.Shop;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;

/**
 * date:2019/7/27
 * name:windy
 * function:
 */
public interface ApiService {

    @GET(Constant.HOME_URL)
    Observable<Result<HomeType>> showData();

    //查询购物车数据
    @GET("order/verify/v1/findShoppingCart")
    Observable<Result<List<Shop>>> shopCar(@Header("userId") String userId,
                                           @Header("sessionId") String sessionId);

}
