package com.example.cruddemo;

import com.example.cruddemo.dao.AppDAO;
import com.example.cruddemo.entity.Course;
import com.example.cruddemo.entity.Instructor;
import com.example.cruddemo.entity.InstructorDetail;
import com.example.cruddemo.entity.Review;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.NoSuchElementException;

@SpringBootApplication
public class CruddemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(CruddemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(AppDAO appDAO) {
        return runner -> {
//            createInstructor(appDAO);
//            findInstructor(appDAO);
//            deleteInstructorById(appDAO);

//            findInstructorDeletailById(appDAO);
//            deleteInstructorDeletailById(appDAO);
            
//            createInstructorWithCourses(appDAO);
//            findInstructorWithCourses(appDAO);
//            findCoursesForInstructor(appDAO);
//            findInstructorByidJoinFetch(appDAO);
//            updateInstructorById(appDAO);
//            updateCourseById(appDAO);
//            deleteInstructor(appDAO);
//            deleteCourse(appDAO);
//                saveCourse(appDAO);
//            findCourse(appDAO);
            deleteCourseAndReview(appDAO);

        };
    }

    private void deleteCourseAndReview(AppDAO appDAO) {
        int id = 11;
        System.out.println("Attempting to delete course and reviews with id " + id);
        appDAO.deleteCourseById(id);

        System.out.println("Done");

    }

    private void findCourse(AppDAO appDAO) {
        int id = 11;
        System.out.println("Find Course " + id );

        Course course = appDAO.findCourseAndReviewCourseById(id);
        System.out.println("Cousre " + course);

        System.out.println("The review is " + course.getReviews());

        System.out.println("DONE!");

    }

    private void saveCourse(AppDAO appDAO) {
        Course course = new Course("Testing saving course 2");
        System.out.println("Attempting to save course");


        course.addReview(new Review("Goodlooking 2"));
        course.addReview(new Review("Goodlooking at this course 2"));
        course.addReview(new Review("Goodlooking at this path 2"));
        course.addReview(new Review("Goodlooking at the last course 2"));
        course.addReview(new Review("Goodlooking at the last course 2"));
        course.addReview(new Review("Goodlooking at the last course 2"));



        appDAO.save(course);
        System.out.println("Course saved successfully with id: " + course.getId());
        System.out.println("Review saved successfully with id: " + course.getReviews());
        System.out.println("DONE");
    }

    private void deleteCourse(AppDAO appDAO) {
        int id = 10;
        System.out.println("Attempting to delete course with id " + id);
        System.out.println("Deleting course with : " + id);
        appDAO.deleteCourseById(id);
        System.out.println("Course with id " + id + " deleted successfully");
        System.out.println("DONE");
    }

    private void deleteInstructor(AppDAO appDAO) {
        int id = 1;
        System.out.println("Attempting to delete instructor with id " + id);

        System.out.println("Deleting instructor with : " + id);
        appDAO.deleteInstructorById(id);
        System.out.println("Instructor with id " + id + " deleted successfully");

        System.out.println("DONE");
    }

    private void updateCourseById(AppDAO appDAO) {
        int id = 10;
        System.out.println("Attempting to update course with id " + id);

        Course course = appDAO.findCourseByCourseId(id);

        if(course!= null) {
            System.out.println("Updating course with : " + id);
            course.setTitle("Testing update course");
            appDAO.updateCourse(course);
            System.out.println("course with id " + id + " updated successfully");
        } else {
            System.out.println("course not found with id " + id);
        }

        System.out.println("DONE");
    }

    private void updateInstructorById(AppDAO appDAO) {
        int id = 1;
        System.out.println("Attempting to update instructor with id " + id);

        Instructor instructor = appDAO.findInstructorById(id);

        if(instructor!= null) {
            System.out.println("Updating instructor with : " + id);
            instructor.setLastName("Testing");
            appDAO.update(instructor);
            System.out.println("Instructor with id " + id + " updated successfully");
        } else {
            System.out.println("Instructor not found with id " + id);
        }

        System.out.println("DONE");
    }

    private void findInstructorByidJoinFetch(AppDAO appDAO) {
        int id = 1;
        System.out.println("Attempting to find instructor with id " + id + " using join fetch");

        Instructor instructor = appDAO.findInstructorByIdJoinFetch(id);

        if(instructor != null) {
            System.out.println("Instructor with id " + id + " : " + instructor);
            System.out.println("Courses for instructor " + instructor.getCourse());
        } else {
            System.out.println("Instructor not found with id " + id);
        }

        System.out.println("DONE");
    }

    private void findCoursesForInstructor(AppDAO appDAO) {
        int id = 1;

        // find instructions
        System.out.println("Attempting to find instructor with id " + id);

        Instructor instructor = appDAO.findInstructorById(id);

        if(instructor != null) {
            System.out.println("Instructor with id " + id + " : " + instructor);

        } else {
            System.out.println("Instructor not found with id " + id);
        }

        // find course for instructor
        System.out.println("Attempting to find Course with id " + id);

        List<Course> courses = appDAO.findCourseByInstructorId(id);
        instructor.setCourse(courses);
        if (!courses.isEmpty()) {

            System.out.println("Courses for Instructor with id " + id + " : " + instructor.getCourse());
        } else {
            System.out.println("No courses found for Instructor with id " + id);
        }
        System.out.println("DONE");





    }

    private void findInstructorWithCourses(AppDAO appDAO) {
        int id = 1;
        System.out.println("Attempting to find instructor with id " + id);

        Instructor instructor = appDAO.findInstructorById(id);

        if(instructor != null) {
            System.out.println("Instructor with id " + id + " : " + instructor);
            System.out.println("Courses : " + instructor.getCourse());
        } else {
            System.out.println("Instructor not found with id " + id);
        }
    }

    private void createInstructorWithCourses(AppDAO appDAO) {

        // create the instructor
        Instructor instructor = new Instructor(
                "Viki", "Vil", "vilki@gmail.com" );

        // create the instructor_detail
        InstructorDetail instructorDetails = new InstructorDetail(
                "http://www.vilki.com/youtube","Gaming"
        );

        // associate the object
        instructor.setInstructorDetail(instructorDetails);

        // create the courses
        Course course1 = new Course("The spring boot full course");
        Course course2 = new Course("The spring Java full course");

        // add courses to the instructor
        instructor.add(course1);
        instructor.add(course2);

        // save the instructor and courses
        System.out.println("Saving instructor : " + instructor);
        System.out.println("Saving Courses : " + instructor.getCourse());
        appDAO.save(instructor);
        System.out.println("DONW");

    }

    private void deleteInstructorDeletailById(AppDAO appDAO) {
        int id = 4;
        System.out.println("Attempting to delete instructor detail with id " + id);

        appDAO.deleteInstructorDetailById(id);
        System.out.println("Instructor Detail deleted with id " + id);
    }

    private void findInstructorDeletailById(AppDAO appDAO) {
        int id = 3;
        System.out.println("Attempting to find instructor detail with id " + id);

        InstructorDetail instructorDetail = appDAO.findInstructorDetailById(id);
        System.out.println("Instructor Detail : " + instructorDetail);
        System.out.println("Instructor : " + instructorDetail.getInstructor());
    }

    private void deleteInstructorById(AppDAO appDAO) {
        // Assuming instructor with id 9 exists in the database.
        int id = 2;
        System.out.println("Attempting to delete instructor with id " + id);

        // check if the instructor not found
        try {
            appDAO.deleteInstructorById(id);
            System.out.println("Instructor deleted with id " + id);
        } catch (NoSuchElementException e) {
            System.out.println("Error: " + e.getMessage());
        }


    }

    private void findInstructor(AppDAO appDAO) {
        int id = 1;
        System.out.println("Find instructor " + id );

        Instructor instructor= appDAO.findInstructorById(id);

        System.out.println("Instructor : " + instructor);
        System.out.println("Instructor Detail : " + instructor.getInstructorDetail());



    }


    private void createInstructor(AppDAO appDAO) {

        // create the instructor
        Instructor instructor = new Instructor(
                "Lii", "Peng", "peng@gmail.com" );

        // create the instructor_detail

        InstructorDetail instructorDetails = new InstructorDetail(
                "http://www.peng.com/youtube","Codding"
        );
       /*

        Instructor instructor = new Instructor(
                "Lii", "Suu", "suu@gmail.com" );

        // create the instructor_detail

        InstructorDetail instructorDetails = new InstructorDetail(
                "http://www.peng.com/youtube","Running"
        );

 */
        // associate the object
        instructor.setInstructorDetail(instructorDetails);

        // save the instructor
        //
        // This will save the details of the instructor
        // because of CasCadeType.All
        //
        System.out.println("Saving Instructor : " + instructor);
        appDAO.save(instructor);

        System.out.println("DONE");





    }


}
