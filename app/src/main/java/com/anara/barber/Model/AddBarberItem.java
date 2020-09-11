package com.anara.barber.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class AddBarberItem implements Serializable, Parcelable{

    private String name;
    private String email;
    private String mobile;
    private String exp_year;
    private String exp_month;
    private String barber_profile;

    public AddBarberItem() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getExp_year() {
        return exp_year;
    }

    public void setExp_year(String exp_year) {
        this.exp_year = exp_year;
    }

    public String getExp_month() {
        return exp_month;
    }

    public void setExp_month(String exp_month) {
        this.exp_month = exp_month;
    }

    public String getBarber_profile() {
        return barber_profile;
    }

    public void setBarber_profile(String barber_profile) {
        this.barber_profile = barber_profile;
    }


    public static Creator<AddBarberItem> getCREATOR() {
        return CREATOR;
    }


    protected AddBarberItem(Parcel in) {
        name = in.readString();
        email = in.readString();
        mobile = in.readString();
        exp_year = in.readString();
        exp_month = in.readString();
        barber_profile = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(email);
        dest.writeString(mobile);
        dest.writeString(exp_year);
        dest.writeString(exp_month);
        dest.writeString(barber_profile);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<AddBarberItem> CREATOR = new Creator<AddBarberItem>() {
        @Override
        public AddBarberItem createFromParcel(Parcel in) {
            return new AddBarberItem(in);
        }

        @Override
        public AddBarberItem[] newArray(int size) {
            return new AddBarberItem[size];
        }
    };
}
