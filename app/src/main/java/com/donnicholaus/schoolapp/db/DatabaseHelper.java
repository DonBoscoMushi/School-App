package com.donnicholaus.schoolapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;


import com.donnicholaus.schoolapp.UserModal;
import com.donnicholaus.schoolapp.student.Student;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static DatabaseHelper db;

    private static  final int DATABASE_VERSION = 1;
    private  static final String DATABASE_NAME = DbConfig.DATABASE_NAME;
    Location location = new Location();

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

    private static final String addAdmin = "INSERT INTO " +  DbConfig.TABLE_USERS + " ("
            + DbConfig.COLUMN_REGNO+ ", " + DbConfig.COLUMN_FIRSTNAME + ", " + DbConfig.COLUMN_MIDDLENAME
            + ", " + DbConfig.COLUMN_LASTNAME + ", " + DbConfig.COLUMN_GENDER + ", " + DbConfig.COLUMN_EMAIL
            + ", " + DbConfig.COLUMN_PHONE + ", " + DbConfig.COLUMN_BIRTHDATE + ", " + DbConfig.COLUMN_ROLE
            + ", " + DbConfig.COLUMN_PASSWORD + ", " + DbConfig.COLUMN_DEGREE_PROGRAM + ", " + DbConfig.COLUMN_REGION
            + ", " + DbConfig.COLUMN_DISTRICT + ", " + DbConfig.COLUMN_WARD + " ) " +
            "VALUES ('Admin', null, null, null, null, null, null, null, 'Admin', 'admin', null, null, null, null)";

    private static final String CREATE_SUBJECT_TABLE = "CREATE TABLE " + DbConfig.TABLE_SUBJECT + "("
            + DbConfig.SUBJECT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + DbConfig.SUBJECT_NAME + " TEXT NOT NULL, "
            + DbConfig.SUBJECT_CODE + " TEXT NOT NULL UNIQUE, "
            + DbConfig.SUBJECT_CREDIT + " REAL" //nullable
            + ")";

    //public static final String AdminQuery = "";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME  , null, DATABASE_VERSION);
    }



    public static synchronized DatabaseHelper getInstance(Context context){
        if(db == null){
            db= new DatabaseHelper(context);
        }
        return db;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE_STUDENTS);
        db.execSQL(addAdmin);
        db.execSQL(CREATE_SUBJECT_TABLE);
        db.execSQL(location.createRegionTable);
        db.execSQL(location.createDistrictTable);
        db.execSQL(location.createWardTable);
        db.execSQL(location.insertRegions);
        db.execSQL(location.insertDistricts);
//        db.execSQL(location.insertWardsGroup1);
//        db.execSQL(location.insertWardsGroup2);

        //db.close();

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + DbConfig.TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + DbConfig.TABLE_SUBJECT);
        db.execSQL("DROP TABLE IF EXISTS districts");
        db.execSQL("DROP TABLE IF EXISTS regions");
        db.execSQL("DROP TABLE IF EXISTS wards");

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


//    public boolean checkAdminExist(){
//
//        String username = "Admin";
//        String password = "admin";
//        SQLiteDatabase db = this.getReadableDatabase();
//        String[] columns = {"RegNo"};
//
//        String selection = "RegNo = ? and password = ?";
//        String[] selectionArgs = {username, password};
//
//        Cursor cursor = db.query(DbConfig.TABLE_USERS, columns, selection, selectionArgs, null, null, null);
//
////        Cursor cursor = db.rawQuery("SELECT RegNo, password FROM " + DbConfig.TABLE_USERS +
////                " WHERE RegNo == 'Admin'AND password == 'admin' ", null);
//
//        int count = cursor.getCount();
//
//        cursor.close();
//        Log.d("Error", String.valueOf(count));
//        close();
//
//        if(count > 0){
//            return true;
//        } else {
//            return false;
//        }
//    }



    public String checkUserExist(String regNo, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        String role;
        //String[] columns = {DbConfig.COLUMN_REGNO, DbConfig.COLUMN_PASSWORD};
        String selection = "RegNo = ? AND password = ?";
        String[] selectionArgs = {regNo, password};

        Cursor cursor = db.query(DbConfig.TABLE_USERS,  null, selection, selectionArgs, null, null, null);

        if(cursor.moveToFirst()){
            role = cursor.getString(cursor.getColumnIndexOrThrow(DbConfig.COLUMN_ROLE));

            //Get Other Details
            String regno, firstname, middlename, lastname, email,phone, birthdate, degreeProgram;

            regno = cursor.getString(cursor.getColumnIndexOrThrow(DbConfig.COLUMN_REGNO));
            firstname = cursor.getString(cursor.getColumnIndexOrThrow(DbConfig.COLUMN_FIRSTNAME));
            middlename = cursor.getString(cursor.getColumnIndexOrThrow(DbConfig.COLUMN_MIDDLENAME));
            lastname = cursor.getString(cursor.getColumnIndexOrThrow(DbConfig.COLUMN_LASTNAME));
            email = cursor.getString(cursor.getColumnIndexOrThrow(DbConfig.COLUMN_EMAIL));
            phone = cursor.getString(cursor.getColumnIndexOrThrow(DbConfig.COLUMN_PHONE));
            birthdate = cursor.getString(cursor.getColumnIndexOrThrow(DbConfig.COLUMN_BIRTHDATE));
            degreeProgram = cursor.getString(cursor.getColumnIndexOrThrow(DbConfig.COLUMN_DEGREE_PROGRAM));

            UserModal.setRegNo(regno);
            UserModal.setFirstname(firstname);
            UserModal.setMiddlename(middlename);
            UserModal.setLastname(lastname);
            UserModal.setEmail(email);
            UserModal.setPhone(phone);
            UserModal.setBirthdate(birthdate);
            UserModal.setDegreeProg(degreeProgram);

        }else{
            role = "";
        }

        cursor.close();
        db.close();

        return role;
    }

    public List<String> getRegions(){
        List<String> regionsList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM regions";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                regionsList.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return regionsList;
    }

    public List<String> getDistricts(String regionName){
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = "region_name=?";
        Cursor regionCode = db.query("regions", new String[]{"region_code",}, selection, new  String[] {regionName}, null,null, null);
        regionCode.moveToFirst();
        int region_code = regionCode.getInt(0);
        List<String> districtList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM districts WHERE region_code="+region_code;
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                districtList.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return districtList;
    }

    public List<String> getWards(String districtName){
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = "district_name=?";
        Cursor regionCode = db.query("districts", new String[]{"district_code"}, selection, new  String[] {districtName}, null,null, null);
        regionCode.moveToFirst();
        int district_code = regionCode.getInt(0);
        List<String> wardsList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM wards WHERE district_code="+district_code;
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                wardsList.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return wardsList;
    }

    public int wardCode(String wardName) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = "ward_name=?";
        Cursor wardCursor = db.query("wards", new String[]{"ward_code"}, selection, new String[]{wardName}, null, null, null);
        wardCursor.moveToFirst();
        int ward_code = wardCursor.getInt(0);
        return ward_code;

    }

}
