package com.nuggets.mvp.ui.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.nuggets.R;
import com.nuggets.common.Constants;
import com.nuggets.events.ItemSelectedEvent;
import com.nuggets.mvp.ui.activity.base.BaseActivity;
import com.nuggets.module.Movies;
import com.nuggets.mvp.presenter.impl.MoviePresenter;
import com.nuggets.mvp.view.IMovieView;
import com.nuggets.util.DividerItemDecoration;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.adapter.recyclerview.wrapper.LoadMoreWrapper;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.header.StoreHouseHeader;

/**
 * Created by Administrator on 2016/11/18.
 */

public class MovieActivity extends BaseActivity implements IMovieView {
    MoviePresenter mMoviePresenter;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    private ProgressDialog mProgressDialog;
    @Bind(R.id.rv_movie_list)
    RecyclerView rvMovieList;
    @Bind(R.id.store_house_ptr_frame)
    PtrFrameLayout storeHousePtrFrame;
    @Bind(R.id.fab)
    FloatingActionButton fAb;
    private TextView load_more;//加载更多的按钮

    @Override
    public int getLayoutId() {
        return R.layout.activity_movie;
    }

    @Override
    public void initViews() {
        eventBus.register(this);
        initView();
        mMoviePresenter = new MoviePresenter(this);
        mMoviePresenter.getMovie();//启动软件时默认加载
    }

    private void initView() {
        tvTitle.setText("豆瓣Top250的电影");
        initPtr();
        rvMovieList.setLayoutManager(new LinearLayoutManager(mActivity));//设置为listview的布局
        rvMovieList.setItemAnimator(new DefaultItemAnimator());//设置动画
        rvMovieList.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));//添加分割线
    }

    /**
     * 初始化(配置)下拉刷新组件
     */
    private void initPtr() {
        //下面是一些基础的配置,直接拿来用就可以 不用深究
        storeHousePtrFrame.setResistance(1.7f);
        storeHousePtrFrame.setRatioOfHeaderHeightToRefresh(1.2f);
        storeHousePtrFrame.setDurationToClose(200);
        storeHousePtrFrame.setDurationToCloseHeader(1000);
        storeHousePtrFrame.setPullToRefresh(false);
        storeHousePtrFrame.setKeepHeaderWhenRefresh(true);
        StoreHouseHeader header = new StoreHouseHeader(this);
        float scale = getResources().getDisplayMetrics().density;
        header.setPadding(0, (int) (15 * scale + 0.5f), 0, (int) (15 * scale + 0.5f));
        header.initWithString("HDL");//自定义头显示的字样,设置图片的话看另外的api
        header.setTextColor(Color.RED);
        header.setBackgroundColor(Color.parseColor("#11000000"));
        storeHousePtrFrame.setHeaderView(header);//添加头
        storeHousePtrFrame.addPtrUIHandler(header);//同时也要加上这一句
        storeHousePtrFrame.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                mMoviePresenter.loadMoreMovie();//下拉刷新的时候加载更多数据
                frame.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        storeHousePtrFrame.refreshComplete();
                    }
                }, 150);//为了增加用户体验 延迟0.15s再通知刷新结束
            }
        });

    }

    @Override
    public void showBottom(int lastIndex) {
        load_more.setText("点击加载更多");//设置最底下的加载更多显示的内容    加载中-->点击加载更多
        rvMovieList.scrollToPosition(lastIndex);
    }

    @Override
    public void showProgress() {
        Log.e("TAG", "show-----");
        mProgressDialog = ProgressDialog.show(MovieActivity.this, "提示", "正在获取中,请稍后...");
    }

    @Override
    public void hideProgress() {
        mProgressDialog.hide();
    }

    @Override
    public void showMsg(String message) {

    }

    @Override
    public void showData(List<Movies.SubjectsBean> movies) {
        CommonAdapter<Movies.SubjectsBean> commonAdapter = new CommonAdapter<Movies.SubjectsBean>(this, R.layout.movie_item, movies) {

            @Override
            protected void convert(ViewHolder holder, Movies.SubjectsBean subjectsBean, int position) {
                String title = (position + 1) + "、" + subjectsBean.getTitle() + "/" + subjectsBean.getOriginal_title();
                holder.setText(R.id.tv_movie_title, title);//设置电影名
                String doc = "";
                for (Movies.SubjectsBean.DirectorsBean directorsBean : subjectsBean.getDirectors()) {
                    doc += directorsBean.getName() + "  ";
                }
                holder.setText(R.id.tv_movie_doc, "导演:" + doc);
                String casts = "";
                for (Movies.SubjectsBean.CastsBean castsBean : subjectsBean.getCasts()) {
                    casts += castsBean.getName() + "  ";
                }

                holder.setText(R.id.tv_movie_art, "主演:" + casts);
                String genres = "";
                for (String genre : subjectsBean.getGenres()) {
                    genres += genre + " ";
                }
                holder.setText(R.id.tv_movie_type, subjectsBean.getYear() + " / " + genres);//年份+分级
                holder.setText(R.id.tv_movie_grade, subjectsBean.getRating().getAverage() + "");//评分
                ImageView iv_pic = holder.getView(R.id.iv_movie_pic);
                Glide.with(mActivity)
                        .load(subjectsBean.getImages().getSmall())
                        .diskCacheStrategy(DiskCacheStrategy.ALL)//加快显示速度---缓存在本地磁盘
                        .into(iv_pic);//图片
            }
        };
        /**
         * 配置加载更多(通用适配器里面的类哦)
         */
        LoadMoreWrapper mLoadMoreWrapper = new LoadMoreWrapper(commonAdapter);//加载更多的包装器(传入通用适配器)
        View view = View.inflate(mActivity, R.layout.load_more, null);
        //要设置一下的布局参数,因为布局填充到包装器的时候,自己的一些属性会无效
        LinearLayout.LayoutParams mLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(mLayoutParams);
        load_more = (TextView) view.findViewById(R.id.tv_load_more);
        //监听点击加载更多事件
        load_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                load_more.setText("加载中...");//点击加载更多-->加载中
                mMoviePresenter.loadMoreMovie();
            }
        });
        mLoadMoreWrapper.setLoadMoreView(view);
        rvMovieList.setAdapter(mLoadMoreWrapper);//注意  这里添加的是包装器 不是适配器哦
    }

    @Override
    public void showInfo(String info) {
        showToast(this, info);
    }

    @Override
    public void toLogin() {
        startActivity(new Intent(MovieActivity.this, LoginActivity.class));
    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //      订阅者所在的类中则需要进行注册及解绑EventBus
        eventBus.unregister(this);
    }

    @OnClick({R.id.iv_title_back, R.id.tv_title, R.id.fab})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_title_back:
//                finish();
                break;
            case R.id.tv_title:
                mMoviePresenter.toLogin();
                break;
            case R.id.fab:
                rvMovieList.smoothScrollToPosition(0);
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(ItemSelectedEvent event) {
        Log.e(Constants.ACTIVITY, "msg:" + event.getTitle());
    }
}
