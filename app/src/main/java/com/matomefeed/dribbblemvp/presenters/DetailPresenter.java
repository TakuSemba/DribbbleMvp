package com.matomefeed.dribbblemvp.presenters;

import android.content.Context;

import com.matomefeed.dribbblemvp.views.DetailView;

/**
 * Created by takusemba on 2016/11/01.
 */

public interface DetailPresenter extends Presenter<DetailView> {

    void fetchShot(Context context, int id);
}