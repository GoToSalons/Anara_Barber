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
import com.anara.barber.MainActivityOwner;
import com.anara.barber.Model.BarberModel;
import com.anara.barber.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;

import java.util.ArrayList;


public class BarbersAdapter extends RecyclerView.Adapter<BarbersAdapter.MyViewHolder> {

    ArrayList<BarberModel> barberModels;

    MainActivityOwner mainActivityOwner;
    public BarbersAdapter(MainActivityOwner mainActivityOwner, ArrayList<BarberModel> barberModels) {
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
        BarberModel barberModel = barberModels.get(holder.getAdapterPosition());
        holder.barberName.setText(barberModel.getBarberName());
        Glide.with(holder.barberImage).load(barberModel.getBarberImage()).centerCrop().into(holder.barberImage);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mainActivityOwner, ShowIncomeActivity.class);
                mainActivityOwner.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return barberModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView barberImage;
        TextView barberName;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            barberImage = itemView.findViewById(R.id.barber_image);
            barberName = itemView.findViewById(R.id.barber_name);
        }
    }
}
