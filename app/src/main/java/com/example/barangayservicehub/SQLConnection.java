package com.example.barangayservicehub;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class SQLConnection extends SQLiteOpenHelper {

    private  Context context;
    private static final String DATABASE_NAME ="Barangay.db";
    private static final int DATABASE_VERSION = 1;

    // creating user account
    private static final String TABLE_NAME = "User_Account";
    private static final String COLUMN_ID = "ID";
    private static final String COLUMN_Username = "Username";
    private static final String COLUMN_Email = "Email";
    private static final String COLUMN_Password = "Password";
    private static final String COLUMN_AccountType = "Account_Type";


    // Creating barangay stats table
    private static final String TABLE_NAME_BARANGAY_STATS = "Barangay_Stats";
    private static final String COLUMN_Population = "Population";
    private static final String COLUMN_Household = "Household";
    private static final String COLUMN_Establishment = "Establishment";
    private static final String COLUMN_Landmark = "Landmark";




    // remember account
    private static final String Remember_NAME = "Remember_Account";
    private static final String Remember_ID = "Remember_ID";
    private static final String Remember_Username = "Remember_Username";
    private static final String Remember_Password = "Remember_Password";


    public SQLConnection(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // for create account
        String createUserAccountTable = "CREATE TABLE "+ TABLE_NAME +
                " ("+ COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_Username + " TEXT, "
                + COLUMN_Email + " TEXT, "
                + COLUMN_Password + " TEXT, "
                + COLUMN_AccountType + " Boolean);";


        // for barangay stats
        String createBarangayStatsTable = "CREATE TABLE " + TABLE_NAME_BARANGAY_STATS +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_Population + " INTEGER, "
                + COLUMN_Household + " INTEGER, "
                + COLUMN_Establishment + " INTEGER, "
                + COLUMN_Landmark + " TEXT);";

        db.execSQL(createUserAccountTable);
        db.execSQL(createBarangayStatsTable);
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_BARANGAY_STATS);
        onCreate(db);
    }


    // check when the table user account if its empty
    boolean userAccountEmpty(){
        String query = " SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        boolean isEmpty = true;

        if(db !=null){
            cursor = db.rawQuery(query, null);

            if(cursor !=null){
                isEmpty = cursor.getCount() == 0;
                cursor.close();
            }
        }
        return  isEmpty;
    }



    // login user account in database
    Cursor loginUserAccount(String enterUsername, String enterPassword){
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns ={COLUMN_Username, COLUMN_Password, COLUMN_AccountType};

        String selection = COLUMN_Username + " = ? AND " + COLUMN_Password + " = ? AND " + COLUMN_AccountType + " = ?";

        String[] selectionArgs = {enterUsername, enterPassword, "0"};

        return  db.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null);

    }


    Cursor readTableUserAccount(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db !=null){
            cursor = db.rawQuery(query, null);
        }
        return  cursor;
    }



    // add user account in database
    public void addRegisterUser(String username, String email, String password, Boolean accountType){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_Username, username);
        cv.put(COLUMN_Email, email);
        cv.put(COLUMN_Password, password);
        cv.put(COLUMN_AccountType, accountType);

        long result = db.insert(TABLE_NAME,null, cv);
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context, "Added Successfully", Toast.LENGTH_SHORT).show();
        }
    }

    // Add barangay stats in database
    public void addBarangayStats(int population, int household, int establishment, String landmark) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_Population, population);
        cv.put(COLUMN_Household, household);
        cv.put(COLUMN_Establishment, establishment);
        cv.put(COLUMN_Landmark, landmark);
        long result = db.insert(TABLE_NAME_BARANGAY_STATS, null, cv);
        if (result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Added Successfully", Toast.LENGTH_SHORT).show();
        }
    }





    // all read

    // Read all barangay stats
    Cursor readTableBarangayStats() {
        String query = "SELECT * FROM " + TABLE_NAME_BARANGAY_STATS;
        SQLiteDatabase db = this.getReadableDatabase();
        return db != null ? db.rawQuery(query, null) : null;
    }
}
