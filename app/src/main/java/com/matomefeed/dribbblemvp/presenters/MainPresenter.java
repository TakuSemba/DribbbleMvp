package com.matomefeed.dribbblemvp.presenters;

import android.content.Context;

import com.matomefeed.dribbblemvp.models.entities.Shot;
import com.matomefeed.dribbblemvp.views.MainView;

import java.util.List;

import rx.Observable;

/**
 * Created by takusemba on 2016/11/01.
 */

public interface MainPresenter extends Presenter<MainView> {

    void fetchShots(Context context, int page, Observable.Transformer<List<Shot>, List<Shot>> transformer);
}
