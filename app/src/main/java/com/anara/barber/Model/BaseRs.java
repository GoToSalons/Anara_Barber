package com.anara.barber.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class BaseRs {

    @SerializedName("status")
    public String status;

    @SerializedName("login")
    public String login;

    @SerializedName("message")
    public String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
