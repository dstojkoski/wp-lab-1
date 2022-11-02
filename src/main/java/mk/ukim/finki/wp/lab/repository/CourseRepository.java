package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.List;

@Repository
public class CourseRepository {


    public List<Student> students = DataHolder.students;
    @PostConstruct
    void init(){

    }
    public List<Course> findAllCourses(){
        return DataHolder.courses;
    }

    public Course findById(long courseId){
        return DataHolder.courses.stream().filter(s -> s.getCourseId() == courseId).findFirst().orElse(null);
    }

    public List<Student> findAllStudentsByCourse(Long courseId){
        return findById(courseId).getStudents();
    }


    public Course addStudentToCourse(Student student, Course course){
        if(student == null || course == null)
            return null;

        Course c = DataHolder.courses.stream().filter(s -> s.equals(course)).findFirst().orElse(null);
        if(c != null)
        {
            c.getStudents().removeIf(a -> a.getUsername().equals(student.getUsername()));
            c.getStudents().add(student);
        }

        return c;
    }
}
