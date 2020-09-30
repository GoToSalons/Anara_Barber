package com.anara.barber.Activities;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.anara.barber.Adapters.ServiceAdapter;
import com.anara.barber.Apis.Const;
import com.anara.barber.Dialogs.AddServiceDialog;
import com.anara.barber.Model.SalonModel;
import com.anara.barber.R;
import com.bumptech.glide.Glide;
import com.shivtechs.maplocationpicker.LocationPickerActivity;
import com.shivtechs.maplocationpicker.MapUtility;

import java.util.ArrayList;
import java.util.Calendar;

public class SalonDetailsActivity extends AppCompatActivity implements View.OnClickListener, AddServiceDialog.AddService {

    private static final int ADDRESS_PICKER_REQUEST = 1020;

    ImageView im1, im2, im3, im4, a1, a2, a3, a4;
    EditText name, address, start_time, end_time, facebook_url, instagram_url, twitter_url;
    TextView add_service;

    ArrayList<String> files = new ArrayList<>();

    // for salon data
    SalonModel salonModel = new SalonModel();

    RadioButton male, female, unisex;

    // salon type
    String salonType = "";

    // salon number
    String salonNumber;

    ArrayList<SalonModel.SalonService> salonServices = new ArrayList<>();

    ServiceAdapter serviceAdapter;
    RecyclerView recyclerViewService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salon_details);

        salonNumber = getIntent().getStringExtra("number");

        recyclerViewService = findViewById(R.id.service_recycler_view);

        serviceAdapter = new ServiceAdapter(salonServices);
        recyclerViewService.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewService.setAdapter(serviceAdapter);

        RelativeLayout iml1, iml2, iml3, iml4, next;

        iml1 = findViewById(R.id.iml_1);
        iml2 = findViewById(R.id.iml_2);
        iml3 = findViewById(R.id.iml_3);
        iml4 = findViewById(R.id.iml_4);
        next = findViewById(R.id.next);

        im1 = findViewById(R.id.im1);
        im2 = findViewById(R.id.im2);
        im3 = findViewById(R.id.im3);
        im4 = findViewById(R.id.im4);

        a1 = findViewById(R.id.a1);
        a2 = findViewById(R.id.a2);
        a3 = findViewById(R.id.a3);
        a4 = findViewById(R.id.a4);

        name = findViewById(R.id.salon_name);
        address = findViewById(R.id.salon_address);
        start_time = findViewById(R.id.start_time);
        end_time = findViewById(R.id.end_time);
        facebook_url = findViewById(R.id.facebook_url);
        instagram_url = findViewById(R.id.instagram_url);
        twitter_url = findViewById(R.id.twitter_url);

        start_time.setClickable(true);
        start_time.setLongClickable(false);
        start_time.setInputType(InputType.TYPE_NULL);

        start_time.setOnClickListener(view -> Const.showTimePicker(this, start_time));

        end_time.setClickable(true);
        end_time.setLongClickable(false);
        end_time.setInputType(InputType.TYPE_NULL);

        end_time.setOnClickListener(view -> Const.showTimePicker(this, end_time));

        male = findViewById(R.id.male);
        female = findViewById(R.id.female);
        unisex = findViewById(R.id.unisex);

        add_service = findViewById(R.id.add_service);

        male.setOnClickListener(this);
        female.setOnClickListener(this);
        unisex.setOnClickListener(this);

        iml1.setOnClickListener(this);
        iml2.setOnClickListener(this);
        iml3.setOnClickListener(this);
        iml4.setOnClickListener(this);
        next.setOnClickListener(this);

        add_service.setOnClickListener(this);

        findViewById(R.id.back_button).setOnClickListener(view -> finish());

    }


    @Override
    public void onClick(View view) {
        Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        switch (view.getId()) {
            case R.id.next:
                validateAndPass();
                break;
            case R.id.iml_1:
                if (check_permissions())
                    startActivityForResult(Intent.createChooser(i, "Select Picture"), 1);
                break;
            case R.id.iml_2:
                if (check_permissions())
                    startActivityForResult(Intent.createChooser(i, "Select Picture"), 2);
                break;
            case R.id.iml_3:
                if (check_permissions())
                    startActivityForResult(Intent.createChooser(i, "Select Picture"), 3);
                break;
            case R.id.iml_4:
                if (check_permissions())
                    startActivityForResult(Intent.createChooser(i, "Select Picture"), 4);
                break;
            case R.id.male:
                salonType = "Male";
                male.setChecked(true);
                female.setChecked(false);
                unisex.setChecked(false);
                break;
            case R.id.female:
                salonType = "Female";
                male.setChecked(false);
                female.setChecked(true);
                unisex.setChecked(false);
                break;
            case R.id.unisex:
                salonType = "Unisex";
                male.setChecked(false);
                female.setChecked(false);
                unisex.setChecked(true);
                break;
            case R.id.add_service:
                AddServiceDialog addServiceDialog = new AddServiceDialog(SalonDetailsActivity.this);
                addServiceDialog.setAddService(this);
                addServiceDialog.show(getSupportFragmentManager(), "AddDetails");
        }
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == ADDRESS_PICKER_REQUEST) {
                if (data != null) {
                    double currentLatitude = data.getDoubleExtra(MapUtility.LATITUDE, 0.0);
                    double currentLongitude = data.getDoubleExtra(MapUtility.LONGITUDE, 0.0);

                    salonModel.setLatitude(String.valueOf(currentLatitude));
                    salonModel.setLogitude(String.valueOf(currentLongitude));
                    Intent intent = new Intent(SalonDetailsActivity.this, OwnerDetailsActivity.class);
                    intent.putExtra(Const.salon_DATA_KEY, (Parcelable) salonModel);
                    intent.putExtra("number", salonNumber);
                    startActivity(intent);
                }
            } else if (data != null) {
                Uri filePath = data.getData();
                convertUriToPath(filePath, requestCode);
            }
        }
    }

    private void validateAndPass() {
        if (name.getText().toString().equals("") || name.getText().toString().length() < 5) {
            Toast.makeText(this, "Salon Name Must be Longer " + name.getText().toString().length(), Toast.LENGTH_SHORT).show();
        } else if (address.getText().toString().equals("") || address.getText().toString().length() < 15) {
            Toast.makeText(this, "Salon Address Must be Longer", Toast.LENGTH_SHORT).show();
        } else if (files.size() < 2) {
            Toast.makeText(this, "2 Photos Minimum", Toast.LENGTH_SHORT).show();
        } else if (salonType.equals("")) {
            Toast.makeText(this, "Select Salon Type", Toast.LENGTH_SHORT).show();
        } else if (salonServices.size() < 1) {
            Toast.makeText(this, "Add Service", Toast.LENGTH_SHORT).show();
        } else {
            salonModel.setsalonName(name.getText().toString());
            salonModel.setsalonAddress(address.getText().toString());
            salonModel.setsalonImages(files);
            salonModel.setType(salonType);
            salonModel.setOpen_time(start_time.getText().toString());
            salonModel.setClose_time(end_time.getText().toString());
            salonModel.setFacebook(facebook_url.getText().toString());
            salonModel.setInstagram(instagram_url.getText().toString());
            salonModel.setTwitter(twitter_url.getText().toString());
            salonModel.setServices(salonServices);
            Intent intent = new Intent(SalonDetailsActivity.this, LocationPickerActivity.class);
            startActivityForResult(intent, ADDRESS_PICKER_REQUEST);
        }
    }

    void convertUriToPath(Uri selectedImage, int requestCode) {
        String[] filePathColumn = {MediaStore.Images.Media.DATA};

        Cursor cursor = null;
        if (selectedImage != null) {
            cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
        }
        if (cursor != null) {
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String img = cursor.getString(columnIndex);

            cursor.close();

            if (img != null) {
                if (requestCode == 1) {
                    Glide.with(im1).load(img).centerCrop().into(im1);
                    a1.setVisibility(View.GONE);
                } else if (requestCode == 2) {
                    Glide.with(im2).load(img).centerCrop().into(im2);
                    a2.setVisibility(View.GONE);
                } else if (requestCode == 3) {
                    Glide.with(im3).load(img).centerCrop().into(im3);
                    a3.setVisibility(View.GONE);
                } else if (requestCode == 4) {
                    Glide.with(im4).load(img).centerCrop().into(im4);
                    a4.setVisibility(View.GONE);
                }
                files.add(img);
            }
        }
    }

    @Override
    public void onAddServiceClick(SalonModel.SalonService salonService) {
        salonServices.add(salonService);
        serviceAdapter.notifyItemInserted(salonServices.size() - 1);
    }
}