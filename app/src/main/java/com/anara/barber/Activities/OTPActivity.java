package com.anara.barber.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.anara.barber.Helpers.CustomTextWatcher;
import com.anara.barber.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;


public class OTPActivity extends AppCompatActivity implements View.OnClickListener {

    String mobileNumber;
    EditText ed1, ed2, ed3, ed4, ed5, ed6;
    private FirebaseAuth mAuth;
    private String mVerificationId;
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

        Intent intent = getIntent();
        mobileNumber = intent.getStringExtra("mobile");
        mAuth = FirebaseAuth.getInstance();
        sendVerificationCode(mobileNumber);

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
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.back_button) {
            onBackPressed();
        } else if (view.getId() == R.id.continue_button) {
            verifyCode();
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

        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(OTPActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                        } else {
                            Toast.makeText(OTPActivity.this, "Invalid Code", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}