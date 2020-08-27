package com.anara.barber.Activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.anara.barber.R;
import com.bumptech.glide.Glide;

import java.io.File;
import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

public class OwnerDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    CircleImageView circleImageView;
    ImageView a1;
    File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_details);

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
                Intent intent = new Intent(OwnerDetailsActivity.this, BarberDetailsActivity.class);
                startActivity(intent);
                break;
            case R.id.profile_image:
                Intent i = new Intent();
                i.setType("image/*");
                i.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(i, "Select Picture"), 1);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            Bitmap img = null;
            try {
                Uri filePath = data.getData();
                img = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                file = new File(filePath.getPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
            Glide.with(circleImageView).load(img).centerCrop().into(circleImageView);
            a1.setVisibility(View.GONE);
        }
    }
}