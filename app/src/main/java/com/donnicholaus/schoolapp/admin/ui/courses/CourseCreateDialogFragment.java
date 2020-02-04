package com.donnicholaus.schoolapp.admin.ui.courses;

import android.app.Dialog;
import androidx.fragment.app.DialogFragment;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Nullable;

import android.provider.SyncStateContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.donnicholaus.schoolapp.db.CourseQueryImplementation;
import com.donnicholaus.schoolapp.db.QueryContract;
import com.donnicholaus.schoolapp.db.QueryResponse;

import com.donnicholaus.schoolapp.R;


public class CourseCreateDialogFragment extends DialogFragment {

    private EditText courseNameEditText,courseCodeEditText,courseCreditEditText;
    private Button createButton;
    private Button cancelButton;
    private Context context;
    private static CourseCrudListener courseCrudListener;
    public static final String TITLE = "title";

    public CourseCreateDialogFragment() {
    }

    public static CourseCreateDialogFragment newInstance(String title, CourseCrudListener listener){
        courseCrudListener = listener;
        CourseCreateDialogFragment courseCreateDialogFragment = new CourseCreateDialogFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        courseCreateDialogFragment.setArguments(args);

        courseCreateDialogFragment.setStyle(DialogFragment.STYLE_NORMAL, R.style.CustomDialog);

        return courseCreateDialogFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_course_create_dialog, container, false);

        courseNameEditText = view.findViewById(R.id.courseNameEditText);
        courseCodeEditText = view.findViewById(R.id.courseCodeEditText);
        courseCreditEditText = view.findViewById(R.id.courseCreditEditText);
        createButton = view.findViewById(R.id.createButton);
        cancelButton = view.findViewById(R.id.cancelButton);

        String title = getArguments().getString(TITLE);
        getDialog().setTitle(title);

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String courseName = courseNameEditText.getText().toString();
                String courseCode = courseCodeEditText.getText().toString();
                String courseCredit = courseCreditEditText.getText().toString();

                final Course course = new Course(-1, courseName, courseCode, courseCredit);

                QueryContract.CourseQuery query = new CourseQueryImplementation(getContext());
                query.createCourse(course, new QueryResponse<Boolean>() {
                    @Override
                    public void onSuccess(Boolean data) {
                        getDialog().dismiss();
                        courseCrudListener.onCourseListUpdate(true);
                        Toast.makeText(getContext(), "Course created successfully", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(String message) {
                        courseCrudListener.onCourseListUpdate(false);
                        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
                    }
                });

            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().dismiss();
            }
        });

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.WRAP_CONTENT;
            //noinspection ConstantConditions
            dialog.getWindow().setLayout(width, height);
        }
    }
}

