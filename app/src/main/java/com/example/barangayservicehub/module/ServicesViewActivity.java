package com.example.barangayservicehub.module;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.barangayservicehub.R;

public class ServicesViewActivity extends AppCompatActivity {

    TextView viewServicesTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_services_view);

        viewServicesTitle = findViewById(R.id.viewServiceTitle);

        String serviceTitle = getIntent().getStringExtra("SERVICES_TITLE");

        viewServicesTitle.setText(serviceTitle);

        ImageView btnBack = findViewById(R.id.btnBackArrow);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }


}