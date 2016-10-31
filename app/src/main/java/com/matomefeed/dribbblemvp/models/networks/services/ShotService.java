package com.matomefeed.dribbblemvp.models.networks.services;

import android.content.Context;

import com.matomefeed.dribbblemvp.models.entities.Shot;
import com.matomefeed.dribbblemvp.models.networks.RestClient;
import com.matomefeed.dribbblemvp.models.networks.apis.ShotApi;

import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by takusemba on 2016/07/20.
 */
public class ShotService {

    public static void fetchShots(Context context, int page, Observable.Transformer<List<Shot>, List<Shot>> transformer, Observer<List<Shot>> observer) {
        RestClient.getRetrofit(context).create(ShotApi.class)
                .fetchShots(page, 24)
                .compose(transformer)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public static void fetchShot(Context context, int id, Observable.Transformer<Shot, Shot> transformer, Observer<Shot> observer) {
        RestClient.getRetrofit(context).create(ShotApi.class)
                .fetchShot(id)
                .compose(transformer)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

}
