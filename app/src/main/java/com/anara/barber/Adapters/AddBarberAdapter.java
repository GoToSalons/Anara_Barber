package com.anara.barber.Adapters;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.anara.barber.Activities.BarberDetailsActivity;
import com.anara.barber.Model.AddBarberItem;
import com.anara.barber.R;
import com.bumptech.glide.Glide;
import com.hbb20.CountryCodePicker;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class AddBarberAdapter extends RecyclerView.Adapter<AddBarberAdapter.MyViewHolder> {

    private ArrayList<AddBarberItem> addBarberItems;
    private BarberDetailsActivity barberDetailsActivity;
    private String barberAction;
    private OnImageClick onImageClick;

    public AddBarberAdapter(ArrayList<AddBarberItem> addBarberItems, BarberDetailsActivity barberDetailsActivity, String barberAction) {
        this.addBarberItems = addBarberItems;
        this.barberDetailsActivity = barberDetailsActivity;
        this.barberAction = barberAction;
    }

    public void setOnImageClick(OnImageClick onImageClick) {
        this.onImageClick = onImageClick;
    }

    public ArrayList<AddBarberItem> getAddBarberItems() {
        return addBarberItems;
    }

    @NonNull
    @Override
    public AddBarberAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_add_barber, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AddBarberAdapter.MyViewHolder holder, int position) {
        AddBarberItem addBarberItem = addBarberItems.get(holder.getAdapterPosition());
        holder.bindData(addBarberItem);
    }

    @Override
    public int getItemCount() {
        return addBarberItems.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        EditText barber_name, e_mail, phone_number, exp_yrs, exp_mon;
        TextView add_barber;
        CircleImageView profile_image;
        View im_layout;
        ImageView a1;
        CountryCodePicker countryCodePicker;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            barber_name = itemView.findViewById(R.id.barber_name);
            e_mail = itemView.findViewById(R.id.e_mail);
            phone_number = itemView.findViewById(R.id.phone_number);
            exp_yrs = itemView.findViewById(R.id.exp_yrs);
            exp_mon = itemView.findViewById(R.id.exp_mon);
            add_barber = itemView.findViewById(R.id.add_barber);
            profile_image = itemView.findViewById(R.id.profile_image);
            im_layout = itemView.findViewById(R.id.im_layout);
            a1 = itemView.findViewById(R.id.a1);
            countryCodePicker = itemView.findViewById(R.id.cpp);
            countryCodePicker.registerCarrierNumberEditText(phone_number);

        }

        @SuppressLint("SetTextI18n")
        public void bindData(AddBarberItem addBarberItem) {

            switch (barberAction) {
                case "new":
                    add_barber.setVisibility(View.VISIBLE);
                    add_barber.setText("Add Barber");
                    break;
                case "add":
                    add_barber.setVisibility(View.GONE);
                    add_barber.setText("Add Barber");
                    break;
            }

            barber_name.setText(addBarberItem.getName());
            e_mail.setText(addBarberItem.getEmail());
            phone_number.setText(addBarberItem.getMobile());
            exp_yrs.setText(addBarberItem.getExp_year());
            exp_mon.setText(addBarberItem.getExp_month());

//            barber_name.addTextChangedListener(th);

            Log.e("tag", " = =  = = = kkk   = = = " + addBarberItem.getBarber_profile());
            if (addBarberItem.getBarber_profile() != null) {
                Glide.with(barberDetailsActivity)
                        .load(addBarberItem.getBarber_profile())
                        .centerCrop()
                        .into(profile_image);
                a1.setVisibility(View.GONE);
            }

            im_layout.setOnClickListener(view -> {
                onImageClick.onSelectImage(getAdapterPosition());
            });

            add_barber.setOnClickListener(view -> {
                if (barberAction.equals("new")) {
                    AddBarberItem addBarberItem2 = new AddBarberItem();
                    addBarberItems.add(addBarberItem2);
                    notifyItemInserted(addBarberItems.size() - 1);
                }
            });

        }

    }

    public interface OnImageClick {
        void onSelectImage(int position);
    }

}
