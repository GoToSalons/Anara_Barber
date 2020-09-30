package com.anara.barber;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.anara.barber.Adapters.BarbersAdapter;
import com.anara.barber.ApiRS.BarbersRS;
import com.anara.barber.Apis.Const;
import com.anara.barber.Apis.RequestResponseManager;
import com.anara.barber.Dialogs.AddRemoveBarber;
import com.anara.barber.ApiRS.BaseRs;
import com.anara.barber.ApiRS.SalonEarningsRS;
import com.anara.barber.utils.PrefManager;
import com.bumptech.glide.Glide;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivityOwner extends AppCompatActivity implements BarbersAdapter.OnClick {


    // progress dialog
    ProgressDialog progressDialog;

    TextView todayEarning, weeklyEarning, monthlyEarning, yearlyEarning, owner_name, todayDate;

    // barber list
    RecyclerView recyclerView;

    ImageView imageView;

    public PrefManager prefManager;

    BarbersAdapter chooseBarbersAdapter;

    ArrayList<BarbersRS> barbersRS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_owner);

        prefManager = new PrefManager(this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait......");
        progressDialog.setCancelable(false);

        todayDate = findViewById(R.id.tv2);

        Calendar c = Calendar.getInstance();

        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDate = df.format(c.getTime());
        todayDate.setText(formattedDate);

        owner_name = findViewById(R.id.owner_name);
        todayEarning = findViewById(R.id.today_earning);
        weeklyEarning = findViewById(R.id.weekly_earning);
        monthlyEarning = findViewById(R.id.monthly_earning);
        yearlyEarning = findViewById(R.id.yearly_earning);

        recyclerView = findViewById(R.id.barber_list);


        imageView = findViewById(R.id.profile_image);

        owner_name.setText(prefManager.getString(Const.SALON_NAME, ""));

        Glide.with(MainActivityOwner.this).load(prefManager.getString(Const.OWNER_IMAGE, "")).into(imageView);

        ImageView menu = findViewById(R.id.menu);
        menu.setOnClickListener(view -> {
            AddRemoveBarber addRemoveBarber = new AddRemoveBarber(MainActivityOwner.this);
            addRemoveBarber.show(getSupportFragmentManager(), "Add");
        });
        

    }

    @Override
    protected void onStart() {
        super.onStart();
        getSalonDetails();
    }

    @SuppressLint("SetTextI18n")
    private void getSalonDetails() {
        progressDialog.show();
        try {
            JSONObject jsonObject = new JSONObject();

            jsonObject.put("salon_id", prefManager.getString(Const.SALON_ID,""));

            RequestResponseManager.getSalonIncome(jsonObject, Const.salon_Register_Request, response -> {
                if (response != null) {
                    BaseRs baseRs = (BaseRs) response;
                    if (baseRs.getStatus().equals("success")) {
                        barbersRS = baseRs.getBarbersRSArrayList();
                        SalonEarningsRS salonEarningsRS = baseRs.getSalonEarningsRS();
                        todayEarning.setText("₹ "+salonEarningsRS.getToday_earning());
                        weeklyEarning.setText("₹ "+salonEarningsRS.getWeek_earning());
                        monthlyEarning.setText("₹ "+salonEarningsRS.getMonth_earning());
                        yearlyEarning.setText("₹ "+salonEarningsRS.getYear_earning());

                        chooseBarbersAdapter = new BarbersAdapter(MainActivityOwner.this, barbersRS);
                        chooseBarbersAdapter.setOnClick(this);
                        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivityOwner.this));
                        recyclerView.setAdapter(chooseBarbersAdapter);
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

    public void onBarberDelete() {
        if (chooseBarbersAdapter != null) {
            chooseBarbersAdapter.setDelete(true);
        }
    }

    @Override
    public void onDelete(int barberId, int adapterPosition) {

        progressDialog.show();

        try {

            JSONObject jsonObject = new JSONObject();

//            jsonObject.put("barber_id", prefManager.getString(Const.SALON_ID, ""));
            jsonObject.put("barber_id", barberId);
            jsonObject.put("salon_id", prefManager.getString(Const.SALON_ID, ""));

            RequestResponseManager.deleteBarber(jsonObject, Const.Delete_Barber_Request, response -> {
                if (response != null) {
                    BaseRs baseRs = (BaseRs) response;
                    if (baseRs.getStatus().equals("success")) {
                        barbersRS.remove(adapterPosition);
                        chooseBarbersAdapter.notifyItemRemoved(adapterPosition);
                        Toast.makeText(MainActivityOwner.this, ""+baseRs.getMessage(), Toast.LENGTH_SHORT).show();
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