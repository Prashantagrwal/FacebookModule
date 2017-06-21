
package com.example.dell.facebookmodule.List.page_detail_list.page_post_details;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Posts {

    @SerializedName("data")
    @Expose
    private List<com.example.dell.facebookmodule.List.page_detail_list.page_post_details.Datum> Postdata = null;
    @SerializedName("paging")
    @Expose
    private Paging__ paging;

    public List<com.example.dell.facebookmodule.List.page_detail_list.page_post_details.Datum> getPostData() {
        return Postdata;
    }

    public void setData(List<com.example.dell.facebookmodule.List.page_detail_list.page_post_details.Datum> Postdata) {
        this.Postdata = Postdata;
    }

    public Paging__ getPaging() {
        return paging;
    }

    public void setPaging(Paging__ paging) {
        this.paging = paging;
    }

}
