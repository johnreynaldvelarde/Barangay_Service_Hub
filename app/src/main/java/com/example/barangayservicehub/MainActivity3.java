package com.example.barangayservicehub;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity3 extends AppCompatActivity {

    TextView upper_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main3);

        getAccountName();
    }



    public void getAccountName(){
        upper_name = findViewById(R.id.upper_account_name);
        Intent intent = getIntent();
        String usernameAccount = intent.getStringExtra(LoginActivity.EXTRA_MESSAGE);

        upper_name.setText("Hi, " + usernameAccount);
    }
}