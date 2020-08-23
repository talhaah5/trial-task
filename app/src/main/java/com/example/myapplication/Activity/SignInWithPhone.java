package com.example.myapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.Activity.CreateAccount;
import com.example.myapplication.Activity.codeConfirmation;
import com.example.myapplication.Activity.login;
import com.example.myapplication.Helper.HelperStatusbar;
import com.example.myapplication.R;

public class SignInWithPhone extends AppCompatActivity {
    private EditText phoneText;
    private TextView signIn;
    private Button next_step;
    private ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_with_phone);
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
            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getBaseContext(), login.class);
                    startActivity(intent);
                }
            });


            signIn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getBaseContext(), CreateAccount.class);
                    startActivity(intent);
                }
            });


            next_step.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getBaseContext(), codeConfirmation.class);
                    startActivity(intent);

                }
            });
        }catch(Exception ex){
            Log.d("Catch_Exception", ex.getMessage());
        }
}
    private void getXML() {
        try {
            phoneText = (EditText) findViewById(R.id.phoneText);
            signIn = findViewById(R.id.sign_in);
            next_step = findViewById(R.id.tv_Next_step);
            image = findViewById(R.id.imageView);
            phoneText.addTextChangedListener(changeColor);
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
            String Pnum = phoneText.getText().toString().trim();
            if(!Pnum.isEmpty() )
                phoneText.setBackgroundColor(Color.parseColor("#EAEFF5"));
            else
                phoneText.setBackgroundColor(Color.parseColor("#F7F8FC"));
            next_step.setEnabled(!Pnum.isEmpty());
            if(!Pnum.isEmpty()) {
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
        public void afterTextChanged(Editable editable) {}
    };
}