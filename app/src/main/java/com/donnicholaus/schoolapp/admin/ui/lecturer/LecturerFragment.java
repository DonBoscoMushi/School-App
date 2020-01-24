package com.donnicholaus.schoolapp.admin.ui.lecturer;

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

public class LecturerFragment extends Fragment {

    private LecturerViewModel lecturerViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        lecturerViewModel =
                ViewModelProviders.of(this).get(LecturerViewModel.class);
        View root = inflater.inflate(R.layout.fragment_lecturer, container, false);
        final TextView textView = root.findViewById(R.id.text_tools);
        lecturerViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}