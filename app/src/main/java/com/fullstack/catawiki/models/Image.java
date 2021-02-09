package com.fullstack.catawiki.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Image {

    @SerializedName("height")
    @Expose
    public Integer height;
    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("url")
    @Expose
    public String url;
    @SerializedName("width")
    @Expose
    public Integer width;
}
