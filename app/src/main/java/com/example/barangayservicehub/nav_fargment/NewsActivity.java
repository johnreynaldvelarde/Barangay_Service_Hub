package com.example.barangayservicehub.nav_fargment;

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
import com.example.barangayservicehub.adapter.CrimeReportAdapter;
import com.example.barangayservicehub.adapter.NewsAdapter;
import com.example.barangayservicehub.connector.Crime_Report;
import com.example.barangayservicehub.connector.MyAdapter;
import com.example.barangayservicehub.connector.User;
import com.example.barangayservicehub.getter_class.Get_CrimeReport;
import com.example.barangayservicehub.getter_class.Get_News;
import com.example.barangayservicehub.report_module.ReportAddActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

public class NewsActivity extends AppCompatActivity {

    private ProgressBar progressBarNews;
    private RecyclerView recyclerNews;

    private ArrayList<Get_News> listNews;
    private DatabaseReference databaseReference;
    private NewsAdapter newsAdapter;

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

        // for news dashboard
        recyclerNews = findViewById(R.id.recycleViewNews);
        databaseReference = FirebaseDatabase.getInstance().getReference("Barangay_News");
        listNews = new ArrayList<>();
        recyclerNews.setLayoutManager(new LinearLayoutManager(this));
        newsAdapter =  new NewsAdapter(this , listNews);
        recyclerNews.setAdapter(newsAdapter);
        progressBarNews = findViewById(R.id.progressBarNews);

        progressBarNews.setVisibility(View.VISIBLE);

        Query query = databaseReference.orderByChild("newsStatus").equalTo(0);

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

                String errorMessage = "Database error: " + error.getMessage();
                Log.e("DashboardFragment", errorMessage);
                Toast.makeText(NewsActivity.this, errorMessage, Toast.LENGTH_SHORT).show();

            }
        });

        /*

        databaseReference = FirebaseDatabase.getInstance().getReference("users");

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

         */
    }
}