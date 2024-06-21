package com.example.barangayservicehub.admin_module;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.barangayservicehub.R;
import com.example.barangayservicehub.connector.Firebase_Connect;
import com.example.barangayservicehub.core.function;

public class Admin_Services extends AppCompatActivity {

    public ImageView btnBack;

    public TextView viewDate;

    final function function;
    final Firebase_Connect connect;

    public Admin_Services(){
        connect = new Firebase_Connect();
        function = new function();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_admin_services);

        btnBack = findViewById(R.id.btnBackArrow);

        viewDate = findViewById(R.id.date_now);
        String current = function.getCurrentDate();
        viewDate.setText(current);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });




    }
}