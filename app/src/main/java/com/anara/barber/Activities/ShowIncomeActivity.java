package com.anara.barber.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

import org.json.JSONObject;

import java.util.ArrayList;


public class ShowIncomeActivity extends AppCompatActivity {

    // progress dialog
    ProgressDialog progressDialog;

    // barbers list
    RecyclerView recyclerView;

    TextView barber_name, todayEarning, weeklyEarning, monthlyEarning, yearlyEarning;

    ArrayList<BarbersRS> barbersRS;
    int selectPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_income);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait......");
        progressDialog.setCancelable(false);

        barbersRS = getIntent().getParcelableArrayListExtra("barber_list");
        selectPosition = getIntent().getIntExtra("select_position", 0);

        recyclerView = findViewById(R.id.slot_recycler);

        barber_name = findViewById(R.id.barber_name);

        todayEarning = findViewById(R.id.today_earning);
        weeklyEarning = findViewById(R.id.weekly_earning);
        monthlyEarning = findViewById(R.id.monthly_earning);
        yearlyEarning = findViewById(R.id.yearly_earning);

        getBarbersIncome();

        findViewById(R.id.back).setOnClickListener(view -> {
            if (selectPosition > 0) {
                selectPosition = selectPosition - 1;
                getBarbersIncome();
            }
        });

        findViewById(R.id.next).setOnClickListener(view -> {
            if (selectPosition < barbersRS.size()) {
                selectPosition = selectPosition + 1;
                getBarbersIncome();
            }
        });

    }

    private void getBarbersIncome() {
        progressDialog.show();

        try {
            JSONObject jsonObject = new JSONObject();

            jsonObject.put("barber_id", barbersRS.get(selectPosition).getId());
            jsonObject.put("date","06-09-2020");

            RequestResponseManager.getBarberIncome(jsonObject, Const.Barber_Income_Request, response -> {
                if (response != null) {
                    BaseRs baseRs = (BaseRs) response;
                    if (baseRs.getStatus().equals("success")) {

                        BarbersRS barbersRS = baseRs.getBarbersRS();

                        barber_name.setText(barbersRS.getName());

                        SalonEarningsRS salonEarningsRS = baseRs.getSalonEarningsRS();
                        todayEarning.setText(salonEarningsRS.getToday_earning());
                        weeklyEarning.setText(salonEarningsRS.getWeek_earning());
                        monthlyEarning.setText(salonEarningsRS.getMonth_earning());
                        yearlyEarning.setText(salonEarningsRS.getYear_earning());

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
            Log.e("tag"," = = = error = = " + e.getMessage());
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
        }

        getBarberBookings();

    }

    private void getBarberBookings() {
        try {
            JSONObject jsonObject = new JSONObject();

            jsonObject.put("barber_id", barbersRS.get(selectPosition).getId());

            RequestResponseManager.getBarberBooking(jsonObject, Const.Barber_Income_Request, response -> {
                if (response != null) {
                    BaseRs baseRs = (BaseRs) response;
                    if (baseRs.getStatus().equals("success")) {
                        BarberSlotAdapter barberSlotAdapter = new BarberSlotAdapter(baseRs.getBookingListRS(), "edit");
                        recyclerView.setLayoutManager(new LinearLayoutManager(ShowIncomeActivity.this));
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
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
        }

    }

}