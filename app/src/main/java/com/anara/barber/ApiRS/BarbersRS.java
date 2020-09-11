package com.anara.barber.ApiRS;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class BarbersRS implements Serializable, Parcelable {

    private int id;
    private String name;
    private String mobile;
    private String profile_image;
    private String today_earning;
    private String month_earning;

    protected BarbersRS(Parcel in) {
        id = in.readInt();
        name = in.readString();
        mobile = in.readString();
        profile_image = in.readString();
        today_earning = in.readString();
        month_earning = in.readString();
    }

    public static final Creator<BarbersRS> CREATOR = new Creator<BarbersRS>() {
        @Override
        public BarbersRS createFromParcel(Parcel in) {
            return new BarbersRS(in);
        }

        @Override
        public BarbersRS[] newArray(int size) {
            return new BarbersRS[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeString(mobile);
        parcel.writeString(profile_image);
        parcel.writeString(today_earning);
        parcel.writeString(month_earning);
    }
}
