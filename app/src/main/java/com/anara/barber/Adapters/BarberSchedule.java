package com.anara.barber.Adapters;

import android.annotation.SuppressLint;
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

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull BarberSchedule.MyViewHolder holder, int position) {

        BarberScheduleRS barberSchedule = barberScheduleRS.get(holder.getAdapterPosition());

//        holder.CustomerName.setText(prefManager.getString(Const.BARBER_NAME,""));
        holder.date.setText(barberSchedule.getSchedule_date());
        holder.From.setText(barberSchedule.getFrom_time());
        holder.To.setText(barberSchedule.getTo_time());
        holder.BreakFrom.setText("Breaks between  "+barberSchedule.getBreak_from_time());
        holder.BreakTo.setText(barberSchedule.getBreak_to_time());

    }

    @Override
    public int getItemCount() {
        return barberScheduleRS.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView From;
        TextView BreakFrom;
        TextView BreakTo;
        TextView To;
        TextView date;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            From = itemView.findViewById(R.id.time_from);
            To = itemView.findViewById(R.id.time_to);
            BreakFrom = itemView.findViewById(R.id.break_time_from);
            BreakTo = itemView.findViewById(R.id.break_time_to);
            date = itemView.findViewById(R.id.date);

        }
    }
}
