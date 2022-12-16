package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.model.Teacher;
import mk.ukim.finki.wp.lab.model.enumerations.Type;
import mk.ukim.finki.wp.lab.model.exceptions.CourseNotFoundException;
import mk.ukim.finki.wp.lab.model.exceptions.TeacherNotFoundException;
import mk.ukim.finki.wp.lab.repository.jpa.CourseRepository;
import mk.ukim.finki.wp.lab.service.CourseService;
import mk.ukim.finki.wp.lab.service.StudentService;
import mk.ukim.finki.wp.lab.service.TeacherService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
        return courseRepository.findAll();
    }
    @Override
    public List<Course> listAllSorted() {
        //return CourseRepository.findAllByOrderByNameDesc();
        return courseRepository.findAllByOrderByCourseId();
    }

    @Override
    public List<Course> listFiltered(String text) {
        return courseRepository.findAllByNameLike(text.trim());
    }


    @Override
    public List<Student> listStudentsByCourse(Long courseId) {
        Course c = courseRepository.findById(courseId).orElseThrow(()->new CourseNotFoundException(courseId));
        return c.getStudents();
    }

    @Override
    public Optional<Course> findById(Long id) {
        return courseRepository.findById(id);
    }

    @Override
    @Transactional
    public Course addStudentInCourse(String username, Long courseId) {
        Course c = courseRepository.findById(courseId).orElseThrow(() -> new CourseNotFoundException(courseId));
        Student s = studentService.searchByUsername(username);

        c.getStudents().removeIf(st -> st.getUsername().equals(username));
        c.getStudents().add(s);

        return c;
    }

    @Override
    @Transactional
    public Optional<Course> addCourse(String course, String description, Long teacherId, Type type) {
        Teacher t = teacherService.findById(teacherId)
                .orElseThrow(() -> new TeacherNotFoundException(teacherId));

        if(!this.courseRepository.findAllByName(course).isEmpty())
        {
            Course c = this.courseRepository.findAllByName(course).get(0);
            c.setName(course);
            c.setDescription(description);
            c.setTeacher(t);
            c.setType(type);
            return Optional.of(c);
        }

        return Optional.of(this.courseRepository.save(new Course(course, description, t, type)));
    }

    @Override
    public void deleteById(Long id) {
        this.courseRepository.deleteById(id);
    }
}
