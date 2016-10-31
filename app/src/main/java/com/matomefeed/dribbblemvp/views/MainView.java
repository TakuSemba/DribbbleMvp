package com.matomefeed.dribbblemvp.views;

import com.matomefeed.dribbblemvp.models.entities.Shot;

import java.util.List;

/**
 * Created by takusemba on 2016/10/31.
 */

public interface MainView extends BaseView {

    void showCenterProgress();

    void hideCenterProgress();

    void showFooterProgress();

    void hideFooterProgress();

    void addShots(List<Shot> shots);

    void clearShots();
}
