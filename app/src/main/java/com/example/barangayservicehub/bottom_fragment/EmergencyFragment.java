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
import com.example.barangayservicehub.adapter.CallAdapter;
import com.example.barangayservicehub.getter_class.Get_Call;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class EmergencyFragment extends Fragment {

    private RecyclerView recyclerCall;
    private ArrayList<Get_Call> listCall;
    private DatabaseReference databaseReference;
    private CallAdapter callAdapter;
    private ProgressBar progressBarCall;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_emergency, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // for call
        recyclerCall = view.findViewById(R.id.recycleViewCall);
        databaseReference = FirebaseDatabase.getInstance().getReference("Barangay_Call_Number");
        listCall = new ArrayList<>();
        recyclerCall.setLayoutManager(new LinearLayoutManager(getContext()));
        callAdapter =  new CallAdapter(getContext() , listCall);
        recyclerCall.setAdapter(callAdapter);
        progressBarCall = view.findViewById(R.id.progressBarCall);

        progressBarCall.setVisibility(View.VISIBLE);

        Query query = databaseReference.orderByChild("availableStatus").equalTo("0");

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                listCall.clear();

                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                    try{

                        Get_Call call = dataSnapshot.getValue(Get_Call.class);
                        listCall.add(call);

                    }
                    catch (DatabaseException e){
                        Log.e("Barangay Emergency", "Error parsing User object: " + e.getMessage());
                    }
                }
                //Collections.reverse(listNews);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        callAdapter.notifyDataSetChanged();
                        progressBarCall.setVisibility(View.GONE);
                    }
                }, 500);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                String errorMessage = "Database error: " + error.getMessage();
                Log.e("EmergencyFragment", errorMessage);
                Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show();

            }
        });

    }
}