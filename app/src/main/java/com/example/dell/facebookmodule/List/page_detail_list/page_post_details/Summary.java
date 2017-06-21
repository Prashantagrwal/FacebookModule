
package com.example.dell.facebookmodule.List.page_detail_list.page_post_details;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Summary {

    @SerializedName("total_count")
    @Expose
    private Integer totalCount;
    @SerializedName("can_like")
    @Expose
    private Boolean canLike;
    @SerializedName("has_liked")
    @Expose
    private Boolean hasLiked;

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Boolean getCanLike() {
        return canLike;
    }

    public void setCanLike(Boolean canLike) {
        this.canLike = canLike;
    }

    public Boolean getHasLiked() {
        return hasLiked;
    }

    public void setHasLiked(Boolean hasLiked) {
        this.hasLiked = hasLiked;
    }

}
