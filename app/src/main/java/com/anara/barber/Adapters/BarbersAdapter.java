package com.anara.barber.Adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.anara.barber.Activities.ShowIncomeActivity;
import com.anara.barber.ApiRS.BarbersRS;
import com.anara.barber.MainActivityOwner;
import com.anara.barber.Model.BarberModel;
import com.anara.barber.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;

import java.util.ArrayList;


public class BarbersAdapter extends RecyclerView.Adapter<BarbersAdapter.MyViewHolder> {

    ArrayList<BarbersRS> barberModels;

    MainActivityOwner mainActivityOwner;

    public BarbersAdapter(MainActivityOwner mainActivityOwner, ArrayList<BarbersRS> barberModels) {
        this.barberModels = barberModels;
        this.mainActivityOwner = mainActivityOwner;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.barber_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        BarbersRS barberModel = barberModels.get(holder.getAdapterPosition());

        holder.barberName.setText(barberModel.getName());
        holder.monthEarning.setText(barberModel.getMonth_earning());
        holder.todayEarning.setText(barberModel.getToday_earning());

        Glide.with(holder.barberImage).load(barberModel.getProfile_image()).centerCrop().into(holder.barberImage);

        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(mainActivityOwner, ShowIncomeActivity.class);
            mainActivityOwner.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return barberModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView barberImage;
        TextView barberName, monthEarning, todayEarning;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            barberImage = itemView.findViewById(R.id.barber_image);
            barberName = itemView.findViewById(R.id.barber_name);
            monthEarning = itemView.findViewById(R.id.month_earning);
            todayEarning = itemView.findViewById(R.id.today_earning);

        }
    }
}
