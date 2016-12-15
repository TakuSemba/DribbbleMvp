package com.matomefeed.dribbblemvp.models.entities;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by takusemba on 2016/11/01.
 */

public class Shot implements Serializable {

    @SerializedName("id")
    public int id;

    @SerializedName("title")
    public String title;

    @SerializedName("description")
    public String description;

    @SerializedName("user")
    public User user;

    @SerializedName("images")
    public Image image;

}
