package com.anara.barber.Dialogs;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.anara.barber.Activities.FutureBookingActivity;
import com.anara.barber.Apis.Const;
import com.anara.barber.R;
import com.github.jhonnyx2012.horizontalpicker.HorizontalPicker;

import java.util.Objects;

public class AddBreakDialog extends DialogFragment {

    FutureBookingActivity futureBookingActivity;

    EditText time_from, time_to;

    public AddBreakDialog(FutureBookingActivity futureBookingActivity) {
        this.futureBookingActivity = futureBookingActivity;
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

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.add_break_dialog, container, false);

        time_from = contentView.findViewById(R.id.time_from);
        time_to = contentView.findViewById(R.id.time_to);

        contentView.findViewById(R.id.continue_button).setOnClickListener(view -> {
            futureBookingActivity.addBreaks(time_from.getText().toString(), time_to.getText().toString());
            Toast.makeText(futureBookingActivity, "Break Added", Toast.LENGTH_SHORT).show();
            dismiss();
        });

        time_from.setClickable(true);
        time_from.setLongClickable(false);
        time_from.setInputType(InputType.TYPE_NULL);

        time_from.setOnClickListener(view -> Const.showTimePicker(futureBookingActivity, time_from));

        time_to.setClickable(true);
        time_to.setLongClickable(false);
        time_to.setInputType(InputType.TYPE_NULL);

        time_to.setOnClickListener(view -> Const.showTimePicker(futureBookingActivity, time_to));


        return contentView;
    }



}
