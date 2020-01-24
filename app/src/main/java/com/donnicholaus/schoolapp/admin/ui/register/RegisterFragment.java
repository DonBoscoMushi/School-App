package com.donnicholaus.schoolapp.admin.ui.register;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.donnicholaus.schoolapp.R;
import com.donnicholaus.schoolapp.db.DatabaseHelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Objects;


public class RegisterFragment extends Fragment {

    private EditText rgFirstname, rgMiddlename, rgLastname, rgEmail, rgPhone, rgBirthdate,
            rgDegreeProg, rgRegion, rgDistrict, rgWard;
    private TextView rgUsername;
    private RadioGroup radioGender;
    private Spinner rgRole;
    private DatabaseHelper db;
    private String initial;
    private RegisterViewModel registerViewModel;

    private String regNo;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_register, container, false);
        registerViewModel =
                ViewModelProviders.of(this).get(RegisterViewModel.class);


        db = new DatabaseHelper(getActivity());


        rgFirstname = root.findViewById(R.id.firstname);
        rgMiddlename = root.findViewById(R.id.middlename);
        rgLastname = root.findViewById(R.id.lastname);
        rgEmail = root.findViewById(R.id.email);
        rgPhone = root.findViewById(R.id.phone);
        rgBirthdate = root.findViewById(R.id.birthdate);
        rgUsername = root.findViewById(R.id.username);
        rgRegion = root.findViewById(R.id.region);
        rgDistrict = root.findViewById(R.id.district);
        rgWard = root.findViewById(R.id.ward);
        rgRole = root.findViewById(R.id.role);
        radioGender = root.findViewById(R.id.gender);
        rgDegreeProg = root.findViewById(R.id.degreeProgram);
        Button btnRegister = root.findViewById(R.id.btnRegister);




//        final TextView textView = root.findViewById(R.id.text_gallery);
//        registerViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });


        // Calendar Dialog
        final Calendar myCalendar = Calendar.getInstance();
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String myFormat = "dd/MM/yyyy";
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                rgBirthdate.setText(sdf.format(myCalendar.getTime()));
            }

        };

        rgBirthdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getContext(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        //When spinner value is selected

        rgRole.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String role = rgRole.getSelectedItem().toString();

                switch (role) {
                    case "Admin":
                        initial = "2019-A-";
                        break;
                    case "Teacher":
                        initial = "2019-T-";
                        break;
                    case "Student":
                        initial = "2019-04";
                        break;
                }

                String preReg = db.registrationNo();

                Log.d("Value", preReg);

                String lastNo = "0000".substring(preReg.length()) + preReg;

                regNo = initial + lastNo;
                rgUsername.setText(regNo);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //When the reg button is selected

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String firstname = rgFirstname.getText().toString();
                String middlename = rgMiddlename.getText().toString();
                String lastname = rgLastname.getText().toString();
                String email = rgEmail.getText().toString();
                String phone = rgPhone.getText().toString().trim();
                String birthdate = rgBirthdate.getText().toString();
                String region = rgRegion.getText().toString();
                String district = rgDistrict.getText().toString();
                String ward = rgWard.getText().toString();
                String role = rgRole.getSelectedItem().toString();
                String degreeProgram = rgDegreeProg.getText().toString();

                int checkedradio = radioGender.getCheckedRadioButtonId();
                RadioButton rgGender;
                rgGender = root.findViewById(checkedradio);
                String gender = (String) rgGender.getText();



                //Check for inputs

                if(firstname.isEmpty() || middlename.isEmpty() || lastname.isEmpty() || birthdate.isEmpty() || region.isEmpty()
                        || district.isEmpty() || ward.isEmpty() || phone.isEmpty()
                        || role.isEmpty() || degreeProgram.isEmpty()){

                    Toast.makeText(Objects.requireNonNull(getActivity()).getApplicationContext(),
                            "Please fill all fields", Toast.LENGTH_SHORT).show();

                }else{

                    String password = lastname.toUpperCase();
                    boolean insert = db.insert(firstname,middlename, lastname, gender, email, phone,
                            birthdate, role, regNo, password, degreeProgram, region, district, ward);
                    if (insert){

                        Toast.makeText(getActivity().getApplicationContext(), "Registered Successful", Toast.LENGTH_SHORT).show();

                        //Clear the form
                        clearForm((ViewGroup) root.findViewById(R.id.register_form));

//                        rgFirstname.setText("");
//                        rgMiddlename.setText("");
//                        rgLastname.setText("");
//                        rgEmail.setText("");
//                        rgPhone.setText("");
//                        rgBirthdate.setText("");
//                        rgRole.setText("");
//                        rgDegreeProg.setText("");


                        //Intent intent = new Intent(Register.this, Register.class);
                    }else{
                        Toast.makeText(getActivity().getApplicationContext(), "Registration failed", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });




        return root;

    }

    private void clearForm(ViewGroup group) {
        rgRole.setSelection(0);
        for (int i = 0, count = group.getChildCount(); i < count; ++i) {
            View view = group.getChildAt(i);
            if(view instanceof EditText) {
                ((EditText)view).setText("");
            }

            if(view instanceof  ViewGroup && (((ViewGroup)view).getChildCount() > 0))
                clearForm((ViewGroup)view);
        }
    }

//    public void onRadioButtonClicked(View view) {
//        // Is the button now checked?
//        boolean checked = ((RadioButton) view).isChecked();
//
//        // Check which radio button was clicked
//        switch(view.getId()) {
//            case R.id.radio_male:
//                if (checked)
//                    // Pirates are the best
//                    break;
//            case R.id.radio_female:
//                if (checked)
//                    // Ninjas rule
//                    break;
//        }
//    }
}


