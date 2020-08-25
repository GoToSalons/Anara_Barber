package com.anara.barber.Dialogs;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.anara.barber.Activities.BarberDetailsActivity;
import com.anara.barber.R;

public class AddServiceDialog extends DialogFragment implements View.OnClickListener {

    private BarberDetailsActivity barberDetailsActivity;

    public AddServiceDialog(BarberDetailsActivity barberDetailsActivity) {
        this.barberDetailsActivity = barberDetailsActivity;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.AppTheme_FullScreenDialog);
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            int width = ViewGroup.LayoutParams.WRAP_CONTENT;
            int height = ViewGroup.LayoutParams.WRAP_CONTENT;
            Objects.requireNonNull(dialog.getWindow()).setLayout(width, height);
            Objects.requireNonNull(getDialog().getWindow()).setBackgroundDrawableResource(R.drawable.dialog_bg);
            dialog.getWindow().setGravity(Gravity.CENTER_HORIZONTAL );
        }
    }

    @SuppressLint("SetTextI18n")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.add_service_dialog, container, false);


        return contentView;
    }

    @Override
    public void onClick(View v) {

//        if (v.getId()==R.id.profile_layout){
//            Intent intent = new Intent(mainActivity, ProfileActivity.class);
//            startActivity(intent);
//        }else if (v.getId()==R.id.appointments){
//            Intent intent = new Intent(mainActivity, BookingsActivity.class);
//            startActivity(intent);
//        }

    }

}
