package com.example.barangayservicehub;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.barangayservicehub.bottom_fragment.CategoryFragment;
import com.example.barangayservicehub.nav_fargment.DashboardFragment;
import com.example.barangayservicehub.nav_fargment.HomeFragment;
import com.example.barangayservicehub.nav_fargment.NewsFragment;
import com.example.barangayservicehub.nav_fargment.ServicesFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

public class Sample_Main_Activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{


    FloatingActionButton fab;
    DrawerLayout drawerLayout;
    BottomNavigationView bottomNavigationView;
    FragmentManager fragmentManager;
    Toolbar toolbar;


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

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setBackground(null);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int bottomID = menuItem.getItemId();
                if(bottomID == R.id.bottom_home){
                    openFragment(new CategoryFragment());
                    return  true;
                } else if (bottomID == R.id.bottom_services) {
                    openFragment(new ServicesFragment());
                    return true;
                }
                return false;
            }
        });

        fragmentManager = getSupportFragmentManager();
        openFragment(new CategoryFragment());

    }

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

}