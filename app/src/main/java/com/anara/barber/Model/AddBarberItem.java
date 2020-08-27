package com.anara.barber.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;

public class AddBarberItem implements Serializable, Parcelable{

    private String name;
    private String email;
    private String mobile;
    private String exp_year;
    private String exp_month;
    private String barber_profile;
    private ArrayList<BarberService> services;

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

    public ArrayList<BarberService> getServices() {
        return services;
    }

    public void setServices(ArrayList<BarberService> services) {
        this.services = services;
    }

    public static Creator<AddBarberItem> getCREATOR() {
        return CREATOR;
    }

    public static class BarberService implements Serializable, Parcelable {

        private String service_name;
        private String service_description;
        private String service_id;
        private String hours;
        private String minutes;
        private String price;

        public BarberService() {
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

        public static Creator<BarberService> getCREATOR() {
            return CREATOR;
        }

        protected BarberService(Parcel in) {
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

        public static final Creator<BarberService> CREATOR = new Creator<BarberService>() {
            @Override
            public BarberService createFromParcel(Parcel in) {
                return new BarberService(in);
            }

            @Override
            public BarberService[] newArray(int size) {
                return new BarberService[size];
            }
        };
    }

    protected AddBarberItem(Parcel in) {
        name = in.readString();
        email = in.readString();
        mobile = in.readString();
        exp_year = in.readString();
        exp_month = in.readString();
        barber_profile = in.readString();
        services = in.createTypedArrayList(BarberService.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(email);
        dest.writeString(mobile);
        dest.writeString(exp_year);
        dest.writeString(exp_month);
        dest.writeString(barber_profile);
        dest.writeTypedList(services);
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
