package com.example.barangayservicehub.bottom_fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.barangayservicehub.R;
import com.example.barangayservicehub.adapter.ServicesAdapter;
import com.example.barangayservicehub.getter_class.Get_Services;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ServicesFragment extends Fragment {

    private RecyclerView recyclerService;
    private ArrayList<Get_Services> listService;
    private DatabaseReference databaseReference;
    private ServicesAdapter serviceAdapter;
    private ProgressBar progressBarServices;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_programs, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // for services
        recyclerService = view.findViewById(R.id.recycleViewServices);
        databaseReference = FirebaseDatabase.getInstance().getReference("Barangay_Service");
        listService = new ArrayList<>();
        recyclerService.setLayoutManager(new LinearLayoutManager(getContext()));
        serviceAdapter =  new ServicesAdapter(getContext() , listService);
        recyclerService.setAdapter(serviceAdapter);
        progressBarServices = view.findViewById(R.id.progressBarServices);

        progressBarServices.setVisibility(View.VISIBLE);

        Query query = databaseReference.orderByChild("availableStatus").equalTo("0");

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                listService.clear();

                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                    try{

                        Get_Services services = dataSnapshot.getValue(Get_Services.class);
                        listService.add(services);

                    }
                    catch (DatabaseException e){
                        Log.e("Barangay Services", "Error parsing User object: " + e.getMessage());
                    }
                }
                //Collections.reverse(listNews);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        serviceAdapter.notifyDataSetChanged();
                        progressBarServices.setVisibility(View.GONE);
                    }
                }, 500);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                String errorMessage = "Database error: " + error.getMessage();
                Log.e("ServicesFragment", errorMessage);
                Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show();

            }
        });

    }
}