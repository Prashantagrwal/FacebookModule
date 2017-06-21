
package com.example.dell.facebookmodule.List.page_detail_list.page_video_details;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Paging__ {

    @SerializedName("cursors")
    @Expose
    private Cursors__ cursors;

    public Cursors__ getCursors() {
        return cursors;
    }

    public void setCursors(Cursors__ cursors) {
        this.cursors = cursors;
    }

}
