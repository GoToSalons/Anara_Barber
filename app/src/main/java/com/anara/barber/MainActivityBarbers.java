package com.anara.barber;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.anara.barber.Adapters.BarberSlotAdapter;
import com.anara.barber.Dialogs.DatePickerDialog;
import com.anara.barber.Model.BarberSlotModel;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MainActivityBarbers extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imageView = findViewById(R.id.profile_image);
        Glide.with(MainActivityBarbers.this).load(getResources().getDrawable(R.drawable.akim)).into(imageView);

        TextView textView = findViewById(R.id.manage_bookings);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivityBarbers.this);
                datePickerDialog.show(getSupportFragmentManager(), "date");
            }
        });

        ArrayList<BarberSlotModel> barberSlots = new ArrayList<>();
        barberSlots.add(new BarberSlotModel("Akim Kasmani","Hair Cut | Beard","11:00 AM","12:00 PM","₹ 500"));
        barberSlots.add(new BarberSlotModel("Akim Kasmani","Hair Cut | Beard","11:00 AM","12:00 PM","₹ 500"));
        barberSlots.add(new BarberSlotModel("Empty","","11:00 AM","12:00 PM",""));
        barberSlots.add(new BarberSlotModel("Akim Kasmani","Hair Cut | Beard","11:00 AM","12:00 PM","₹ 500"));
        barberSlots.add(new BarberSlotModel("Akim Kasmani","Hair Cut | Beard","11:00 AM","12:00 PM","₹ 500"));

        BarberSlotAdapter barberSlotAdapter = new BarberSlotAdapter(barberSlots, "edit");

        RecyclerView recyclerView = findViewById(R.id.slot_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivityBarbers.this));
        recyclerView.setAdapter(barberSlotAdapter);
    }
}