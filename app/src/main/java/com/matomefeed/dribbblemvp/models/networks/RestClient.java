package com.matomefeed.dribbblemvp.models.networks;

import android.content.Context;

import com.matomefeed.dribbblemvp.BuildConfig;
import com.matomefeed.dribbblemvp.models.networks.intercepter.ErrorHandlingInterceptor;
import com.matomefeed.dribbblemvp.models.networks.intercepter.HeaderSettingInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by takusemba on 2016/07/20.
 */
public class RestClient {
    private static final String TAG = RestClient.class.getSimpleName();

    public static Retrofit getRetrofit(Context context) {

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        OkHttpClient client;

        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.HEADERS);
            builder.addInterceptor(logging);
        }

        builder.addInterceptor(new HeaderSettingInterceptor(context));
        builder.addInterceptor(new ErrorHandlingInterceptor(context));

        client = builder
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .build();

        return new Retrofit.Builder()
                .baseUrl("https://api.dribbble.com/v1/")
                .client(client)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
