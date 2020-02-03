package com.donnicholaus.schoolapp.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.donnicholaus.schoolapp.admin.ui.courses.Course;
import com.donnicholaus.schoolapp.db.QueryContract;

import java.util.ArrayList;
import java.util.List;

public class CourseQueryImplementation implements QueryContract.CourseQuery {

    private DatabaseHelper db = DatabaseHelper.getInstance();

    @Override
    public void createCourse(Course course, QueryResponse<Boolean> response) {
        SQLiteDatabase sqLiteDatabase = db.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(DbConfig.SUBJECT_NAME, course.getName());
        contentValues.put(DbConfig.SUBJECT_NAME, course.getName());
        contentValues.put(DbConfig.SUBJECT_CODE, course.getCode());
        contentValues.put(DbConfig.SUBJECT_CREDIT, course.getCredit());

        try {
            long id = sqLiteDatabase.insertOrThrow(DbConfig.TABLE_SUBJECT, null, contentValues);
            if(id>0) {
                response.onSuccess(true);
            }
            else
                response.onFailure("Failed to create student. Unknown Reason!");
        } catch (SQLiteException e){
            response.onFailure(e.getMessage());
        } finally {
            sqLiteDatabase.close();
        }
    }

    @Override
    public void readAllCourse(QueryResponse<List<Course>> response) {
        SQLiteDatabase sqLiteDatabase = db.getReadableDatabase();

        List<Course> courseList = new ArrayList<>();

        Cursor cursor = null;
        try {
            cursor = sqLiteDatabase.query(DbConfig.TABLE_SUBJECT, null, null, null, null, null, null);

            if(cursor!=null && cursor.moveToFirst()){
                do {
                    Course course = getCourseFromCursor(cursor);
                    courseList.add(course);
                } while (cursor.moveToNext());

                response.onSuccess(courseList);
            } else
                response.onFailure("There are no course in database");

        } catch (Exception e){
            response.onFailure(e.getMessage());
        } finally {
            sqLiteDatabase.close();
            if (cursor!=null)
                cursor.close();
        }
    }

    @Override
    public void updateCourse(Course course, QueryResponse<Boolean> response) {
        SQLiteDatabase sqLiteDatabase = db.getWritableDatabase();

        ContentValues contentValues = getContentValuesFromCourse(course);

        try {
            long rowCount = sqLiteDatabase.update(DbConfig.TABLE_SUBJECT, contentValues,
                    DbConfig.SUBJECT_ID + " =? ", new String[]{String.valueOf(course.getId())});

            if(rowCount>0)
                response.onSuccess(true);
            else
                response.onFailure("No course is updated at all");

        } catch (Exception e){
            response.onFailure(e.getMessage());
        } finally {
            sqLiteDatabase.close();
        }
    }

    @Override
    public void deleteCourse(int courseId, QueryResponse<Boolean> response) {
        SQLiteDatabase sqLiteDatabase = db.getWritableDatabase();

        try {
            long rowCount = sqLiteDatabase.delete(DbConfig.TABLE_SUBJECT,
                    DbConfig.SUBJECT_ID + " =? ", new String[]{String.valueOf(courseId)});

            if(rowCount>0)
                response.onSuccess(true);
            else
                response.onFailure("No course is deleted at all");

        } catch (Exception e){
            response.onFailure(e.getMessage());
        } finally {
            sqLiteDatabase.close();
        }
    }

    private Course getCourseFromCursor(Cursor cursor) {
        int id = cursor.getInt(cursor.getColumnIndex(DbConfig.SUBJECT_ID));
        String courseName = cursor.getString(cursor.getColumnIndex(DbConfig.SUBJECT_NAME));
        int courseCode = cursor.getInt(cursor.getColumnIndex(DbConfig.SUBJECT_CODE));
        double courseCredit = cursor.getDouble(cursor.getColumnIndex(DbConfig.SUBJECT_CREDIT));

        return new Course(id, courseName, courseCode, courseCredit);
    }

    private ContentValues getContentValuesFromCourse(Course course) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(DbConfig.SUBJECT_NAME, course.getName());
        contentValues.put(DbConfig.SUBJECT_CODE, course.getCode());
        contentValues.put(DbConfig.SUBJECT_CREDIT, course.getCredit());

        return contentValues;
    }
}
