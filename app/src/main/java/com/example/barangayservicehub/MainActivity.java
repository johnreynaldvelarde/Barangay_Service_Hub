package com.example.barangayservicehub;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.barangayservicehub.bottom_fragment.CrimeReportFragment;
import com.example.barangayservicehub.bottom_fragment.DashboardFragment;
import com.example.barangayservicehub.bottom_fragment.EmergencyFragment;
import com.example.barangayservicehub.bottom_fragment.FileRequestFragment;
import com.example.barangayservicehub.bottom_fragment.ServicesFragment;
import com.example.barangayservicehub.nav_fargment.FeedbackActivity;
import com.example.barangayservicehub.nav_fargment.NewsActivity;
import com.example.barangayservicehub.nav_fargment.OfficialActivity;
import com.example.barangayservicehub.nav_fargment.SettingsActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements DashboardFragment.DrawerToggleListener, NavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView bottomNavigationView;
    private FrameLayout frameLayout;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    private DrawerLayout drawerLayout;

    TextView upper_name;


    public static final String EXTRA_MESSAGE = "com.example.registration.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        bottomNavigationView = findViewById(R.id.bottom_navigation);
        frameLayout = findViewById(R.id.frame_layout);

        // bottom navigation
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                int itemId = menuItem.getItemId();

                if(itemId == R.id.bottom_dashboard){
                    loadFragment(new DashboardFragment(), false);
                    getAccountName();
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
        getAccountName();


        drawerLayout = findViewById(R.id.drawerLayout);
        NavigationView navigationView = findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int itemId = item.getItemId();

        if(itemId == R.id.nav_news){
            startActivity(new Intent(MainActivity.this, NewsActivity.class));
        }
        else if(itemId == R.id.nav_feedback){
            startActivity(new Intent(MainActivity.this, FeedbackActivity.class));
        }
        else if(itemId == R.id.nav_official){
            startActivity(new Intent(MainActivity.this, OfficialActivity.class));
        }
        else if(itemId == R.id.nav_setting){
            startActivity(new Intent(MainActivity.this, SettingsActivity.class));
        }
        else{
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }

        // Close the drawer when item is clicked
        drawerLayout.closeDrawer(GravityCompat.END);

        return true;
    }

    @Override
    public void toggleDrawer() {
        if (drawerLayout.isDrawerOpen(GravityCompat.END)) {
            drawerLayout.closeDrawer(GravityCompat.END);
        } else {
            drawerLayout.openDrawer(GravityCompat.END);
        }
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

    public void getAccountName(){
        String username = getIntent().getStringExtra(LoginActivity.EXTRA_MESSAGE);
        DashboardFragment dashboardFragment = DashboardFragment.newInstance(username);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, dashboardFragment).commit();
    }
}