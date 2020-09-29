package com.anara.barber.Dialogs;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.anara.barber.Model.AddBarberItem;
import com.anara.barber.Model.SalonModel;
import com.anara.barber.R;

public class AddServiceDialog extends DialogFragment implements View.OnClickListener {

    private Context context;
    private SalonModel.SalonService salonService;

    private EditText service_name, description, hours, mins, price;
    private RadioButton hair, beard_skin, beauty, others;
    private String serviceId = "1";
    private String serviceCategory = "Hair";

    private AddService addService;


    public AddServiceDialog(Context context) {
        this.context = context;
    }

    public void setAddService(AddService addService) {
        this.addService = addService;
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

        salonService = new SalonModel.SalonService();

        service_name = contentView.findViewById(R.id.service_name);
        description = contentView.findViewById(R.id.description);
        hours = contentView.findViewById(R.id.hours);
        mins = contentView.findViewById(R.id.mins);
        price = contentView.findViewById(R.id.price);
        hair = contentView.findViewById(R.id.hair);
        beard_skin = contentView.findViewById(R.id.beard_skin);
        beauty = contentView.findViewById(R.id.beauty);
        others = contentView.findViewById(R.id.others);

        return contentView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        view.findViewById(R.id.add_service).setOnClickListener(this);

        hair.setOnClickListener(this);
        beard_skin.setOnClickListener(this);
        beauty.setOnClickListener(this);
        others.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        if (v.getId()==R.id.add_service){
            salonService.setService_name(service_name.getText().toString());
            salonService.setService_description(description.getText().toString());
            salonService.setPrice(price.getText().toString());
            salonService.setHours(hours.getText().toString());
            salonService.setMinutes(mins.getText().toString());
            salonService.setService_id(serviceId);
            salonService.setService_category(serviceCategory);
            addService.onAddServiceClick(salonService);
            dismiss();
        } else if (v.getId() == R.id.hair) {
            serviceId = "1";
            serviceCategory = "Hair";
            hair.setChecked(true);
            beard_skin.setChecked(false);
            beauty.setChecked(false);
            others.setChecked(false);
        } else if (v.getId() == R.id.beard_skin) {
            serviceId = "2";
            serviceCategory = "Beard and Skin";
            hair.setChecked(false);
            beard_skin.setChecked(true);
            beauty.setChecked(false);
            others.setChecked(false);
        } else if (v.getId() == R.id.beauty) {
            serviceId = "3";
            serviceCategory = "Beauty";
            hair.setChecked(false);
            beard_skin.setChecked(false);
            beauty.setChecked(true);
            others.setChecked(false);
        } else if (v.getId() == R.id.others) {
            serviceId = "4";
            serviceCategory = "Others";
            hair.setChecked(false);
            beard_skin.setChecked(false);
            beauty.setChecked(false);
            others.setChecked(true);
        }

    }


    public interface AddService {
        void onAddServiceClick(SalonModel.SalonService salonService);
    }

}
