package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.model.Teacher;
import mk.ukim.finki.wp.lab.model.exceptions.CourseNotFoundException;
import mk.ukim.finki.wp.lab.model.exceptions.TeacherNotFoundException;
import mk.ukim.finki.wp.lab.repository.CourseRepository;
import mk.ukim.finki.wp.lab.service.CourseService;
import mk.ukim.finki.wp.lab.service.StudentService;
import mk.ukim.finki.wp.lab.service.TeacherService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final StudentService studentService;
    private final TeacherService teacherService;

    public CourseServiceImpl(CourseRepository courseRepository, StudentService studentService, TeacherService teacherService) {
        this.courseRepository = courseRepository;
        this.studentService = studentService;
        this.teacherService = teacherService;
    }

    @Override
    public List<Course> listAll() {
        return courseRepository.findAllCourses();
    }
    @Override
    public List<Course> listAllSorted() {
        return courseRepository.findAllCoursesSorted();
    }

    @Override
    public List<Course> listFiltered(String text) {
        return courseRepository.findByName(text.trim());
    }


    @Override
    public List<Student> listStudentsByCourse(Long courseId) {
       return courseRepository.findAllStudentsByCourse(courseId);
    }

    @Override
    public Optional<Course> findById(Long id) {
        return courseRepository.findById(id);
    }

    @Override
    public Course addStudentInCourse(String username, Long courseId) {
        Course c = courseRepository.findById(courseId).orElseThrow(() -> new CourseNotFoundException(courseId));
        Student s = studentService.searchByUsername(username);

        return courseRepository.addStudentToCourse(s, c);
    }

    @Override
    public Optional<Course> addCourse(String course, String description, Long teacherId) {
        Teacher t = teacherService.findById(teacherId)
                .orElseThrow(() -> new TeacherNotFoundException(teacherId));


        return this.courseRepository.addCourse(course, description, t);
    }

    @Override
    public void deleteById(Long id) {
        this.courseRepository.deleteById(id);
    }
}
