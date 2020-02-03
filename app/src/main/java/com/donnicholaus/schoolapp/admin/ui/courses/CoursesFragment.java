package com.donnicholaus.schoolapp.admin.ui.courses;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.donnicholaus.schoolapp.R;
import com.donnicholaus.schoolapp.db.CourseQueryImplementation;
import com.donnicholaus.schoolapp.db.QueryContract;
import com.donnicholaus.schoolapp.db.QueryResponse;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class CoursesFragment extends Fragment implements CourseCrudListener {
    private CoursesViewModel coursesViewModel;

    private RecyclerView recyclerView;
    private FloatingActionButton fab;

    public static final String CREATE_COURSE = "create_subject";

    private List<Course> subjectList = new ArrayList<>();
    private CourseListAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        coursesViewModel =
                ViewModelProviders.of(this).get(CoursesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_courses, container, false);

//        Toolbar toolbar = root.findViewById(R.id.toolbar);
        recyclerView = root.findViewById(R.id.recyclerView);

        fab = root.findViewById(R.id.fab);
//        initialization();

        adapter = new CourseListAdapter(getActivity(), subjectList, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);

        showCourseList();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CourseCreateDialogFragment dialogFragment = CourseCreateDialogFragment.newInstance("Create Course", CoursesFragment.this);
                dialogFragment.show(getActivity().getSupportFragmentManager(), CREATE_COURSE);

            }
        });

        return root;
    }

    public void onCourseListUpdate(boolean isUpdate) {
        if (isUpdate) {

            showCourseList();
        }
    }
//
//    public boolean onSupportNavigateUp() {
//        finish();
//        return super.onSupportNavigateUp();
//    }

//    private void showTableRowCount() {
//        QueryContract.TableRowCountQuery query = new TableRowCountQueryImplementation();
//        query.getTableRowCount(new QueryResponse<TableRowCount>() {
//            @Override
//            public void onSuccess(TableRowCount data) {
//                studentCountTextView.setText(getString(R.string.student_count, data.getStudentRow()));
//                subjectCountTextView.setText(getString(R.string.subject_count, data.getCourseRow()));
//                takenSubjectCountTextView.setText(getString(R.string.taken_subject_count, data.getTakenSubjectRow()));
//            }
//
//            @Override
//            public void onFailure(String message) {
//                studentCountTextView.setText(getString(R.string.table_row_count_failed));
//                subjectCountTextView.setText(message);
//                takenSubjectCountTextView.setText("");
//            }
//        });
//    }

    private void showCourseList() {
        QueryContract.CourseQuery query = new CourseQueryImplementation();
        query.readAllCourse(new QueryResponse<List<Course>>() {
            @Override
            public void onSuccess(List<Course> data) {
                recyclerView.setVisibility(View.VISIBLE);
//                noDataFoundTextView.setVisibility(View.GONE);

                subjectList.clear();
                subjectList.addAll(data);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(String message) {
                recyclerView.setVisibility(View.GONE);
//                noDataFoundTextView.setVisibility(View.VISIBLE);
            }
        });
    }

//    private void initialization() {
//        recyclerView = findViewById(R.id.recyclerView);
//
//        fab = findViewById(R.id.fab);
//    }

//    @Override
//    public void onSubjectListUpdate(boolean isUpdate) {
//
//
//    }


}
// {
//
//
//
//    public View onCreateView(@NonNull LayoutInflater inflater,
//                             ViewGroup container, Bundle savedInstanceState) {
//        final View root = inflater.inflate(R.layout.fragment_courses, container, false);
//
//
//        return root;
//    }
//
//    }
//}
