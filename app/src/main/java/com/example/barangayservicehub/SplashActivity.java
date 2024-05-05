package com.example.barangayservicehub;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.barangayservicehub.connector.Firebase_Connect;


public class SplashActivity extends AppCompatActivity {

    final Firebase_Connect connect;

    public SplashActivity(){
        connect = new Firebase_Connect();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                chooseLunchActivity();
                finish();
            }
        }, 1000);
    }

    public void chooseLunchActivity(){
        SQLConnection conn = new SQLConnection(SplashActivity.this);
        connect.checkAndCreateAdminIfNeeded();
        if(conn.userAccountEmpty()){
            startActivity(new Intent(SplashActivity.this, GetStartedActvity.class));
        }
        else {
            startActivity(new Intent(SplashActivity.this, LoginActivity.class));
        }
    }
}