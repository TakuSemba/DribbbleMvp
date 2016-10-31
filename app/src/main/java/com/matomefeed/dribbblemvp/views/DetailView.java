package com.matomefeed.dribbblemvp.views;

import com.matomefeed.dribbblemvp.models.entities.Shot;

/**
 * Created by takusemba on 2016/11/01.
 */

public interface DetailView extends BaseView {

    void showSnackBar();

    void showCenterProgress();

    void hideCenterProgress();

    void showShot(Shot shot);
}
