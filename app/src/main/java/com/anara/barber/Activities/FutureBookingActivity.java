
package com.anara.barber.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.anara.barber.Adapters.BarberSlotAdapter;
import com.anara.barber.Dialogs.AddBreakDialog;
import com.anara.barber.Dialogs.DatePickerDialog;
import com.anara.barber.MainActivityBarbers;
import com.anara.barber.R;

import java.util.ArrayList;

public class FutureBookingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_future_booking);


        TextView textView = findViewById(R.id.manage_bookings);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddBreakDialog addBreakDialog = new AddBreakDialog(FutureBookingActivity.this);
                addBreakDialog.show(getSupportFragmentManager(), "break");
            }
        });

//        ArrayList<BarberSlotModel> barberSlots = new ArrayList<>();
//        barberSlots.add(new BarberSlotModel("Akim Kasmani","Hair Cut | Beard","11:00 AM","12:00 PM","₹ 500"));
//        barberSlots.add(new BarberSlotModel("Akim Kasmani","Hair Cut | Beard","11:00 AM","12:00 PM","₹ 500"));
//        barberSlots.add(new BarberSlotModel("Empty","","11:00 AM","12:00 PM",""));
//        barberSlots.add(new BarberSlotModel("Akim Kasmani","Hair Cut | Beard","11:00 AM","12:00 PM","₹ 500"));
//        barberSlots.add(new BarberSlotModel("Akim Kasmani","Hair Cut | Beard","11:00 AM","12:00 PM","₹ 500"));
//
//        BarberSlotAdapter barberSlotAdapter = new BarberSlotAdapter(barberSlots,"no edit");
//
//        RecyclerView recyclerView = findViewById(R.id.slot_list);
//        recyclerView.setLayoutManager(new LinearLayoutManager(FutureBookingActivity.this));
//        recyclerView.setAdapter(barberSlotAdapter);
    }
}