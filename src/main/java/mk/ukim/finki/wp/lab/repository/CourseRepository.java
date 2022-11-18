package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.model.Teacher;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class CourseRepository {

    private List<Course> courses = new ArrayList<>();

    @PostConstruct
    void init() {
        courses.add(new Course("Веб програмирање", "WP"));
        courses.add(new Course("Вештачка интелигенција", "AI"));
        courses.add(new Course("Машинско учење", "ML"));
        courses.add(new Course("Паралелно програмирање", "PP"));
        courses.add(new Course("Линеарна алгебра", "LA"));
    }

    public List<Course> findAllCourses() {
        return courses;
    }

    public List<Course> findAllCoursesSorted(){return courses.stream().sorted(Comparator.comparing(Course::getName)).collect(Collectors.toList());}

    public Optional<Course> findById(long courseId) {
        return courses.stream().filter(s -> s.getCourseId() == courseId).findFirst();
    }

    public List<Course> findByName(String name){
        return courses.stream().filter(s -> s.getName().contains(name)).collect(Collectors.toList());
    }

    public List<Student> findAllStudentsByCourse(Long courseId) {
        return findById(courseId).get().getStudents();
    }


    public Course addStudentToCourse(Student student, Course course) {
        if (student == null || course == null)
            return null;

        Course c = courses.stream().filter(s -> s.getCourseId() == course.getCourseId()).findFirst().orElse(null);
        if (c != null) {
            if (c.getStudents() != null) {
                c.getStudents().removeIf(a -> a.getUsername().equals(student.getUsername()));
            }
            c.getStudents().add(student);
        }
        return c;
    }

    // TODO posledno baranje od lab, dali tuka da proveruvam za duplikat kurs?
    public Optional<Course> addCourse(String name, String description, Teacher teacher){
        courses.removeIf(c -> c.getName().equals(name));
        Course c = new Course(name, description);
        c.setTeacher(teacher);
        courses.add(c);
        return Optional.of(c);
    }

    public void deleteById(Long id) {
        courses.removeIf(c -> c.getCourseId() == id);
    }
}