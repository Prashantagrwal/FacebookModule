
package com.example.dell.facebookmodule.List.page_detail_list.page_info;

import com.example.dell.facebookmodule.List.page_detail_list.page_info.Accounts;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Example {

    @SerializedName("accounts")
    @Expose
    private Accounts accounts;
    @SerializedName("id")
    @Expose
    private String id;

    public Accounts getAccounts() {
        return accounts;
    }

    public void setAccounts(Accounts accounts) {
        this.accounts = accounts;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
