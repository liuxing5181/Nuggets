package com.nuggets.mvp.presenter.base;

import android.support.annotation.NonNull;

import com.nuggets.mvp.view.base.BaseView;

/**
 * Created by Administrator on 2016/11/23.
 */

public interface BasePresenter {
    //    void onResume();

    void onCreate();

    void attachView(@NonNull BaseView view);

    void onDestroy();
}
