package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Teacher;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class TeacherRepository {
    private List<Teacher> teachers;

    @PostConstruct
    void init() {
        teachers = new ArrayList<>();
        teachers.add(new Teacher("Ристе", "Стојанов"));
        teachers.add(new Teacher("Сашо","Граматиков"));
        teachers.add(new Teacher("Димитар","Трајанов"));
        teachers.add(new Teacher("Ана","Тодоровска"));
        teachers.add(new Teacher("Костадин","Мишев"));
    }

    public List<Teacher> findAll(){
        return teachers;
    }

    public Optional<Teacher> findById(Long id){
        return teachers.stream().filter(t -> t.getId().equals(id)).findFirst();
    }

}
