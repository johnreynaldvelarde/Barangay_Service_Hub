package com.example.barangayservicehub.bottom_fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.barangayservicehub.NotificationActivity;
import com.example.barangayservicehub.R;
import com.example.barangayservicehub.report_module.ReportAddActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CrimeReportFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_crime_report, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FloatingActionButton btnAdd = view.findViewById(R.id.btnAddReport);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ReportAddActivity.class);
                startActivity(intent);
            }
        });
    }
}