package com.matomefeed.dribbblemvp.presenters.impl;

import android.content.Context;

import com.matomefeed.dribbblemvp.models.entities.Shot;
import com.matomefeed.dribbblemvp.models.networks.services.ShotService;
import com.matomefeed.dribbblemvp.presenters.MainPresenter;
import com.matomefeed.dribbblemvp.views.MainView;
import com.matomefeed.dribbblemvp.views.activities.MainActivity;

import java.util.List;

import rx.Observer;

/**
 * Created by takusemba on 2016/11/01.
 */

public class MainPresenterImpl implements MainPresenter {

    private MainView mainView;

    @Override
    public void fetchShots(Context context, int page) {
        ShotService.fetchShots(context, page, ((MainActivity) context).<List<Shot>>bindToLifecycle(), new Observer<List<Shot>>() {
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
    }
}
