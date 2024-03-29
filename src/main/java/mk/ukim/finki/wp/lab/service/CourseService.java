package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.model.enumerations.Type;

import java.util.List;
import java.util.Optional;


public interface CourseService{
    List<Course> listAll();
    List<Course> listAllSorted();
    List<Student> listStudentsByCourse(Long courseId);
    Course searchByName(String course);
    List<Course> listFiltered(String text);
    Optional<Course> findById(Long id);
    Course addStudentInCourse(String username, Long courseId);
    Optional<Course> addCourse(String course, String description, Long professor, Type type);
    Optional<Course> addCourse(String course, String description, Type type);
    void deleteById(Long id);
}
