package com.tc.tcbase.base.network;

import okhttp3.ResponseBody;
import rx.Subscriber;

public abstract class HttpFileSubscriber extends Subscriber<ResponseBody> {

    private boolean isFileSaveSuccess;//文件是否成功保存到本地

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        if (e instanceof Exception) {
            //访问获得对应的Exception
            ExceptionHandler.ResponeThrowable responeThrowable = ExceptionHandler.handleException(e);
            onError(responeThrowable.code, responeThrowable.message);
        } else {
            //将Throwable 和 未知错误的status code返回
            ExceptionHandler.ResponeThrowable responeThrowable = new ExceptionHandler.ResponeThrowable(e, ExceptionHandler.ERROR.UNKNOWN);
            onError(responeThrowable.code, responeThrowable.message);
        }
    }

    @Override
    public void onNext(ResponseBody responseBody) {
        onNext(isFileSaveSuccess);
    }

    public void setFileSaveSuccess(boolean fileSaveSuccess) {
        isFileSaveSuccess = fileSaveSuccess;
    }

    //具体实现下面两个方法，便可从中得到更直接详细的信息
    public abstract void onNext(boolean isFileSaveSuccess);
    public abstract void onError(int errType, String errMessage);
}