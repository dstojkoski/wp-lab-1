package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CourseRepository {

    private List<Course> courses = new ArrayList<>();

    @PostConstruct
    void init(){
        courses.add(new Course(101,"Веб програмирање", "WP"));
        courses.add(new Course(201, "Вештачка интелигенција", "AI"));
        courses.add(new Course(301, "Машинско учење", "ML"));
        courses.add(new Course(301, "Паралелно програмирање", "PP"));
        courses.add(new Course(301, "Линеарна алгебра", "LA"));
    }
    public List<Course> findAllCourses(){
        return courses;
    }

    public Course findById(long courseId){
        return courses.stream().filter(s -> s.getCourseId() == courseId).findFirst().orElse(null);
    }

    public List<Student> findAllStudentsByCourse(Long courseId){
        return findById(courseId).getStudents();
    }


    public Course addStudentToCourse(Student student, Course course){
        if(student == null || course == null)
            return null;

        Course c = courses.stream().filter(s -> s.equals(course)).findFirst().orElse(null);
        if(c != null)
        {
            c.getStudents().removeIf(a -> a.getUsername().equals(student.getUsername()));
            c.getStudents().add(student);
        }

        return c;
    }
}
