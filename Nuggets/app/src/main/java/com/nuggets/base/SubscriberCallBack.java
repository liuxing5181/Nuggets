package com.nuggets.base;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;

/**
 * Created by Administrator on 2016/11/23.
 * <p>
 * rxjava
 */

public class SubscriberCallBack<T> extends Subscriber<T> {
    private ApiCallback<T> apiCallback;

    public SubscriberCallBack(ApiCallback<T> apiCallback) {
        this.apiCallback = apiCallback;
    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        //失败的时候回调-----一下可以忽略 直接 callBack.onFaild("请求失败");
        if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            //httpException.response().errorBody().string()
            int code = httpException.code();
            if (code == 500 || code == 404) {
                apiCallback.onFaild("服务器出错");
            }
        } else if (e instanceof ConnectException) {
            apiCallback.onFaild("网络断开,请打开网络!");
        } else if (e instanceof SocketTimeoutException) {
            apiCallback.onFaild("网络连接超时!!");
        } else {
            apiCallback.onFaild("发生未知错误" + e.getMessage());
        }
    }

    @Override
    public void onNext(T t) {
        apiCallback.onSuccessful(t);//请求成功---回调
    }
}
