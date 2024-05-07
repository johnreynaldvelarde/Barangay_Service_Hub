package com.example.barangayservicehub.nav_fargment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
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
import com.example.barangayservicehub.adapter.CrimeReportAdapter;
import com.example.barangayservicehub.connector.Crime_Report;
import com.example.barangayservicehub.connector.MyAdapter;
import com.example.barangayservicehub.connector.User;
import com.example.barangayservicehub.getter_class.Get_CrimeReport;
import com.example.barangayservicehub.report_module.ReportAddActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class NewsActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Get_CrimeReport> list;
    DatabaseReference databaseReference;
    CrimeReportAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_news);

        ImageView btnBack = findViewById(R.id.btnBackArrow);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



        databaseReference = FirebaseDatabase.getInstance().getReference("Crime_Report");

        recyclerView = findViewById(R.id.recycleViewNews);
        list = new ArrayList<>();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CrimeReportAdapter(this, list);
        recyclerView.setAdapter(adapter);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                list.clear();
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    try {
                        Get_CrimeReport report = dataSnapshot.getValue(Get_CrimeReport.class);
                        if (report != null) {
                            list.add(report);
                        }
                    } catch (DatabaseException e) {
                        Log.e("NewsActivity", "Error parsing User object: " + e.getMessage());
                    }
                }
                adapter.notifyDataSetChanged();

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText( NewsActivity.this, "User object is null", Toast.LENGTH_SHORT).show();
            }
        });


    }
}