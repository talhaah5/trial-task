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
import android.widget.TextView;

import com.example.myapplication.Helper.HelperStatusbar;
import com.example.myapplication.R;

public class CreateAccount extends AppCompatActivity {
    private EditText firstName;
    private EditText lastName;
    private Button next_step;
    private TextView terms;
    private TextView signIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_create_account);

        try {
            getSupportActionBar().hide();
            Window window = this.getWindow();
            HelperStatusbar.StatusBarLightMode(this);
        }catch (Exception ex){
            Log.d("Catch_Exception", ex.getMessage());
        }
        getXML();
        init();




    }
    private void init(){
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
                    Intent intent = new Intent(getBaseContext(), ContactinfoActivity.class);
                    intent.putExtra("firstName", firstName.getText().toString());
                    intent.putExtra("lastName", lastName.getText().toString());
                    startActivity(intent);
                }
            });
        }
    private void getXML() {
        try{
            firstName = findViewById(R.id.tv_first_name);
            lastName = findViewById(R.id.tv_last_name);
            next_step = findViewById(R.id.tv_Next_step);
            terms = findViewById(R.id.Terms_and_condition_text);
            signIn = findViewById(R.id.sign_in);
            firstName.addTextChangedListener(changeColor);
            lastName.addTextChangedListener(changeColor);
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
            String Fname = firstName.getText().toString().trim();
            String Lname = lastName.getText().toString().trim();
            if(!Fname.isEmpty() )
            firstName.setBackgroundColor(Color.parseColor("#EAEFF5"));
            else
                firstName.setBackgroundColor(Color.parseColor("#F7F8FC"));
            if(!Lname.isEmpty())
            lastName.setBackgroundColor(Color.parseColor("#EAEFF5"));
            else
                lastName.setBackgroundColor(Color.parseColor("#F7F8FC"));
            next_step.setEnabled(!Fname.isEmpty() && !Lname.isEmpty());
            if(!Fname.isEmpty() && !Lname.isEmpty()) {
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