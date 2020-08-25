package com.anara.barber.Model;

import android.graphics.drawable.Drawable;

public class BarberModel {

    Drawable barberImage;
    String barberName;

    public BarberModel(Drawable barberImage, String barberName) {
        this.barberImage = barberImage;
        this.barberName = barberName;
    }

    public Drawable getBarberImage() {
        return barberImage;
    }

    public String getBarberName() {
        return barberName;
    }
}
