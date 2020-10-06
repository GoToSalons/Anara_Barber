package com.anara.barber.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.anara.barber.ApiRS.BaseRs;
import com.anara.barber.ApiRS.OwnerRS;
import com.anara.barber.Apis.Const;
import com.anara.barber.Apis.RequestResponseManager;
import com.anara.barber.R;
import com.anara.barber.utils.PrefManager;
import com.bumptech.glide.Glide;
import com.hbb20.CountryCodePicker;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class EditBarberActivity extends AppCompatActivity {

    EditText barber_name, e_mail, phone_number, exp_yrs, exp_mon;
    TextView add_barber, remove_barber;
    CircleImageView profile_image;
    View im_layout;
    ImageView a1;
    CountryCodePicker countryCodePicker;

    PrefManager prefManager;
    // progress dialog
    ProgressDialog progressDialog;

    String barberProfileImage = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_barber);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait......");
        progressDialog.setCancelable(false);

        prefManager = new PrefManager(this);

        barber_name = findViewById(R.id.barber_name);
        e_mail = findViewById(R.id.e_mail);
        phone_number = findViewById(R.id.phone_number);
        exp_yrs = findViewById(R.id.exp_yrs);
        exp_mon = findViewById(R.id.exp_mon);
        add_barber = findViewById(R.id.add_barber);
        remove_barber = findViewById(R.id.remove_barber);
        profile_image = findViewById(R.id.profile_image);
        im_layout = findViewById(R.id.im_layout);
        a1 = findViewById(R.id.a1);
        countryCodePicker = findViewById(R.id.cpp);
        countryCodePicker.registerCarrierNumberEditText(phone_number);

        barber_name.setText(prefManager.getString(Const.BARBER_NAME,""));
        e_mail.setText(prefManager.getString(Const.BARBER_EMAIL,""));
        String number = prefManager.getString(Const.BARBER_MOBILE,"");
        phone_number.setText(number);

        Glide.with(this).load(prefManager.getString(Const.BARBER_IMAGE,"")).centerCrop().into(a1);

        barberProfileImage = prefManager.getString(Const.BARBER_IMAGE,"");

        findViewById(R.id.continue_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateBarberProfile();
            }
        });
    }

    private void updateBarberProfile() {
        try {
            JSONObject jsonObject = new JSONObject();

            JSONObject barberJsonObject = new JSONObject();
            barberJsonObject.put("name", barber_name.getText().toString());
            barberJsonObject.put("email", e_mail.getText().toString());
            barberJsonObject.put("mobile", countryCodePicker.getFullNumberWithPlus());
            barberJsonObject.put("exp_year", exp_yrs.getText().toString());
            barberJsonObject.put("exp_month", exp_mon.getText().toString());
            if (!barberProfileImage.contains("http")) {
                barberJsonObject.put("barber_profile", Const.getBase64ImageFromBitmap(barberProfileImage));
            }
            RequestResponseManager.updateBarberProfile(jsonObject, Const.salon_Register_Request, response -> {
                if (response != null) {
                    BaseRs baseRs = (BaseRs) response;
                    if (baseRs.getStatus().equals("success")) {
                        PrefManager prefManager = new PrefManager(this);

                        Toast.makeText(this, "" + baseRs.getMessage(), Toast.LENGTH_SHORT).show();

                        startActivity(new Intent(this, ChooseActivity.class));
                        finish();
                    } else {
                        Toast.makeText(this, ""+baseRs.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                    Log.e("tag", " = =  = call = = = " + baseRs.getStatus());
                } else {
                    Toast.makeText(this, "Server error Try again", Toast.LENGTH_SHORT).show();
                }
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
            });

        } catch (JSONException e) {
            e.printStackTrace();
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
        }
    }

}