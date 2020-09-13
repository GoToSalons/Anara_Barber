package com.anara.barber.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.anara.barber.R;

import mehdi.sakout.aboutpage.AboutPage;
import mehdi.sakout.aboutpage.Element;

public class ContactActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        @SuppressLint("ResourceType")
        View aboutPage = new AboutPage(this)
                .setDescription("Contact Us For Any Business Inquires or for a Technical Support")
                .addItem(getMobileElement1())
                .addItem(getMobileElement2())
                .addItem(getMobileElement3())
                .create();

        setContentView(aboutPage);

    }
    Element getMobileElement1() {
        Element copyRightsElement = new Element();
        copyRightsElement.setTitle("+916263620227");
        copyRightsElement.setIconDrawable(R.drawable.ic_baseline_call_24);
        copyRightsElement.setGravity(Gravity.CENTER);
        copyRightsElement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + "+916263620227"));
                startActivity(intent);
            }
        });
        return copyRightsElement;
    }
    Element getMobileElement2() {
        Element copyRightsElement = new Element();
        copyRightsElement.setTitle("+918965034485");
        copyRightsElement.setIconDrawable(R.drawable.ic_baseline_call_24);
        copyRightsElement.setGravity(Gravity.CENTER);
        copyRightsElement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + "+918965034485"));
                startActivity(intent);
            }
        });
        return copyRightsElement;
    }
    Element getMobileElement3() {
        Element copyRightsElement = new Element();
        copyRightsElement.setTitle("+919425743111");
        copyRightsElement.setIconDrawable(R.drawable.ic_baseline_call_24);
        copyRightsElement.setGravity(Gravity.CENTER);
        copyRightsElement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + "+919425743111"));
                startActivity(intent);
            }
        });
        return copyRightsElement;
    }
}