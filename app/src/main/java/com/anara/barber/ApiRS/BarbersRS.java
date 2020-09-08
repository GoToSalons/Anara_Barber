package com.anara.barber.ApiRS;

import java.io.Serializable;

public class BarbersRS implements Serializable {

    private int id;
    private String name;
    private String mobile;
    private String profile_image;
    private String today_earning;
    private String month_earning;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(String profile_image) {
        this.profile_image = profile_image;
    }

    public String getToday_earning() {
        return today_earning;
    }

    public void setToday_earning(String today_earning) {
        this.today_earning = today_earning;
    }

    public String getMonth_earning() {
        return month_earning;
    }

    public void setMonth_earning(String month_earning) {
        this.month_earning = month_earning;
    }
}
