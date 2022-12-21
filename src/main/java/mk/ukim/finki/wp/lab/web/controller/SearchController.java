package mk.ukim.finki.wp.lab.web.controller;


import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.service.CourseService;
import mk.ukim.finki.wp.lab.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/full-text")
public class SearchController {
    private final StudentService studentService;
    private final CourseService courseService;

    public SearchController(StudentService studentService, CourseService courseService) {
        this.studentService = studentService;
        this.courseService = courseService;
    }

    @GetMapping
    public String getItems(Model model){
        List<Student> students = studentService.listAll();
        List<Course> course = courseService.listAll();

        model.addAttribute("students", students);
        model.addAttribute("courses", course);

        return "full-text-search";
    }

    @PostMapping
    public String filterItems(@RequestParam String search, Model model){

        List<Student> st = studentService.searchByNameOrSurname(search);
        List<Course>  courses = courseService.listFiltered(search);

        model.addAttribute("foundStudents", st);
        model.addAttribute("students", st);
        model.addAttribute("courses", courses);

        return "full-text-search";

    }
}
