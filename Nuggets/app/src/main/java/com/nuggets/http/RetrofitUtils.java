package com.nuggets.http;

import com.nuggets.base.GlobalField;

import java.util.concurrent.TimeUnit;

import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Retofit网络请求工具类
 * Created by HDL on 2016/7/29.
 */
public class RetrofitUtils {
    private static String TAG = "Network";
    private static APIService retrofitInterface;
    private static okhttp3.OkHttpClient.Builder builder = new okhttp3.OkHttpClient.Builder();
    private static okhttp3.OkHttpClient okHttpClient;
    private static Converter.Factory gsonConverterFactory = GsonConverterFactory.create();
    private static CallAdapter.Factory rxJavaCallAdapterFactory = RxJavaCallAdapterFactory.create();
    private static HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();

    private static final int READ_TIMEOUT = 60;//读取超时时间,单位  秒
    private static final int CONN_TIMEOUT = 12;//连接超时时间,单位  秒

    private RetrofitUtils() {
    }
    public static APIService getRetrofitInterface() {
        if (retrofitInterface == null) {
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(interceptor);
            builder.connectTimeout(CONN_TIMEOUT, TimeUnit.SECONDS).readTimeout(READ_TIMEOUT ,TimeUnit.MINUTES);
            okHttpClient = builder.build();
            Retrofit retrofit = new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(GlobalField.MOVIE_TOP250_URL  )
                    .addConverterFactory(gsonConverterFactory)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .build();
            retrofitInterface = retrofit.create(APIService.class);
            //RestAdapter生成一个刚才定义的接口的实现类，使用的是动态代理。
        }
        return retrofitInterface;
    }

}
