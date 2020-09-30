package com.anara.barber.ApiRS;

import java.io.Serializable;

public class BookingServices implements Serializable {

    private String id;
    private String book_date;
    private String customer_name;
    private String customer_id;
    private String salon_name;
    private String salon_id;
    private String barber_name;
    private String barber_id;
    private String book_number;
    private String from_time;
    private String to_time;
    private String total_price;
    private String transaction_id;
    private String billing_name;
    private String billing_mobile;
    private String billing_email;
    private String status;
    private String service_name;
    private String service_price;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBook_date() {
        return book_date;
    }

    public void setBook_date(String book_date) {
        this.book_date = book_date;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getsalon_name() {
        return salon_name;
    }

    public void setsalon_name(String salon_name) {
        this.salon_name = salon_name;
    }

    public String getsalon_id() {
        return salon_id;
    }

    public void setsalon_id(String salon_id) {
        this.salon_id = salon_id;
    }

    public String getBarber_name() {
        return barber_name;
    }

    public void setBarber_name(String barber_name) {
        this.barber_name = barber_name;
    }

    public String getBarber_id() {
        return barber_id;
    }

    public void setBarber_id(String barber_id) {
        this.barber_id = barber_id;
    }

    public String getBook_number() {
        return book_number;
    }

    public void setBook_number(String book_number) {
        this.book_number = book_number;
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

    public String getTotal_price() {
        return total_price;
    }

    public void setTotal_price(String total_price) {
        this.total_price = total_price;
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    public String getBilling_name() {
        return billing_name;
    }

    public void setBilling_name(String billing_name) {
        this.billing_name = billing_name;
    }

    public String getBilling_mobile() {
        return billing_mobile;
    }

    public void setBilling_mobile(String billing_mobile) {
        this.billing_mobile = billing_mobile;
    }

    public String getBilling_email() {
        return billing_email;
    }

    public void setBilling_email(String billing_email) {
        this.billing_email = billing_email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getService_name() {
        return service_name;
    }

    public void setService_name(String service_name) {
        this.service_name = service_name;
    }

    public String getService_price() {
        return service_price;
    }

    public void setService_price(String service_price) {
        this.service_price = service_price;
    }
}
