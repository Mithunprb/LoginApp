package com.example.android.loginapp;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    // creating a constant variables for our database.
    // below variable is for our database name.
    private static final String DB_NAME = "Login";

    private static final int DB_VERSION = 2;

    private static final String TABLE_NAME = "users";

    private static final String TABLE_NAME1 = "form";


    private static final String username = "username";

    private static final String NAME_COL = "name";


    private static final String PASSWORD = "password";

    private static final String DOB = "dob";

    private static final String EMAIL = "eamil";

    private static final String ADDRESS = "address";

    private static final String COLLEGE = "clg";

    private static final String PERCENTAGE = "percentage";

    private static final String ECA = "eca";

    private static final String BLOOGGRP = "bloodgrp";

    private static final String QUALIFICATION = "qualification";

    private static final String VACCINATION = "vaccination";

    private static final String REFRENCEID = "refrenceid";

    private static final String DATE = "date";


    // creating a constructor for our database handler.
    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        // on below line we are creating
        // an sqlite query and we are
        // setting our column names
        // along with their data types.
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + NAME_COL + " TEXT,"
                + username + " TEXT PRIMARY KEY, "
                + PASSWORD + " TEXT)";

        String query1 = "CREATE TABLE " + TABLE_NAME1 + " ("
                + NAME_COL + " TEXT,"
                + username + " TEXT PRIMARY KEY, "
                + DOB + " TEXT,"
                + EMAIL + "TEXT,"
                + ADDRESS + "TEXT,"
                + COLLEGE + "TEXT,"
                + PERCENTAGE + "TEXT,"
                + ECA + "TEXT,"
                + BLOOGGRP + "TEXT,"
                + QUALIFICATION + "TEXT,"
                + VACCINATION + "TEXT,"
                + REFRENCEID + "TEXT,"
                + DATE +"TEXT)";

        // at last we are calling a exec sql
        // method to execute above sql query
        db.execSQL(query);
        db.execSQL(query1);
   // MyDB.execSQL("CREATE TABLE users(username TEXT Primary key , password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    public Boolean insertData(String name, String username, String password) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("name", name);
        contentValues.put("username", username);
        contentValues.put("password", password);

        long result = db.insert("users", null, contentValues);
        db.close();
        return result != -1;
    }
    public Boolean insertForm(String name, String DOB, String EMAIL,
                              String ADDRESS, String COLLEGE, String PERCENTAGE, String ECA,
                              String BLOOGGRP, String QUALIFICATION, String VACCINATION, String REFRENCEID, String DATE) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("dob", DOB);
        contentValues.put("email", EMAIL);
        contentValues.put("address", ADDRESS);
        contentValues.put("clg", COLLEGE);
        contentValues.put("percentage", PERCENTAGE);
        contentValues.put("eca", ECA);
        contentValues.put("bloodgrp", BLOOGGRP);
        contentValues.put("qualification", QUALIFICATION);
        contentValues.put("vaccination", VACCINATION);
        contentValues.put("refrenceid", REFRENCEID);
        contentValues.put("date", DATE);
        db.insert("form", null, contentValues);
        db.close();
        return true;
    }
    public String getRecords(String username){
        String query=("SELECT * FROM form WHERE email = "+ username);
        StringBuilder result= new StringBuilder();
        SQLiteDatabase db=this.getReadableDatabase();
        //	db.q((TABLE_NAME,"id=?",new String[]{id});
        @SuppressLint("Recycle") Cursor cursor=db.rawQuery(query,null);

        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            result.append(cursor.getString(0)).append(" ").append(cursor.getString(1)).append(" ").append(cursor.getString(2)).append("\n");
            cursor.moveToNext();
        }

        db.close();
        return result.toString();
    }

        public Boolean checkUsername(String username){
            SQLiteDatabase MyDB = this.getWritableDatabase();
            @SuppressLint("Recycle") Cursor cursor = MyDB.rawQuery("SELECT * FROM users WHERE username = ?", new String[] {username});
            return cursor.getCount() > 0;
        }
    public Boolean checkusernamepassword(String username, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        @SuppressLint("Recycle") Cursor cursor = MyDB.rawQuery("SELECT * FROM users WHERE username = ? AND password = ?", new String[] {username, password});
        return cursor.getCount() > 0;
    }

}
