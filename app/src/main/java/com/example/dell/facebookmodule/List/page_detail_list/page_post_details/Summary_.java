
package com.example.dell.facebookmodule.List.page_detail_list.page_post_details;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Summary_ {

    @SerializedName("total_count")
    @Expose
    private Integer totalCount;
    @SerializedName("viewer_reaction")
    @Expose
    private String viewerReaction;

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public String getViewerReaction() {
        return viewerReaction;
    }

    public void setViewerReaction(String viewerReaction) {
        this.viewerReaction = viewerReaction;
    }

}
