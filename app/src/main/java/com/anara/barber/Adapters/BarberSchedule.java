package com.anara.barber.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.anara.barber.ApiRS.BarberScheduleRS;
import com.anara.barber.Apis.Const;
import com.anara.barber.R;
import com.anara.barber.utils.PrefManager;

import java.util.ArrayList;

public class BarberSchedule extends RecyclerView.Adapter<BarberSchedule.MyViewHolder>  {

    ArrayList<BarberScheduleRS> barberScheduleRS;
    PrefManager prefManager;

    public BarberSchedule(ArrayList<BarberScheduleRS> barberScheduleRS, Context context) {
        this.barberScheduleRS = barberScheduleRS;
        prefManager = new PrefManager(context);
    }

    @NonNull
    @Override
    public BarberSchedule.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_schedule, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BarberSchedule.MyViewHolder holder, int position) {

        BarberScheduleRS barberSchedule = barberScheduleRS.get(holder.getAdapterPosition());

//        holder.CustomerName.setText(prefManager.getString(Const.BARBER_NAME,""));
        holder.date.setText(barberSchedule.getSchedule_date());
        holder.From.setText(barberSchedule.getFrom_time());
        holder.To.setText(barberSchedule.getTo_time());

    }

    @Override
    public int getItemCount() {
        return barberScheduleRS.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView From, To, CustomerName, date;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            From = itemView.findViewById(R.id.time_from);
            To = itemView.findViewById(R.id.time_to);
//            CustomerName = itemView.findViewById(R.id.Name);
            date = itemView.findViewById(R.id.date);

        }
    }
}
