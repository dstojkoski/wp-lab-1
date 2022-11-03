package mk.ukim.finki.wp.lab.model;

import lombok.Data;

import java.util.List;

@Data
public class Course {
    private long courseId;
    private String name;
    private String description;
    private List<Student> students;

    public Course(long courseId, String name, String description) {
        this.courseId = courseId;
        this.name = name;
        this.description = description;
    }
}
