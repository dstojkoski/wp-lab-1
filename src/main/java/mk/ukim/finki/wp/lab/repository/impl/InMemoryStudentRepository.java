package mk.ukim.finki.wp.lab.repository.impl;

import mk.ukim.finki.wp.lab.model.Student;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class InMemoryStudentRepository {

    private List<Student> students = new ArrayList<>();

    @PostConstruct
    void init(){
        students.add(new Student("aleksandar.aleksandrovski","pass123", "Александар", "Александровски" ));
        students.add(new Student("bojan.karagjozov",         "kepler",       "Бојан",        "Бојанов" ));
        students.add(new Student("martin.vujadinov",         "pass123",     "Мартин",       "Мартинов" ));
        students.add(new Student("mario.jovanov",            "testest",      "Марио",        "Јованов" ));
        students.add(new Student("teodora.miladinova",       "cassiopeia", "Теодора",     "Миладинова" ));
        students.add(new Student("jovana.aleksandrova",        "orion",       "Јована", "Александрова" ));
    }
    public List<Student> findAllStudents(){
        return students;
    }

    public List<Student> findAllByNameOrSurname(String text){
        return students.stream().filter(s -> s.getName().toLowerCase().contains(text.toLowerCase()) || s.getSurname().toLowerCase().contains(text.toLowerCase())).collect(Collectors.toList());
    }

    public List<Student> findAllByNameOrUsername(String text){
        final String t = text.toLowerCase();
        return students.stream().filter(s -> s.getName().toLowerCase().contains(t)
                || s.getSurname().toLowerCase().contains(t)
                || s.getUsername().toLowerCase().contains(t)).collect(Collectors.toList());

    }

    public Student addStudent(String username, String password, String name, String surname){
        Student s = new Student(username,password,name,surname);
        students.add(s);

        return s;
    }


}
