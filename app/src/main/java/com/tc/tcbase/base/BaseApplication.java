package com.tc.tcbase.base;

import android.app.Application;

import com.tc.tcbase.base.network.RetrofitUtil;
import com.tc.tcbase.base.utils.CrashHandler;
import com.tc.tcbase.base.utils.EventBusUtil;
import com.tc.tcbase.base.utils.ToastUtil;
import com.tc.tcbase.base.utils.log.LogUtil;

/**
 * author：   tc
 * date：     2017/10/22 & 下午2:02
 * version    1.0
 * description
 * modify by
 */
public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ToastUtil.init(this);//初始化吐司
        LogUtil.init(true);//初始化Log打印
        CrashHandler.getInstance().init(this);//初始化崩溃输出
        RetrofitUtil.init(this);//初始化retrofit
        EventBusUtil.openIndex();//开启Index加速
    }
}
