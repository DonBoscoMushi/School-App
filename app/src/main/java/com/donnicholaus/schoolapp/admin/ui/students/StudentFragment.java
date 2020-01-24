package com.donnicholaus.schoolapp.admin.ui.students;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.donnicholaus.schoolapp.R;

public class StudentFragment extends Fragment {

    private StudentViewModel studentViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_student, container, false);


        studentViewModel =
                ViewModelProviders.of(this).get(StudentViewModel.class);
//        final TextView textView = root.findViewById(R.id.text_send);
//        studentViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        return root;
    }
}