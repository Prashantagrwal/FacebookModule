
package com.example.dell.facebookmodule.List.page_detail_list.page_video_details;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Likes {

    @SerializedName("data")
    @Expose
    private List<Datum_> data = null;
    @SerializedName("paging")
    @Expose
    private Paging paging;
    @SerializedName("summary")
    @Expose
    private Summary summary;

    public List<Datum_> getData() {
        return data;
    }

    public void setData(List<Datum_> data) {
        this.data = data;
    }

    public Paging getPaging() {
        return paging;
    }

    public void setPaging(Paging paging) {
        this.paging = paging;
    }

    public Summary getSummary() {
        return summary;
    }

    public void setSummary(Summary summary) {
        this.summary = summary;
    }

}
