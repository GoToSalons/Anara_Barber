package com.anara.barber.Dialogs;

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

import com.anara.barber.Activities.FutureBookingActivity;
import com.anara.barber.MainActivityBarbers;
import com.anara.barber.R;
import com.github.jhonnyx2012.horizontalpicker.DatePickerListener;
import com.github.jhonnyx2012.horizontalpicker.HorizontalPicker;

import org.joda.time.DateTime;

import java.util.Objects;

public class DatePickerDialog extends DialogFragment implements DatePickerListener {

    MainActivityBarbers mainActivityBarbers;
    public DatePickerDialog(MainActivityBarbers mainActivityBarbers) {
        this.mainActivityBarbers = mainActivityBarbers;
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
        View contentView = inflater.inflate(R.layout.date_picker_dialog, container, false);
        HorizontalPicker picker = (HorizontalPicker) contentView.findViewById(R.id.datePicker);
        picker.setListener(this).init();
        RelativeLayout relativeLayout = contentView.findViewById(R.id.next);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mainActivityBarbers, FutureBookingActivity.class);
                mainActivityBarbers.startActivity(intent);
            }
        });
        return contentView;
    }

    @Override
    public void onDateSelected(DateTime dateSelected) {

    }
}
