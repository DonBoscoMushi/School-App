package com.donnicholaus.schoolapp.student;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.donnicholaus.schoolapp.R;
import com.donnicholaus.schoolapp.UserModal;


public class Student extends AppCompatActivity {
    TextView regNo, firstname, middlename, lastname, email, phone, birthdate, degree_prog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);


        regNo = findViewById(R.id.s_regNo);
        firstname = findViewById(R.id.s_firstname);
        middlename = findViewById(R.id.s_middlename);
        lastname = findViewById(R.id.s_lastname);
        email = findViewById(R.id.s_email);
        phone = findViewById(R.id.s_phone);
        birthdate = findViewById(R.id.s_birthdate);
        degree_prog = findViewById(R.id.s_degree_prog);

        regNo.setText(UserModal.getRegNo());
        firstname.setText(UserModal.getFirstname());
        middlename.setText(UserModal.getMiddlename());
        lastname.setText(UserModal.getLastname());
        email.setText(UserModal.getEmail());
        phone.setText(String.valueOf(UserModal.getPhone()));
        birthdate.setText(UserModal.getBirthdate());
        degree_prog.setText(UserModal.getDegreeProg());

    }

//
//    public static void studentData(String regno, String firstname, String middlename,
//                                   String lastname, String email, double phone, String birthdate, String degreeProgram) {
//
//        regNo.setText(regno);
//        _firstname.setText(firstname);
//        _middlename.setText(middlename);
//        _lastname.setText(lastname);
//        _email.setText(email);
//        _phone.setText(String.valueOf(phone));
//        _birthdate.setText(birthdate);
//        degree_prog.setText(degreeProgram);
//
//    }

//    public static Cursor cursorData(Cursor cursor){
//        String regno, firstname, middlename, lastname, email, birthdate, degreeProgram;
//        double phone;
//
//        regno = cursor.getString(cursor.getColumnIndexOrThrow(DbConfig.COLUMN_REGNO));
//        firstname = cursor.getString(cursor.getColumnIndexOrThrow(DbConfig.COLUMN_FIRSTNAME));
//        middlename = cursor.getString(cursor.getColumnIndexOrThrow(DbConfig.COLUMN_MIDDLENAME));
//        lastname = cursor.getString(cursor.getColumnIndexOrThrow(DbConfig.COLUMN_LASTNAME));
//        email = cursor.getString(cursor.getColumnIndexOrThrow(DbConfig.COLUMN_EMAIL));
//        phone = cursor.getDouble(cursor.getColumnIndexOrThrow(DbConfig.COLUMN_PHONE));
//        birthdate = cursor.getString(cursor.getColumnIndexOrThrow(DbConfig.COLUMN_BIRTHDATE));
//        degreeProgram = cursor.getString(cursor.getColumnIndexOrThrow(DbConfig.COLUMN_DEGREE_PROGRAM));
//
//
//        regNo.setText(userModal.getRegNo());
//        firstname.setText(userModal.getFirstname());;
//        middlename.setText(userModal.getMiddlename());
//        lastname.setText(userModal.getLastname());
//        email.setText(userModal.getEmail());
//        phone.setText(String.valueOf(userModal.getPhone()));
//        birthdate.setText(userModal.getBirthdate());
//        degree_prog.setText(userModal.getDegreeProg());
//
//
//
//        return cursor;
//    }

//    public UserModal data(UserModal userModal){
//
//        String name = userModal.getFirstname();
//
//        return UserModal(regno, name, middlename, lastname, email, birthdate, degreeProgram);
//
//    }

}

/**
 *
 *
 * @Override
 *     protected void onCreate(Bundle savedInstanceState) {
 *         super.onCreate(savedInstanceState);
 *         requestWindowFeature(Window.FEATURE_NO_TITLE);
 *         requestWindowFeature(Window.FEATURE_ACTION_BAR);
 *         setContentView(R.layout.activity_main);
 *     }
 *
 *  Button addButton;
 *  @Override
 *  public void onCreate(Bundle savedInstanceState) {
 *  super.onCreate(savedInstanceState);
 *  setContentView(R.layout.inputPage );
 *  addButton= (Button)findViewById(R.id.addButton);
 *  }
 *
 */