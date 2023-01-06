package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Grade;
import mk.ukim.finki.wp.lab.model.Student;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface GradeService {
    List<Grade> findAll();
    Grade findByStudentAndCourse(Student s, Course c);
    Map<String, Character> mappedGrades(Course c);
    Grade save(Character grade, Student s, Course c, LocalDateTime date);
    List<Grade> findBetween(LocalDateTime from, LocalDateTime to, Course c);
    void deleteByCourse(Course course);
    void deleteByStudent(Student student);
}
