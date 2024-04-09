package com.example.barangayservicehub;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;

public class MainActivity extends AppCompatActivity {

    TextView upper_name;

    DrawerLayout drawerLayout;
    ImageButton btnDrawerToggle;
    public static final String EXTRA_MESSAGE = "com.example.registration.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        getAccountName();

        ImageView profileImage = findViewById(R.id.mainProfile);
        profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NextProfileAccount();
            }
        });

    }

    public void NextProfileAccount(){
        String upperName = upper_name.getText().toString();
        Intent intent  = new Intent(this, ProfileActivity.class);
        intent.putExtra(EXTRA_MESSAGE, upperName);
        startActivity(intent);
    }

    public void getAccountName(){
        upper_name = findViewById(R.id.upper_account_name);
        Intent intent = getIntent();
        String usernameAccount = intent.getStringExtra(LoginActivity.EXTRA_MESSAGE);

        upper_name.setText(usernameAccount);
    }
}