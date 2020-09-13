package com.anara.barber.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.anara.barber.Apis.Const;
import com.anara.barber.Dialogs.MainScreenDialog;
import com.anara.barber.MainActivityBarbers;
import com.anara.barber.MainActivityOwner;
import com.anara.barber.R;
import com.anara.barber.utils.PrefManager;
import com.bumptech.glide.Glide;

public class ChooseActivity extends AppCompatActivity {

    PrefManager prefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        prefManager = new PrefManager(this);
        setContentView(R.layout.activity_choose);
        ImageView imageView = findViewById(R.id.salon_image);
        ImageView menu = findViewById(R.id.menu);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainScreenDialog mainScreenDialog = new MainScreenDialog(ChooseActivity.this);
                mainScreenDialog.show(getSupportFragmentManager(), "Main");
            }
        });
        Glide.with(imageView).load(R.drawable.main_page_image).centerCrop().into(imageView);

        findViewById(R.id.owner).setOnClickListener(view -> {
            if (prefManager.getString(Const.isLoginOwner,"false").equals("true")) {
                Intent intent = new Intent(ChooseActivity.this, MainActivityOwner.class);
                startActivity(intent);
            } else {
                Intent intent = new Intent(ChooseActivity.this, MobileNumberActivity.class);
                intent.putExtra(Const.LOGIN_TYPE_KEY, Const.LOGIN_TYPE_OWNER);
                startActivity(intent);
            }
//            Intent intent = new Intent(ChooseActivity.this,SalonDetailsActivity.class);
        });

        findViewById(R.id.barbers).setOnClickListener(view -> {
            if (prefManager.getString(Const.isLoginBarber,"false").equals("true")) {
                Intent intent = new Intent(ChooseActivity.this, MainActivityBarbers.class);
                startActivity(intent);
            } else {
                Intent intent = new Intent(ChooseActivity.this, MobileNumberActivity.class);
                intent.putExtra(Const.LOGIN_TYPE_KEY, Const.LOGIN_TYPE_BARBER);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onBackPressed(){
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }

}