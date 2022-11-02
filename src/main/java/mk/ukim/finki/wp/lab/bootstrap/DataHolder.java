package mk.ukim.finki.wp.lab.bootstrap;


import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class DataHolder {

    public static List<Course> courses = new ArrayList<>();
    public static List<Student> students = new ArrayList<>();

    @PostConstruct
    void init(){
        students.add(new Student("aleksandar.aleksandrovski","pass123", "Aleksandar", "Aleksandrovski" ));
        students.add(new Student("bojan.karagjozov",         "kepler",  "Bojan",      "Karagjozov" ));
        students.add(new Student("martin.vujadinov",         "pass123", "Martin",     "Vujadinov" ));
        students.add(new Student("teodora.miladinova",       "cassiopeia","Teodora", "Miladinova" ));
        students.add(new Student("mihaela.petrovska",        "orion",   "Mihaela",    "Petrovska" ));

        courses.add(new Course(100, "Веб Програмирање", "Learning web programming", new ArrayList<Student>(Arrays.asList(students.get(0),students.get(1)))));
        courses.add(new Course(200, "Алгоритми и податочни структури", "Fundamental algorithms and data structures", new ArrayList<Student>(Arrays.asList(students.get(1),students.get(3)))));
        courses.add(new Course(300, "Линеарна алгебра", "Vectors and matrices", new ArrayList<Student>(students.subList(2,4))));
        courses.add(new Course(400, "Паралелно програмирање", "Learning parallel programming", new ArrayList<Student>(Arrays.asList(students.get(4),students.get(4)))));
        courses.add(new Course(500, "Компјутерска графика", "Fundamentals of computer graphics", new ArrayList<Student>(Arrays.asList(students.get(1),students.get(3), students.get(4)))));

     }
}
