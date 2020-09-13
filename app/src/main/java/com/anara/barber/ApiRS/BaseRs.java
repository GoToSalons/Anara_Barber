package com.anara.barber.ApiRS;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class BaseRs {

    @SerializedName("status")
    private String status;

    @SerializedName("login")
    private String login;

    @SerializedName("message")
    private String message;

    @SerializedName("barber")
    private BarbersRS barbersRS;

    @SerializedName("earnings")
    private SalonEarningsRS salonEarningsRS;

    @SerializedName("user")
    private OwnerRS ownerRS;

    @SerializedName("saloon")
    private OwnerRS saloon;

    @SerializedName("barbers")
    private ArrayList<BarbersRS> barbersRSArrayList;

    @SerializedName("today_slots")
    private ArrayList<BarberSlotsRS> barberSlotsRS;

    @SerializedName("booking_list")
    private ArrayList<BookingListRS> bookingListRS;

    public ArrayList<BookingListRS> getBookingListRS() {
        return bookingListRS;
    }

    public void setBookingListRS(ArrayList<BookingListRS> bookingListRS) {
        this.bookingListRS = bookingListRS;
    }

    public OwnerRS getOwnerRS() {
        return ownerRS;
    }

    public OwnerRS getSaloon() {
        return saloon;
    }

    public void setSaloon(OwnerRS saloon) {
        this.saloon = saloon;
    }

    public void setOwnerRS(OwnerRS ownerRS) {
        this.ownerRS = ownerRS;
    }

    public ArrayList<BarberSlotsRS> getBarberSlotsRS() {
        return barberSlotsRS;
    }

    public void setBarberSlotsRS(ArrayList<BarberSlotsRS> barberSlotsRS) {
        this.barberSlotsRS = barberSlotsRS;
    }

    public BarbersRS getBarbersRS() {
        return barbersRS;
    }

    public void setBarbersRS(BarbersRS barbersRS) {
        this.barbersRS = barbersRS;
    }

    public ArrayList<BarbersRS> getBarbersRSArrayList() {
        return barbersRSArrayList;
    }

    public void setBarbersRSArrayList(ArrayList<BarbersRS> barbersRSArrayList) {
        this.barbersRSArrayList = barbersRSArrayList;
    }

    public SalonEarningsRS getSalonEarningsRS() {
        return salonEarningsRS;
    }

    public void setSalonEarningsRS(SalonEarningsRS salonEarningsRS) {
        this.salonEarningsRS = salonEarningsRS;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
