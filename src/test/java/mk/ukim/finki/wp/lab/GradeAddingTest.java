package mk.ukim.finki.wp.lab;

import mk.ukim.finki.wp.lab.model.*;
import mk.ukim.finki.wp.lab.model.converter.TeacherFullNameConverter;
import mk.ukim.finki.wp.lab.model.enumerations.Type;
import mk.ukim.finki.wp.lab.repository.jpa.CourseRepository;
import mk.ukim.finki.wp.lab.repository.jpa.GradeRepository;
import mk.ukim.finki.wp.lab.repository.jpa.StudentRepository;
import mk.ukim.finki.wp.lab.repository.jpa.TeacherRepository;
import mk.ukim.finki.wp.lab.service.CourseService;
import mk.ukim.finki.wp.lab.service.GradeService;
import mk.ukim.finki.wp.lab.service.StudentService;
import mk.ukim.finki.wp.lab.service.TeacherService;
import mk.ukim.finki.wp.lab.service.impl.CourseServiceImpl;
import mk.ukim.finki.wp.lab.service.impl.GradeServiceImpl;
import mk.ukim.finki.wp.lab.service.impl.StudentServiceImpl;
import mk.ukim.finki.wp.lab.service.impl.TeacherServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;

@RunWith(MockitoJUnitRunner.class)
public class GradeAddingTest {
    @Mock
    private GradeRepository gradeRepository;
    @Mock
    private StudentRepository studentRepository;
    @Mock
    private CourseRepository courseRepository;
    @Mock
    private TeacherRepository teacherRepository;

    private TeacherService teacherService;
    private StudentService studentService;
    private GradeService gradeService;
    private CourseService courseService;

    @Before
    public void init(){
        MockitoAnnotations.openMocks(this);
        Student student = new Student("username", "password", "name", "surname");
        Course course = new Course("courseName", "courseDesc", Type.WINTER);

        this.studentService = Mockito.spy(new StudentServiceImpl(this.studentRepository));
        this.teacherService = Mockito.spy(new TeacherServiceImpl(teacherRepository));
        this.courseService = Mockito.spy(new CourseServiceImpl(courseRepository, studentService, teacherService));
        this.gradeService = Mockito.spy(new GradeServiceImpl(gradeRepository, studentService, courseService));

        studentService.save("username", "password", "name", "surname");

    }

    @Test
    public void testSuccessfulGrade(){
        Grade grade = this.gradeService.save('F', "username", "courseName", LocalDateTime.now());

        Mockito.verify(this.gradeService).save('F', "username", "courseName", LocalDateTime.now());

        Assert.assertNotNull("Grade is null", grade);
        Assert.assertEquals("Grades do not match", (Object)"F".charAt(0), (Object)grade.getGrade());
    }
}
