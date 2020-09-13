package com.anara.barber.Activities;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;


import com.anara.barber.R;

import mehdi.sakout.aboutpage.AboutPage;

public class PrivacyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View aboutPage = new AboutPage(this)

                .setDescription(getString(R.string.privacy))
                .addGroup("Connect with us")
                .addEmail("gotosalons@gmail.com")
                .create();

        setContentView(aboutPage);
    }


}