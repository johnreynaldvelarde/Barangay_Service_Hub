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
import com.example.barangayservicehub.adapter.RequestFileAdapter;
import com.example.barangayservicehub.getter_class.Get_RequestFile;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class FileRequestFragment extends Fragment {

    private RecyclerView recyclerFile;
    private ArrayList<Get_RequestFile> listFile;
    private DatabaseReference databaseReference;
    private RequestFileAdapter fileAdapter;
    private ProgressBar progressBarFile;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_file_request, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // for request file view
        recyclerFile = view.findViewById(R.id.recycleViewFile);
        databaseReference = FirebaseDatabase.getInstance().getReference("Barangay_Services");
        listFile = new ArrayList<>();
        recyclerFile.setLayoutManager(new LinearLayoutManager(getContext()));
        fileAdapter =  new RequestFileAdapter(getContext() , listFile);
        recyclerFile.setAdapter(fileAdapter);
        progressBarFile = view.findViewById(R.id.progressBarFile);

        progressBarFile.setVisibility(View.VISIBLE);

        Query query = databaseReference.orderByChild("availableStatus").equalTo("0");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                listFile.clear();

                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                    try{

                        String id = dataSnapshot.getKey();
                        //String availableStatus = dataSnapshot.child("availableStatus").getValue(String.class);
                        //String fileName = dataSnapshot.child("fileName").getValue(String.class);

                        Get_RequestFile file = dataSnapshot.getValue(Get_RequestFile.class);
                        listFile.add(file);

                    }
                    catch (DatabaseException e){
                        Log.e("Barangay Request File", "Error parsing User object: " + e.getMessage());
                    }
                }
                //Collections.reverse(listNews);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        fileAdapter.notifyDataSetChanged();
                        progressBarFile.setVisibility(View.GONE);
                    }
                }, 500);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                String errorMessage = "Database error: " + error.getMessage();
                Log.e("RequestFileFragment", errorMessage);
                Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }
}