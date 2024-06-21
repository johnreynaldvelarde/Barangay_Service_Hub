package com.example.barangayservicehub.admin_module;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.barangayservicehub.R;
import com.example.barangayservicehub.connector.Firebase_Connect;
import com.example.barangayservicehub.core.function;
import com.google.android.material.textfield.TextInputLayout;

public class Admin_Call extends AppCompatActivity {

    final function function;
    final Firebase_Connect connect;

    ProgressBar progressBar;

    TextView numberName, callNumber, btnText, viewDate;
    FrameLayout btnLayout;

    TextInputLayout layoutName, layoutNumber;
    ImageView btnBack;

    public Admin_Call(){
        function = new function();
        connect = new Firebase_Connect();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_admin_call);

        viewDate = findViewById(R.id.date_now);
        viewDate.setText(function.getCurrentDate());


        btnLayout = findViewById(R.id.btnFrameSubmit);
        btnText = findViewById(R.id.btnTextSubmit);
        progressBar = findViewById(R.id.progressBar);

        btnBack = findViewById(R.id.btnBackArrow);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        btnLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                btnText.setVisibility(View.INVISIBLE);
                addCallNumber();
            }
        });
    }

    public void addCallNumber(){

        layoutName = findViewById(R.id.layoutCallName);
        layoutNumber = findViewById(R.id.layoutCallNumber);

        numberName = findViewById(R.id.callInputName);
        callNumber = findViewById(R.id.callInputNumber);

        String name = numberName.getText().toString();
        String number = callNumber.getText().toString().trim();

        numberName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                layoutName.setError(null);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        callNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                layoutNumber.setError(null);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        if(name.isEmpty()){

            function.finishActionProgressBar(progressBar, btnText);
            layoutName.setError("* Fill in the blank");

        } else if (number.isEmpty()) {
            function.finishActionProgressBar(progressBar, btnText);
            layoutNumber.setError("* Fill in the blank");
        }else{

           boolean result = connect.addCallNumber(name, number);

            if (result){
                function.finishActionProgressBar(progressBar, btnText);
                Toast.makeText(Admin_Call.this, "Emergency number added successfully", Toast.LENGTH_SHORT).show();
                finish();
            }else {
                function.finishActionProgressBar(progressBar, btnText);
                Toast.makeText(Admin_Call.this, "Emergency number added error", Toast.LENGTH_SHORT).show();
            }

        }
    }
}