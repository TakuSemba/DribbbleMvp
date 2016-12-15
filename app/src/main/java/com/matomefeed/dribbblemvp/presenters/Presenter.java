package com.matomefeed.dribbblemvp.presenters;

/**
 * Created by takusemba on 2016/11/01.
 */

public interface Presenter<V> {

    void attachView(V view);

    void detachView();
}
