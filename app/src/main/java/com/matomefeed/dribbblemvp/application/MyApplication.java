package com.matomefeed.dribbblemvp.application;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

/**
 * Created by takusemba on 2016/11/01.
 */

public class MyApplication extends Application {

    @Override public void onCreate() {
        super.onCreate();
        LeakCanary.install(this);
    }
}