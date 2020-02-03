package com.donnicholaus.schoolapp.admin.ui.courses;

import android.app.Dialog;
import android.os.Bundle;
import androidx.fragment.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.donnicholaus.schoolapp.R;
import com.donnicholaus.schoolapp.db.CourseQueryImplementation;
import com.donnicholaus.schoolapp.db.QueryContract;
import com.donnicholaus.schoolapp.db.QueryResponse;

public class CourseUpdateDialogFragment extends DialogFragment {

    private static CourseCrudListener courseCrudListener;
    public static final String TITLE = "Title";

    private EditText courseNameEditText;
    private EditText courseCodeEditText;
    private EditText courseCreditEditText;
    private Button updateButton;
    private Button cancelButton;

    private static Course course;

    public CourseUpdateDialogFragment() {
        // Required empty public constructor
    }

    public static CourseUpdateDialogFragment newInstance(Course sub, String title, CourseCrudListener listener){
        course = sub;
        courseCrudListener = listener;
        CourseUpdateDialogFragment courseUpdateDialogFragment = new CourseUpdateDialogFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        courseUpdateDialogFragment.setArguments(args);

        courseUpdateDialogFragment.setStyle(DialogFragment.STYLE_NORMAL, R.style.CustomDialog);

        return courseUpdateDialogFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_course_update_dialog, container, false);
        String title = getArguments().getString(TITLE);
        getDialog().setTitle(title);

        courseNameEditText = view.findViewById(R.id.courseNameEditText);
        courseCodeEditText = view.findViewById(R.id.courseCodeEditText);
        courseCreditEditText = view.findViewById(R.id.courseCreditEditText);
        updateButton = view.findViewById(R.id.updateButton);
        cancelButton = view.findViewById(R.id.cancelButton);

        courseNameEditText.setText(course.getName());
        courseCodeEditText.setText(String.valueOf(course.getCode()));
        courseCreditEditText.setText(String.valueOf(course.getCredit()));

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String courseName = courseNameEditText.getText().toString();
                int courseCode = Integer.parseInt(courseCodeEditText.getText().toString());
                double courseCredit = Double.parseDouble(courseCreditEditText.getText().toString());

                course.setName(courseName);
                course.setCode(courseCode);
                course.setCredit(courseCredit);

                QueryContract.CourseQuery courseQuery = new CourseQueryImplementation();
                courseQuery.updateCourse(course, new QueryResponse<Boolean>() {
                    @Override
                    public void onSuccess(Boolean data) {
                        getDialog().dismiss();
                        courseCrudListener.onCourseListUpdate(data);
                        Toast.makeText(getContext(), "Course updated successfully", Toast.LENGTH_LONG).show();
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
