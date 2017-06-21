
package com.example.dell.facebookmodule.List.page_detail_list.page_post_details;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Reactions {

    @SerializedName("data")
    @Expose
    private List<Datum__> data = null;
    @SerializedName("paging")
    @Expose
    private Paging_ paging;
    @SerializedName("summary")
    @Expose
    private Summary_ summary;

    public List<Datum__> getData() {
        return data;
    }

    public void setData(List<Datum__> data) {
        this.data = data;
    }

    public Paging_ getPaging() {
        return paging;
    }

    public void setPaging(Paging_ paging) {
        this.paging = paging;
    }

    public Summary_ getSummary() {
        return summary;
    }

    public void setSummary(Summary_ summary) {
        this.summary = summary;
    }

}
