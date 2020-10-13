package com.anara.barber.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.anara.barber.Adapters.AddBarberAdapter;
import com.anara.barber.ApiRS.OwnerRS;
import com.anara.barber.Apis.Const;
import com.anara.barber.Apis.RequestResponseManager;
import com.anara.barber.Model.AddBarberItem;
import com.anara.barber.ApiRS.BaseRs;
import com.anara.barber.Model.OwnerModel;
import com.anara.barber.Model.SalonModel;
import com.anara.barber.R;
import com.anara.barber.utils.PrefManager;
import com.hbb20.CountryCodePicker;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

public class BarberDetailsActivity extends AppCompatActivity implements AddBarberAdapter.OnImageClick {

    // select image position
    int barberPosition = 0;
    // add barber model class
    public ArrayList<AddBarberItem> addBarberItems;
    // add barber details adapter
    AddBarberAdapter addBarberAdapter;
    // add barber Recyclerview
    RecyclerView recyclerView;

    // for salon data
    SalonModel salonModel;

    // for owner data
    OwnerModel ownerModel;


    // progress dialog
    ProgressDialog progressDialog;


    String barberAction;

    PrefManager prefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barber_details);

        prefManager = new PrefManager(this);

        barberAction = getIntent().getStringExtra("barber_action");
        if (barberAction != null && barberAction.equals("new")) {
            salonModel = getIntent().getParcelableExtra(Const.salon_DATA_KEY);
            ownerModel = getIntent().getParcelableExtra(Const.OWNER_DATA_KEY);
        }
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait......");
        progressDialog.setCancelable(false);

        addBarberItems = new ArrayList<>();
        addBarberItems.add(new AddBarberItem());

        recyclerView = findViewById(R.id.recycler_view_barbers);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        addBarberAdapter = new AddBarberAdapter(addBarberItems, this, barberAction);
        recyclerView.setAdapter(addBarberAdapter);

        addBarberAdapter.setOnImageClick(this);

        findViewById(R.id.continue_button).setOnClickListener(view -> {
            progressDialog.show();
            if (barberAction != null && barberAction.equals("new")) {
                registerBarberWithFullData();
            } else {
                addBarber();
            }
        });

        findViewById(R.id.back_button).setOnClickListener(view -> finish());

    }

    public boolean check_permissions() {

        String[] PERMISSIONS = {
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA
        };

        if (!hasPermissions(this, PERMISSIONS)) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(PERMISSIONS, 2);
            } else {
                return true;
            }
        } else {

            return true;
        }

        return false;
    }

    public boolean hasPermissions(Context context, String... permissions) {
        if (context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    private void registerBarberWithFullData() {
        try {
            JSONObject jsonObject = new JSONObject();

            // salon data add to json
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
//            jsonObject.put("mobile", "9973553344");
            jsonObject.put("owner_image", Const.getBase64ImageFromBitmap(ownerModel.getOwnerImages()));
            Log.e("tag", " = =  = d = = = " + salonModel.getType());

            JSONArray serviceJsonArray = new JSONArray();

            if (salonModel.getServices() != null) {
                for (SalonModel.SalonService salonService : salonModel.getServices()) {
                    JSONObject serviceJsonObject = new JSONObject();
                    serviceJsonObject.put("service_name", salonService.getService_name());
                    serviceJsonObject.put("service_description", salonService.getService_description());
                    serviceJsonObject.put("service_id", salonService.getService_id());
                    serviceJsonObject.put("hours", salonService.getHours());
                    serviceJsonObject.put("minutes", salonService.getMinutes());
                    serviceJsonObject.put("price", salonService.getPrice());
                    serviceJsonArray.put(serviceJsonObject);
                }
            }
            jsonObject.put("services", serviceJsonArray);

            // barber data add to json
            JSONArray barberJsonArray = new JSONArray();

            ArrayList<AddBarberItem> barberItems = addBarberAdapter.getAddBarberItems();
            for (int i = 0; i < barberItems.size(); i++) {
                AddBarberItem addBarberItem = barberItems.get(i);

                JSONObject barberJsonObject = new JSONObject();
                barberJsonObject.put("name", ((TextView) Objects.requireNonNull(recyclerView.findViewHolderForAdapterPosition(i)).itemView.findViewById(R.id.barber_name)).getText().toString());
                barberJsonObject.put("email", ((TextView) Objects.requireNonNull(recyclerView.findViewHolderForAdapterPosition(i)).itemView.findViewById(R.id.e_mail)).getText().toString());
                barberJsonObject.put("mobile", ((CountryCodePicker) Objects.requireNonNull(recyclerView.findViewHolderForAdapterPosition(i)).itemView.findViewById(R.id.cpp)).getFullNumberWithPlus());
                barberJsonObject.put("exp_year", ((TextView) Objects.requireNonNull(recyclerView.findViewHolderForAdapterPosition(i)).itemView.findViewById(R.id.exp_yrs)).getText().toString());
                barberJsonObject.put("exp_month", ((TextView) Objects.requireNonNull(recyclerView.findViewHolderForAdapterPosition(i)).itemView.findViewById(R.id.exp_mon)).getText().toString());
                barberJsonObject.put("barber_profile", Const.getBase64ImageFromBitmap(addBarberItem.getBarber_profile()));

                barberJsonArray.put(barberJsonObject);
            }

            jsonObject.put("barbers", barberJsonArray);

            RequestResponseManager.getApiCall(jsonObject, Const.salon_Register_Request, response -> {
                if (response != null) {
                    BaseRs baseRs = (BaseRs) response;
                    if (baseRs.getStatus().equals("success")) {
                        PrefManager prefManager = new PrefManager(this);
                        prefManager.setString(Const.isLoginOwner,"true");
                        prefManager.setString(Const.isOwnerRegister,"true");

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
//                        prefManager.setString(Const.OWNER_NAME, ownerRS.getContact_person());
//                        prefManager.setString(Const.Bank_Name, ownerRS.getBank_name());
//                        prefManager.setString(Const.OWNER_EMAIL, ownerRS.getEmail());
//                        prefManager.setString(Const.Account_number, ownerRS.getAccount_number());
//                        prefManager.setString(Const.IFSC_CODE, ownerRS.getIfsc_code());

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

    private void addBarber() {

        try {

            ArrayList<AddBarberItem> barberItems = addBarberAdapter.getAddBarberItems();

            JSONObject barberJsonObject = new JSONObject();

            barberJsonObject.put("salon_id", prefManager.getString(Const.SALON_ID,""));
            AddBarberItem addBarberItem = barberItems.get(0);
            barberJsonObject.put("name", ((TextView) Objects.requireNonNull(recyclerView.findViewHolderForAdapterPosition(0)).itemView.findViewById(R.id.barber_name)).getText().toString());
            barberJsonObject.put("email", ((TextView) Objects.requireNonNull(recyclerView.findViewHolderForAdapterPosition(0)).itemView.findViewById(R.id.e_mail)).getText().toString());
            barberJsonObject.put("mobile", ((CountryCodePicker) Objects.requireNonNull(recyclerView.findViewHolderForAdapterPosition(0)).itemView.findViewById(R.id.cpp)).getFullNumberWithPlus());
            barberJsonObject.put("exp_year", ((TextView) Objects.requireNonNull(recyclerView.findViewHolderForAdapterPosition(0)).itemView.findViewById(R.id.exp_yrs)).getText().toString());
            barberJsonObject.put("exp_month", ((TextView) Objects.requireNonNull(recyclerView.findViewHolderForAdapterPosition(0)).itemView.findViewById(R.id.exp_mon)).getText().toString());
            barberJsonObject.put("profile_image", Const.getBase64ImageFromBitmap(addBarberItem.getBarber_profile()));

            Log.e("tag"," = = =  = mm m  ===  " + barberJsonObject.toString());
            RequestResponseManager.addBarber(barberJsonObject, Const.salon_Register_Request, response -> {
                if (response != null) {
                    BaseRs baseRs = (BaseRs) response;
                    if (baseRs.getStatus().equals("success")) {
                        Toast.makeText(this, ""+baseRs.getMessage(), Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(this, ""+baseRs.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                    Log.e("tag", " = =  = call = = = " + baseRs.getStatus());
                } else {
                    Toast.makeText(this, "server error try again", Toast.LENGTH_SHORT).show();
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
    protected void onPause() {
        super.onPause();
        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == 21) {
                if (data != null) {
                    Uri filePath = data.getData();
                    if (filePath != null && filePath.getPath() != null) {
                        convertUriToPath(filePath);
                    }
                }
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
            String picturePath = cursor.getString(columnIndex);

            cursor.close();

            AddBarberItem addBarberItem = addBarberItems.get(barberPosition);
            try {
                addBarberItem.setName(((TextView) Objects.requireNonNull(recyclerView.findViewHolderForAdapterPosition(barberPosition)).itemView.findViewById(R.id.barber_name)).getText().toString());
                addBarberItem.setEmail(((TextView) Objects.requireNonNull(recyclerView.findViewHolderForAdapterPosition(barberPosition)).itemView.findViewById(R.id.e_mail)).getText().toString());
                addBarberItem.setMobile(((TextView) Objects.requireNonNull(recyclerView.findViewHolderForAdapterPosition(barberPosition)).itemView.findViewById(R.id.phone_number)).getText().toString());
                addBarberItem.setExp_year(((TextView) Objects.requireNonNull(recyclerView.findViewHolderForAdapterPosition(barberPosition)).itemView.findViewById(R.id.exp_yrs)).getText().toString());
                addBarberItem.setExp_month(((TextView) Objects.requireNonNull(recyclerView.findViewHolderForAdapterPosition(barberPosition)).itemView.findViewById(R.id.exp_mon)).getText().toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
            addBarberItem.setBarber_profile(picturePath);
            addBarberItems.remove(addBarberItems.get(barberPosition));
            addBarberItems.add(barberPosition, addBarberItem);
            addBarberAdapter.notifyItemChanged(barberPosition);

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    }

    @Override
    public void onSelectImage(int position) {
        barberPosition = position;
        if (check_permissions()) {
            Intent i = new Intent(
                    Intent.ACTION_PICK,
                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(i, 21);
        }
    }


}