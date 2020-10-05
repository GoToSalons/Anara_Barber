package com.anara.barber.ApiRS;

import java.io.Serializable;

public class BarberScheduleRS implements Serializable {

    private int id;
    private String schedule_date;
    private String from_time;
    private String to_time;
    private String break_from_time;
    private String break_to_time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSchedule_date() {
        return schedule_date;
    }

    public void setSchedule_date(String schedule_date) {
        this.schedule_date = schedule_date;
    }

    public String getFrom_time() {
        return from_time;
    }

    public void setFrom_time(String from_time) {
        this.from_time = from_time;
    }

    public String getTo_time() {
        return to_time;
    }

    public void setTo_time(String to_time) {
        this.to_time = to_time;
    }

    public String getBreak_from_time() {
        return break_from_time;
    }

    public void setBreak_from_time(String break_from_time) {
        this.break_from_time = break_from_time;
    }

    public String getBreak_to_time() {
        return break_to_time;
    }

    public void setBreak_to_time(String break_to_time) {
        this.break_to_time = break_to_time;
    }
}
