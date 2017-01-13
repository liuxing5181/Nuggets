package com.nuggets.mvp.presenter.impl;

import android.support.annotation.NonNull;

import com.nuggets.base.ApiCallback;
import com.nuggets.module.Movies;
import com.nuggets.mvp.model.IMovieModel;
import com.nuggets.mvp.presenter.IMoviePresenter;
import com.nuggets.mvp.model.impl.MovieModel;
import com.nuggets.mvp.view.IMovieView;
import com.nuggets.mvp.view.base.BaseView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/18.
 */

public class MoviePresenter implements IMoviePresenter {
    IMovieModel mIMovieModel;//M层
    IMovieView mIMovieView;//V层
    public  int start = 0;//从第几个开始
    public  int count = 4;//请求多少个
    List<Movies.SubjectsBean> mMovies = new ArrayList<>();//请求到的电影信息对象集合

    public MoviePresenter(IMovieView mIMovieView) {
        this.mIMovieView = mIMovieView;
        mIMovieModel = new MovieModel();
    }
    @Override
    public void getMovie() {
        mIMovieView.showProgress();//通知V层显示对话框
        //每次刷新加载4个
        mIMovieModel.getMovie(start, count, new ApiCallback<Movies>() {//有一个请求结果的回调,即我调用请求电影信息的方法了,M层要返回一个成功还是失败的信息给我
            @Override
            public void onSuccessful(Movies movies) {//获取电影信息成功了,返回movies对象
                mIMovieView.hideProgress();//通知V层隐藏对话框
                mMovies.addAll(movies.getSubjects());//追加数据
                mIMovieView.showData(mMovies);//将获取到的信息显示到界面之前
                mIMovieView.showBottom(start - 5);//实现换页效果
            }

            @Override
            public void onFaild(String errorMsg) {
                mIMovieView.hideProgress();//通知V层隐藏对话框
                mIMovieView.showInfo(errorMsg);//通知V层显示错误信息
            }
        });
        start = start + 4;//改变请求的起点
    }

    @Override
    public void loadMoreMovie() {
        getMovie();
    }

    @Override
    public void toLogin() {
        mIMovieView.toLogin();
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
