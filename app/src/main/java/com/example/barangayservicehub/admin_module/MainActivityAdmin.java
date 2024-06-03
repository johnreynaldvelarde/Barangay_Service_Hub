package com.example.barangayservicehub.admin_module;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.barangayservicehub.DashboardActivity;
import com.example.barangayservicehub.R;
import com.example.barangayservicehub.RegisterActivity;

public class MainActivityAdmin extends AppCompatActivity {

    private ImageView btnInformation, btnNews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_admin);

        btnInformation = findViewById(R.id.btnBarangayStats);
        btnNews = findViewById(R.id.btnBarangayNews);

        btnInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextLaunchAddStats();
            }
        });
        btnNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextLaunchAddNews();
            }
        });

    }
    public void nextLaunchAddStats(){
        Intent intent = new Intent(this, Admin_Information.class);
        startActivity(intent);
    }

    public void nextLaunchAddNews(){
        Intent intent = new Intent(this, Admin_News.class);
        startActivity(intent);
    }
}