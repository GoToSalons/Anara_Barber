package com.anara.barber.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;

public class SalonModel implements Serializable, Parcelable {

    String saloonName;
    String saloonAddress;
    String type;
    String open_time;
    String close_time;
    String latitude;
    String logitude;
    String instagram;
    String facebook;
    String twitter;
    ArrayList<String> saloonImages;
    private ArrayList<SalonService> services;

    public SalonModel() {
    }

    protected SalonModel(Parcel in) {
        saloonName = in.readString();
        saloonAddress = in.readString();
        type = in.readString();
        open_time = in.readString();
        close_time = in.readString();
        latitude = in.readString();
        logitude = in.readString();
        instagram = in.readString();
        facebook = in.readString();
        twitter = in.readString();
        saloonImages = in.createStringArrayList();
        services = in.createTypedArrayList(SalonService.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(saloonName);
        dest.writeString(saloonAddress);
        dest.writeString(type);
        dest.writeString(open_time);
        dest.writeString(close_time);
        dest.writeString(latitude);
        dest.writeString(logitude);
        dest.writeString(instagram);
        dest.writeString(facebook);
        dest.writeString(twitter);
        dest.writeStringList(saloonImages);
        dest.writeTypedList(services);
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOpen_time() {
        return open_time;
    }

    public void setOpen_time(String open_time) {
        this.open_time = open_time;
    }

    public String getClose_time() {
        return close_time;
    }

    public void setClose_time(String close_time) {
        this.close_time = close_time;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLogitude() {
        return logitude;
    }

    public void setLogitude(String logitude) {
        this.logitude = logitude;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public ArrayList<String> getSaloonImages() {
        return saloonImages;
    }

    public void setSaloonImages(ArrayList<String> saloonImages) {
        this.saloonImages = saloonImages;
    }

    public ArrayList<SalonService> getServices() {
        return services;
    }

    public void setServices(ArrayList<SalonService> services) {
        this.services = services;
    }

    public static class SalonService implements Serializable, Parcelable {

        private String service_name;
        private String service_description;
        private String service_id;
        private String hours;
        private String minutes;
        private String price;

        public SalonService() {
        }

        public String getService_name() {
            return service_name;
        }

        public void setService_name(String service_name) {
            this.service_name = service_name;
        }

        public String getService_description() {
            return service_description;
        }

        public void setService_description(String service_description) {
            this.service_description = service_description;
        }

        public String getService_id() {
            return service_id;
        }

        public void setService_id(String service_id) {
            this.service_id = service_id;
        }

        public String getHours() {
            return hours;
        }

        public void setHours(String hours) {
            this.hours = hours;
        }

        public String getMinutes() {
            return minutes;
        }

        public void setMinutes(String minutes) {
            this.minutes = minutes;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public static Creator<SalonService> getCREATOR() {
            return CREATOR;
        }

        protected SalonService(Parcel in) {
            service_name = in.readString();
            service_description = in.readString();
            service_id = in.readString();
            hours = in.readString();
            minutes = in.readString();
            price = in.readString();
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(service_name);
            dest.writeString(service_description);
            dest.writeString(service_id);
            dest.writeString(hours);
            dest.writeString(minutes);
            dest.writeString(price);
        }

        @Override
        public int describeContents() {
            return 0;
        }

        public static final Creator<SalonService> CREATOR = new Creator<SalonService>() {
            @Override
            public SalonService createFromParcel(Parcel in) {
                return new SalonService(in);
            }

            @Override
            public SalonService[] newArray(int size) {
                return new SalonService[size];
            }
        };
    }


}
