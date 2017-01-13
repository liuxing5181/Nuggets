package com.nuggets.mvp.presenter.impl;

import android.support.annotation.NonNull;

import com.nuggets.base.ApiCallback;
import com.nuggets.mvp.model.IloginModel;
import com.nuggets.mvp.model.impl.LoginModel;
import com.nuggets.mvp.presenter.ILoignPresenter;
import com.nuggets.mvp.view.ILoginView;
import com.nuggets.mvp.view.base.BaseView;

/**
 * Created by Administrator on 2016/11/17.
 */

public class LoginPresenter implements ILoignPresenter {
    private ILoginView mView;
    private IloginModel mModel;

    public LoginPresenter(ILoginView mView) {
        this.mView = mView;
        mModel = new LoginModel();
    }

    @Override
    public void login() {
        mModel.login(mView.getUserLogin(), new ApiCallback<String>() {
            @Override
            public void onSuccessful(String s) {
                mView.showMsg(s);
                mView.toMain();
            }

            @Override
            public void onFaild(String error) {
                mView.showMsg(error);
            }
        });
    }

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
