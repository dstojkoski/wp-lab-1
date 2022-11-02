package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Student;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class StudentRepository {

    public List<Student> findAllStudents(){
        return DataHolder.students;
    }

    public List<Student> findAllByNameOrSurname(String text){
        return DataHolder.students.stream().filter(s -> s.getName().contains(text) || s.getSurname().contains(text)).collect(Collectors.toList());
    }

    public Student addStudent(String username, String password, String name, String surname){
        Student s = new Student(username,password,name,surname);
        DataHolder.students.add(s);

        return s;
    }


}
