package com.donnicholaus.schoolapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.donnicholaus.schoolapp.admin.Nav;
import com.donnicholaus.schoolapp.db.DatabaseHelper;
import com.donnicholaus.schoolapp.student.Student;

public class Login extends AppCompatActivity {

    EditText lg_username, lg_password;
    Button btn_login;


    private  DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        boolean admin = db.checkAdminExist();


        if(!admin){
            addDefaultUser();
        }

        lg_username = findViewById(R.id.etUsername);
        lg_password = findViewById(R.id.etPassword);
        btn_login = findViewById(R.id.btnLogin);


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = lg_username.getText().toString();
                String password = lg_password.getText().toString();

                String login = db.checkUserExist(username, password);
                switch (login) {
                    case "Admin": {
                        Intent intent = new Intent(getApplicationContext(), Nav.class);
                        startActivity(intent);
                        break;
                    }
                    case "Teacher":
                        Toast.makeText(getApplicationContext(), "Bado activity ya walim", Toast.LENGTH_SHORT).show();
                        break;
                    case "Student": {
                        Intent intent = new Intent(getApplicationContext(), Student.class);
                        startActivity(intent);
                        break;
                    }
                    default: {
                        Toast.makeText(getApplicationContext(), "No user found", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

    public void addDefaultUser(){

        boolean insert = db.insert("Admin", null, null, null, null,
                null,null, "Admin", "Admin", "admin", null,
                null, null, null);
        if(insert){
            Toast.makeText(getApplicationContext(), "Login", Toast.LENGTH_SHORT).show();
        }else
            Toast.makeText(getApplicationContext(), "No Admin", Toast.LENGTH_SHORT).show();
    }


}
