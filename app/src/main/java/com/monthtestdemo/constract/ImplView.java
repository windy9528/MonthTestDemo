package com.monthtestdemo.constract;

import com.monthtestdemo.entity.Result;

/**
 * date:2019/7/27
 * name:windy
 * function:
 */
public interface ImplView<T> {

    void success(Result result);

    void fail(Result result);

}
