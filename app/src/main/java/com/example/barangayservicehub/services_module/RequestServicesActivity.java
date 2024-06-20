package com.example.barangayservicehub.services_module;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.barangayservicehub.R;
import com.example.barangayservicehub.connector.Firebase_Connect;
import com.example.barangayservicehub.report_module.ReportAddActivity;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class RequestServicesActivity extends AppCompatActivity {

    TextInputEditText inputName, inputAge, inputAddress;
    TextView viewRequestTitle, requestDate, btnServiceRequest, requestName, requestAge, requestAddress;
    AutoCompleteTextView requestPurpose;

    ImageView btnBack;

    ProgressBar progressBar;

    FrameLayout btnServicesRequest;
    final Firebase_Connect connect;

    public RequestServicesActivity(){

        connect = new Firebase_Connect();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_request_services);

        viewRequestTitle = findViewById(R.id.requestViewTitle);
        requestDate = findViewById(R.id.request_add_date_now);
        String currentDate = getCurrentDate();
        requestDate.setText(currentDate);

        btnServiceRequest = findViewById(R.id.textRequestBtn);

        String requestTitle = getIntent().getStringExtra("SERVICES_TITLE");

        viewRequestTitle.setText(requestTitle);

        btnBack = findViewById(R.id.btnBackArrow);

        progressBar = findViewById(R.id.progressBar);
        progressBar.bringToFront();
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnServicesRequest  = findViewById(R.id.btnRequest);
        btnServicesRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressBar.setVisibility(View.VISIBLE);
                btnServiceRequest.setVisibility(View.INVISIBLE);
                requestServices();
            }
        });

        listPurpose();
    }

    public void requestServices(){

        TextInputLayout layoutRequestName = findViewById(R.id.layout_request_name);
        TextInputLayout layoutRequestAge = findViewById(R.id.layout_request_age);
        TextInputLayout layoutRequestAddress = findViewById(R.id.layout_request_address);
        TextInputLayout layoutRequestPurpose = findViewById(R.id.layout_request_purpose);

        requestName = findViewById(R.id.request_name);
        requestAge  = findViewById(R.id.request_age);
        requestAddress = findViewById(R.id.request_address);
        requestPurpose = findViewById(R.id.selectPurpose);

        String name = requestName.getText().toString();
        String ageText = requestAge.getText().toString().trim();
        String address = requestAddress.getText().toString();
        String purpose = requestPurpose.getText().toString();

        requestName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                layoutRequestName.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        requestAge.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                layoutRequestAge.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        requestAddress.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                layoutRequestAddress.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        if(name.isEmpty()){
            finishActionProgressBar();
            layoutRequestName.setError("* Fill in the blank");
        }else if(ageText.isEmpty()){
            finishActionProgressBar();
            layoutRequestAge.setError("* Fill in the blank");
        } else if (address.isEmpty()) {
            finishActionProgressBar();
            layoutRequestAddress.setError("* Fill in the blank");
        } else if (purpose.isEmpty() || purpose.equals(getString(R.string.select_a_purpose))) {
            finishActionProgressBar();
            layoutRequestPurpose.setError("* Fill in the blank");
        }
        else {

            int age;

            try {
                age = Integer.parseInt(ageText);
                if (age < 10 || age > 100) {
                    finishActionProgressBar();
                    layoutRequestAge.setError("* Age must be between 10 and 100");
                    return;
                }
            }catch (NumberFormatException e){
                finishActionProgressBar();
                layoutRequestAge.setError("* Invalid age format");
                return;
            }

            SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
            String userID = sharedPreferences.getString("userID", "");

            String serviceId = getIntent().getStringExtra("SERVICE_ID");


            boolean result = connect.addServicesRequest(userID, name, age, address, purpose, serviceId);

            if(result){

                finishActionProgressBar();

                Toast.makeText(RequestServicesActivity.this, "Successfully request a service", Toast.LENGTH_SHORT).show();
                finish();

            }else {
                Toast.makeText(RequestServicesActivity.this, "Failed to request service", Toast.LENGTH_SHORT).show();
            }

        }

    }

    public void finishActionProgressBar(){
        progressBar.setVisibility(View.INVISIBLE);
        btnServiceRequest.setVisibility(View.VISIBLE);
    }

    public void listPurpose(){
        String[] purposes = getResources().getStringArray(R.array.purpose_list);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, purposes);

        AutoCompleteTextView autoCompleteTextView = findViewById(R.id.selectPurpose);
        autoCompleteTextView.setAdapter(adapter);
    }

    private String getCurrentDate() {
        // Create a SimpleDateFormat object with desired date format
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

        // Get current date
        String currentDate = sdf.format(new Date());

        return currentDate;
    }
}