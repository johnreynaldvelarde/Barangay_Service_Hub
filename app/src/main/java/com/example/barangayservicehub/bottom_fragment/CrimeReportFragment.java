package com.example.barangayservicehub.bottom_fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.barangayservicehub.NotificationActivity;
import com.example.barangayservicehub.R;
import com.example.barangayservicehub.adapter.CrimeReportAdapter;
import com.example.barangayservicehub.connector.Crime_Report;
import com.example.barangayservicehub.connector.MyAdapter;
import com.example.barangayservicehub.getter_class.Get_CrimeReport;
import com.example.barangayservicehub.report_module.ReportAddActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CrimeReportFragment extends Fragment {

    RecyclerView recyclerView;
    ArrayList<Get_CrimeReport> list;
    DatabaseReference databaseReference;
    CrimeReportAdapter adapter;

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


        recyclerView = view.findViewById(R.id.recycleViewReport);
        databaseReference = FirebaseDatabase.getInstance().getReference("Crime_Report");
        list = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new CrimeReportAdapter(getContext() , list);
        recyclerView.setAdapter(adapter);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                list.clear();

                for(DataSnapshot dataSnapshot: snapshot.getChildren()){

                    try{
                        Get_CrimeReport report = dataSnapshot.getValue(Get_CrimeReport.class);
                        if (report != null) {
                            list.add(report);
                        }
                    }
                    catch (DatabaseException e) {
                        Log.e("NewsActivity", "Error parsing User object: " + e.getMessage());
                    }

                }
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                String errorMessage = "Database error: " + error.getMessage();
                Log.e("CrimeReportFragment", errorMessage);
                Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }
}