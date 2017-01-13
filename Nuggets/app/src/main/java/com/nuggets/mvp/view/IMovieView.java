package com.nuggets.mvp.view;

import android.content.Context;

import com.nuggets.module.Movies;
import com.nuggets.mvp.view.base.BaseView;

import java.util.List;

/**
 * Created by Administrator on 2016/11/23.
 */

public interface IMovieView extends BaseView {
    void showBottom(int lastIndex);

    void showData(List<Movies.SubjectsBean> movies);

    void showInfo(String info);

    void toLogin();
}
