package com.tc.tcbase.base.network;

import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Streaming;
import rx.Observable;

public interface BaseApiService {



    //请求参数逐个传入
    @FormUrlEncoded
    @POST("请求地址")
    Observable<HttpResult> getInfo(@Field("token") String token, @Field("id") int id);

    //请求参数一次性传入（通过Map来存放参数名和参数值）
    @FormUrlEncoded
    @POST("请求地址")
    Observable<HttpResult> getInfo(@FieldMap Map<String, String> map);

    //上传文本和单个文件
    @Multipart
    @POST("请求地址")
    Observable<HttpResult> upLoadTextAndFile(@Part("textKey") String text,
                                             @Part("fileKey\"; filename=\"test.png") RequestBody fileBody);

    //上传文本和多个文件（多个文件通过Map来传入）
    @Multipart
    @POST("请求地址")
    Observable<HttpResult> upLoadTextAndFiles(@Part("textKey") String text,
                                              @PartMap Map<String, RequestBody> fileBodyMap);

    //下载大文件时，请加上@Streaming，否则容易出现IO异常
    @Streaming
    @GET("请求地址")
    Observable<ResponseBody> downloadFile();
}