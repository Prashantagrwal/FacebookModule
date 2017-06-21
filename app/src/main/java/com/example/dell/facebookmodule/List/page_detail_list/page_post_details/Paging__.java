
package com.example.dell.facebookmodule.List.page_detail_list.page_post_details;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Paging__ {

    @SerializedName("cursors")
    @Expose
    private Cursors__ cursors;
    @SerializedName("next")
    @Expose
    private String next;

    public Cursors__ getCursors() {
        return cursors;
    }

    public void setCursors(Cursors__ cursors) {
        this.cursors = cursors;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

}
