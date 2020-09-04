package com.anara.barber.Activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;

import com.anara.barber.Adapters.AddBarberAdapter;
import com.anara.barber.Apis.Const;
import com.anara.barber.Apis.RequestResponseManager;
import com.anara.barber.Model.AddBarberItem;
import com.anara.barber.Model.BaseRs;
import com.anara.barber.Model.OwnerModel;
import com.anara.barber.Model.SalonModel;
import com.anara.barber.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class BarberDetailsActivity extends AppCompatActivity implements AddBarberAdapter.OnImageClick {

    // select image position
    int barberPosition = 0;
    // add barber model class
    ArrayList<AddBarberItem> addBarberItems;
    // add barber details adapter
    AddBarberAdapter addBarberAdapter;
    // add barber Recyclerview
    RecyclerView recyclerView;

    // for saloon data
    SalonModel salonModel;

    // for owner data
    OwnerModel ownerModel;


    // progress dialog
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barber_details);

        salonModel = getIntent().getParcelableExtra(Const.SALOON_DATA_KEY);
        ownerModel = getIntent().getParcelableExtra(Const.OWNER_DATA_KEY);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait......");
        progressDialog.setCancelable(false);

        addBarberItems = new ArrayList<>();
        addBarberItems.add(new AddBarberItem());

        recyclerView = findViewById(R.id.recycler_view_barbers);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        addBarberAdapter = new AddBarberAdapter(addBarberItems, this);
        recyclerView.setAdapter(addBarberAdapter);

        addBarberAdapter.setOnImageClick(this);

        findViewById(R.id.continue_button).setOnClickListener(view -> {
//            Intent intent = new Intent(BarberDetailsActivity.this, MainActivityOwner.class);
//            startActivity(intent);
            progressDialog.show();
            registerBarberWithFullData();
        });

    }

    private void registerBarberWithFullData() {
        try {
            JSONObject jsonObject = new JSONObject();

            // saloon data add to json
            jsonObject.put("saloon_name", salonModel.getSaloonName());
            jsonObject.put("street_address", salonModel.getSaloonAddress());
            jsonObject.put("open_time", salonModel.getOpen_time());
            jsonObject.put("close_time", salonModel.getOpen_time());
            jsonObject.put("latitude", salonModel.getLatitude());
            jsonObject.put("logitude", salonModel.getLogitude());
            jsonObject.put("instagram", salonModel.getInstagram());
            jsonObject.put("facebook", salonModel.getFacebook());
            jsonObject.put("twitter", salonModel.getTwitter());
            JSONArray saloonImageJsonArray = new JSONArray();
            for (String s : salonModel.getSaloonImages()) {
                saloonImageJsonArray.put(Const.getBase64ImageFromBitmap(s));
            }
            jsonObject.put("saloon_gallery", saloonImageJsonArray);
            jsonObject.put("saloon_type", salonModel.getType());

            // owner data add to json
            jsonObject.put("contact_person", ownerModel.getOwnerName());
            jsonObject.put("email", ownerModel.getOwnerEmailAddress());
//            jsonObject.put("mobile", ownerModel.getOwnerNumber());
            jsonObject.put("mobile", "9973553344");
            jsonObject.put("owner_image", Const.getBase64ImageFromBitmap(ownerModel.getOwnerImages()));

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
                    Log.e("tag", " = =  = d = = = " + salonService.getService_name());
                    serviceJsonArray.put(serviceJsonObject);
                }
            }
            jsonObject.put("services", serviceJsonArray);

            // barber data add to json
            JSONArray barberJsonArray = new JSONArray();

            for (AddBarberItem addBarberItem : addBarberAdapter.getAddBarberItems()) {
                JSONObject barberJsonObject = new JSONObject();
                barberJsonObject.put("name", addBarberItem.getName());
                barberJsonObject.put("email", addBarberItem.getEmail());
                barberJsonObject.put("mobile", addBarberItem.getMobile());
                barberJsonObject.put("exp_year", addBarberItem.getExp_year());
                barberJsonObject.put("exp_month", addBarberItem.getExp_month());
                barberJsonObject.put("barber_profile", Const.getBase64ImageFromBitmap(addBarberItem.getBarber_profile()));

                barberJsonArray.put(barberJsonObject);
            }

            jsonObject.put("barbers", barberJsonArray);

            RequestResponseManager.getApiCall(jsonObject, Const.Saloon_Register_Request, response -> {
                if (response != null) {
                    BaseRs baseRs = (BaseRs) response;
                    Log.e("tag", " = =  = call = = = " + baseRs.getStatus());
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
            addBarberItem.setBarber_profile(picturePath);
            addBarberItems.remove(addBarberItems.get(barberPosition));
            addBarberItems.add(barberPosition, addBarberItem);
            addBarberAdapter.notifyItemChanged(barberPosition);

        }
    }

    @Override
    public void onSelectImage(int position) {
        barberPosition = position;
        Intent i = new Intent(
                Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i, 21);
    }

}