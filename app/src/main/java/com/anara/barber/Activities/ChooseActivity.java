package com.anara.barber.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.anara.barber.Apis.Const;
import com.anara.barber.MainActivityBarbers;
import com.anara.barber.R;
import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ChooseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_choose);
        ImageView imageView = findViewById(R.id.salon_image);
        Glide.with(imageView).load(R.drawable.main_page_image).centerCrop().into(imageView);

        findViewById(R.id.owner).setOnClickListener(view -> {
//            Intent intent = new Intent(ChooseActivity.this, MobileNumberActivity.class);
//            intent.putExtra(Const.LOGIN_TYPE_KEY, Const.LOGIN_TYPE_OWNER);
//            startActivity(intent);
            Intent intent = new Intent(ChooseActivity.this,SalonDetailsActivity.class);
            startActivity(intent);
        });
        findViewById(R.id.barbers).setOnClickListener(view -> {
            Intent intent = new Intent(ChooseActivity.this, MobileNumberActivity.class);
            intent.putExtra(Const.LOGIN_TYPE_KEY, Const.LOGIN_TYPE_BARBER);
            startActivity(intent);
//            Intent intent = new Intent(ChooseActivity.this, MainActivityBarbers.class);
//            startActivity(intent);
        });
    }
}