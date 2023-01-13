package mk.ukim.finki.wp.lab;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.model.enumerations.Type;
import mk.ukim.finki.wp.lab.model.exceptions.InvalidUsernameOrPasswordException;
import mk.ukim.finki.wp.lab.model.exceptions.StudentAlreadyExistsException;
import mk.ukim.finki.wp.lab.repository.jpa.CourseRepository;
import mk.ukim.finki.wp.lab.repository.jpa.StudentRepository;
import mk.ukim.finki.wp.lab.repository.jpa.TeacherRepository;
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
import org.springframework.security.core.parameters.P;

import javax.swing.text.html.Option;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class AddStudentTest {
    @Mock
    private StudentRepository studentRepository;

    private StudentService studentService;

    @Before
    public void init(){
        MockitoAnnotations.openMocks(this);
        Student student = new Student("username", "password", "name", "surname");
        Mockito.when(this.studentRepository.save(Mockito.any(Student.class))).thenReturn(student);

        this.studentService = Mockito.spy(new StudentServiceImpl(this.studentRepository));

        //studentService.save("username", "password", "name", "surname");

    }

    @Test
    public void testSuccessfulAdd(){
        Student student = studentService.save("username", "password", "name", "surname");
        Mockito.verify(this.studentService).save("username", "password", "name", "surname");


        Assert.assertNotNull("Student is null", student);
        Assert.assertEquals("Names do not match", "name", student.getName());
        Assert.assertEquals("Surnames do not match", "surname", student.getSurname());
        Assert.assertEquals("Passwords do no match", "password", student.getPassword());
        Assert.assertEquals("Usernames do not match", "username", student.getUsername());
    }

    @Test
    public void testNullUsername(){
        Assert.assertThrows("InvalidUsernameOrPasswordException expected",
                InvalidUsernameOrPasswordException.class,
                () -> this.studentService.save(null, "password", "name", "surname"));
        Mockito.verify(this.studentService).save(null, "password", "name", "surname");
    }

    @Test
    public void testEmptyUsername(){
        Assert.assertThrows("InvalidUsernameOrPasswordException expected",
                InvalidUsernameOrPasswordException.class,
                () -> this.studentService.save("", "password", "name", "surname"));
        Mockito.verify(this.studentService).save("", "password", "name", "surname");
    }

    @Test
    public void testNullPassword(){
        Assert.assertThrows("InvalidUsernameOrPasswordException expected",
                InvalidUsernameOrPasswordException.class,
                () -> this.studentService.save("username", null, "name", "surname"));
        Mockito.verify(this.studentService).save("username", null, "name", "surname");
    }

    @Test
    public void testEmptyPassword(){
        Assert.assertThrows("InvalidUsernameOrPasswordException expected",
                InvalidUsernameOrPasswordException.class,
                () -> this.studentService.save("username", "", "name", "surname"));
        Mockito.verify(this.studentService).save("username", "", "name", "surname");
    }
    @Test
    public void studentExists(){
        Student student = new Student("username", "password", "name", "surname");
        Mockito.when(this.studentRepository.findStudentByUsername(Mockito.anyString())).thenReturn(Optional.of(student));
        String username = "username";

        Assert.assertThrows("UsernameAlreadyExistsException expected",
                StudentAlreadyExistsException.class,
                () -> this.studentService.save(username, "password", "name", "surname"));
        Mockito.verify(this.studentService).save(username, "password", "name", "surname");
    }
}
