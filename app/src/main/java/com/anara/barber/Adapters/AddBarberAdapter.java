package com.anara.barber.Adapters;

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
import com.anara.barber.Dialogs.AddServiceDialog;
import com.anara.barber.Model.AddBarberItem;
import com.anara.barber.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class AddBarberAdapter extends RecyclerView.Adapter<AddBarberAdapter.MyViewHolder> {

    private ArrayList<AddBarberItem> addBarberItems;
    private BarberDetailsActivity barberDetailsActivity;
    private OnImageClick onImageClick;

    public AddBarberAdapter(ArrayList<AddBarberItem> addBarberItems, BarberDetailsActivity barberDetailsActivity) {
        this.addBarberItems = addBarberItems;
        this.barberDetailsActivity = barberDetailsActivity;
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

        }

        public void bindData(AddBarberItem addBarberItem) {

            barber_name.setText(addBarberItem.getName());
            e_mail.setText(addBarberItem.getEmail());
            phone_number.setText(addBarberItem.getMobile());
            exp_yrs.setText(addBarberItem.getExp_year());
            exp_mon.setText(addBarberItem.getExp_month());

            Log.e("tag"," = =  = = = kkk   = = = " + addBarberItem.getBarber_profile() );
            if (addBarberItem.getBarber_profile() != null) {
                Glide.with(barberDetailsActivity)
                        .load(addBarberItem.getBarber_profile())
                        .centerCrop()
                        .into(profile_image);
                a1.setVisibility(View.GONE);
            }

            im_layout.setOnClickListener(view -> onImageClick.onSelectImage(getAdapterPosition()));



            add_barber.setOnClickListener(view -> {
                AddBarberItem addBarberItem2 = new AddBarberItem();
                addBarberItems.add(addBarberItem2);
                notifyItemInserted(addBarberItems.size()-1);
            });

        }

    }

    public interface OnImageClick {
        void onSelectImage(int position);
    }

}
