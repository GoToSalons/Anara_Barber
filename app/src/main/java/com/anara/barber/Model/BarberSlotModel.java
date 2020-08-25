package com.anara.barber.Model;

public class BarberSlotModel {

    String CustomerName;
    String CustomerServices;
    String FromTime;
    String ToTime;
    String Amount;

    public BarberSlotModel(String customerName, String customerServices, String fromTime, String toTime, String amount) {
        CustomerName = customerName;
        CustomerServices = customerServices;
        FromTime = fromTime;
        ToTime = toTime;
        Amount = amount;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }

    public String getCustomerServices() {
        return CustomerServices;
    }

    public void setCustomerServices(String customerServices) {
        CustomerServices = customerServices;
    }

    public String getFromTime() {
        return FromTime;
    }

    public void setFromTime(String fromTime) {
        FromTime = fromTime;
    }

    public String getToTime() {
        return ToTime;
    }

    public void setToTime(String toTime) {
        ToTime = toTime;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }
}
