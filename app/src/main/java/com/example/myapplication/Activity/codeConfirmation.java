package com.example.myapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.myapplication.Helper.HelperStatusbar;
import com.example.myapplication.R;

public class codeConfirmation extends AppCompatActivity {
    private int counter;
    private ProgressBar pd;
    private TextView signIn;
    private Button next_step;
    private ImageView image;
    private TextView t1,t2,t3,t4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            counter = 299;
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_code_confirmation);

            pd = findViewById(R.id.pd);
            final TextView counttime = findViewById(R.id.timer);

            new CountDownTimer(300000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    counttime.setText(String.valueOf(counter / 60) + ": " + String.valueOf(counter % 60));
                    counter -= 1;
                }

                @Override
                public void onFinish() {
                    counttime.setText("0:00");
                    pd.setVisibility(View.GONE);
                }
            }.start();
        }catch(Exception ex){
            Log.d("Catch_Exception", ex.getMessage());
        }
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
        try {
            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getBaseContext(), SignInWithPhone.class);
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
                    Intent intent = new Intent(getBaseContext(), welcomeback.class);
                    startActivity(intent);

                }
            });
        }catch(Exception ex){
            Log.d("Catch_Exception", ex.getMessage());
        }


}
    private void getXML() {
        try{
            signIn = findViewById(R.id.sign_in);
            next_step = findViewById(R.id.tv_Next_step);
            image = findViewById(R.id.backView);
            t1 = findViewById(R.id.verifyNum1);
            t2 = findViewById(R.id.verifyNum2);
            t3 = findViewById(R.id.verifyNum3);
            t4 = findViewById(R.id.verifyNum4);
            t1.addTextChangedListener(changeColor);
            t2.addTextChangedListener(changeColor);
            t3.addTextChangedListener(changeColor);
            t4.addTextChangedListener(changeColor);
        }
        catch(Exception ex){
            Log.d("Catch_Exception", ex.getMessage());
        }
    }

    private TextWatcher changeColor = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            String p1 = t1.getText().toString().trim();
            String p2 = t2.getText().toString().trim();
            String p3= t3.getText().toString().trim();
            String p4 = t4.getText().toString().trim();
            if(!p1.isEmpty())
                t1.setBackgroundColor(Color.parseColor("#EAEFF5"));
            else
                t1.setBackgroundColor(Color.parseColor("#F7F8FC"));
            if(!p2.isEmpty())
                t2.setBackgroundColor(Color.parseColor("#EAEFF5"));
            else
                t2.setBackgroundColor(Color.parseColor("#F7F8FC"));
            if(!p3.isEmpty())
                t3.setBackgroundColor(Color.parseColor("#EAEFF5"));
            else
                t3.setBackgroundColor(Color.parseColor("#F7F8FC"));
            if(!p4.isEmpty())
                t4.setBackgroundColor(Color.parseColor("#EAEFF5"));
            else
                t4.setBackgroundColor(Color.parseColor("#F7F8FC"));
            next_step.setEnabled(!p1.isEmpty()&& !p2.isEmpty() && !p3.isEmpty() && !p4.isEmpty());
            if(!p1.isEmpty()&& !p2.isEmpty() && !p3.isEmpty() && !p4.isEmpty()) {
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