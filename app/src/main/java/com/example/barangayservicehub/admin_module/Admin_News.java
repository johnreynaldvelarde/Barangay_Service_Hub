package com.example.barangayservicehub.admin_module;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.barangayservicehub.R;

public class Admin_News extends AppCompatActivity {

    private ImageView btnBack;
    private FrameLayout btnSubmit;
    private TextView getInfoPopulation, getInfoHousehold, getInfoEstablishment, getInfoLandmark;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_admin_news);


        btnBack = findViewById(R.id.btnBackArrow);
        btnSubmit = findViewById(R.id.btnSubmit);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    public void Get_All_Information(){
        getInfoPopulation = findViewById(R.id.i_population);
        getInfoHousehold = findViewById(R.id.i_household);
        getInfoEstablishment = findViewById(R.id.i_establishment);
        getInfoLandmark = findViewById(R.id.i_Landmark);
    }
}