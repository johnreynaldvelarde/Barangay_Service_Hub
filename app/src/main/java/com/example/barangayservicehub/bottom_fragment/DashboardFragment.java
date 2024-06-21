package com.example.barangayservicehub.bottom_fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.barangayservicehub.MainActivity;
import com.example.barangayservicehub.NotificationActivity;
import com.example.barangayservicehub.ProfileActivity;
import com.example.barangayservicehub.R;
import com.example.barangayservicehub.adapter.BarangayStatsAdapter;
import com.example.barangayservicehub.adapter.CrimeReportAdapter;
import com.example.barangayservicehub.adapter.NewsAdapter;
import com.example.barangayservicehub.getter_class.Get_CrimeReport;
import com.example.barangayservicehub.getter_class.Get_News;
import com.example.barangayservicehub.getter_class.Get_Stats;
import com.example.barangayservicehub.module.NewsViewActivity;
import com.example.barangayservicehub.nav_fargment.NewsActivity;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

public class DashboardFragment extends Fragment {

    private static final String ARG_USERNAME = "username";
    private String username;
    private String email;

    private ImageView drawerToggle;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private boolean isNavigationViewVisible = false;

    private DrawerToggleListener drawerToggleListener;

    private TextView btnViewAll;

    private ProgressBar progressBarNews, progressBarStats;

    private RecyclerView recyclerStats, recyclerNews;
    private ArrayList<Get_News> listNews;
    private ArrayList<Get_Stats> listStats;
    private DatabaseReference databaseReference;
    private BarangayStatsAdapter barangayStatsAdapter;
    private NewsAdapter newsAdapter;

    public class Constants {
        public static final String USERNAME_EXTRA = "USERNAME_EXTRA";
        public static final String EMAIL_EXTRA = "EMAIL_EXTRA";
    }

    public static DashboardFragment newInstance(String username) {
        DashboardFragment fragment = new DashboardFragment();
        Bundle args = new Bundle();
        args.putString(ARG_USERNAME, username);
        //args.putString(Constants.EMAIL_EXTRA, ;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            username = getArguments().getString(ARG_USERNAME);
            email = getArguments().getString(Constants.EMAIL_EXTRA);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_dashboard, container, false);
        View rootView = inflater.inflate(R.layout.fragment_dashboard, container, false);

        // Now you can use the username in your fragment UI
        TextView textView = rootView.findViewById(R.id.upper_account_name);
        textView.setText(username);


        // calling the drawer in main activity
        drawerToggle = rootView.findViewById(R.id.drawerToggle);
        drawerToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleDrawer();
            }
        });
        return rootView;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            drawerToggleListener = (DrawerToggleListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement DrawerToggleListener");
        }
    }

    private void toggleDrawer() {
        drawerToggleListener.toggleDrawer();
    }

    public interface DrawerToggleListener {
        boolean onNavigationItemSelected(@NonNull MenuItem item);

        void toggleDrawer();
    }

    private void toggleNavigationView() {
        if (isNavigationViewVisible) {
            navigationView.setVisibility(View.GONE);
        } else {
            navigationView.setVisibility(View.VISIBLE);
        }
        isNavigationViewVisible = !isNavigationViewVisible;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageView profileImage = view.findViewById(R.id.mainProfile);
        ImageView btnNotify = view.findViewById(R.id.btnNotification);
        ImageView btnNewsView = view.findViewById(R.id.btnNewsView);
        btnViewAll = view.findViewById(R.id.btnViewall);

        // click profile
        profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ProfileActivity.class);
                intent.putExtra(Constants.USERNAME_EXTRA, username);
                startActivity(intent);
            }
        });

        // click notifcation bell
        btnNotify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), NotificationActivity.class);
                startActivity(intent);
            }
        });

        // click view all --> news activity
        btnViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), NewsActivity.class);
                startActivity(intent);
            }
        });

        // for barangay stats
        recyclerStats = view.findViewById(R.id.recycleViewStats);
        databaseReference = FirebaseDatabase.getInstance().getReference("Barangay_Stats");
        listStats = new ArrayList<>();
        recyclerStats.setLayoutManager(new LinearLayoutManager(getContext()));
        barangayStatsAdapter =  new BarangayStatsAdapter(getContext() , listStats);
        recyclerStats.setAdapter(barangayStatsAdapter);
        progressBarStats = view.findViewById(R.id.progressBarStats);

        progressBarStats.setVisibility(View.VISIBLE);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                listStats.clear();
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                    try{

                        Get_Stats stats = dataSnapshot.getValue(Get_Stats.class);
                        listStats.add(stats);

                    }
                    catch (DatabaseException e){
                        Log.e("Barangay Stats", "Error parsing User object: " + e.getMessage());
                    }
                }
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        barangayStatsAdapter.notifyDataSetChanged();
                        progressBarStats.setVisibility(View.GONE);
                    }
                }, 500);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                String errorMessage = "Database error: " + error.getMessage();
                Log.e("DashboardFragment", errorMessage);
                Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show();

            }
        });



        // for news dashboard
        recyclerNews = view.findViewById(R.id.recycleViewNews);
        databaseReference = FirebaseDatabase.getInstance().getReference("Barangay_News");
        listNews = new ArrayList<>();
        recyclerNews.setLayoutManager(new LinearLayoutManager(getContext()));
        newsAdapter =  new NewsAdapter(getContext() , listNews);
        recyclerNews.setAdapter(newsAdapter);
        progressBarNews = view.findViewById(R.id.progressBarNews);

        progressBarNews.setVisibility(View.VISIBLE);

        Query query = databaseReference.orderByChild("newsStatus").equalTo(0).limitToLast(100);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                listNews.clear();

                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                    try{

                        Get_News news = dataSnapshot.getValue(Get_News.class);
                        listNews.add(news);

                    }
                    catch (DatabaseException e){
                        Log.e("Barangay News", "Error parsing User object: " + e.getMessage());
                    }
                }
                Collections.reverse(listNews);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        newsAdapter.notifyDataSetChanged();
                        progressBarNews.setVisibility(View.GONE);
                    }
                }, 1000);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Log.e("Barangay News", "Error fetching data: " + error.getMessage());
                String errorMessage = "Database error: " + error.getMessage();
                Log.e("DashboardFragment", errorMessage);
                Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show();

            }
        });
    }
}