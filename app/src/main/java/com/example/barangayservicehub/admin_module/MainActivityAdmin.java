package com.example.barangayservicehub.admin_module;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.barangayservicehub.DashboardActivity;
import com.example.barangayservicehub.LoginActivity;
import com.example.barangayservicehub.ProfileActivity;
import com.example.barangayservicehub.R;
import com.example.barangayservicehub.RegisterActivity;

public class MainActivityAdmin extends AppCompatActivity {


    CardView btnInfo, btnNews, btnSevices, btnProgram, btnNumber, btnViewRequest, btnViewIncident, btnViewFeedBack, btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_admin);


        btnInfo = findViewById(R.id.btnUpdateBarangayStats);
        btnNews = findViewById(R.id.btnAddBarangayNews);
        btnProgram = findViewById(R.id.btnAddBarangayProgram);
        btnNumber = findViewById(R.id.btnAddBarangayNumber);
        btnViewRequest = findViewById(R.id.btnViewBarangayRequest);
        btnViewIncident = findViewById(R.id.btnViewBarangayIncident);
        btnViewFeedBack = findViewById(R.id.btnViewBarangayFeedback);
        btnLogout = findViewById(R.id.btnAdminLogout);

        btnInfo.setOnClickListener(new View.OnClickListener() {
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

        btnProgram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextLaunchAddProgram();
            }
        });

        btnNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextLunchAddNumber();
            }
        });

        btnViewRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextLaunchViewIncidentReport();
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logout();
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

    public void nextLaunchAddServices(){
        Intent intent = new Intent(this, Admin_Services.class);
        startActivity(intent);
    }

    public void nextLunchAddNumber(){
        Intent intent = new Intent(this, Admin_Call.class );
        startActivity(intent);
    }

    public void nextLaunchAddProgram(){
        Intent intent = new Intent(this, Admin_Program.class);
        startActivity(intent);
    }

    public void nextLaunchViewIncidentReport(){
        Intent intent = new Intent(this, Admin_View_Services_Request.class);
        startActivity(intent);
    }

    public void Logout(){
        Intent intent = new Intent(MainActivityAdmin.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}