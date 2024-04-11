package com.example.barangayservicehub;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
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
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements DashboardFragment.DrawerToggleListener {

    private BottomNavigationView bottomNavigationView;
    private FrameLayout frameLayout;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private NavigationView navigationView;

    private DrawerLayout drawerLayout;

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

        /*
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem drawerItem) {
                int drawer_id = drawerItem.getItemId();

                if(drawer_id == R.id.navDash){
                    Toast.makeText(MainActivity.this,"Menu Clicked", Toast.LENGTH_SHORT).show();
                }
                else if(drawer_id == R.id.navNews){
                    Toast.makeText(MainActivity.this,"News Clicked", Toast.LENGTH_SHORT).show();
                }
                else if(drawer_id == R.id.navServices){
                    Toast.makeText(MainActivity.this,"Services Clicked", Toast.LENGTH_SHORT).show();
                }
                else if(drawer_id == R.id.navRequest){
                    Toast.makeText(MainActivity.this,"Request Clicked", Toast.LENGTH_SHORT).show();
                }
                else if(drawer_id == R.id.navEmergency){
                    Toast.makeText(MainActivity.this,"Emergency Clicked", Toast.LENGTH_SHORT).show();
                }
                else if(drawer_id == R.id.navFeedback){
                    Toast.makeText(MainActivity.this,"Feedbacl /Suggestions Clicked", Toast.LENGTH_SHORT).show();
                }
                else if(drawer_id == R.id.navOfficial){
                    Toast.makeText(MainActivity.this,"Brgy.Official Clicked", Toast.LENGTH_SHORT).show();
                }
                else if(drawer_id == R.id.navSetting){
                    Toast.makeText(MainActivity.this,"Settings Clicked", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this,"Logout Clicked", Toast.LENGTH_SHORT).show();
                }

                return false;
            }
        });

         */

        drawerLayout = findViewById(R.id.drawer_layout);

    }

    @Override
    public void toggleDrawer() {
        if (drawerLayout.isDrawerOpen(GravityCompat.END)) {
            drawerLayout.closeDrawer(GravityCompat.END);
        } else {
            drawerLayout.openDrawer(GravityCompat.END);
        }
    }

    /*
    public void toggleDrawer() {
        DrawerLayout drawer = findViewById(R.id.nav_view);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            drawer.openDrawer(GravityCompat.START);
        }
    }
     */

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
        String username = getIntent().getStringExtra(LoginActivity.EXTRA_MESSAGE);
        DashboardFragment dashboardFragment = DashboardFragment.newInstance(username);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, dashboardFragment).commit();
    }
}