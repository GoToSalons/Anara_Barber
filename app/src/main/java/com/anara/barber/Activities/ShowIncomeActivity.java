package com.anara.barber.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.anara.barber.Adapters.BarberSlotAdapter;
import com.anara.barber.Model.BarberSlotModel;
import com.anara.barber.R;

import java.util.ArrayList;

public class ShowIncomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_income);

        ArrayList<BarberSlotModel> barberSlots = new ArrayList<>();
        barberSlots.add(new BarberSlotModel("Akim Kasmani","Hair Cut | Beard","11:00 AM","12:00 PM","₹ 500"));
        barberSlots.add(new BarberSlotModel("Akim Kasmani","Hair Cut | Beard","11:00 AM","12:00 PM","₹ 500"));
        barberSlots.add(new BarberSlotModel("Akim Kasmani","Hair Cut | Beard","11:00 AM","12:00 PM","₹ 500"));
        barberSlots.add(new BarberSlotModel("Akim Kasmani","Hair Cut | Beard","11:00 AM","12:00 PM","₹ 500"));
        barberSlots.add(new BarberSlotModel("Akim Kasmani","Hair Cut | Beard","11:00 AM","12:00 PM","₹ 500"));

        BarberSlotAdapter barberSlotAdapter = new BarberSlotAdapter(barberSlots, "no edit");

        RecyclerView recyclerView = findViewById(R.id.slot_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(ShowIncomeActivity.this));
        recyclerView.setAdapter(barberSlotAdapter);
    }
}