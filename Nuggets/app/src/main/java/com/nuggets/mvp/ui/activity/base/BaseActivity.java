package com.nuggets.mvp.ui.activity.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;


/**
 * Created by Administrator on 2016/7/22.
 */
public abstract class BaseActivity extends AppCompatActivity {
    public Activity mActivity;
    private static Toast mToast;
    public EventBus eventBus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = this;
        eventBus = EventBus.getDefault();
//        new ZTLUtils(mActivity).setTranslucentStatus();
        int layoutId = getLayoutId();
        setContentView(layoutId);
        ButterKnife.bind(this);
        initViews();
    }

    @Override
    protected void onStart() {
        super.onStart();

    }


    public abstract int getLayoutId();

    public abstract void initViews();

    public static void showToast(Context context, String content) {
        if (mToast == null) {
            mToast = Toast.makeText(context, "error:" + content, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(content);
        }
        mToast.show();
    }
}
