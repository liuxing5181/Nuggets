package com.nuggets.mvp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.nuggets.R;
import com.nuggets.common.Constants;
import com.nuggets.events.ItemSelectedEvent;
import com.nuggets.module.UserInfo;
import com.nuggets.mvp.presenter.impl.LoginPresenter;
import com.nuggets.mvp.ui.activity.base.BaseActivity;
import com.nuggets.mvp.view.ILoginView;

/**
 * Created by Administrator on 2016/11/17.
 */

public class LoginActivity extends BaseActivity implements ILoginView {
    private EditText etName, etPwd;
    private Button btnLogin;
    private LoginPresenter mloginp;
    private ImageView imageView;

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initViews() {
        etName = (EditText) findViewById(R.id.et_name);
        etPwd = (EditText) findViewById(R.id.et_pwd);
        btnLogin = (Button) findViewById(R.id.btn_login);
        mloginp = new LoginPresenter(this);
        imageView = (ImageView) findViewById(R.id.iv_title_back);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mloginp.login();
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                //发送事件的类中不需要进行注册及解绑EventBus
                eventBus.post(new ItemSelectedEvent("1234..."));
            }
        });
    }

    @Override
    public Context getCurContext() {
        return LoginActivity.this;
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showMsg(String msg) {
        showToast(mActivity, msg);
    }

    @Override
    public void toMain() {
        startActivity(new Intent(mActivity, MovieActivity.class));
    }

    @Override
    public UserInfo getUserLogin() {
        UserInfo userInfo = new UserInfo();
        userInfo.name = etName.getText().toString().trim();
        userInfo.pwe = etPwd.getText().toString().trim();
        return userInfo;
    }

}
