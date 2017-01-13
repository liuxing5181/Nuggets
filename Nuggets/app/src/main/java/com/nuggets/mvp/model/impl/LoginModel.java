package com.nuggets.mvp.model.impl;

import android.util.Log;

import com.nuggets.base.ApiCallback;
import com.nuggets.module.UserInfo;
import com.nuggets.mvp.model.IloginModel;

/**
 * Created by Administrator on 2016/11/17.
 */

public class LoginModel implements IloginModel {
    @Override
    public void login(UserInfo userInfo, ApiCallback<String> back) {
        Log.e("model", "name:" + userInfo.name + ";pwe:" + userInfo.pwe);
        if (userInfo.name.equals("李白")) {
            back.onSuccessful("成功");
        } else {
            back.onFaild("失败");
        }
    }
}
