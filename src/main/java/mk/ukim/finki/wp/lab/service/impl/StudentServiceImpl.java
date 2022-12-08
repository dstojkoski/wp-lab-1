package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.repository.StudentRepository;
import mk.ukim.finki.wp.lab.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> listAll() {
        return studentRepository.findAllStudents();
    }

    @Override
    public List<Student> searchByNameOrSurname(String text) {
        return studentRepository.findAllByNameOrSurname(text.trim());
    }

    @Override
    public List<Student> search(String text) {
        return studentRepository.findAllByNameOrUsername((text.trim()));
    }

    @Override
    public Student searchByUsername(String username) {
        return studentRepository.findAllStudents().stream().filter(s->s.getUsername().equals(username)).findFirst().get();
    }

    @Override
    public Student save(String username, String password, String name, String surname) {
        return studentRepository.addStudent(username, password, name, surname);
    }
}
