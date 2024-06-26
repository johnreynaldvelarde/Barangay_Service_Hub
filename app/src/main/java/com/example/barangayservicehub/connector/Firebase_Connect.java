package com.example.barangayservicehub.connector;

import androidx.annotation.NonNull;

import com.example.barangayservicehub.core.function;
import com.example.barangayservicehub.setter_class.Set_Feedback;
import com.example.barangayservicehub.setter_class.Set_News;
import com.example.barangayservicehub.setter_class.Set_Number;
import com.example.barangayservicehub.setter_class.Set_Program;
import com.example.barangayservicehub.setter_class.Set_Purpose;
import com.example.barangayservicehub.setter_class.Set_Services;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Firebase_Connect {

    private final DatabaseReference mDatabase;

    final function function;
    private boolean exists;

    public Firebase_Connect(){
        mDatabase = FirebaseDatabase.getInstance().getReference();
        function = new function();
    }

    // create a admin automatically

    public void checkAndCreateAdminIfNeeded(){
        DatabaseReference userAccountRef = mDatabase.child("User_Account");
        userAccountRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(!dataSnapshot.exists()){
                    createAdminUser();
                }
                else {

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void createAdminUser() {
        final String adminUsername = "admin";
        final String adminPassword = "admin12345";

        try {
            String userId = mDatabase.child("User_Account").push().getKey();
            User_Account adminUser = new User_Account(adminUsername, "", adminPassword, 1, 1);
            mDatabase.child("User_Account").child(userId).setValue(adminUser);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Register Activity
    public boolean Register(String name, String email, String password, int account_type ){
        try {

            String userId = mDatabase.child("User_Account").push().getKey();
            User_Account register = new User_Account(name, email, password, account_type, 1);
            mDatabase.child("User_Account").child(userId).setValue(register);

            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            return  false;
        }
    }

    // existing username
    public void checkExistingUser(String username, String email, UserNameCheckCallback callback) {
        DatabaseReference userAccountRef = mDatabase.child("User_Account");
        Query query = userAccountRef.orderByChild("name").equalTo(username);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                boolean exists = dataSnapshot.exists();
                callback.onUserNameCheckResult(exists);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                callback.onUserNameCheckError(error);
            }
        });
    }

    // existing email
    public void checkExistingEmail(String email, EmailCheckCallback callback) {
        DatabaseReference userAccountRef = mDatabase.child("User_Account");
        Query query = userAccountRef.orderByChild("email").equalTo(email);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                boolean exists = dataSnapshot.exists();
                callback.onEmailCheckResult(exists);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                callback.onEmailCheckError(error);
            }
        });
    }

    public interface UserNameCheckCallback {
        void onUserNameCheckResult(boolean exists);
        void onUserNameCheckError(DatabaseError error);
    }

    public interface EmailCheckCallback {
        void onEmailCheckResult(boolean exists);
        void onEmailCheckError(DatabaseError error);
    }


    // -- MODULE --
    // CRIME REPORT MODULE / add a crime report
    public boolean addCrimeReport(String userID, String title, String location, String comment, String crimeImageURL){
        try{

            //Date dateNow = new Date();

            // Format the date and time
            //SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String formattedDate = function.formatDate();

            String reportId = mDatabase.child("Crime_Report").push().getKey();

            Crime_Report add_report = new Crime_Report(userID, title, location, comment, formattedDate, crimeImageURL, 0);

            mDatabase.child("Crime_Report").child(reportId).setValue(add_report);

            return  true;
        }
        catch (Exception e) {
            e.printStackTrace();
            return  false;
        }
    }

    // ADD NEWS MODULE

    public  boolean addBarangayNews(String userID, String title, String article, String newsImageURL, int isImportant){
        try {

            String formattedDate = function.formatDate();

            String newsId = mDatabase.child("Barangay_News").push().getKey();

            Set_News add_news = new Set_News(userID, title, article, newsImageURL, formattedDate, 0, isImportant, 0 );

            mDatabase.child("Barangay_News").child(newsId).setValue(add_news);

            return true;

        }catch (Exception e) {
            e.printStackTrace();
            return  false;
        }
    }

    // add program

    public boolean addPrograms(String programName, String programDescription){

        try {

            String programId = mDatabase.child("Barangay_Program").push().getKey();

            Set_Program add_program = new Set_Program(programName, programDescription, 0);

            mDatabase.child("Barangay_Program").child(programId).setValue(add_program);

            return true;

        }catch (Exception e) {
            e.printStackTrace();
            return  false;
        }
    }

    // add purpose
    public boolean addPurpose(String purposeName){
        try{
            String formattedDate = function.formatDate();

            String purposeID = mDatabase.child("Purpose").push().getKey();

            Set_Purpose add_purpose = new Set_Purpose(purposeName, formattedDate, 0);

            mDatabase.child("Purpose").child(purposeID).setValue(add_purpose);

            return true;

        }catch (Exception e) {
            e.printStackTrace();
            return  false;
        }
    }

    // add services
    public boolean addServices(String servicesName, String purposeID){
        try{

            String formattedDate = function.formatDate();

            String servicesID = mDatabase.child("Barangay_Services").push().getKey();

            Set_Services add_services = new Set_Services(servicesName, formattedDate, purposeID, 0);

            mDatabase.child("Barangay_Services").child(servicesID).setValue(add_services);

            return true;

        }catch (Exception e) {
            e.printStackTrace();
            return  false;
        }
    }

    // add emergency number
    public boolean addCallNumber(String name, String number){
        try{

            String numberID = mDatabase.child("Barangay_Call_Number").push().getKey();

            Set_Number add_number = new Set_Number(name, number, 0);

            mDatabase.child("Barangay_Call_Number").child(numberID).setValue(add_number);

            return true;

        }catch (Exception e) {
            e.printStackTrace();
            return  false;
        }
    }

    // add User Request in Services
    public boolean addServicesRequest(String userID, String name, int age, String address, String purpose, String serviceID ){
        try {

            Date dateNow = new Date();
            // Format the date and time
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String formattedDate = dateFormat.format(dateNow);

            String requestID  = mDatabase.child("Services_Request").push().getKey();

            Service_Request add_request = new Service_Request(userID, serviceID, name, address, formattedDate, purpose, age, 0, 0);

            mDatabase.child("Services_Request").child(requestID).setValue(add_request);

            return true;

        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // add Feeback and Review
    public boolean addFeedbackReview(String userID, String title, String rating, String comment){

        try {

            Date dateNow = new Date();
            // Format the date and time
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String formattedDate = dateFormat.format(dateNow);

            String feedbackID  = mDatabase.child("Feedback_Review").push().getKey();

            Set_Feedback add_request = new Set_Feedback(userID, title,rating, comment ,formattedDate, 0);

            mDatabase.child("Feedback_Review").child(feedbackID).setValue(add_request);

            return true;

        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // show a crime report in recycle view

    // ADMIN MODULE

    public boolean updateBarangayStats(int Population, int Household, int Establishment, int Landmark){
        try {
            DatabaseReference statsRef = mDatabase.child("Barangay_Stats");

            statsRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    Map<String, Object> statsUpdates = new HashMap<>();

                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        String key = snapshot.getKey();
                        String statsName = snapshot.child("statsName").getValue(String.class);

                        int currentStatsNumber = Integer.parseInt(snapshot.child("statsNumber").getValue(String.class));
                        int updatedStatsNumber = currentStatsNumber;

                        if ("Population".equals(statsName)) {
                            updatedStatsNumber += Population;
                        } else if ("Household".equals(statsName)) {
                            updatedStatsNumber += Household;
                        } else if ("Establishment".equals(statsName)) {
                            updatedStatsNumber += Establishment;
                        } else if ("Landmark".equals(statsName)) {
                            updatedStatsNumber += Landmark;
                        }

                        statsUpdates.put(key + "/statsNumber", String.valueOf(updatedStatsNumber));
                    }

                    statsRef.updateChildren(statsUpdates);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    // Handle error
                }
            });

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }




    // Method to retrieve Barangay stats
    public void getBarangayStats(final BarangayStatsCallback callback) {
        DatabaseReference statsRef = mDatabase.child("Barangay_Stats");
        statsRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Map<String, String> stats = new HashMap<>();
                for (DataSnapshot statSnapshot : dataSnapshot.getChildren()) {
                    String statsName = statSnapshot.child("statsName").getValue(String.class);
                    String statsNumber = statSnapshot.child("statsNumber").getValue(String.class);
                    stats.put(statsName, statsNumber);
                }
                callback.onBarangayStatsResult(stats);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                callback.onBarangayStatsError(databaseError);
            }
        });
    }

    public interface BarangayStatsCallback {
        void onBarangayStatsResult(Map<String, String> stats);
        void onBarangayStatsError(DatabaseError error);
    }

}
