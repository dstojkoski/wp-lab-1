package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;

import java.util.List;
import java.util.Optional;


public interface CourseService{
    List<Course> listAll();
    List<Course> listAllSorted();
    List<Student> listStudentsByCourse(Long courseId);
    List<Course> listFiltered(String text);
    Optional<Course> findById(Long id);
    Course addStudentInCourse(String username, Long courseId);
    Optional<Course> addCourse(String course, String description, Long professor);
    void deleteById(Long id);
}
