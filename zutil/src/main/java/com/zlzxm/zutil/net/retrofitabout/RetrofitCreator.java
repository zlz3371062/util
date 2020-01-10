package com.zlzxm.zutil.net.retrofitabout;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitCreator {


    private RetrofitCreator(){

    }

    /**
     * 返回string
     * @param url
     * @return
     */
    public static Retrofit creator(String url){


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(ScalarsConverterFactory.create())
                .client(initClient())
                .build();


        return retrofit;
    }


    /**
     * gosn版
     * @param url
     * @return
     */
    public static Retrofit creatorWithGson(String url){


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .client(initClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        return retrofit;
    }

    public static Retrofit creatorWithGsonSupportRx(String url){


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .client(initClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();


        return retrofit;
    }


    private static  OkHttpClient initClient() {



        return new  OkHttpClient.Builder()
                .addInterceptor( new Interceptor(){
                    @Override
                    public Response intercept( Chain chain) throws IOException {

                        Request original = chain.request();


                        Request.Builder requestBuilder = original.newBuilder()
                                .addHeader("Content-Type", "application/json");
                        Request request = requestBuilder.build();

                        return chain.proceed(request);

                    }
                })
                .addInterceptor( new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30,TimeUnit.SECONDS)
                .build();

    }



}
