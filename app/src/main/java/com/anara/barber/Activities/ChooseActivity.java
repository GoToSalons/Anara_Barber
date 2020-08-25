package com.anara.barber.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.anara.barber.MainActivityBarbers;
import com.anara.barber.R;
import com.bumptech.glide.Glide;

public class ChooseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);
        ImageView imageView = findViewById(R.id.salon_image);
        Glide.with(imageView).load(getResources().getDrawable(R.drawable.main_page_image)).centerCrop().into(imageView);

        RelativeLayout relativeLayout = findViewById(R.id.owner);
        RelativeLayout relativeLayout2 = findViewById(R.id.barbers);

        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChooseActivity.this,SalonDetailsActivity.class);
                startActivity(intent);
            }
        });
        relativeLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChooseActivity.this, MainActivityBarbers.class);
                startActivity(intent);
            }
        });
    }
}