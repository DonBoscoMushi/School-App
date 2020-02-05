package com.donnicholaus.schoolapp.student;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.donnicholaus.schoolapp.R;
import com.donnicholaus.schoolapp.UserModal;

public class Student extends AppCompatActivity {

    TextView regNo, firstname, middlename, lastname, email, phone, birthdate, degree_prog;

    private static UserModal userModal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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

        regNo.setText(userModal.getRegNo());
        firstname.setText(userModal.getFirstname());;
        middlename.setText(userModal.getMiddlename());
        lastname.setText(userModal.getLastname());
        email.setText(userModal.getEmail());
        phone.setText(String.valueOf(userModal.getPhone()));
        birthdate.setText(userModal.getBirthdate());
        degree_prog.setText(userModal.getDegreeProg());

    }





}
