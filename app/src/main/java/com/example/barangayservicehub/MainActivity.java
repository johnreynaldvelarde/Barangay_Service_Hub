package com.example.barangayservicehub;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
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
import com.example.barangayservicehub.module.Alert_View_Activity;
import com.example.barangayservicehub.module.Notification_View_Activity;
import com.example.barangayservicehub.nav_fargment.FeedbackActivity;
import com.example.barangayservicehub.nav_fargment.NewsActivity;
import com.example.barangayservicehub.nav_fargment.OfficialActivity;
import com.example.barangayservicehub.nav_fargment.SettingsActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity implements DashboardFragment.DrawerToggleListener, NavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView bottomNavigationView;
    private FrameLayout frameLayout;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private DrawerLayout drawerLayout;

    private NavigationView navigationView;

    TextView upper_name, upper_email;


    public static final String EXTRA_MESSAGE = "com.example.registration.MESSAGE";
    private static final String CHANNEL_ID = "emergency_channel";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        DatabaseReference newsRef = FirebaseDatabase.getInstance().getReference("Barangay_News");
        newsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot newsSnapshot : dataSnapshot.getChildren()) {
                    int isImportant = newsSnapshot.child("isImportant").getValue(Integer.class);
                    if (isImportant == 1) {
                        String newsTitle = newsSnapshot.child("newsTitle").getValue(String.class);
                        String newsArticle = newsSnapshot.child("newsArticle").getValue(String.class);
                        String newsDateAdded = newsSnapshot.child("newsDateAdded").getValue(String.class);
                        showNotification(newsTitle, newsArticle, newsDateAdded);
                        triggerEmergencyAlert();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

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
        navigationView = findViewById(R.id.navigationView);
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);

    }
    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.END)){
            drawerLayout.closeDrawer(GravityCompat.END);
        }
        else{
            super.onBackPressed();
        }
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
        else{
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }

        // Close the drawer when item is clicked
        item.setCheckable(false);
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

    private void showNotification(String title, String content, String date) {
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);


        //Toast.makeText(this, "Notification Alert Triggered!", Toast.LENGTH_SHORT).show();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "Emergency Alerts", NotificationManager.IMPORTANCE_HIGH);
            channel.setDescription("Channel for emergency alerts");
            notificationManager.createNotificationChannel(channel);
        }

        Intent intent = new Intent(this, Alert_View_Activity.class);
        intent.putExtra("NEWS_TITLE", title);
        intent.putExtra("NEWS_CONTENT", content);
        intent.putExtra("NEWS_DATE", date);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_warning)
                .setContentTitle(title)
                .setContentText(content)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(content))
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        notificationManager.notify(1, builder.build());
    }

    private void triggerEmergencyAlert() {
        // Display a toast message
        //Toast.makeText(this, "Emergency Alert Triggered!", Toast.LENGTH_SHORT).show();
        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        if (vibrator != null && vibrator.hasVibrator()) {
            long[] vibrationPattern = {0, 500, 1000};
            vibrator.vibrate(vibrationPattern, -1);
        }
    }
}