package com.anara.barber.ApiRS;

import java.io.Serializable;

public class OwnerRS implements Serializable {

    private long salon_id;
    private String salon_name;
    private String open_time;
    private String close_time;
    private String salon_type;
    private String contact_no;
    private String street_address;
    private String owner_image;
    private String instagram;
    private String facebook;
    private String twitter;
    private String latitude;
    private String logitude;

    private String id;
    private String name;
    private String email;
    private String mobile;
    private String profile_image;

    public long getsalon_id() {
        return salon_id;
    }

    public void setsalon_id(long salon_id) {
        this.salon_id = salon_id;
    }

    public String getsalon_name() {
        return salon_name;
    }

    public void setsalon_name(String salon_name) {
        this.salon_name = salon_name;
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

    public String getsalon_type() {
        return salon_type;
    }

    public void setsalon_type(String salon_type) {
        this.salon_type = salon_type;
    }

    public String getContact_no() {
        return contact_no;
    }

    public void setContact_no(String contact_no) {
        this.contact_no = contact_no;
    }

    public String getStreet_address() {
        return street_address;
    }

    public void setStreet_address(String street_address) {
        this.street_address = street_address;
    }

    public String getOwner_image() {
        return owner_image;
    }

    public void setOwner_image(String owner_image) {
        this.owner_image = owner_image;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(String profile_image) {
        this.profile_image = profile_image;
    }
}
