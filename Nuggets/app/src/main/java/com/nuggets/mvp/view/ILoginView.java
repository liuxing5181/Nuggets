package com.nuggets.mvp.view;

import android.content.Context;

import com.nuggets.module.UserInfo;
import com.nuggets.mvp.view.base.BaseView;

/**
 * Created by Administrator on 2016/11/23.
 */

public interface ILoginView extends BaseView{
    Context getCurContext();

    void showMsg(String msg);

    void toMain();

    UserInfo getUserLogin();
}
