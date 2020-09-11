package com.anara.barber.Apis;

import android.annotation.SuppressLint;
import android.app.TimePickerDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;
import android.widget.EditText;

import java.io.ByteArrayOutputStream;
import java.util.Calendar;

public class Const {


    public static String BASE_URL = "http://anarasalons.com/";

    public static int Saloon_Register_Request = 1;
    public static int Check_Register_Request = 2;
    public static int Check_Barber_Register_Request = 3;
    public static int Salon_Income_Request = 4;
    public static int Barber_Income_Request = 5;
    public static int Login_Barber_Request = 6;
    public static int Add_Barber_Request = 7;
    public static int Delete_Barber_Request = 8;

    public static final String SALOON_DATA_KEY = "saloon_data";
    public static final String OWNER_DATA_KEY = "owner_data";



    public static final String LOGIN_TYPE_KEY = "login_type";
    public static final String LOGIN_TYPE_OWNER = "owner";
    public static final String LOGIN_TYPE_BARBER = "barber";

    public static final String isLoginOwner = "owner_login";
    public static final String isOwnerRegister = "owner_Register";
    public static final String isLoginBarber = "barber_login";

    public static final String SALON_ID = "salon_id";
    public static final String SALON_NAME = "salon_name";
    public static final String OPEN_TIME = "open_time";
    public static final String CLOSE_TIME = "close_time";
    public static final String SALON_TYPE = "salon_type";
    public static final String CONTACT_NO = "contacts_no";
    public static final String STREET_ADDRESS = "street_address";
    public static final String OWNER_IMAGE = "owner_image";
    public static final String INSTAGRAM = "instagram";
    public static final String FACEBOOK = "facebook";
    public static final String TWITTER = "twitter";
    public static final String LATITUDE = "latitude";
    public static final String LONGITUDE = "longitude";

    public static final String BARBER_ID = "barber_id";
    public static final String BARBER_NAME = "barber_name";
    public static final String BARBER_EMAIL = "barber_email";
    public static final String BARBER_MOBILE = "barber_mobile";
    public static final String BARBER_IMAGE = "barber_image";

    public static String getBase64ImageFromBitmap(String filePath) {
        Log.e("tag"," = = = file path = = =  " + filePath);
        Bitmap selectedImageBitmap = BitmapFactory.decodeFile(filePath);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        selectedImageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] byteArrayImage = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(byteArrayImage, Base64.DEFAULT);
    }

    public static void showTimePicker(Context context, EditText editText){
        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        @SuppressLint("SetTextI18n")
        TimePickerDialog timePickerDialog = new TimePickerDialog(context, (timePicker, selectedHour, selectedMinute) -> editText.setText(selectedHour + ":" + selectedMinute), hour, minute, false);
        timePickerDialog.show();
    }

}
