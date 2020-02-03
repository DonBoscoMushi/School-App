package com.donnicholaus.schoolapp.db;

import java.util.List;

import com.donnicholaus.schoolapp.admin.ui.courses.Course;

public class QueryContract {

//    public interface StudentQuery {
//        void createStudent(Student student, QueryResponse<Boolean> response);
//        void readStudent(int studentId, QueryResponse<Student> response);
//        void readAllStudent(QueryResponse<List<Student>> response);
//        void updateStudent(Student student, QueryResponse<Boolean> response);
//        void deleteStudent(int studentId, QueryResponse<Boolean> response);
//    }

    public interface CourseQuery {
        void createCourse(Course course, QueryResponse<Boolean> response);
        void readAllCourse(QueryResponse<List<Course>> response);
        void updateCourse(Course course, QueryResponse<Boolean> response);
        void deleteCourse(int courseId, QueryResponse<Boolean> response);
    }

//    public interface TakenSubjectQuery {
//        void createTakenSubject(int studentId, int subjectId, QueryResponse<Boolean> response);
//        void readAllTakenSubjectByStudentId(int studentId, QueryResponse<List<Subject>> response);
//        void readAllSubjectWithTakenStatus(int studentId, QueryResponse<List<TakenSubject>> response);
//        void deleteTakenSubject(int studentId, int subjectId, QueryResponse<Boolean> response);
//    }

//    public interface TableRowCountQuery {
//        void getTableRowCount(QueryResponse<TableRowCount> response);
//    }
}

