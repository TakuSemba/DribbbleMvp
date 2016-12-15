package com.matomefeed.dribbblemvp.presenters;

import android.content.Context;

import com.matomefeed.dribbblemvp.views.MainView;

/**
 * Created by takusemba on 2016/11/01.
 */

public interface MainPresenter extends Presenter<MainView> {

    void fetchShots(Context context, int page);
}
