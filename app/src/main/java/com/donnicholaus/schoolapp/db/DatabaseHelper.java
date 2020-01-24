package com.donnicholaus.schoolapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static DatabaseHelper databaseHelper;

    private static  final int DATABASE_VERSION = 1;
    private  static final String DATABASE_NAME = DbConfig.DATABASE_NAME;

    private static final String CREATE_TABLE_STUDENTS = "CREATE TABLE " + DbConfig.TABLE_USERS + "("
            + DbConfig.COLUMN_ID + " INT PRIMARY KEY, "
            + DbConfig.COLUMN_REGNO + " TEXT, "
            + DbConfig.COLUMN_FIRSTNAME + " TEXT, "
            + DbConfig.COLUMN_MIDDLENAME + " TEXT, "
            + DbConfig.COLUMN_LASTNAME + " TEXT, "
            + DbConfig.COLUMN_GENDER + " TEXT, "
            + DbConfig.COLUMN_EMAIL + " TEXT, "
            + DbConfig.COLUMN_PHONE + " TEXT, "
            + DbConfig.COLUMN_BIRTHDATE + " TEXT, "
            + DbConfig.COLUMN_ROLE + " TEXT, "
            + DbConfig.COLUMN_PASSWORD + " TEXT, "
            + DbConfig.COLUMN_DEGREE_PROGRAM + " TEXT, "
            + DbConfig.COLUMN_REGION + " TEXT, "
            + DbConfig.COLUMN_DISTRICT + " TEXT, "
            + DbConfig.COLUMN_WARD + " TEXT "
            + ")";



    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME  , null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE_STUDENTS);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + DbConfig.TABLE_USERS);

    }

    public boolean insert(String Firstname, String Middlename, String Lastname,String gender,
                          String email, String phone, String Birthdate, String role,
                          String RegNo, String Password, String DegreeProgram, String region,
                          String district, String ward){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DbConfig.COLUMN_REGNO, RegNo);
        contentValues.put(DbConfig.COLUMN_FIRSTNAME, Firstname);
        contentValues.put(DbConfig.COLUMN_MIDDLENAME, Middlename);
        contentValues.put(DbConfig.COLUMN_LASTNAME, Lastname);
        contentValues.put(DbConfig.COLUMN_GENDER,gender);
        contentValues.put(DbConfig.COLUMN_EMAIL, email);
        contentValues.put(DbConfig.COLUMN_PHONE, phone);
        contentValues.put(DbConfig.COLUMN_BIRTHDATE, Birthdate);
        contentValues.put(DbConfig.COLUMN_ROLE, role);
        contentValues.put(DbConfig.COLUMN_PASSWORD, Password);
        contentValues.put(DbConfig.COLUMN_DEGREE_PROGRAM, DegreeProgram);
        contentValues.put(DbConfig.COLUMN_REGION,region);
        contentValues.put(DbConfig.COLUMN_DISTRICT, district);
        contentValues.put(DbConfig.COLUMN_WARD, ward);

        long ins = db.insert(DbConfig.TABLE_USERS, null, contentValues);
        return ins != -1;
    }

    public String registrationNo(){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM " + DbConfig.TABLE_USERS,null);

        int C = c.getCount();
        c.close();
        db.close();
        return String.valueOf(C);
    }


    public boolean checkAdminExist(){

        String username = "Admin";
        String password = "admin";
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"RegNo"};

        String selection = "RegNo = ? and password = ?";
        String[] selectionArgs = {username, password};

        Cursor cursor = db.query(DbConfig.TABLE_USERS, columns, selection, selectionArgs, null, null, null);

//        Cursor cursor = db.rawQuery("SELECT RegNo, password FROM " + DbConfig.TABLE_USERS +
//                " WHERE RegNo == 'Admin'AND password == 'admin' ", null);

        int count = cursor.getCount();

        cursor.close();
        close();

        return count > 0;
    }


    public String checkUserExist(String regNo, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        String role = null;
        String[] columns = {DbConfig.COLUMN_REGNO, DbConfig.COLUMN_PASSWORD};
        String selection = "RegNo = ? AND password=?";
        String[] selectionArgs = {regNo, password};

        Cursor cursor = db.query(DbConfig.TABLE_USERS, columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();

        if (count > 0) {
            role = cursor.getString(cursor.getColumnIndex(DbConfig.COLUMN_ROLE));
        }

        cursor.close();
        db.close();

        return role;
    }
}
