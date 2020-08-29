package com.anara.barber.Activities;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.anara.barber.Apis.Const;
import com.anara.barber.Model.AddBarberItem;
import com.anara.barber.Model.OwnerModel;
import com.anara.barber.Model.SalonModel;
import com.anara.barber.R;
import com.bumptech.glide.Glide;

import java.io.File;
import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

public class OwnerDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    CircleImageView circleImageView;
    ImageView a1;
    File file;
    String ownerImagePath = "";

    // for saloon data
    SalonModel salonModel;

    // for owner data
    OwnerModel ownerModel;

    EditText owner_name, e_mail, phone_number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_details);

        ownerModel = new OwnerModel();
        salonModel = getIntent().getParcelableExtra(Const.SALOON_DATA_KEY);

        owner_name = findViewById(R.id.owner_name);
        e_mail = findViewById(R.id.e_mail);
        phone_number = findViewById(R.id.phone_number);

        circleImageView = findViewById(R.id.profile_image);
        RelativeLayout continueButton = findViewById(R.id.continue_button);
        a1 = findViewById(R.id.a1);

        continueButton.setOnClickListener(this);
        circleImageView.setOnClickListener(this);
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
        if (owner_name.getText().toString().equals("") || owner_name.getText().toString().length() < 3) {
            Toast.makeText(this, "Owner Name Must be Longer " + owner_name.getText().toString().length(), Toast.LENGTH_SHORT).show();
        } else if (e_mail.getText().toString().equals("")) {
            Toast.makeText(this, "Enter valid email", Toast.LENGTH_SHORT).show();
        } else if (phone_number.getText().toString().equals("") || phone_number.getText().toString().length() < 10) {
            Toast.makeText(this, "Enter valid number", Toast.LENGTH_SHORT).show();
        } else if (ownerImagePath.equals("")) {
            Toast.makeText(this, "select image", Toast.LENGTH_SHORT).show();
        } else {
            ownerModel.setOwnerName(owner_name.getText().toString());
            ownerModel.setOwnerEmailAddress(e_mail.getText().toString());
            ownerModel.setOwnerNumber(phone_number.getText().toString());
            ownerModel.setOwnerImages(ownerImagePath);
            Intent intent = new Intent(OwnerDetailsActivity.this, BarberDetailsActivity.class);
            intent.putExtra(Const.SALOON_DATA_KEY, (Parcelable) salonModel);
            intent.putExtra(Const.OWNER_DATA_KEY, (Parcelable) ownerModel);
            startActivity(intent);
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