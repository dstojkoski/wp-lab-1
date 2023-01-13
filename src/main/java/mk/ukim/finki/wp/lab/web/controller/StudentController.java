package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.service.CourseService;
import mk.ukim.finki.wp.lab.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/students")
public class StudentController {
    private StudentService studentService;
    private CourseService courseService;


    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/AddStudent")
    String addStudent(Model model){
        model.addAttribute("students", studentService.listAll());
        model.addAttribute("bodyContent", "newListStudents");

        return "master-template";

    }

    @GetMapping("/CreateStudent")
    String createStudent(Model model){
        model.addAttribute("bodyContent", "createStudent");

        return "master-template";
    }
}
