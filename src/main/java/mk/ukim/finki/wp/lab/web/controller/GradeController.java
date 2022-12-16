package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Grade;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.service.CourseService;
import mk.ukim.finki.wp.lab.service.GradeService;
import mk.ukim.finki.wp.lab.service.StudentService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/grades")
public class GradeController {

    private final CourseService courseService;
    private final StudentService studentService;
    private final GradeService gradeService;

    public GradeController(CourseService courseService, StudentService studentService, GradeService gradeService) {
        this.courseService = courseService;
        this.studentService = studentService;
        this.gradeService = gradeService;
    }

    @GetMapping("/allGrades")
    public String showGrades(@RequestParam(required = false) String error,
                             @RequestParam(required = false) String from,
                             @RequestParam(required = false) String to,
                             @RequestParam(required = false) String courseId,
                             Model model)
    {
        if(error != null)
        {
            model.addAttribute("hasError",true);
            model.addAttribute("error",error);
        }
        if(from != null && to != null)
        {
            LocalDateTime fromPar = LocalDateTime.parse(from);
            LocalDateTime toPar = LocalDateTime.parse(to);
            Course c = courseService.findById(Long.parseLong(courseId)).get();
            model.addAttribute("grades",gradeService.findBetween(fromPar,toPar,c));
            model.addAttribute("courses",courseService.listAll());
        }
        else {
            model.addAttribute("grades", gradeService.findAll());
            model.addAttribute("courses", courseService.listAll());
        }
        model.addAttribute("bodyContent","listGrades");
        return "listGrades";
    }

    @GetMapping
    public String listGrades(Model model){
        List<Grade> grades = gradeService.findAll();

        model.addAttribute("allGrades", grades);

        return "listGrades";
    }

    @GetMapping("/add-grade/{course-id}&{student-id}")
    public String addGrade(@PathVariable("course-id") Long cid, @PathVariable("student-id") String student, Model model){
            Course course = this.courseService.findById(cid).get();
            Student st = studentService.searchByUsername(student);

            model.addAttribute("course", course);
            model.addAttribute("student", student);
            return "add-grade";

    }

    @PostMapping("/add-grade/{id}")
    public String addGradeToStudent(@RequestParam("date")
                                        @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime date,
                                    @RequestParam String student,
                                    @RequestParam String grade,
                                    @PathVariable Long id){

        gradeService.save(grade.charAt(0), studentService.searchByUsername(student), courseService.findById(id).get(), date);
        return "redirect:/courses";
    }
}
