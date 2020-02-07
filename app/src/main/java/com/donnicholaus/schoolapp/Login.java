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
import com.donnicholaus.schoolapp.lecturer.Lecturer;
import com.donnicholaus.schoolapp.student.Student;

public class Login extends AppCompatActivity {

    EditText lg_username, lg_password;
    Button btn_login;

    DatabaseHelper db = new DatabaseHelper(Login.this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

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
                        Toast.makeText(getApplicationContext(), login, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), Nav.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();
                        break;
                    }
                    case "Teacher": {
                        Toast.makeText(getApplicationContext(), login, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), Lecturer.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();
                        break;
                    }
                    case "Student": {
                        Toast.makeText(getApplicationContext(), login, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), Student.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();
                        break;
                    }
                    default: {
                        Toast.makeText(getApplicationContext(), "No user found", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

    }

    /**
     * Intent i = new Intent(oldActivity, newActivity)
     * i.setFlasgs(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
     * startActivity(i);
     *
     * */

}
