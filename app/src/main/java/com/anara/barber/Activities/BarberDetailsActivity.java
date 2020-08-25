package com.anara.barber.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.anara.barber.Dialogs.AddServiceDialog;
import com.anara.barber.MainActivityBarbers;
import com.anara.barber.MainActivityOwner;
import com.anara.barber.R;

public class BarberDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barber_details);

        RelativeLayout relativeLayout = findViewById(R.id.continue_button);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BarberDetailsActivity.this, MainActivityOwner.class);
                startActivity(intent);
            }
        });

        TextView AddService = findViewById(R.id.add_service);
        AddService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddServiceDialog addServiceDialog = new AddServiceDialog(BarberDetailsActivity.this);
                addServiceDialog.show(getSupportFragmentManager(), "AddDetails");
            }
        });
    }
}