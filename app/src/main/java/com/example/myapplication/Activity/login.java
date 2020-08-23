package com.example.myapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.Helper.HelperStatusbar;
import com.example.myapplication.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class login extends AppCompatActivity {
    private TextInputEditText textInputEmail;
    private TextInputLayout mailLayout;
    private Button next_step;
    private TextView sign_phone;
    private TextView signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    try {
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
        try{
            signUp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getBaseContext(), CreateAccount.class);
                    startActivity(intent);
                }
            });


            sign_phone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getBaseContext(), SignInWithPhone.class);
                    startActivity(intent);
                }
            });


            next_step.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getBaseContext(), welcomeback.class);
                    startActivity(intent);
                }

            });

        }catch(Exception ex){
            Log.d("Catch_Exception", ex.getMessage());
        }
    }

    private void getXML() {
        try {
            next_step = findViewById(R.id.Sign_in);
            mailLayout = findViewById(R.id.mailLayout);
            textInputEmail = findViewById(R.id.tv_mail);
            textInputEmail.addTextChangedListener(changeColor);
            sign_phone = findViewById(R.id.sign_in_with_phone);
            signUp = findViewById(R.id.sign_in);
        }catch(Exception ex){
            Log.d("Catch_Exception", ex.getMessage());
        }

    }

    private TextWatcher changeColor = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            String Fname = textInputEmail.getText().toString().trim();
            String emailInput = textInputEmail.getText().toString().trim();
            if (TextUtils.isEmpty(textInputEmail.getText().toString())) {
                mailLayout.setHint("Email Address");
            } else {
                mailLayout.setHint(null);
            }
            if(!Fname.isEmpty() ){
                textInputEmail.setBackgroundColor(Color.parseColor("#EAEFF5"));
                mailLayout.setBackgroundColor(Color.parseColor("#EAEFF5"));
            }


            else{
                textInputEmail.setBackgroundColor(Color.parseColor("#F7F8FC"));
                mailLayout.setBackgroundColor(Color.parseColor("#F7F8FC"));
            }
            next_step.setEnabled(!Fname.isEmpty());
            if(!Fname.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
                next_step.setBackgroundColor(Color.parseColor("#11C856"));
                next_step.setHintTextColor(Color.parseColor("#FFFFFF"));
            }
            else{

                next_step.setEnabled(false);
                next_step.setBackgroundColor(Color.parseColor("#F7F8FC"));
                next_step.setHintTextColor(Color.parseColor("#7C7B8A"));
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {
        }
    };
}