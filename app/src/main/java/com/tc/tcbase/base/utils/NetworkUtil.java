package com.tc.tcbase.base.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * author：   zp
 * date：     2015/8/19 0019 17:47
 * description: 网络工具
 * modify by
 */
public class NetworkUtil {
    /**
     * 网络不可用
     */
    public static final int NO_NETWORK = 0;
    /**
     * 是wifi连接
     */
    public static final int WIFI = 1;
    /**
     * 不是wifi连接
     */
    public static final int NO_WIFI = 2;

    public static final int INVALID_NETWORK_TYPE = -1;

    /**
     * 获取当前网络连接类型
     *
     * @param context context
     * @return -1不可用
     */
    public static int getNetWorkType(Context context) {
        NetworkInfo mNetworkInfo = null;
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
        }
        return (mNetworkInfo != null && mNetworkInfo.isAvailable()) ? mNetworkInfo.getType() : INVALID_NETWORK_TYPE;
    }

    /**
     * 判断当前网络状态是否可用
     *
     * @param context context
     * @return 可用返回true,否则false
     */
    public static boolean isNetWorkAvailable(Context context) {
        if (context == null) {
            return false;
        }
//        Context context = activity.getApplicationContext();
        // 获取手机所有连接管理对象（包括对wi-fi,net等连接的管理）
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm == null) {
            return false;
        }
        NetworkInfo mNetworkInfo = cm.getActiveNetworkInfo();

        return mNetworkInfo != null && mNetworkInfo.isAvailable();
    }

    /**
     * 是否为wifi连接
     *
     * @param context context
     * @return wifi为true，否则为false
     */
    public boolean isWifiConnected(Context context) {
        NetworkInfo mWiFiNetworkInfo = null;
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            mWiFiNetworkInfo = mConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        }
        return mWiFiNetworkInfo != null && mWiFiNetworkInfo.isAvailable();
    }

    /**
     * 是否为手机网络连接
     *
     * @param context context
     * @return 是返回true,否则为false
     */
    public boolean isMobileConnected(Context context) {
        NetworkInfo mMobileNetworkInfo = null;
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            mMobileNetworkInfo = mConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        }
        return mMobileNetworkInfo != null && mMobileNetworkInfo.isAvailable();
    }

    /**
     * 获取apn 类型
     *
     * @param context context
     * @return 类型
     */
    public static String getAPNType(Context context) {
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo == null) {
            return null;
        }
        if (networkInfo.getExtraInfo() == null) {
            return null;
        }
        return networkInfo.getExtraInfo().toLowerCase();
    }
}
