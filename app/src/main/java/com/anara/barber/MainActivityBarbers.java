package com.anara.barber;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.anara.barber.Adapters.BarberSlotAdapter;
import com.anara.barber.ApiRS.BarbersRS;
import com.anara.barber.ApiRS.BaseRs;
import com.anara.barber.ApiRS.SalonEarningsRS;
import com.anara.barber.Apis.Const;
import com.anara.barber.Apis.RequestResponseManager;
import com.anara.barber.Dialogs.DatePickerDialog;
import com.anara.barber.utils.PrefManager;
import com.bumptech.glide.Glide;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class MainActivityBarbers extends AppCompatActivity {

    // progress dialog
    ProgressDialog progressDialog;

    // barbers list
    RecyclerView recyclerView;

    ImageView barberProfile;

    TextView barber_name, todayEarning, weeklyEarning, monthlyEarning, yearlyEarning, todayDate;

    PrefManager prefManager;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prefManager = new PrefManager(this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait......");
        progressDialog.setCancelable(false);
        progressDialog.show();

        recyclerView = findViewById(R.id.slot_list);

        barber_name = findViewById(R.id.barber_name);
        barberProfile = findViewById(R.id.profile_image);
        todayEarning = findViewById(R.id.today_earning);
        weeklyEarning = findViewById(R.id.weekly_earning);
        monthlyEarning = findViewById(R.id.monthly_earning);
        yearlyEarning = findViewById(R.id.yearly_earning);

        todayDate = findViewById(R.id.tv2);

        barber_name.setText(prefManager.getString(Const.BARBER_NAME, ""));

        Glide.with(this).load(prefManager.getString(Const.BARBER_IMAGE, "")).into(barberProfile);

        Calendar c = Calendar.getInstance();

        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDate = df.format(c.getTime());
        todayDate.setText(formattedDate);


        TextView textView = findViewById(R.id.manage_bookings);
        textView.setOnClickListener(view -> {
            DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivityBarbers.this);
            datePickerDialog.show(getSupportFragmentManager(), "date");
        });

        findViewById(R.id.log_out).setOnClickListener(view -> {
            prefManager.setString(Const.isLoginBarber,"false");
            finish();
        });

        try {
            JSONObject jsonObject = new JSONObject();

            jsonObject.put("barber_id", prefManager.getString(Const.BARBER_ID,""));
            jsonObject.put("date", formattedDate);

            RequestResponseManager.getBarberIncome(jsonObject, Const.Barber_Income_Request, response -> {
                if (response != null) {
                    BaseRs baseRs = (BaseRs) response;
                    if (baseRs.getStatus().equals("success")) {

                        BarbersRS barbersRS = baseRs.getBarbersRS();

                        barber_name.setText(barbersRS.getName());
                        Glide.with(MainActivityBarbers.this).load(barbersRS.getProfile_image()).into(barberProfile);

                        SalonEarningsRS salonEarningsRS = baseRs.getSalonEarningsRS();
                        todayEarning.setText("₹ "+salonEarningsRS.getToday_earning());
                        weeklyEarning.setText("₹ "+salonEarningsRS.getWeek_earning());
                        monthlyEarning.setText("₹ "+salonEarningsRS.getMonth_earning());
                        yearlyEarning.setText("₹ "+salonEarningsRS.getYear_earning());


                    } else {
                        Toast.makeText(this, "" + baseRs.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }


        getBarberBookings();

    }

    private void getBarberBookings() {
        try {
            JSONObject jsonObject = new JSONObject();
            PrefManager prefManager = new PrefManager(this);
            jsonObject.put("barber_id", prefManager.getString(Const.BARBER_ID,""));

            RequestResponseManager.getBarberBooking(jsonObject, Const.Barber_Income_Request, response -> {
                if (response != null) {
                    BaseRs baseRs = (BaseRs) response;
                    if (baseRs.getStatus().equals("success")) {
                        BarberSlotAdapter barberSlotAdapter = new BarberSlotAdapter(baseRs.getBookingListRS(), "edit");
                        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivityBarbers.this));
                        recyclerView.setAdapter(barberSlotAdapter);
                    } else {
                        Toast.makeText(this, ""+baseRs.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}