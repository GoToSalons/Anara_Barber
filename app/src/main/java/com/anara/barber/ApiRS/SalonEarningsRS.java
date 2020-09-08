package com.anara.barber.ApiRS;

import java.io.Serializable;

public class SalonEarningsRS implements Serializable {

    private String today_earning;
    private String week_earning;
    private String month_earning;
    private String year_earning;


    public String getToday_earning() {
        return today_earning;
    }

    public void setToday_earning(String today_earning) {
        this.today_earning = today_earning;
    }

    public String getWeek_earning() {
        return week_earning;
    }

    public void setWeek_earning(String week_earning) {
        this.week_earning = week_earning;
    }

    public String getMonth_earning() {
        return month_earning;
    }

    public void setMonth_earning(String month_earning) {
        this.month_earning = month_earning;
    }

    public String getYear_earning() {
        return year_earning;
    }

    public void setYear_earning(String year_earning) {
        this.year_earning = year_earning;
    }
}
