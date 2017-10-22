package com.tc.tcbase.model;

import com.tc.tcbase.base.network.HttpSubscriber;
import com.tc.tcbase.base.network.RetrofitUtil;
import com.tc.tcbase.entity.res.MovieRes;

import java.util.List;

import rx.Observable;

public class MovieModel {


    public static MovieModel getInstance() {
        return MovieModel.SingletonHolder.instance;
    }

    private static class SingletonHolder {
        private static final MovieModel instance = new MovieModel();
    }

    private MovieModel() {
    }

    /**
     * 获取正在上映的电影
     *
     * @param count            获取的电影数量
     * @param subscriber       请求后的回调
     */
    public void getPlayingMovie(int count, HttpSubscriber<List<MovieRes>> subscriber) {
        Observable observable = RetrofitUtil.getApiService().getPlayingMovie(count);//如果需要嵌套请求的话，则在后面加入flatMap进行处理
        observable.subscribe(subscriber);

    }

    /**
     * 获取即将上映的电影
     *
     * @param count            获取的电影数量
     * @param subscriber       请求后的回调
     */
    public void getCommingMovie(int count, HttpSubscriber<List<MovieRes>> subscriber ) {
        Observable observable = RetrofitUtil.getApiService().getCommingMovie(count);
        observable.subscribe(subscriber);
    }





}