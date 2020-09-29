package com.anara.barber.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.anara.barber.ApiRS.OwnerRS;
import com.anara.barber.Apis.Const;
import com.anara.barber.Apis.RequestResponseManager;
import com.anara.barber.Helpers.CustomTextWatcher;
import com.anara.barber.MainActivityBarbers;
import com.anara.barber.MainActivityOwner;
import com.anara.barber.ApiRS.BaseRs;
import com.anara.barber.R;
import com.anara.barber.utils.PrefManager;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.TimeUnit;


public class OTPActivity extends AppCompatActivity implements View.OnClickListener {

    // progress dialog
    ProgressDialog progressDialog;

    String mobileNumber;
    EditText ed1, ed2, ed3, ed4, ed5, ed6;
    private FirebaseAuth mAuth;
    private String mVerificationId;

    private String loginType;

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallback = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
            String code = phoneAuthCredential.getSmsCode();

            if (code != null) {
                ed1.setText(String.valueOf(code.charAt(0)));
                ed2.setText(String.valueOf(code.charAt(1)));
                ed3.setText(String.valueOf(code.charAt(2)));
                ed4.setText(String.valueOf(code.charAt(3)));
                ed5.setText(String.valueOf(code.charAt(4)));
                ed6.setText(String.valueOf(code.charAt(5)));
                verifyVerificationCode(code);
            }
        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            Toast.makeText(OTPActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }

        @Override
        public void onCodeSent(@NonNull String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            mVerificationId = s;
            Toast.makeText(OTPActivity.this, "Code Send", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait......");
        progressDialog.setCancelable(false);


        Intent intent = getIntent();
        mobileNumber = intent.getStringExtra("mobile");
        loginType = intent.getStringExtra(Const.LOGIN_TYPE_KEY);
        mAuth = FirebaseAuth.getInstance();
        sendVerificationCode(mobileNumber);
//        Intent intent2 = new Intent(OTPActivity.this, SalonDetailsActivity.class);
//        intent2.putExtra("number", mobileNumber);
//        startActivity(intent2);
        ed1 = findViewById(R.id.ed1);
        ed2 = findViewById(R.id.ed2);
        ed3 = findViewById(R.id.ed3);
        ed4 = findViewById(R.id.ed4);
        ed5 = findViewById(R.id.ed5);
        ed6 = findViewById(R.id.ed6);
        RelativeLayout continueButton = findViewById(R.id.continue_button);
        ImageView backButton = findViewById(R.id.back_button);

        continueButton.setOnClickListener(this);
        backButton.setOnClickListener(this);

        EditText[] edList = {ed1, ed2, ed3, ed4, ed5, ed6};
        CustomTextWatcher textWatcher = new CustomTextWatcher(edList, continueButton);
        for (EditText editText : edList) editText.addTextChangedListener(textWatcher);


        findViewById(R.id.back_button).setOnClickListener(view -> finish());
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.back_button) {
            onBackPressed();
        } else if (view.getId() == R.id.continue_button) {
            verifyCode();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    private void sendVerificationCode(String mobileNumber) {

        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                mobileNumber,
                60,
                TimeUnit.SECONDS,
                TaskExecutors.MAIN_THREAD,
                mCallback);
    }

    public void verifyCode() {
        String code = "" + ed1.getText().toString() + ed2.getText().toString() + ed3.getText().toString() + ed4.getText().toString() + ed5.getText().toString() + ed6.getText().toString();
        if (code.isEmpty() || code.length() < 6) {
            Toast.makeText(this, "Enter the Correct verification Code", Toast.LENGTH_SHORT).show();
            return;
        }
        verifyVerificationCode(code);
    }

    private void verifyVerificationCode(String otp) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, otp);
        signInWithPhoneAuthCredential(credential);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        progressDialog.show();
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(OTPActivity.this, task -> {
                    if (task.isSuccessful()) {
                        if (loginType.equals(Const.LOGIN_TYPE_OWNER)) {
                            checkRegister();
                        } else {
                            if (progressDialog.isShowing()) {
                                progressDialog.dismiss();
                            }
                            PrefManager prefManager = new PrefManager(this);
                            prefManager.setString(Const.isLoginBarber,"true");
                            Intent intent = new Intent(OTPActivity.this, ChooseActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    } else {
                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }
                        Toast.makeText(OTPActivity.this, "Invalid Code", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void checkRegister() {

        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("mobile", mobileNumber);
            Log.e("tag"," = =  = call owner = = = " + jsonObject.toString());
            RequestResponseManager.checkRegister(jsonObject, Const.Check_Register_Request, response -> {
                if (response != null) {
                    BaseRs baseRs = (BaseRs) response;
                    Log.e("tag"," = =  = call = = = " + baseRs.getStatus());
                    if (baseRs.getLogin().equals("false")) {
                        Intent intent = new Intent(OTPActivity.this, SalonDetailsActivity.class);
                        intent.putExtra("number", mobileNumber);
                        startActivity(intent);
                    } else {
                        OwnerRS ownerRS = baseRs.getOwnerRS();
                        PrefManager prefManager = new PrefManager(this);
                        prefManager.setString(Const.isLoginOwner,"true");
                        prefManager.setString(Const.isOwnerRegister,"true");
                        prefManager.setString(Const.SALON_ID, String.valueOf(ownerRS.getSaloon_id()));
                        prefManager.setString(Const.SALON_NAME, ownerRS.getSaloon_name());
                        prefManager.setString(Const.OPEN_TIME, ownerRS.getOpen_time());
                        prefManager.setString(Const.CLOSE_TIME, ownerRS.getClose_time());
                        prefManager.setString(Const.SALON_TYPE, ownerRS.getSaloon_type());
                        prefManager.setString(Const.CONTACT_NO, ownerRS.getContact_no());
                        prefManager.setString(Const.STREET_ADDRESS, ownerRS.getStreet_address());
                        prefManager.setString(Const.OWNER_IMAGE, ownerRS.getOwner_image());
                        prefManager.setString(Const.INSTAGRAM, ownerRS.getInstagram());
                        prefManager.setString(Const.FACEBOOK, ownerRS.getFacebook());
                        prefManager.setString(Const.TWITTER, ownerRS.getTwitter());
                        prefManager.setString(Const.LATITUDE, ownerRS.getLatitude());
                        prefManager.setString(Const.LONGITUDE, ownerRS.getLogitude());
                        Intent intent = new Intent(OTPActivity.this, ChooseActivity.class);
                        startActivity(intent);
                    }
                    finish();
                }
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
        }

    }



}