package com.nuggets.mvp.model;

import com.nuggets.base.ApiCallback;
import com.nuggets.module.UserInfo;
import com.nuggets.mvp.model.base.BaseModel;

/**
 * Created by Administrator on 2016/11/23.
 */

public interface IloginModel extends BaseModel{
    void login(UserInfo userInfo, ApiCallback<String> back);
}
