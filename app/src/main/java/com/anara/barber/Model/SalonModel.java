package com.anara.barber.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;

public class SalonModel implements Serializable, Parcelable {

    String saloonName;
    String saloonAddress;
    String type;
    ArrayList<String> saloonImages;

    public SalonModel() {
    }


    protected SalonModel(Parcel in) {
        saloonName = in.readString();
        saloonAddress = in.readString();
        saloonImages = in.createStringArrayList();
        type = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(saloonName);
        dest.writeString(saloonAddress);
        dest.writeStringList(saloonImages);
        dest.writeString(type);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<SalonModel> CREATOR = new Creator<SalonModel>() {
        @Override
        public SalonModel createFromParcel(Parcel in) {
            return new SalonModel(in);
        }

        @Override
        public SalonModel[] newArray(int size) {
            return new SalonModel[size];
        }
    };

    public String getSaloonName() {
        return saloonName;
    }

    public void setSaloonName(String saloonName) {
        this.saloonName = saloonName;
    }

    public String getSaloonAddress() {
        return saloonAddress;
    }

    public void setSaloonAddress(String saloonAddress) {
        this.saloonAddress = saloonAddress;
    }

    public ArrayList<String> getSaloonImages() {
        return saloonImages;
    }

    public void setSaloonImages(ArrayList<String> saloonImages) {
        this.saloonImages = saloonImages;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
