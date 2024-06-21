package com.example.barangayservicehub.admin_module;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barangayservicehub.R;
import com.example.barangayservicehub.adapter.NewsAdapter;
import com.example.barangayservicehub.adapter.ServicesRequestAdapter;
import com.example.barangayservicehub.getter_class.Get_News;
import com.example.barangayservicehub.getter_class.Get_Services_Request;
import com.example.barangayservicehub.nav_fargment.NewsActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

public class Admin_View_Services_Request extends AppCompatActivity {

    private ProgressBar progressBar;
    private RecyclerView recyclerRequest;

    private ArrayList<Get_Services_Request> listRequest;
    private DatabaseReference databaseReference;
    private ServicesRequestAdapter servicesRequestAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_admin_view_services_request);

        ImageView btnBack = findViewById(R.id.btnBackArrow);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // for news dashboard
        recyclerRequest = findViewById(R.id.recycleViewServiceRequest);
        databaseReference = FirebaseDatabase.getInstance().getReference("Services_Request");
        listRequest = new ArrayList<>();
        recyclerRequest.setLayoutManager(new LinearLayoutManager(this));
        servicesRequestAdapter =  new ServicesRequestAdapter(this , listRequest);
        recyclerRequest.setAdapter(servicesRequestAdapter);
        progressBar = findViewById(R.id.progressBar);

        progressBar.setVisibility(View.VISIBLE);

        Query query = databaseReference.orderByChild("archive").equalTo(0);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listRequest.clear();

                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                    try{

                        Get_Services_Request request = dataSnapshot.getValue(Get_Services_Request.class);
                        listRequest.add(request);

                    }
                    catch (DatabaseException e){
                        Log.e("Barangay Services Request", "Error parsing User object: " + e.getMessage());
                    }
                }
                Collections.reverse(listRequest);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        servicesRequestAdapter.notifyDataSetChanged();
                        progressBar.setVisibility(View.GONE);
                    }
                }, 1000);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                String errorMessage = "Database error: " + error.getMessage();
                Log.e("Admin_View_Services_Request", errorMessage);
                Toast.makeText(Admin_View_Services_Request.this, errorMessage, Toast.LENGTH_SHORT).show();

            }
        });

    }
}