package com.matomefeed.dribbblemvp.presenters;

import android.content.Context;

import com.matomefeed.dribbblemvp.models.entities.Shot;
import com.matomefeed.dribbblemvp.views.DetailView;

import rx.Observable;

/**
 * Created by takusemba on 2016/11/01.
 */

public interface DetailPresenter extends Presenter<DetailView> {

    void fetchShot(Context context, int id, Observable.Transformer<Shot, Shot> transformer);
}