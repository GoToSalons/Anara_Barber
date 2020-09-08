package com.anara.barber.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.anara.barber.ApiRS.BarberSlotsRS;
import com.anara.barber.R;

import java.util.ArrayList;

public class BarberSlotAdapter extends RecyclerView.Adapter<BarberSlotAdapter.MyViewHolder> {
    ArrayList<BarberSlotsRS> barberSlots;
    String s;

    public BarberSlotAdapter(ArrayList<BarberSlotsRS> barberSlots, String s) {
        this.barberSlots = barberSlots;
        this.s = s;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.barber_slot_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        BarberSlotsRS barberSlotModel = barberSlots.get(holder.getAdapterPosition());

        holder.CustomerName.setText(barberSlotModel.getBarber_name());
        holder.ServiceName.setText(barberSlotModel.getServices().get(0).getService_name());
        holder.Amount.setText(barberSlotModel.getTotal_price());
        holder.From.setText(barberSlotModel.getFrom_time());
        holder.To.setText(barberSlotModel.getTo_time());

        if (s.equals("edit")) {
            holder.Amount.setVisibility(View.VISIBLE);
            holder.Delete.setVisibility(View.GONE);
        } else {
            holder.Amount.setVisibility(View.GONE);
            holder.Delete.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        return barberSlots.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView From, To, CustomerName, ServiceName, Amount;
        ImageView Delete;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            From = itemView.findViewById(R.id.time_from);
            To = itemView.findViewById(R.id.time_to);
            CustomerName = itemView.findViewById(R.id.Name);
            ServiceName = itemView.findViewById(R.id.service_name);
            Amount = itemView.findViewById(R.id.amount);
            Delete = itemView.findViewById(R.id.delete);
        }
    }
}
