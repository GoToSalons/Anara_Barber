package com.anara.barber;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.anara.barber.Adapters.BarbersAdapter;
import com.anara.barber.Dialogs.AddRemoveBarber;
import com.anara.barber.Model.BarberModel;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MainActivityOwner extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_owner);
        ImageView imageView = findViewById(R.id.profile_image);
        Glide.with(MainActivityOwner.this).load(getResources().getDrawable(R.drawable.akim)).into(imageView);

        RecyclerView recyclerView = findViewById(R.id.barber_list);

        ImageView menu = findViewById(R.id.menu);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddRemoveBarber addRemoveBarber = new AddRemoveBarber(MainActivityOwner.this);
                addRemoveBarber.show(getSupportFragmentManager(), "Add");
            }
        });

        ArrayList<BarberModel> barberModels = new ArrayList<>();
        barberModels.add(new BarberModel(getResources().getDrawable(R.drawable.akim),"Akim Kasmani"));
        barberModels.add(new BarberModel(getResources().getDrawable(R.drawable.akim),"Akim Kasmani"));
        barberModels.add(new BarberModel(getResources().getDrawable(R.drawable.akim),"Akim Kasmani"));
        barberModels.add(new BarberModel(getResources().getDrawable(R.drawable.akim),"Akim Kasmani"));

        BarbersAdapter chooseBarbersAdapter = new BarbersAdapter(MainActivityOwner.this,barberModels);

        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivityOwner.this));
        recyclerView.setAdapter(chooseBarbersAdapter);
    }
}