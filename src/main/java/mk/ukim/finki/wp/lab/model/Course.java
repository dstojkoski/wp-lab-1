package mk.ukim.finki.wp.lab.model;

import lombok.Data;
import mk.ukim.finki.wp.lab.model.enumerations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long courseId;

    @Column(unique = true)
    private String name;

    @Column(length = 4000)
    private String description;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Student> students;

    @ManyToOne
    private Teacher teacher;

    @Enumerated(EnumType.STRING)
    private Type type;

    public Course(String name, String description, Type t) {
        this.name = name;
        this.description = description;
        students = new ArrayList<>();
        this.type = t;
    }
    public Course(String name, String description,Teacher teacher, Type t) {
        this.name = name;
        this.description = description;
        students = new ArrayList<>();
        this.teacher = teacher;
        this.type = t;
    }


    public Course() {

    }
}
