
package com.anara.barber.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.anara.barber.Adapters.BarberSchedule;
import com.anara.barber.ApiRS.BarberScheduleRS;
import com.anara.barber.ApiRS.BaseRs;
import com.anara.barber.Apis.Const;
import com.anara.barber.Apis.RequestResponseManager;
import com.anara.barber.Dialogs.AddBreakDialog;
import com.anara.barber.Model.AddBarberItem;
import com.anara.barber.R;
import com.anara.barber.utils.PrefManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;


public class FutureBookingActivity extends AppCompatActivity {

    TextView textView;
    EditText time_from, time_to;
    View continue_button;

    // progress dialog
    ProgressDialog progressDialog;

    String fromTimeBreak = "", toTimeBreak = "";

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_future_booking);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait......");
        progressDialog.setCancelable(false);

        String date = getIntent().getIntExtra("day", 0) + "-"
                + getIntent().getIntExtra("month", 0) + "-"
                + getIntent().getIntExtra("year", 0);

        textView = findViewById(R.id.tv1);
        textView.setText("Slots for " + date);

        time_from = findViewById(R.id.time_from);
        time_to = findViewById(R.id.time_to);

        time_from.setClickable(true);
        time_from.setLongClickable(false);
        time_from.setInputType(InputType.TYPE_NULL);

        time_from.setOnClickListener(view -> Const.showTimePicker(this, time_from));

        time_to.setClickable(true);
        time_to.setLongClickable(false);
        time_to.setInputType(InputType.TYPE_NULL);

        time_to.setOnClickListener(view -> Const.showTimePicker(this, time_to));


        continue_button = findViewById(R.id.continue_button);

        findViewById(R.id.manage_bookings).setOnClickListener(view -> {
            AddBreakDialog addBreakDialog = new AddBreakDialog(FutureBookingActivity.this);
            addBreakDialog.show(getSupportFragmentManager(), "break");
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

        getFutureSchedule();

        continue_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFeatureSlot();
            }
        });

        findViewById(R.id.back_button).setOnClickListener(view -> finish());

    }

    private void addFeatureSlot() {
        progressDialog.show();
        try {


            String date = getIntent().getIntExtra("year", 0) + "-"
                    + getIntent().getIntExtra("month", 0) + "-"
                    + getIntent().getIntExtra("day", 0);

            JSONObject barberJsonObject = new JSONObject();
            PrefManager prefManager = new PrefManager(this);

            barberJsonObject.put("barber_id", prefManager.getString(Const.BARBER_ID, ""));
            barberJsonObject.put("schedule_date", date);
            barberJsonObject.put("from_time", time_from.getText().toString());
            barberJsonObject.put("to_time", time_to.getText().toString());

            RequestResponseManager.addBarberSchedule(barberJsonObject, Const.Saloon_Register_Request, response -> {
                if (response != null) {
                    BaseRs baseRs = (BaseRs) response;
                    if (baseRs.getStatus().equals("success")) {
                        Toast.makeText(this, "" + baseRs.getMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "" + baseRs.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                    Log.e("tag", " = =  = call = = = " + baseRs.getStatus());
                }
                addBreak(fromTimeBreak, toTimeBreak);
            });

        } catch (JSONException e) {
            e.printStackTrace();
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
        }
    }

    @SuppressLint("SetTextI18n")
    public void addBreaks(String fromTime, String toTime) {
        Toast.makeText(this, "Break Added", Toast.LENGTH_SHORT).show();
        findViewById(R.id.break_l).setVisibility(View.VISIBLE);
        ((TextView) findViewById(R.id.break_text)).setText("breaks between " + fromTime + " to " + toTime);
        fromTimeBreak = fromTime;
        toTimeBreak = toTime;
    }

    public void addBreak(String fromTime, String toTime) {

        try {

            String date = getIntent().getIntExtra("year", 0) + "-"
                    + getIntent().getIntExtra("month", 0) + "-"
                    + getIntent().getIntExtra("day", 0);

            JSONObject barberJsonObject = new JSONObject();
            PrefManager prefManager = new PrefManager(this);

            barberJsonObject.put("barber_id", prefManager.getString(Const.BARBER_ID, ""));
            barberJsonObject.put("break_date", date);
            barberJsonObject.put("from_time", fromTime);
            barberJsonObject.put("to_time", toTime);

            RequestResponseManager.addBarberScheduleBreak(barberJsonObject, Const.Saloon_Register_Request, response -> {
                if (response != null) {
                    BaseRs baseRs = (BaseRs) response;
                    if (baseRs.getStatus().equals("success")) {
                        Toast.makeText(this, "" + baseRs.getMessage(), Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(this, "" + baseRs.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                    Log.e("tag", " = =  = call = = = " + baseRs.getStatus());
                }
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
            });

        } catch (JSONException e) {
            e.printStackTrace();
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
        }
    }

    private void getFutureSchedule() {

        try {


            JSONObject barberJsonObject = new JSONObject();
            PrefManager prefManager = new PrefManager(this);

            barberJsonObject.put("barber_id", prefManager.getString(Const.BARBER_ID, ""));

            RequestResponseManager.getBarberSchedule(barberJsonObject, Const.Saloon_Register_Request, response -> {
                if (response != null) {
                    BaseRs baseRs = (BaseRs) response;
                    if (baseRs.getStatus().equals("success")) {
                        ArrayList<BarberScheduleRS> barberScheduleRS = baseRs.getBarberScheduleRS();
                        BarberSchedule barberSchedule = new BarberSchedule(barberScheduleRS, FutureBookingActivity.this);
                        RecyclerView recyclerView = findViewById(R.id.slot_list);
                        recyclerView.setLayoutManager(new LinearLayoutManager(FutureBookingActivity.this));
                        recyclerView.setAdapter(barberSchedule);
                    } else {
                        Toast.makeText(this, "" + baseRs.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                    Log.e("tag", " = =  = call = = = " + baseRs.getStatus());
                }
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
            });

        } catch (JSONException e) {
            e.printStackTrace();
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
        }
    }

}