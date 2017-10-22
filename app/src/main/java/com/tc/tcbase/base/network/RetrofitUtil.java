package com.tc.tcbase.base.network;

import android.content.Context;

import com.tc.tcbase.base.utils.NetworkUtil;
import com.tc.tcbase.network.ApiService;
import com.tc.tcbase.network.HttpRequestUrl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.CertificateFactory;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtil {

    private static Context mContext;
    private static ApiService mBaseApiService;//提供各种具体的网络请求
    private static final int DEFAULT_TIMEOUT = 15;//请求超时时长，单位秒

    public static void init(Context context) {
        mContext = context;
    }

    public static ApiService getApiService() {
        if (mBaseApiService == null) {
            OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
            //设置请求超时时长
            okHttpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
            //启用Log日志
            okHttpClientBuilder.addInterceptor(getHttpLoggingInterceptor());
            //设置缓存方式、时长、地址
//            okHttpClientBuilder.addNetworkInterceptor(getCacheInterceptor());
//            okHttpClientBuilder.addInterceptor(getCacheInterceptor());
//            okHttpClientBuilder.cache(getCache());
            //设置https访问(验证证书)
//            okHttpClientBuilder.sslSocketFactory(getSSLSocketFactory(mContext, new int[]{R.raw
// .tomcat}));//请把服务器给的证书文件放在R.raw文件夹下
//            okHttpClientBuilder.hostnameVerifier(org.apache.http.conn.ssl.SSLSocketFactory
// .ALLOW_ALL_HOSTNAME_VERIFIER);
            //设置统一的header
//            okHttpClientBuilder.addInterceptor(getHeaderInterceptor());

            Retrofit retrofit = new Retrofit.Builder()
                    //服务器地址
                    .baseUrl(HttpRequestUrl.HOST_SITE_HTTPS)
                    //配置转化库，采用Gson
                    .addConverterFactory(GsonConverterFactory.create())
                    //配置回调库，采用RxJava
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    //设置OKHttpClient为网络客户端
                    .client(okHttpClientBuilder.build()).build();

            mBaseApiService = retrofit.create(ApiService.class);
            return mBaseApiService;
        } else {
            return mBaseApiService;
        }
    }

    //提供Log日志插值器
    public static HttpLoggingInterceptor getHttpLoggingInterceptor() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return loggingInterceptor;
    }

    //提供缓存插值器
    public static Interceptor getCacheInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                //对request的设置是用来指定有网/无网下所走的方式
                //对response的设置是用来指定有网/无网下的缓存时长

                Request request = chain.request();
                if (!NetworkUtil.isNetWorkAvailable(mContext)) {
                    //无网络下强制使用缓存，无论缓存是否过期,此时该请求实际上不会被发送出去。
                    //有网络时则根据缓存时长来决定是否发出请求
                    request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
                }

                Response response = chain.proceed(request);
                if (NetworkUtil.isNetWorkAvailable(mContext)) {
                    //有网络情况下，根据请求接口的设置，配置缓存。
//                    String cacheControl = request.cacheControl().toString();

                    //有网络情况下，超过1分钟，则重新请求，否则直接使用缓存数据
                    int maxAge = 60; //缓存一分钟
                    String cacheControl = "public,max-age=" + maxAge;
                    //当然如果你想在有网络的情况下都直接走网络，那么只需要
                    //将其超时时间maxAge设为0即可
                    return response.newBuilder().header("Cache-Control", cacheControl)
                            .removeHeader("Pragma").build();
                } else {
                    //无网络时直接取缓存数据，该缓存数据保存1周
                    int maxStale = 60 * 60 * 24 * 7 * 1;  //1周
                    return response.newBuilder().header("Cache-Control", "public,only-if-cached," +
                            "max-stale=" + maxStale).removeHeader("Pragma").build();
                }

            }
        };
    }

    public static Interceptor getHeaderInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request originalRequest = chain.request();
                Request.Builder builder = originalRequest.newBuilder();

                builder.header("timestamp", System.currentTimeMillis() + "");

                Request.Builder requestBuilder = builder.method(originalRequest.method(),
                        originalRequest.body());
                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        };
    }

    //配置缓存
    public static Cache getCache() {
        File cacheFile = new File(mContext.getExternalCacheDir(), "HttpCache");//缓存地址
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 50); //大小50Mb
        return cache;
    }

    //设置https证书
    protected static SSLSocketFactory getSSLSocketFactory(Context context, int[] certificates) {

        if (context == null) {
            throw new NullPointerException("context == null");
        }

        //CertificateFactory用来证书生成
        CertificateFactory certificateFactory;
        try {
            certificateFactory = CertificateFactory.getInstance("X.509");
            //Create a KeyStore containing our trusted CAs
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(null, null);

            for (int i = 0; i < certificates.length; i++) {
                //读取本地证书
                InputStream is = context.getResources().openRawResource(certificates[i]);
                keyStore.setCertificateEntry(String.valueOf(i), certificateFactory
                        .generateCertificate(is));

                if (is != null) {
                    is.close();
                }
            }
            //Create a TrustManager that trusts the CAs in our keyStore
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance
                    (TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(keyStore);

            //Create an SSLContext that uses our TrustManager
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustManagerFactory.getTrustManagers(), new SecureRandom());
            return sslContext.getSocketFactory();

        } catch (Exception e) {

        }
        return null;
    }



}