
package com.example.dell.facebookmodule.List.page_detail_list.post_image_details;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("picture")
    @Expose
    private String picture;
    @SerializedName("id")
    @Expose
    private String id;

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
