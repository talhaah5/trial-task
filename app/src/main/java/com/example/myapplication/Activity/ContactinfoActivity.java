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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.Helper.HelperStatusbar;
import com.example.myapplication.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.hbb20.CountryCodePicker;

public class ContactinfoActivity extends AppCompatActivity {
    private EditText phoneText;
    private TextView signIn;
    private Button next_step;
    private ImageView image;
    private View m;
    private TextView error;
    private CountryCodePicker ccp;
    private TextInputEditText textInputEmail;
    private TextInputLayout mailLayout;
    private TextView nameText;
    String name,lname;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.contactinfo);
        try {
            getSupportActionBar().hide();
            Window window = this.getWindow();
            HelperStatusbar.StatusBarLightMode(this);
        }catch (Exception ex){
            Log.d("Catch_Exception", ex.getMessage());
        }
        getXML();
        init();
        getintent();



    }
    private void getintent(){
        Intent intent = getIntent();
        try{

            if(intent.hasExtra("firstName") && intent.hasExtra("lastName")){
                name = intent.getStringExtra("firstName");
                lname = intent.getStringExtra("lastName");
                nameText.setText("Hi! "+ name);
            }
        }catch(Exception ex){
            Log.d("Catch_Exception", ex.getMessage());
        }
    }
    private void init(){
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), CreateAccount.class);
                startActivity(intent);
            }
        });


        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), login.class);
                startActivity(intent);
            }
        });


        next_step.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    if(validateEmail()){
                        Intent intent = new Intent(getBaseContext(), PasswordActivity.class);
                        intent.putExtra("firstName", name);
                        intent.putExtra("lastName", lname);
                        intent.putExtra("email", textInputEmail.getText().toString());
                        intent.putExtra("phone", phoneText.getText().toString());
                        startActivity(intent);
                    }
                }catch(Exception ex){
                    Log.d("Catch_Exception", ex.getMessage());
                }


            }
        });
    }
    private void getXML() {
        try {
            m = findViewById(R.id.mailline);
            phoneText = (EditText) findViewById(R.id.phoneText);
            signIn = findViewById(R.id.sign_in);
            ccp = (CountryCodePicker) findViewById(R.id.ccp);
            mailLayout = findViewById(R.id.mailLayout);
            textInputEmail = findViewById(R.id.tv_mail);
            error = findViewById(R.id.error);
            ccp.registerCarrierNumberEditText(phoneText);
            next_step = findViewById(R.id.tv_Next_step);
            textInputEmail.addTextChangedListener(changeColor);
            phoneText.addTextChangedListener(changeColor);
            image = findViewById(R.id.imageView);
            nameText = findViewById(R.id.hi_let_s_se);
        }catch (Exception ex){
            Log.d("Catch_Exception", ex.getMessage());
        }
    }

    private boolean validateEmail() {
        String emailInput = textInputEmail.getText().toString().trim();
             if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
                mailLayout.setError("The email address does not seem valid");
                mailLayout.setBackgroundColor(Color.parseColor("#FBEFEE"));
                textInputEmail.setBackgroundColor(Color.parseColor("#FBEFEE"));
            return false;
        } else {
            textInputEmail.setError(null);
            return true;
        }
    }


    private TextWatcher changeColor = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            String Fname = textInputEmail.getText().toString().trim();
            String Pnum = phoneText.getText().toString().trim();
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

            if(!Pnum.isEmpty() )
                phoneText.setBackgroundColor(Color.parseColor("#EAEFF5"));
            else
                phoneText.setBackgroundColor(Color.parseColor("#F7F8FC"));
            next_step.setEnabled(!Fname.isEmpty() && !Pnum.isEmpty());
            if(!Fname.isEmpty() && !Pnum.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
                m.setBackgroundColor(Color.WHITE);
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
            String emailInput = textInputEmail.getText().toString().trim();
            if(!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()){
                 error.setText("The email address does not seem valid");

//                mailLayout.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
                mailLayout.setBackgroundColor(Color.parseColor("#FBEFEE"));
                textInputEmail.setBackgroundColor(Color.parseColor("#FBEFEE"));
                m.setBackgroundColor(Color.RED);
            }
            else {
                m.setBackgroundColor(Color.WHITE);
                error.setText("");
            }

        }
    };
}