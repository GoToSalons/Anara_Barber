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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.anara.barber.Activities.ChooseActivity;
import com.anara.barber.Activities.ContactActivity;
import com.anara.barber.Activities.PrivacyActivity;
import com.anara.barber.R;

import java.util.Objects;


public class MainScreenDialog extends DialogFragment implements View.OnClickListener {

    private ChooseActivity mainActivity;

    public MainScreenDialog(ChooseActivity mainActivity) {
        this.mainActivity = mainActivity;
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
        View contentView = inflater.inflate(R.layout.main_screen_dialog, container, false);

        RelativeLayout dismiss = contentView.findViewById(R.id.dismiss_l);
        RelativeLayout privacy = contentView.findViewById(R.id.privacy);
        RelativeLayout customer = contentView.findViewById(R.id.customer);
        dismiss.setOnClickListener(this);
        privacy.setOnClickListener(this);
        customer.setOnClickListener(this);

        return contentView;
    }

    @Override
    public void onClick(View v) {

         if (v.getId()==R.id.dismiss_l){
            dismiss();
        }else if (v.getId()==R.id.privacy){
            Intent intent = new Intent(mainActivity, PrivacyActivity.class);
            startActivity(intent);
            dismiss();
        }else if (v.getId()==R.id.customer){
            Intent intent = new Intent(mainActivity, ContactActivity.class);
            startActivity(intent);
            dismiss();
        }
    }

}
