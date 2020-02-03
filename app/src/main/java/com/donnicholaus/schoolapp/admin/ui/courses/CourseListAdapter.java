package com.donnicholaus.schoolapp.admin.ui.courses;
import android.content.Context;
import android.content.DialogInterface;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.donnicholaus.schoolapp.R;
import com.donnicholaus.schoolapp.db.CourseQueryImplementation;
import com.donnicholaus.schoolapp.db.QueryContract;
import com.donnicholaus.schoolapp.db.QueryResponse;

import java.util.List;

public class CourseListAdapter extends RecyclerView.Adapter<CourseViewHolder> {

    private Context context;
    private List<Course> subjectList;
    private CourseCrudListener listener;
    public static final String UPDATE_SUBJECT = "update_subject";

    public CourseListAdapter(Context context, List<Course> subjectList, CourseCrudListener listener) {
        this.context = context;
        this.subjectList = subjectList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.courses_card_view, parent, false);
        return new CourseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {
        final Course subject = subjectList.get(position);

        holder.subjectNameTextView.setText(subject.getName());
        holder.courseCodeTextView.setText(context.getString(R.string.course_code, subject.getCode()));
        holder.creditTextView.setText(context.getString(R.string.course_credit, subject.getCredit()));

        holder.editIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CourseUpdateDialogFragment dialogFragment = CourseUpdateDialogFragment.newInstance(subject, "Update Course", new CourseCrudListener() {
                    @Override
                    public void onCourseListUpdate(boolean isUpdate) {
                        listener.onCourseListUpdate(isUpdate);
                    }
                });
                //dialogFragment.show(((context) ).getSupportFragmentManager(), UPDATE_SUBJECT);
            }
        });

        holder.deleteIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showConfirmationDialog(subject.getId());
            }
        });

    }

    @Override
    public int getItemCount() {
        return subjectList.size();
    }

    private void showConfirmationDialog(final int subjectId) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setMessage("Are you sure, You wanted to delete this subject?");
        alertDialogBuilder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        QueryContract.CourseQuery query = new CourseQueryImplementation();
                        query.deleteCourse(subjectId, new QueryResponse<Boolean>() {
                            @Override
                            public void onSuccess(Boolean data) {
                                listener.onCourseListUpdate(data);
                                Toast.makeText(context, "Course is deleted successfully", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onFailure(String message) {
                                Toast.makeText(context, message, Toast.LENGTH_LONG).show();
                            }
                        });

                    }
                });

        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}
