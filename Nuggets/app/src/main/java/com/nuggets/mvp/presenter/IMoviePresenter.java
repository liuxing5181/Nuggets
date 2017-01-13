package com.nuggets.mvp.presenter;

import com.nuggets.mvp.presenter.base.BasePresenter;

/**
 * Created by Administrator on 2016/11/23.
 */

public interface IMoviePresenter extends BasePresenter {
    void getMovie();//获取数据

    void loadMoreMovie();//加载更多

    void toLogin();
}
