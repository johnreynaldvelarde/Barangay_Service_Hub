package com.example.barangayservicehub.bottom_fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.barangayservicehub.MainActivity;
import com.example.barangayservicehub.NotificationActivity;
import com.example.barangayservicehub.ProfileActivity;
import com.example.barangayservicehub.R;
import com.example.barangayservicehub.nav_fargment.NewsActivity;
import com.google.android.material.navigation.NavigationView;

public class DashboardFragment extends Fragment {

    private static final String ARG_USERNAME = "username";
    private String username;

    private ImageView drawerToggle;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private boolean isNavigationViewVisible = false;

    private DrawerToggleListener drawerToggleListener;

    private TextView btnViewAll;

    public class Constants {
        public static final String USERNAME_EXTRA = "USERNAME_EXTRA";
    }


    public static DashboardFragment newInstance(String username) {
        DashboardFragment fragment = new DashboardFragment();
        Bundle args = new Bundle();
        args.putString(ARG_USERNAME, username);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            username = getArguments().getString(ARG_USERNAME);
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
    }
}