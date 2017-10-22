package com.tc.tcbase;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.tc.tcbase.base.network.HttpSubscriber;
import com.tc.tcbase.base.utils.log.LogUtil;
import com.tc.tcbase.entity.res.MovieRes;
import com.tc.tcbase.model.MovieModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MovieModel.getInstance().getCommingMovie(2, new HttpSubscriber<List<MovieRes>>() {
            @Override
            public void onNext(String title, List<MovieRes> list) {
                LogUtil.d(TAG, "获取" + title + "成功");
                LogUtil.json(list);

            }

            @Override
            public void onError(int errType, String errMessage) {
                LogUtil.e("errType:"+errType+"  msg:"+errMessage);
            }
        });
    }
}
