package com.nuggets.base;

/**
 * Created by Administrator on 2016/11/17.
 */

public interface ApiCallback<T> {
    void onSuccessful(T t);

    void onFaild(String error);
}
