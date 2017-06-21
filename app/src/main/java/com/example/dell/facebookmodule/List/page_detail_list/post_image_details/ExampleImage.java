
package com.example.dell.facebookmodule.List.page_detail_list.post_image_details;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ExampleImage {

    @SerializedName("posts")
    @Expose
    private Posts posts;
    @SerializedName("id")
    @Expose
    private String id;

    public Posts getPosts() {
        return posts;
    }

    public void setPosts(Posts posts) {
        this.posts = posts;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
