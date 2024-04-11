package com.example.barangayservicehub;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import androidx.appcompat.widget.Toolbar;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.barangayservicehub.bottom_fragment.CrimeReportFragment;
import com.example.barangayservicehub.bottom_fragment.DashboardFragment;
import com.example.barangayservicehub.bottom_fragment.EmergencyFragment;
import com.example.barangayservicehub.bottom_fragment.FileRequestFragment;
import com.example.barangayservicehub.bottom_fragment.ServicesFragment;
import com.example.barangayservicehub.nav_fargment.SettingFragment;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

public class Sample_Main_Activity extends AppCompatActivity {

    //implements NavigationView.OnNavigationItemSelectedListener


    FloatingActionButton fab;
    DrawerLayout drawerLayout;
    private BottomNavigationView bottomNavigationView;
    private FrameLayout frameLayout;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    Toolbar toolbar;
    BottomAppBar bottomAppBar;

    NavigationView navigationView;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sample_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav, R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = findViewById(R.id.nav_view);
        //navigationView.setNavigationItemSelectedListener(this);

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



        /*

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setBackground(null);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int bottomID = menuItem.getItemId();
                if(bottomID == R.id.bottom_dashboard){
                    openFragment(new DashboardFragment());
                    return  true;
                } else if (bottomID == R.id.bottom_service) {
                    openFragment(new ServicesFragment());
                    return true;
                }
                return false;
            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int itemID = menuItem.getItemId();
                if(itemID == R.id.navDash){
                    Toast.makeText(Sample_Main_Activity.this,"Dashboard Clicked", Toast.LENGTH_SHORT).show();
                }
                if(itemID == R.id.navNews){
                    Toast.makeText(Sample_Main_Activity.this,"News Clicked", Toast.LENGTH_SHORT).show();
                }
                if(itemID == R.id.navServices){
                    Toast.makeText(Sample_Main_Activity.this,"Services Clicked", Toast.LENGTH_SHORT).show();
                }
                drawerLayout.close();
                return false;
            }
        });

        fragmentManager = getSupportFragmentManager();
        openFragment(new DashboardFragment());

         */
    }

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



    /*
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if(itemId == R.id.navDash){
            openFragment(new DashboardFragment());
        }
        else if(itemId == R.id.navNews){
            openFragment(new NewsFragment());
        }
        else if(itemId == R.id.navServices){
            openFragment(new ServicesFragment());
        }
        else if(itemId == R.id.navRequest){
            Toast.makeText(this, "Request", Toast.LENGTH_SHORT).show();
        }
        else if(itemId == R.id.navEmergency){
            Toast.makeText(this, "Emergency", Toast.LENGTH_SHORT).show();
        }
        else if(itemId == R.id.navFeedback){
            Toast.makeText(this, "Feedback / Suggestion", Toast.LENGTH_SHORT).show();
        }
        else if(itemId == R.id.navOfficial){
            Toast.makeText(this, "Brgy. Officials", Toast.LENGTH_SHORT).show();
        }
        else if(itemId == R.id.navSetting){
            Toast.makeText(this, "Setting", Toast.LENGTH_SHORT).show();
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }


    public void openFragment(Fragment fragment){
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();
    }
 */
}