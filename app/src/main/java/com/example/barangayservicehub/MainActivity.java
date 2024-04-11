package com.example.barangayservicehub;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.barangayservicehub.bottom_fragment.CrimeReportFragment;
import com.example.barangayservicehub.bottom_fragment.DashboardFragment;
import com.example.barangayservicehub.bottom_fragment.EmergencyFragment;
import com.example.barangayservicehub.bottom_fragment.FileRequestFragment;
import com.example.barangayservicehub.bottom_fragment.ServicesFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private FrameLayout frameLayout;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    TextView upper_name;

    ImageButton btnDrawerToggle;

    public static final String EXTRA_MESSAGE = "com.example.registration.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        /*
        getAccountName();

        ImageView profileImage = findViewById(R.id.mainProfile);
        profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NextProfileAccount();
            }
        });

         */

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        frameLayout = findViewById(R.id.frame_layout);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                int itemId = menuItem.getItemId();

                if(itemId == R.id.bottom_dashboard){
                    loadFragment(new DashboardFragment(), false);
                }
                else if(itemId == R.id.bottom_service){
                    loadFragment(new ServicesFragment(), false);
                }
                else if(itemId == R.id.bottom_request){
                    loadFragment(new FileRequestFragment(), false);
                }
                else if(itemId == R.id.bottom_crime_reporting){
                    loadFragment(new CrimeReportFragment(), false);
                }
                else {
                    loadFragment(new EmergencyFragment(), false);
                }


                return true;
            }
        });

        loadFragment(new DashboardFragment(), true);
    }

    // bottom navigation fragment
    private void loadFragment(Fragment fragment, boolean isAppInitialized)
    {
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        if(isAppInitialized){
            fragmentTransaction.add(R.id.frame_layout, fragment);
        }
        else{
            fragmentTransaction.replace(R.id.frame_layout, fragment);
        }
        fragmentTransaction.commit();
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