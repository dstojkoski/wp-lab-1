package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Teacher;
import mk.ukim.finki.wp.lab.model.TeacherFullName;
import mk.ukim.finki.wp.lab.repository.jpa.TeacherRepository;
import mk.ukim.finki.wp.lab.service.TeacherService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;

    public TeacherServiceImpl(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Override
    public List<Teacher> findAll() {
        return teacherRepository.findAll();
    }

    public Optional<Teacher> findById(Long id){
        return teacherRepository.findById(id);
    }

    @Override
    @Transactional
    public Teacher addTeacher(String name, String surname) {
        Teacher t = new Teacher(new TeacherFullName(name,surname));

        teacherRepository.save(t);
        return t;
    }

    @Override
    public void deleteTeacher(Long id) {
        teacherRepository.deleteById(id);
    }

    @Override
    public Teacher save(Teacher t){
        return teacherRepository.save(t);
    }
}
