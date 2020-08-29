package com.anara.barber.Apis;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;

import java.io.ByteArrayOutputStream;

public class Const {


    public static String BASE_URL = "http://anarasalons.com/";

    public static int Saloon_Register_Request = 1;

    public static final String SALOON_DATA_KEY = "saloon_data";
    public static final String OWNER_DATA_KEY = "owner_data";


    public static String getBase64ImageFromBitmap(String filePath) {
        Log.e("tag"," = = = file path = = =  " + filePath);
        Bitmap selectedImageBitmap = BitmapFactory.decodeFile(filePath);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        selectedImageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] byteArrayImage = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(byteArrayImage, Base64.DEFAULT);
    }
}
