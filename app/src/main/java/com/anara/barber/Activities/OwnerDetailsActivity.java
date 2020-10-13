package com.anara.barber.Activities;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.anara.barber.ApiRS.BaseRs;
import com.anara.barber.ApiRS.OwnerRS;
import com.anara.barber.Apis.Const;
import com.anara.barber.Apis.RequestResponseManager;
import com.anara.barber.Model.AddBarberItem;
import com.anara.barber.Model.OwnerModel;
import com.anara.barber.Model.SalonModel;
import com.anara.barber.R;
import com.anara.barber.utils.PrefManager;
import com.bumptech.glide.Glide;
import com.hbb20.CountryCodePicker;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class OwnerDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    CircleImageView circleImageView;
    ImageView a1;
    File file;
    String ownerImagePath = "";

    // for salon data
    SalonModel salonModel;

    // for owner data
    OwnerModel ownerModel;

    EditText owner_name, e_mail, bank_name, account_number, ifsc_code, upi_id;

    String edit = "";

    // progress dialog
    ProgressDialog progressDialog;

    PrefManager prefManager;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_details);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait......");
        progressDialog.setCancelable(false);

        prefManager = new PrefManager(this);

        ownerModel = new OwnerModel();
        edit = getIntent().getStringExtra("edit");
        salonModel = getIntent().getParcelableExtra(Const.salon_DATA_KEY);

        owner_name = findViewById(R.id.owner_name);
        e_mail = findViewById(R.id.e_mail);
        bank_name = findViewById(R.id.bank_name);
        account_number = findViewById(R.id.account_number);
        ifsc_code = findViewById(R.id.ifsc_code);
        upi_id = findViewById(R.id.upi_id);

        circleImageView = findViewById(R.id.profile_image);
        RelativeLayout continueButton = findViewById(R.id.continue_button);
        a1 = findViewById(R.id.a1);

        if (edit != null && edit.equals("true")) {
            ((TextView) findViewById(R.id.edit_text)).setText("Save");
        }

        continueButton.setOnClickListener(this);
        circleImageView.setOnClickListener(this);

        findViewById(R.id.back_button).setOnClickListener(view -> finish());

        if (edit.equals("true")) {
            owner_name.setText(prefManager.getString(Const.OWNER_NAME, ""));
            e_mail.setText(prefManager.getString(Const.OWNER_EMAIL, ""));
            bank_name.setText(prefManager.getString(Const.Bank_Name, ""));
            ifsc_code.setText(prefManager.getString(Const.IFSC_CODE, ""));
            account_number.setText(prefManager.getString(Const.Account_number, ""));
            upi_id.setText(prefManager.getString(Const.UPI_ID, ""));
            ownerImagePath = prefManager.getString(Const.OWNER_IMAGE, "");

            Glide.with(this).load(ownerImagePath).centerCrop().into(circleImageView);
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.continue_button:
                next();
                break;
            case R.id.profile_image:
                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, 1);
                break;
        }
    }

    private void next() {
        if (edit.equals("true")) {
            ownerModel.setOwnerName(owner_name.getText().toString());
            ownerModel.setOwnerEmailAddress(e_mail.getText().toString());
            ownerModel.setBank_name(bank_name.getText().toString());
            ownerModel.setAccount_number(account_number.getText().toString());
            ownerModel.setIfsc_code(ifsc_code.getText().toString());
            ownerModel.setUpi_id(upi_id.getText().toString());
            ownerModel.setOwnerNumber(getIntent().getStringExtra("number"));
            ownerModel.setOwnerImages(ownerImagePath);
            updateProfile();
        } else {
            if (owner_name.getText().toString().equals("") || owner_name.getText().toString().length() < 3) {
                Toast.makeText(this, "Owner Name Must be Longer " + owner_name.getText().toString().length(), Toast.LENGTH_SHORT).show();
            } else if (e_mail.getText().toString().equals("")) {
                Toast.makeText(this, "Enter valid email", Toast.LENGTH_SHORT).show();
            } else if (ownerImagePath.equals("")) {
                Toast.makeText(this, "select image", Toast.LENGTH_SHORT).show();
            } else if (bank_name.getText().toString().equals("")) {
                Toast.makeText(this, "Enter Bank Name", Toast.LENGTH_SHORT).show();
            } else if (ifsc_code.getText().toString().equals("")) {
                Toast.makeText(this, "Enter IFSC code", Toast.LENGTH_SHORT).show();
            } else if (account_number.getText().toString().equals("")) {
                Toast.makeText(this, "Enter your account number", Toast.LENGTH_SHORT).show();
            } else if (upi_id.getText().toString().equals("")) {
                Toast.makeText(this, "Enter your upi id", Toast.LENGTH_SHORT).show();
            } else {
                ownerModel.setOwnerName(owner_name.getText().toString());
                ownerModel.setOwnerEmailAddress(e_mail.getText().toString());
                ownerModel.setBank_name(bank_name.getText().toString());
                ownerModel.setAccount_number(account_number.getText().toString());
                ownerModel.setIfsc_code(ifsc_code.getText().toString());
                ownerModel.setUpi_id(upi_id.getText().toString());
                ownerModel.setOwnerNumber(getIntent().getStringExtra("number"));
                ownerModel.setOwnerImages(ownerImagePath);

                Intent intent = new Intent(OwnerDetailsActivity.this, BarberDetailsActivity.class);
                intent.putExtra("barber_action", "new");
                intent.putExtra(Const.salon_DATA_KEY, (Parcelable) salonModel);
                intent.putExtra(Const.OWNER_DATA_KEY, (Parcelable) ownerModel);
                startActivity(intent);
            }
        }
    }

    private void updateProfile() {
        try {
            JSONObject jsonObject = new JSONObject();

            // salon data add to json
            jsonObject.put("salon_id", prefManager.getString(Const.SALON_ID, ""));
            jsonObject.put("salon_name", salonModel.getsalonName());
            jsonObject.put("street_address", salonModel.getsalonAddress());
            jsonObject.put("open_time", salonModel.getOpen_time());
            jsonObject.put("close_time", salonModel.getClose_time());
            jsonObject.put("latitude", salonModel.getLatitude());
            jsonObject.put("logitude", salonModel.getLogitude());
            jsonObject.put("instagram", salonModel.getInstagram());
            jsonObject.put("facebook", salonModel.getFacebook());
            jsonObject.put("twitter", salonModel.getTwitter());
            JSONArray salonImageJsonArray = new JSONArray();
            for (String s : salonModel.getsalonImages()) {
                salonImageJsonArray.put(Const.getBase64ImageFromBitmap(s));
            }
            jsonObject.put("salon_gallery", salonImageJsonArray);
            jsonObject.put("salon_type", salonModel.getType());

            // owner data add to json
            jsonObject.put("contact_person", ownerModel.getOwnerName());
            jsonObject.put("email", ownerModel.getOwnerEmailAddress());
            jsonObject.put("bank_name", ownerModel.getBank_name());
            jsonObject.put("account_number", ownerModel.getAccount_number());
            jsonObject.put("ifsc_code", ownerModel.getIfsc_code());
            jsonObject.put("upi_id", ownerModel.getUpi_id());
            jsonObject.put("mobile", ownerModel.getOwnerNumber());
            if (!ownerModel.getOwnerImages().contains("http")) {
                jsonObject.put("owner_image", Const.getBase64ImageFromBitmap(ownerModel.getOwnerImages()));
            }

            RequestResponseManager.updateSalonProfile(jsonObject, Const.salon_Register_Request, response -> {
                if (response != null) {
                    BaseRs baseRs = (BaseRs) response;
                    if (baseRs.getStatus().equals("success")) {
                        PrefManager prefManager = new PrefManager(this);
                        OwnerRS ownerRS = baseRs.getsalon();

                        prefManager.setString(Const.SALON_ID, String.valueOf(ownerRS.getsalon_id()));
                        prefManager.setString(Const.SALON_NAME, ownerRS.getsalon_name());
                        prefManager.setString(Const.OPEN_TIME, ownerRS.getOpen_time());
                        prefManager.setString(Const.CLOSE_TIME, ownerRS.getClose_time());
                        prefManager.setString(Const.SALON_TYPE, ownerRS.getsalon_type());
                        prefManager.setString(Const.CONTACT_NO, ownerRS.getContact_no());
                        prefManager.setString(Const.STREET_ADDRESS, ownerRS.getStreet_address());
                        prefManager.setString(Const.OWNER_IMAGE, ownerRS.getOwner_image());
                        prefManager.setString(Const.INSTAGRAM, ownerRS.getInstagram());
                        prefManager.setString(Const.FACEBOOK, ownerRS.getFacebook());
                        prefManager.setString(Const.TWITTER, ownerRS.getTwitter());
                        prefManager.setString(Const.LATITUDE, ownerRS.getLatitude());
                        prefManager.setString(Const.LONGITUDE, ownerRS.getLogitude());
                        prefManager.setString(Const.OWNER_NAME, ownerRS.getContact_person());
                        prefManager.setString(Const.Bank_Name, ownerRS.getBank_name());
                        prefManager.setString(Const.OWNER_EMAIL, ownerRS.getEmail());
                        prefManager.setString(Const.Account_number, ownerRS.getAccount_number());
                        prefManager.setString(Const.IFSC_CODE, ownerRS.getIfsc_code());
                        prefManager.setString(Const.UPI_ID, ownerRS.getUpi_id());

                        Toast.makeText(this, "" + baseRs.getMessage(), Toast.LENGTH_SHORT).show();

                        startActivity(new Intent(this, ChooseActivity.class));
                        finish();
                    } else {
                        Toast.makeText(this, "" + baseRs.getMessage(), Toast.LENGTH_SHORT).show();
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if (data != null) {
                Uri filePath = data.getData();
                convertUriToPath(filePath);
            }
        }
    }

    void convertUriToPath(Uri selectedImage) {
        String[] filePathColumn = {MediaStore.Images.Media.DATA};

        Cursor cursor = null;
        if (selectedImage != null) {
            cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
        }
        if (cursor != null) {
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String filePath = cursor.getString(columnIndex);

            cursor.close();

            if (filePath != null) {
                file = new File(filePath);
                ownerImagePath = file.getAbsolutePath();
                Glide.with(circleImageView).load(filePath).centerCrop().into(circleImageView);
                a1.setVisibility(View.GONE);
            }

        }
    }

}