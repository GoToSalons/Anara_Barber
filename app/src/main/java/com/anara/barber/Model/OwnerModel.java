package com.anara.barber.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class OwnerModel implements Serializable, Parcelable {

    String ownerName;
    String ownerEmailAddress;
    String ownerNumber;
    String ownerImages;
    String bank_name;
    String account_number;
    String ifsc_code;

    public OwnerModel() {
    }


    protected OwnerModel(Parcel in) {
        ownerName = in.readString();
        ownerEmailAddress = in.readString();
        ownerNumber = in.readString();
        ownerImages = in.readString();
        bank_name = in.readString();
        account_number = in.readString();
        ifsc_code = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(ownerName);
        dest.writeString(ownerEmailAddress);
        dest.writeString(ownerNumber);
        dest.writeString(ownerImages);
        dest.writeString(bank_name);
        dest.writeString(account_number);
        dest.writeString(ifsc_code);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<OwnerModel> CREATOR = new Creator<OwnerModel>() {
        @Override
        public OwnerModel createFromParcel(Parcel in) {
            return new OwnerModel(in);
        }

        @Override
        public OwnerModel[] newArray(int size) {
            return new OwnerModel[size];
        }
    };

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerEmailAddress() {
        return ownerEmailAddress;
    }

    public void setOwnerEmailAddress(String ownerEmailAddress) {
        this.ownerEmailAddress = ownerEmailAddress;
    }

    public String getOwnerNumber() {
        return ownerNumber;
    }

    public void setOwnerNumber(String ownerNumber) {
        this.ownerNumber = ownerNumber;
    }

    public String getOwnerImages() {
        return ownerImages;
    }

    public void setOwnerImages(String ownerImages) {
        this.ownerImages = ownerImages;
    }

    public String getBank_name() {
        return bank_name;
    }

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }

    public String getAccount_number() {
        return account_number;
    }

    public void setAccount_number(String account_number) {
        this.account_number = account_number;
    }

    public String getIfsc_code() {
        return ifsc_code;
    }

    public void setIfsc_code(String ifsc_code) {
        this.ifsc_code = ifsc_code;
    }

}
