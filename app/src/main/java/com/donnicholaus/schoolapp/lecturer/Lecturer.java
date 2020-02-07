package com.donnicholaus.schoolapp.lecturer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.donnicholaus.schoolapp.R;
import com.donnicholaus.schoolapp.UserModal;

public class Lecturer extends AppCompatActivity {
    TextView regNo, firstname, middlename, lastname, email, phone, birthdate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lecturer);

        regNo = findViewById(R.id.l_regNo);
        firstname = findViewById(R.id.l_firstname);
        middlename = findViewById(R.id.l_middlename);
        lastname = findViewById(R.id.l_lastname);
        email = findViewById(R.id.l_email);
        phone = findViewById(R.id.l_phone);
        birthdate = findViewById(R.id.l_birthdate);

        regNo.setText(UserModal.getRegNo());
        firstname.setText(UserModal.getFirstname());
        middlename.setText(UserModal.getMiddlename());
        lastname.setText(UserModal.getLastname());
        email.setText(UserModal.getEmail());
        phone.setText(String.valueOf(UserModal.getPhone()));
        birthdate.setText(UserModal.getBirthdate());
    }
}
