package mk.ukim.finki.wp.lab.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Course {
    private long courseId;
    private String name;
    private String description;
    private List<Student> students;
    private Teacher teacher;

    public Course(String name, String description) {
        this.courseId = (long) (Math.random() * 1000);
        this.name = name;
        this.description = description;
        students = new ArrayList<>();
    }
}
