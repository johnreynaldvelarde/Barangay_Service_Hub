package com.example.barangayservicehub;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SplashActivity extends AppCompatActivity {

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
        MyConnection conn = new MyConnection(SplashActivity.this);

        if(conn.userAccountEmpty()){
            startActivity(new Intent(SplashActivity.this, GetStartedActvity.class));
        }
        else {
            startActivity(new Intent(SplashActivity.this, LoginActivity.class));
        }
    }
}