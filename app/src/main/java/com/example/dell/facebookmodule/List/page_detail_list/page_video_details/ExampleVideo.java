
package com.example.dell.facebookmodule.List.page_detail_list.page_video_details;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ExampleVideo {

    @SerializedName("videos")
    @Expose
    private Videos videos;
    @SerializedName("id")
    @Expose
    private String id;

    public Videos getVideos() {
        return videos;
    }

    public void setVideos(Videos videos) {
        this.videos = videos;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
