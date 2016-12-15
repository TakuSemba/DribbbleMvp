package com.matomefeed.dribbblemvp.models.networks.intercepter;

import android.content.Context;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by takusemba on 2016/08/26.
 */
public class HeaderSettingInterceptor implements Interceptor {

    Context mContext;

    private final static String TOKEN = "03bcbce45573cd8f7ee5c22674d5c15792db9a556e3fa99cbe32d6d7d1092968";

    public HeaderSettingInterceptor(Context context) {
        mContext = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();
        request = request.newBuilder()
                .addHeader("Authorization", "Bearer " + TOKEN)
                .build();

        return chain.proceed(request);

    }
}
