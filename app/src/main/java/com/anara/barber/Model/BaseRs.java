package com.anara.barber.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class BaseRs {

    @SerializedName("status")
    public String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
