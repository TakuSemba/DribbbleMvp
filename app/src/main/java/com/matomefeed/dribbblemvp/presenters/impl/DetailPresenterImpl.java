package com.matomefeed.dribbblemvp.presenters.impl;

import android.content.Context;

import com.matomefeed.dribbblemvp.models.entities.Shot;
import com.matomefeed.dribbblemvp.models.networks.services.ShotService;
import com.matomefeed.dribbblemvp.presenters.DetailPresenter;
import com.matomefeed.dribbblemvp.views.DetailView;

import rx.Observable;
import rx.Observer;

/**
 * Created by takusemba on 2016/11/01.
 */

public class DetailPresenterImpl implements DetailPresenter {

    private DetailView detailView;

    @Override
    public void fetchShot(Context context, int id, Observable.Transformer<Shot, Shot> transformer) {
        ShotService.fetchShot(context, id, transformer, new Observer<Shot>() {
            @Override
            public void onCompleted() {
                detailView.hideCenterProgress();
            }

            @Override
            public void onError(Throwable e) {
                detailView.hideCenterProgress();
            }

            @Override
            public void onNext(Shot shot) {
                if (shot != null && shot.user != null) {
                    detailView.showShot(shot);
                }
            }
        });
    }

    @Override
    public void attachView(DetailView view) {
        detailView = view;
        detailView.init();
    }

    @Override
    public void detachView() {
        detailView = null;
    }
}
