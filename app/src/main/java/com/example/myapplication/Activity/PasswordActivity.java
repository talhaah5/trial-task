package com.example.myapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.Helper.HelperStatusbar;
import com.example.myapplication.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Pattern;

public class PasswordActivity extends AppCompatActivity {
    private View m1;
    private TextView error1;
    private TextInputEditText password;
    private TextInputLayout passlayout;
    private View m2;
    private TextView error2;
    private TextInputEditText confirm;
    private TextInputLayout confirmlayout;
    private Button next_step;
    private ImageView image;
    private TextView signIn;
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    //"(?=.*[0-9])" +         //at least 1 digit
                    //"(?=.*[a-z])" +         //at least 1 lower case letter
                    //"(?=.*[A-Z])" +         //at least 1 upper case letter
                    //"(?=.*[a-zA-Z])" +      //any letter
                    //"(?=.*[@#$%^&+=])" +    //at least 1 special character
                    //"(?=\\S+$)" +           //no white spaces
                    ".{8,}" +               //at least 8 characters
                    "$");

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.passwordactivity);
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        try{
            getSupportActionBar().hide();
            Window window = this.getWindow();
            HelperStatusbar.StatusBarLightMode(this);
        }catch(Exception ex){
            Log.d("Catch_Exception", ex.getMessage());
        }

        getXML();
        init();


    }
    private void init(){
        try {
            signIn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getBaseContext(), login.class);
                    startActivity(intent);
                }
            });

            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getBaseContext(), ContactinfoActivity.class);
                    startActivity(intent);
                }
            });
            next_step.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (validatePassword() && validateconfirm()) {
                        Intent intent = new Intent(getBaseContext(), success.class);
                        startActivity(intent);
                    }

                }
            });
        }catch(Exception ex){
            Log.d("Catch_Exception", ex.getMessage());
        }
    }
    private void getXML() {
        try {
            m1 = findViewById(R.id.passwordline);
            passlayout = findViewById(R.id.PasswordLayout);
            password = findViewById(R.id.tv_Password);
            error1 = findViewById(R.id.error);

            m2 = findViewById(R.id.confirmline);
            confirmlayout = findViewById(R.id.confirmLayout);
            confirm = findViewById(R.id.tv_confirm);
            error2 = findViewById(R.id.error1);

            next_step = findViewById(R.id.tv_Next_step);
            image = findViewById(R.id.backView);
            password.addTextChangedListener(changeColor);
            confirm.addTextChangedListener(changeColor);

            signIn = findViewById(R.id.sign_in);
        }catch (Exception ex){
            Log.d("Catch_Exception", ex.getMessage());
        }
    }

    private TextWatcher changeColor = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            password.setHintTextColor(Color.parseColor("#7C7B8A"));

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            String Fname = password.getText().toString().trim();
            String Pnum = confirm.getText().toString().trim();
            String emailInput = password.getText().toString().trim();
            if (TextUtils.isEmpty(password.getText().toString())) {
                passlayout.setHint("Password");
            } else {
                passlayout.setHint("");
            }
            if (TextUtils.isEmpty(confirm.getText().toString())) {
                confirmlayout.setHint("Confirm Password");
                confirm.setHintTextColor(Color.parseColor("#7C7B8A"));
            } else {
                confirmlayout.setHint("");
            }
            if (!Fname.isEmpty()) {
                password.setBackgroundColor(Color.parseColor("#EAEFF5"));
                passlayout.setBackgroundColor(Color.parseColor("#EAEFF5"));
            } else {
                password.setBackgroundColor(Color.parseColor("#F7F8FC"));
                passlayout.setBackgroundColor(Color.parseColor("#F7F8FC"));
            }

            if (!Pnum.isEmpty()){
                validateconfirm();
            }
            else {
                confirm.setBackgroundColor(Color.parseColor("#F7F8FC"));
                confirmlayout.setBackgroundColor(Color.parseColor("#F7F8FC"));
            }
            next_step.setEnabled(!Fname.isEmpty() && !Pnum.isEmpty());
            if (!Fname.isEmpty() && !Pnum.isEmpty() && validateconfirm() && validatePassword()) {
                m1.setBackgroundColor(Color.WHITE);
                next_step.setBackgroundColor(Color.parseColor("#11C856"));
                next_step.setHintTextColor(Color.parseColor("#FFFFFF"));
            } else {

                next_step.setEnabled(false);
                next_step.setBackgroundColor(Color.parseColor("#F7F8FC"));
                next_step.setHintTextColor(Color.parseColor("#7C7B8A"));
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {
            validatePassword();

        }
    };

    private boolean validatePassword() {
        String passwordInput = password.getText().toString().trim();
        try{
            if (!PASSWORD_PATTERN.matcher(passwordInput).matches()) {

                passlayout.setBackgroundColor(Color.parseColor("#FBEFEE"));
                password.setBackgroundColor(Color.parseColor("#FBEFEE"));
                m1.setBackgroundColor(Color.RED);
                error1.setText("The password is not strong enough");
                error1.setTextColor(Color.parseColor("#D44333"));
                error1.setError("Password must contain atleast 8 characters");
                return false;
            } else {

                m1.setBackgroundColor(Color.parseColor("#9EE2B8"));
                error1.setText("This looks like a strong password");
                error1.setTextColor(Color.parseColor("#21A453"));
                passlayout.setBackgroundColor(Color.parseColor("#F0FAF3"));
                password.setBackgroundColor(Color.parseColor("#F0FAF3"));
                error1.setError(null);
                return true;
            }
        }catch(Exception ex){
            Log.d("Catch_Exception", ex.getMessage());
        }
        return false;
    }


    private boolean validateconfirm() {
        String passwordInput = password.getText().toString();
        String confirmpass = confirm.getText().toString();
        try{
            if (!passwordInput.equals(confirmpass)) {

                confirmlayout.setBackgroundColor(Color.parseColor("#FBEFEE"));
                confirm.setBackgroundColor(Color.parseColor("#FBEFEE"));
                m2.setBackgroundColor(Color.RED);
                error2.setText("Password not match");
                error2.setTextColor(Color.parseColor("#D44333"));
                return false;
            } else {

                m2.setBackgroundColor(Color.parseColor("#9EE2B8"));
                error2.setText("Password match");
                error2.setTextColor(Color.parseColor("#21A453"));
                confirmlayout.setBackgroundColor(Color.parseColor("#F0FAF3"));
                confirm.setBackgroundColor(Color.parseColor("#F0FAF3"));
                error2.setError(null);
                return true;
            }
        }catch(Exception ex){
            Log.d("Catch_Exception", ex.getMessage());
        }
        return false;
    }
}