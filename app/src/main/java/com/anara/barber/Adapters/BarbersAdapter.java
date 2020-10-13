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
import com.anara.barber.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;


public class BarbersAdapter extends RecyclerView.Adapter<BarbersAdapter.MyViewHolder> {

    ArrayList<BarbersRS> barberModels;

    MainActivityOwner mainActivityOwner;

    OnClick onClick;

    boolean isDelete = false;
    boolean isEdit = false;

    public BarbersAdapter(MainActivityOwner mainActivityOwner, ArrayList<BarbersRS> barberModels) {
        this.barberModels = barberModels;
        this.mainActivityOwner = mainActivityOwner;
    }

    public void setOnClick(OnClick onClick) {
        this.onClick = onClick;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
        notifyDataSetChanged();
    }

    public void setEdit(boolean edit) {
        isEdit = edit;
        notifyDataSetChanged();
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

        if (isDelete) {
            holder.delete_barber.setVisibility(View.VISIBLE);
        } else {
            holder.delete_barber.setVisibility(View.INVISIBLE);
        }
        if (isEdit) {
            holder.edit_barber.setVisibility(View.VISIBLE);
        } else {
            holder.edit_barber.setVisibility(View.INVISIBLE);
        }

        holder.barberName.setText(barberModel.getName());
        holder.monthEarning.setText(barberModel.getMonth_earning());
        holder.todayEarning.setText(barberModel.getToday_earning());

        Glide.with(holder.barberImage).load(barberModel.getProfile_image()).centerCrop().into(holder.barberImage);

        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(mainActivityOwner, ShowIncomeActivity.class);
            intent.putExtra("select_position", holder.getAdapterPosition());
            intent.putExtra("barber_list", barberModels);
            mainActivityOwner.startActivity(intent);
        });

        holder.delete_barber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClick.onDelete(barberModel.getId(), holder.getAdapterPosition());
            }
        });

        holder.edit_barber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClick.onUpdate(barberModel.getId(), barberModel.getMobile(), holder.getAdapterPosition());
            }
        });

    }

    @Override
    public int getItemCount() {
        return barberModels.size();
    }

    public interface OnClick {
        void onDelete(int barberId, int adapterPosition);

        void onUpdate(int id, String barberNumber, int adapterPosition);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView barberImage, delete_barber, edit_barber;
        TextView barberName, monthEarning, todayEarning;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            barberImage = itemView.findViewById(R.id.barber_image);
            delete_barber = itemView.findViewById(R.id.delete_barber);
            edit_barber = itemView.findViewById(R.id.edit_barber);
            barberName = itemView.findViewById(R.id.barber_name);
            monthEarning = itemView.findViewById(R.id.month_earning);
            todayEarning = itemView.findViewById(R.id.today_earning);

        }
    }

}
