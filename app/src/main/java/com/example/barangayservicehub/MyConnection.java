package com.example.barangayservicehub;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyConnection extends SQLiteOpenHelper {

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

    // remember account

    private static final String Remember_NAME = "Remember_Account";
    private static final String Remember_ID = "Remember_ID";
    private static final String Remember_Username = "Remember_Username";
    private static final String Remember_Password = "Remember_Password";


    public MyConnection(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE "+ TABLE_NAME +
                " ("+ COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_Username + " TEXT, "
                + COLUMN_Email + " TEXT, "
                + COLUMN_Password + " TEXT, "
                + COLUMN_AccountType + " Boolean);";

        db.execSQL(query);
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
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
}
