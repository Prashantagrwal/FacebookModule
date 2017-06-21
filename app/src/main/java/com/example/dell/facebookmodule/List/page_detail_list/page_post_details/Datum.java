
package com.example.dell.facebookmodule.List.page_detail_list.page_post_details;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("likes")
    @Expose
    private Likes likes;
    @SerializedName("reactions")
    @Expose
    private Reactions reactions;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("picture")
    @Expose
private String picture;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Likes getLikes() {
        return likes;
    }

    public void setLikes(Likes likes) {
        this.likes = likes;
    }

    public Reactions getReactions() {
        return reactions;
    }

    public void setReactions(Reactions reactions) {
        this.reactions = reactions;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
