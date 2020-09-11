package com.anara.barber.Adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.anara.barber.ApiRS.BarberSlotsRS;
import com.anara.barber.ApiRS.BookingListRS;
import com.anara.barber.ApiRS.BookingServices;
import com.anara.barber.R;

import java.util.ArrayList;

public class BarberSlotAdapter extends RecyclerView.Adapter<BarberSlotAdapter.MyViewHolder> {

    ArrayList<BookingListRS> barberSlots;
    String s;

    public BarberSlotAdapter(ArrayList<BookingListRS> barberSlots, String s) {
        this.barberSlots = barberSlots;
        this.s = s;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.barber_slot_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        BookingListRS barberSlotModel = barberSlots.get(holder.getAdapterPosition());

        holder.CustomerName.setText(barberSlotModel.getCustomer_name());
        StringBuilder serviceName = new StringBuilder();
        ArrayList<BookingServices> services = barberSlotModel.getServices();
        for (int i = 0; i < services.size(); i++) {
            BookingServices bookingServices = services.get(i);
//            if (i == services.size()-1) {
//                serviceName.append(" | ").append(bookingServices.getService_name());
//            } else {
                serviceName.append(bookingServices.getService_name()).append(" | ");
//            }
        }

        holder.ServiceName.setText(serviceName.toString());

        holder.Amount.setText("₹ "+barberSlotModel.getTotal_price());
        holder.From.setText(barberSlotModel.getFrom_time());
        holder.To.setText(barberSlotModel.getTo_time());
        holder.date.setText(barberSlotModel.getBook_date());

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
        TextView From, To, CustomerName, ServiceName, Amount, date;
        ImageView Delete;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            From = itemView.findViewById(R.id.time_from);
            To = itemView.findViewById(R.id.time_to);
            CustomerName = itemView.findViewById(R.id.Name);
            ServiceName = itemView.findViewById(R.id.service_name);
            Amount = itemView.findViewById(R.id.amount);
            Delete = itemView.findViewById(R.id.delete);
            date = itemView.findViewById(R.id.date);
        }
    }
}
