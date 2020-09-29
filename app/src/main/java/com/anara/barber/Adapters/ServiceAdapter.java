package com.anara.barber.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.anara.barber.Model.SalonModel;
import com.anara.barber.R;

import java.util.ArrayList;

public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.MyViewHolder> {

    ArrayList<SalonModel.SalonService> salonServices;

    public ServiceAdapter(ArrayList<SalonModel.SalonService> salonServices) {
        this.salonServices = salonServices;
    }

    @NonNull
    @Override
    public ServiceAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.service_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceAdapter.MyViewHolder holder, int position) {
        SalonModel.SalonService salonService = salonServices.get(holder.getAdapterPosition());

        holder.service_name.setText(salonService.getService_name());
        holder.service_description.setText(salonService.getService_description());
        holder.price.setText(salonService.getPrice());
        holder.time.setText(salonService.getHours() + " hour and " + salonService.getMinutes() + " minutes");
        holder.category.setText(salonService.getService_category());
    }

    @Override
    public int getItemCount() {
        return salonServices.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView service_name, service_description, price, time, category;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            service_name = itemView.findViewById(R.id.service_name);
            service_description = itemView.findViewById(R.id.service_description);
            price = itemView.findViewById(R.id.service_price);
            time = itemView.findViewById(R.id.service_time);
            category = itemView.findViewById(R.id.service_category);
        }
    }
}
