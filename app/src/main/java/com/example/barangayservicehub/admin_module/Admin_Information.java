package com.example.barangayservicehub.admin_module;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.barangayservicehub.R;
import com.example.barangayservicehub.connector.Firebase_Connect;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import java.util.Map;

public class Admin_Information extends AppCompatActivity {

    private ImageView btnBack;
    private FrameLayout btnSubmit;

    private TextView nPopulation, nHousehold, nLandmark, nEstablishment;
    TextInputEditText t_population, t_household, t_landmark, t_establishment;

    private DatabaseReference databaseReference;
    final Firebase_Connect connect;

    public Admin_Information(){
        connect = new Firebase_Connect();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_admin_information);


        btnBack = findViewById(R.id.btnBackArrow);
        btnSubmit = findViewById(R.id.btnSubmit);

        nPopulation = findViewById(R.id.numberPopulation);
        nHousehold = findViewById(R.id.numberHousehold);
        nEstablishment = findViewById(R.id.numberEstablishment);
        nLandmark = findViewById(R.id.numberLandmark);

        t_population = findViewById(R.id.i_population);
        t_household = findViewById(R.id.i_household);
        t_establishment = findViewById(R.id.i_establishment);
        t_landmark = findViewById(R.id.i_Landmark);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int population = getInputOrZero(t_population);
                int household = getInputOrZero(t_household);
                int establishment = getInputOrZero(t_establishment);
                int landmark = getInputOrZero(t_landmark);

                boolean result = connect.updateBarangayStats(population, household, establishment, landmark);

                if(result){
                    Toast.makeText(Admin_Information.this, "Barangay Information update successfully", Toast.LENGTH_SHORT).show();
                    finish();
                }else {
                    Toast.makeText(Admin_Information.this, "Barangay Information update error", Toast.LENGTH_SHORT).show();
                }
            }
        });

        fetchBarangayStats();
    }



    // Helper method to get input value or return 0 if empty
    private int getInputOrZero(TextView textView) {
        String inputText = textView.getText().toString().trim();
        return inputText.isEmpty() ? 0 : Integer.parseInt(inputText);
    }

    private void fetchBarangayStats() {

        connect.getBarangayStats(new Firebase_Connect.BarangayStatsCallback() {
            @Override
            public void onBarangayStatsResult(Map<String, String> stats) {

                nPopulation.setText(stats.get("Population"));
                nHousehold.setText(stats.get("Household"));
                nEstablishment.setText(stats.get("Establishment"));
                nLandmark.setText(stats.get("Landmark"));
            }
            @Override
            public void onBarangayStatsError(DatabaseError error) {

                // Handle error
                nPopulation.setText("Error");
                nHousehold.setText("Error");
                nEstablishment.setText("Error");
                nLandmark.setText("Error");
            }
        });
    }
}