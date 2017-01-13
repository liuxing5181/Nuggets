package com.nuggets.mvp.model.impl;

import com.nuggets.base.ApiCallback;
import com.nuggets.base.SubscriberCallBack;
import com.nuggets.module.Movies;
import com.nuggets.http.RetrofitUtils;
import com.nuggets.mvp.model.IMovieModel;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/11/18.
 */

public class MovieModel implements IMovieModel {
    Subscription subscription;

    @Override
    public void getMovie(int start, int count, final ApiCallback<Movies> callBack) {
        subscription = RetrofitUtils.getRetrofitInterface()
                .getMovies(start, count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SubscriberCallBack(callBack));
    }

}
