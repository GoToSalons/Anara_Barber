package com.anara.barber.Activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;

import com.anara.barber.Adapters.AddBarberAdapter;
import com.anara.barber.Apis.Const;
import com.anara.barber.MainActivityOwner;
import com.anara.barber.Model.AddBarberItem;
import com.anara.barber.Model.OwnerModel;
import com.anara.barber.Model.SalonModel;
import com.anara.barber.R;

import java.io.File;
import java.util.ArrayList;

public class BarberDetailsActivity extends AppCompatActivity implements AddBarberAdapter.OnImageClick {

    // select image position
    int barberPosition = 0;
    // add barber model class
    ArrayList<AddBarberItem> addBarberItems;
    // add barber details adapter
    AddBarberAdapter addBarberAdapter;
    // add barber Recyclerview
    RecyclerView recyclerView;

    // for saloon data
    SalonModel salonModel;

    // for owner data
    OwnerModel ownerModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barber_details);

        salonModel = getIntent().getParcelableExtra(Const.SALOON_DATA_KEY);
        ownerModel = getIntent().getParcelableExtra(Const.OWNER_DATA_KEY);

        addBarberItems = new ArrayList<>();
        addBarberItems.add(new AddBarberItem());

        recyclerView = findViewById(R.id.recycler_view_barbers);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        addBarberAdapter = new AddBarberAdapter(addBarberItems, this);
        recyclerView.setAdapter(addBarberAdapter);

        addBarberAdapter.setOnImageClick(this);

        findViewById(R.id.continue_button).setOnClickListener(view -> {
            Intent intent = new Intent(BarberDetailsActivity.this, MainActivityOwner.class);
            startActivity(intent);
        });

    }

    private void registerBarberWithFullData() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == 21) {
                if (data != null) {
                    Uri filePath = data.getData();
                    if (filePath != null && filePath.getPath() != null) {
                        convertUriToPath(filePath);
                    }
                }
            }
        }
    }

    void convertUriToPath(Uri selectedImage) {
        String[] filePathColumn = {MediaStore.Images.Media.DATA};

        Cursor cursor = null;
        if (selectedImage != null) {
            cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
        }
        if (cursor != null) {
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);

            cursor.close();

            AddBarberItem addBarberItem = addBarberItems.get(barberPosition);
            addBarberItem.setBarber_profile(picturePath);
            addBarberItems.remove(addBarberItems.get(barberPosition));
            addBarberItems.add(barberPosition, addBarberItem);
            addBarberAdapter.notifyItemChanged(barberPosition);

        }
    }

    @Override
    public void onSelectImage(int position) {
        barberPosition = position;
        Intent i = new Intent(
                Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i, 21);
    }

}