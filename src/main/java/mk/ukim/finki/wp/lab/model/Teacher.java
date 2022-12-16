package mk.ukim.finki.wp.lab.model;


import lombok.Data;
import mk.ukim.finki.wp.lab.model.converter.TeacherFullNameConverter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Convert(converter = TeacherFullNameConverter.class)
    private TeacherFullName teacherFullName;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate dateOfEmployment;

    public Teacher(TeacherFullName teacherFullName) {
        this.teacherFullName = teacherFullName;
        //this.dateOfEmployment = date;
        this.dateOfEmployment = LocalDate.now();
    }

    public Teacher() {

    }
}
