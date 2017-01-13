package com.nuggets.mvp.model;

import com.nuggets.base.ApiCallback;
import com.nuggets.module.Movies;
import com.nuggets.mvp.model.base.BaseModel;

/**
 * Created by Administrator on 2016/11/23.
 */

public interface IMovieModel extends BaseModel{
    void getMovie(int start, int count, ApiCallback<Movies> callBack);//获取信息

}
