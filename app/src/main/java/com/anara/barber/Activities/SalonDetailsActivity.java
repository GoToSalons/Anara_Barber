package com.anara.barber.Activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.anara.barber.Model.SalonModel;
import com.anara.barber.R;
import com.bumptech.glide.Glide;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class SalonDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView im1, im2, im3, im4, a1, a2, a3, a4;
    EditText name, address;
    ArrayList<File> files = new ArrayList<>();
    SalonModel salonModel = new SalonModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salon_details);

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

        iml1.setOnClickListener(this);
        iml2.setOnClickListener(this);
        iml3.setOnClickListener(this);
        iml4.setOnClickListener(this);
        next.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        switch (view.getId()) {
            case R.id.next:
                validateAndPass();
                break;
            case R.id.iml_1:
                startActivityForResult(Intent.createChooser(i, "Select Picture"), 1);
                break;
            case R.id.iml_2:
                startActivityForResult(Intent.createChooser(i, "Select Picture"), 2);
                break;
            case R.id.iml_3:
                startActivityForResult(Intent.createChooser(i, "Select Picture"), 3);
                break;
            case R.id.iml_4:
                startActivityForResult(Intent.createChooser(i, "Select Picture"), 4);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap img = null;
        File file = null;
        try {
            Uri filePath = data.getData();
            img = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
            file = new File(filePath.getPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (requestCode == 1) {
            Glide.with(im1).load(img).centerCrop().into(im1);
            a1.setVisibility(View.GONE);
            files.add(file);

        } else if (requestCode == 2) {
            Glide.with(im2).load(img).centerCrop().into(im2);
            a2.setVisibility(View.GONE);
            files.add(file);
        } else if (requestCode == 3) {
            Glide.with(im3).load(img).centerCrop().into(im3);
            a3.setVisibility(View.GONE);
            files.add(file);
        } else if (requestCode == 4) {
            Glide.with(im4).load(img).centerCrop().into(im4);
            a4.setVisibility(View.GONE);
            files.add(file);
        }
    }

    private void validateAndPass() {
        if (name.getText().toString().equals("") || name.getText().toString().length() < 5) {
            Toast.makeText(this, "Salon Name Must be Longer " + name.getText().toString().length(), Toast.LENGTH_SHORT).show();
        } else if (address.getText().toString().equals("") || address.getText().toString().length() < 15) {
            Toast.makeText(this, "Salon Address Must be Longer", Toast.LENGTH_SHORT).show();
        } else if (files.size() < 2) {
            Toast.makeText(this, "2 Photos Minimum", Toast.LENGTH_SHORT).show();
        } else {
            salonModel.setOwnerName(name.getText().toString());
            salonModel.setOwnerAddress(address.getText().toString());
            salonModel.setFiles(files);
            Intent intent = new Intent(SalonDetailsActivity.this, OwnerDetailsActivity.class);
            intent.putExtra("data", salonModel);
            startActivity(intent);

        }
    }
}