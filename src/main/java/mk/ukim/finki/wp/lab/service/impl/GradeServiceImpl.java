package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Grade;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.repository.jpa.GradeRepository;
import mk.ukim.finki.wp.lab.service.GradeService;
import mk.ukim.finki.wp.lab.service.StudentService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class GradeServiceImpl implements GradeService {

    private GradeRepository gradeRepository;
    private StudentService studentService;

    public GradeServiceImpl(GradeRepository gradeRepository, StudentService studentService) {
        this.gradeRepository = gradeRepository;
        this.studentService = studentService;
    }

    @Override
    public List<Grade> findAll() {
        return gradeRepository.findAll();
    }

    @Override
    public Grade findByStudentAndCourse(Student s, Course c) {
        return gradeRepository.findByStudentAndCourse(s, c);
    }

    @Override
    public Map<String, Character> mappedGrades(Course c) {
        List<Grade> grades = gradeRepository.findByCourse(c);
        Map<String, Character> map = new HashMap<>();
        for(Grade g : grades){
            map.put(g.getStudent().getUsername(), g.getGrade());
        }
        return map;
    }

    @Override
    @Transactional
    public Grade save(Character grade, Student s, Course c, LocalDateTime date) {
        gradeRepository.deleteByStudentAndCourse(s, c);

        Grade g = new Grade();
        g.setGrade(grade);
        g.setCourse(c);
        g.setStudent(s);
        g.setTimestamp(date);
        return this.gradeRepository.save(g);
    }

    @Override
    public List<Grade> findBetween(LocalDateTime from, LocalDateTime to, Course c) {
        return gradeRepository.findByTimestampBetweenAndCourse(from, to, c);
    }

    @Override
    @Transactional
    public void deleteByCourse(Course course) {
        this.gradeRepository.deleteByCourse(course);
    }

    @Override
    @Transactional
    public void deleteByStudent(Student student) {
        this.gradeRepository.deleteByStudent(student);
    }
}
