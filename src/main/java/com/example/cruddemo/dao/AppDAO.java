package com.example.cruddemo.dao;

import com.example.cruddemo.entity.Course;
import com.example.cruddemo.entity.Instructor;
import com.example.cruddemo.entity.InstructorDetail;

import java.util.List;

public interface AppDAO {

    void save(Instructor instruction);
    Instructor findInstructorById(int id);

 void  deleteInstructorById(int id);

 InstructorDetail findInstructorDetailById(int id);

 void deleteInstructorDetailById(int id);

 List<Course> findCourseByInstructorId(int id);

    Instructor findInstructorByIdJoinFetch(int id);

    void update(Instructor instruction);

    void updateCourse(Course course);

    Course findCourseByCourseId(int id);


    void deleteCourseById(int id);

    void save (Course course);

    Course findCourseAndReviewCourseById(int id);

}
