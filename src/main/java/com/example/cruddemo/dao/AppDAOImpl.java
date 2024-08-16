package com.example.cruddemo.dao;

import com.example.cruddemo.entity.Course;
import com.example.cruddemo.entity.Instructor;
import com.example.cruddemo.entity.InstructorDetail;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.NoSuchElementException;

@Repository
public class AppDAOImpl implements AppDAO {

    private EntityManager entityManager;

    @Autowired
    public AppDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Instructor instruction) {
        entityManager.persist(instruction);

    }

    @Override
    public Instructor findInstructorById(int id) {
        return entityManager.find(Instructor.class, id);

    }

    @Override
    @Transactional
    public void deleteInstructorById(int id) {

        // Retrieve the instruction
        Instructor instructor = entityManager.find(Instructor.class, id);

        // get the courses
        List<Course> courses = instructor.getCourse();

        // Break associations of all courses for the instructor
        for (Course course : courses) {
            course.setInstructor(null);
        }

        // delete instructor by id
        entityManager.remove(instructor);
    }

    @Override
    public InstructorDetail findInstructorDetailById(int id) {

        return entityManager.find(InstructorDetail.class, id);
    }

    @Override
    @Transactional
    public void deleteInstructorDetailById(int id) {

        InstructorDetail detail = entityManager.find(InstructorDetail.class, id);

        // remove the associated like
        detail.getInstructor().setInstructorDetail(null);

        entityManager.remove(detail);


    }

    @Override
    public List<Course> findCourseByInstructorId(int id) {
        //create query
        TypedQuery<Course> query = entityManager.createQuery(
                "from Course where instructor.id = : data", Course.class);
        query.setParameter("data", id);

        // execute the query
        List<Course> courses = query.getResultList();

        return courses;
    }

    @Override
    public Instructor findInstructorByIdJoinFetch(int id) {

        // Create query
        TypedQuery<Instructor> query = entityManager.createQuery(  // Corrected this line
                "select i from Instructor i "
                        + "JOIN FETCH i.course "
                        + "JOIN FETCH i.instructorDetail "
                        + "where i.id = :data", Instructor.class);

        query.setParameter("data", id);

        Instructor instructor = query.getSingleResult();

        return instructor;
    }

    @Override
    @Transactional
    public void update(Instructor instruction) {
        entityManager.merge(instruction);
    }

    @Override
    @Transactional
    public void updateCourse(Course course) {
        entityManager.merge(course);

    }

    @Override
    public Course findCourseByCourseId(int id) {
        return entityManager.find(Course.class, id);
    }

    @Override
    @Transactional

    public void deleteCourseById(int id) {
        Course course = entityManager.find(Course.class, id);
        // remove the associated instructor
        entityManager.remove(course);
    }

    @Override
    @Transactional
    public void save(Course course) {
        entityManager.persist(course);
    }

    @Override
    public Course findCourseAndReviewCourseById(int id) {
        //Create the query
        TypedQuery<Course> query = entityManager.createQuery(
                "select c from Course c "
                        +"JOIN FETCH c.reviews "
        + " where c.id = :data",
                Course.class);
        query.setParameter("data", id);

        // Execute the query
        Course course = query.getSingleResult();

        return course;

    }


}
