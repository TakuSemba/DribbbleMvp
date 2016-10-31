package com.matomefeed.dribbblemvp.models.entities;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by takusemba on 2016/11/01.
 */

public class User implements Serializable{

    @SerializedName("id")
    public int id;

    @SerializedName("name")
    public String name;
}
