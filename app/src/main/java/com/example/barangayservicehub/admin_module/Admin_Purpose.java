package com.example.barangayservicehub.admin_module;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.barangayservicehub.R;
import com.example.barangayservicehub.connector.Firebase_Connect;
import com.example.barangayservicehub.core.function;

public class Admin_Purpose extends AppCompatActivity {

    final Firebase_Connect connect;
    final function function;

    TextView btnTextSubmit, viewDate;
    FrameLayout btnFrameSubmit;
    ImageView btnBack;

    ProgressBar progressBar;

    public Admin_Purpose(){
        connect = new Firebase_Connect();
        function = new function();
    }

    TextView textPurpose, btnTextPurpose;

    FrameLayout btnAddPurpose;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_admin_purpose);

        btnTextSubmit = findViewById(R.id.btnTextSubmit);
        btnFrameSubmit = findViewById(R.id.btnFrameSubmit);
        viewDate = findViewById(R.id.date_now);
        viewDate.setText(function.getCurrentDate());


        btnFrameSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addPurpose();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



    }
    public void addPurpose(){



    }


}