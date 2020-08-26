package com.anara.barber.Helpers;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.RelativeLayout;

public class CustomTextWatcher implements TextWatcher {
    EditText[] edList;
    RelativeLayout continueButton;

    public CustomTextWatcher(EditText[] edList, RelativeLayout continueButton) {
        this.edList = edList;
        this.continueButton = continueButton;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    @Override
    public void afterTextChanged(Editable s) {
        for (int i = 0; i < edList.length; i++) {
            if (edList[i].getText().toString().trim().length() == 1) {
                if (edList[i] != edList[edList.length - 1]) {
                    edList[i + 1].requestFocus();
                }
                verifyOTP();
            }
        }
    }

    private void verifyOTP() {
        for (EditText editText : edList) {
            if (!editText.getText().toString().equals("")) {
                continueButton.setClickable(true);
                continueButton.setAlpha(1f);
            } else {
                continueButton.setClickable(false);
                continueButton.setAlpha(0.5f);
            }
        }
    }
}