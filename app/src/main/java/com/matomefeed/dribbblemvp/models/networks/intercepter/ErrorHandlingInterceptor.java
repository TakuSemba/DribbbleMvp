package com.matomefeed.dribbblemvp.models.networks.intercepter;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.matomefeed.dribbblemvp.R;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by takusemba on 2016/08/26.
 */
public class ErrorHandlingInterceptor implements Interceptor {

    private static final String TAG = ErrorHandlingInterceptor.class.getSimpleName();

    private static Snackbar mSnackbar;

    private static Context mContext;

    public ErrorHandlingInterceptor(Context context) {
        mContext = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);
        if (!response.isSuccessful()) {
            showErrorMessage();
        }
        return response;
    }

    private void showErrorMessage() {
        mSnackbar = Snackbar
                .make(((Activity) mContext).findViewById(android.R.id.content), mContext.getString(R.string.error_message), Snackbar.LENGTH_SHORT)
                .setActionTextColor(ContextCompat.getColor(mContext, R.color.colorPrimary))
                .setAction(mContext.getString(R.string.ok), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mSnackbar.dismiss();
                    }
                });
        if (!mSnackbar.isShown()) {
            mSnackbar.show();
        }
    }

}
