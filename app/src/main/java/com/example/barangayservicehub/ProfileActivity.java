package com.example.barangayservicehub;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.barangayservicehub.bottom_fragment.DashboardFragment;

import org.w3c.dom.Text;

public class ProfileActivity extends AppCompatActivity {

    TextView profile_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile);

        getProfileName();

        ImageView btnBack = findViewById(R.id.btnBackArrow);
        Button btnLogout = findViewById(R.id.btnLogout);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logout();
            }
        });
    }

    public void Logout(){
        Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    public void getProfileName(){

        String username = getIntent().getStringExtra(DashboardFragment.Constants.USERNAME_EXTRA);

        TextView profileNameTextView = findViewById(R.id.profileName);

        profileNameTextView.setText(username);

    }
}