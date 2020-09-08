package com.anara.barber.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.anara.barber.Adapters.BarberSlotAdapter;
import com.anara.barber.ApiRS.BarbersRS;
import com.anara.barber.ApiRS.BaseRs;
import com.anara.barber.ApiRS.SalonEarningsRS;
import com.anara.barber.Apis.Const;
import com.anara.barber.Apis.RequestResponseManager;
import com.anara.barber.MainActivityBarbers;
import com.anara.barber.R;
import com.bumptech.glide.Glide;

import org.json.JSONObject;


public class ShowIncomeActivity extends AppCompatActivity {

    // progress dialog
    ProgressDialog progressDialog;

    // barbers list
    RecyclerView recyclerView;

    ImageView barberProfile;

    TextView barber_name, todayEarning, weeklyEarning, monthlyEarning, yearlyEarning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_income);

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

        try {
            JSONObject jsonObject = new JSONObject();

            jsonObject.put("barber_id","30");
            jsonObject.put("date","06-09-2020");

            RequestResponseManager.getBarberIncome(jsonObject, Const.Barber_Income_Request, response -> {
                if (response != null) {
                    BaseRs baseRs = (BaseRs) response;
                    if (baseRs.getStatus().equals("success")) {

                        BarbersRS barbersRS = baseRs.getBarbersRS();
                        Log.e("tag"," = = = =  = mmmmmmmmmmm  " + barbersRS.getName());

                        barber_name.setText(barbersRS.getName());
                        Glide.with(ShowIncomeActivity.this).load(barbersRS.getProfile_image()).into(barberProfile);

                        SalonEarningsRS salonEarningsRS = baseRs.getSalonEarningsRS();
                        todayEarning.setText(salonEarningsRS.getToday_earning());
                        weeklyEarning.setText(salonEarningsRS.getWeek_earning());
                        monthlyEarning.setText(salonEarningsRS.getMonth_earning());
                        yearlyEarning.setText(salonEarningsRS.getYear_earning());

                        BarberSlotAdapter barberSlotAdapter = new BarberSlotAdapter(baseRs.getBarberSlotsRS(), "no edit");
                        recyclerView.setLayoutManager(new LinearLayoutManager(ShowIncomeActivity.this));
                        recyclerView.setAdapter(barberSlotAdapter);

                    } else {
                        Toast.makeText(this, "Try Again", Toast.LENGTH_SHORT).show();
                    }
                }
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
        }

    }

}