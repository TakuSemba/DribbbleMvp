package com.matomefeed.dribbblemvp.models.networks.apis;


import com.matomefeed.dribbblemvp.models.entities.Shot;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by takusemba on 2016/07/20.
 */
public interface ShotApi {

    @GET("shots")
    Observable<List<Shot>> fetchShots(@Query("page") int page, @Query("per_page") int perPage);

    @GET("shots/{id}")
    Observable<Shot> fetchShot(@Path("id") int id);

}
