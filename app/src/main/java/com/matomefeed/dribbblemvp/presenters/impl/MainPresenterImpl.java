package com.matomefeed.dribbblemvp.presenters.impl;

import android.content.Context;

import com.matomefeed.dribbblemvp.models.entities.Shot;
import com.matomefeed.dribbblemvp.models.networks.services.ShotService;
import com.matomefeed.dribbblemvp.presenters.MainPresenter;
import com.matomefeed.dribbblemvp.views.MainView;

import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.Subscription;

/**
 * Created by takusemba on 2016/11/01.
 */

public class MainPresenterImpl implements MainPresenter {

    private MainView mainView;

    private Subscription subscription;

    @Override
    public void fetchShots(Context context, int page, Observable.Transformer<List<Shot>, List<Shot>> transformer) {
        subscription = ShotService.fetchShots(context, page, transformer, new Observer<List<Shot>>() {
            @Override
            public void onCompleted() {
                mainView.hideCenterProgress();
                mainView.hideFooterProgress();
            }

            @Override
            public void onError(Throwable e) {
                mainView.hideCenterProgress();
                mainView.hideFooterProgress();
            }

            @Override
            public void onNext(List<Shot> shots) {
                mainView.addShots(shots);
            }
        });
    }

    @Override
    public void attachView(MainView view) {
        mainView = view;
        mainView.init();
    }

    @Override
    public void detachView() {
        mainView = null;
        if (subscription != null) {
            subscription.unsubscribe();
        }
    }
}
