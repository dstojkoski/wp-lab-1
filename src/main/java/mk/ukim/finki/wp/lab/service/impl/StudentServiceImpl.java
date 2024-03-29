package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.model.exceptions.InvalidUsernameOrPasswordException;
import mk.ukim.finki.wp.lab.model.exceptions.StudentAlreadyExistsException;
import mk.ukim.finki.wp.lab.repository.jpa.StudentRepository;
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
        return studentRepository.findAll();
    }

    @Override
    public List<Student> searchByNameOrSurname(String text) {
        text = text.trim();
        return studentRepository.findAllByNameIgnoreCaseContainsOrSurnameIgnoreCaseContainsOrUsernameIgnoreCaseContains(text, text, text);
    }

    @Override
    public Student searchByUsername(String username) {
        return studentRepository.findStudentByUsername(username).get();
    }

    @Override
    public Student save(String username, String password, String name, String surname) {
        if(username == null || username.isEmpty() || password == null || password.isEmpty()){
            throw new InvalidUsernameOrPasswordException();
        }

        studentRepository.findStudentByUsername(username).ifPresent(s  -> { throw new StudentAlreadyExistsException(username);});

        return studentRepository.save(new Student(username, password, name, surname));
    }
}
