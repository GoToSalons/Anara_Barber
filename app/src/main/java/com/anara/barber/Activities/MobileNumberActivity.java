package com.anara.barber.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.anara.barber.ApiRS.BaseRs;
import com.anara.barber.ApiRS.OwnerRS;
import com.anara.barber.Apis.Const;
import com.anara.barber.Apis.RequestResponseManager;
import com.anara.barber.R;
import com.anara.barber.utils.PrefManager;
import com.hbb20.CountryCodePicker;

import org.json.JSONObject;

import java.util.Objects;


public class MobileNumberActivity extends AppCompatActivity {

    CountryCodePicker countryCodePicker;


    // progress dialog
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile_number);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait......");
        progressDialog.setCancelable(false);

        countryCodePicker = findViewById(R.id.cpp);
        EditText phoneNumber = findViewById(R.id.phone_number);
        countryCodePicker.registerCarrierNumberEditText(phoneNumber);

        findViewById(R.id.back_button).setOnClickListener(view -> finish());

        RelativeLayout next = findViewById(R.id.next);
        next.setOnClickListener(view -> {
            try {

                if (Objects.equals(getIntent().getStringExtra(Const.LOGIN_TYPE_KEY), Const.LOGIN_TYPE_OWNER)) {
                    Intent intent = new Intent(MobileNumberActivity.this, OTPActivity.class);
                    intent.putExtra("mobile", countryCodePicker.getFullNumberWithPlus());
                    intent.putExtra(Const.LOGIN_TYPE_KEY, getIntent().getStringExtra(Const.LOGIN_TYPE_KEY));
                    startActivity(intent);
                    finish();
                } else {
                    checkBarberLogin();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });



    }

    private void checkBarberLogin() {
        try {
            progressDialog.show();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("mobile", countryCodePicker.getFullNumberWithPlus());
            Log.e("tag", " = =  = call = = = " + jsonObject.toString());

            RequestResponseManager.loginBarber(jsonObject, Const.Login_Barber_Request, response -> {
                if (response != null) {
                    BaseRs baseRs = (BaseRs) response;
                    if (baseRs.getStatus().equals("success")) {
                        OwnerRS ownerRS = baseRs.getOwnerRS();
                        PrefManager prefManager = new PrefManager(this);
                        prefManager.setString(Const.BARBER_ID, ownerRS.getId());
                        prefManager.setString(Const.BARBER_NAME, ownerRS.getName());
                        prefManager.setString(Const.BARBER_EMAIL, ownerRS.getEmail());
                        prefManager.setString(Const.BARBER_MOBILE, ownerRS.getMobile());
                        prefManager.setString(Const.BARBER_IMAGE, ownerRS.getProfile_image());
                        prefManager.setString(Const.BARBER_EXP_YEAR, ownerRS.getExp_year());
                        prefManager.setString(Const.BARBER_EXP_MONTH, ownerRS.getExp_month());
                        Intent intent = new Intent(MobileNumberActivity.this, OTPActivity.class);
                        intent.putExtra("mobile", countryCodePicker.getFullNumberWithPlus());
                        intent.putExtra(Const.LOGIN_TYPE_KEY, getIntent().getStringExtra(Const.LOGIN_TYPE_KEY));
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(this, "Owner han't added to you a barber", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "Server error try again", Toast.LENGTH_SHORT).show();
                }
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
        }
    }

}