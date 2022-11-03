package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.model.Student;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class StudentRepository {

    private List<Student> students = new ArrayList<>();

    @PostConstruct
    void init(){
        students.add(new Student("aleksandar.aleksandrovski","pass123", "Aleksandar", "Aleksandrovski" ));
        students.add(new Student("bojan.karagjozov",         "kepler",  "Bojan",      "Karagjozov" ));
        students.add(new Student("martin.vujadinov",         "pass123", "Martin",     "Vujadinov" ));
        students.add(new Student("teodora.miladinova",       "cassiopeia","Teodora", "Miladinova" ));
        students.add(new Student("mihaela.petrovska",        "orion",   "Mihaela",    "Petrovska" ));
    }
    public List<Student> findAllStudents(){
        return students;
    }

    public List<Student> findAllByNameOrSurname(String text){
        return students.stream().filter(s -> s.getName().contains(text) || s.getSurname().contains(text)).collect(Collectors.toList());
    }

    public Student addStudent(String username, String password, String name, String surname){
        Student s = new Student(username,password,name,surname);
        students.add(s);

        return s;
    }


}
