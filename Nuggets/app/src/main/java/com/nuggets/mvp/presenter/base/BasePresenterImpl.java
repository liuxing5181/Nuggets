package com.nuggets.mvp.presenter.base;

import android.support.annotation.NonNull;

import com.nuggets.mvp.view.base.BaseView;

/**
 * Created by Administrator on 2016/11/23.
 */

public class BasePresenterImpl<T extends BaseView, E> implements BasePresenter {
    @Override
    public void onCreate() {

    }

    @Override
    public void attachView(@NonNull BaseView view) {

    }

    @Override
    public void onDestroy() {

    }
}
