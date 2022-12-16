package mk.ukim.finki.wp.lab.web.controller;


import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Teacher;
import mk.ukim.finki.wp.lab.model.enumerations.Type;
import mk.ukim.finki.wp.lab.service.CourseService;
import mk.ukim.finki.wp.lab.service.TeacherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller // Vrakja view
// @RestController kreira RESTful API, ne vrakja view, tuku vrakja podatoci (primer JSON)
// Klientskata aplikacija (React, Angular) pravi povici do toa API
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;
    private final TeacherService teacherService;

    public CourseController(CourseService courseService, TeacherService teacherService) {
        this.courseService = courseService;
        this.teacherService = teacherService;
    }

    @GetMapping
    public String getCoursesPage(@RequestParam(required = false) String error, Model model)  {
        if(error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }

        model.addAttribute("courses", courseService.listAll());
        return "listCourses";
    }

    @GetMapping("/add-student/{id}")
    public String addStudent(@PathVariable Long id, HttpServletRequest req){
        req.getSession().setAttribute("courseId", id);

        return "redirect:/AddStudent";
    }

    @GetMapping("/edit-form/{id}")
    public String getEditCoursePage(@PathVariable Long id, Model model) {
        if(this.courseService.findById(id).isPresent()){
            Course course = this.courseService.findById(id).get();
            List<Teacher> teachers = this.teacherService.findAll();

            model.addAttribute("course", course);
            model.addAttribute("teachers", teachers);
            model.addAttribute("types", Type.values());
            return "add-course";
        }
        return "redirect:/courses?error=CourseNotFound";
    }


    @GetMapping("/add-form")
    public String getAddCoursePage(Model model) {
        List<Teacher> teachers = this.teacherService.findAll();

        model.addAttribute("teachers", teachers);
        model.addAttribute("types", Type.values());
        return "add-course";
    }

    @PostMapping("/add")
    public String saveCourse(@RequestParam String course,
                             @RequestParam String description,
                             @RequestParam Long teacherId,
                             @RequestParam Type courseType
                             ){
        courseService.addCourse(course, description, teacherId,  courseType);

        return "redirect:/courses";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteCourse(@PathVariable Long id){

        courseService.deleteById(id);

        return "redirect:/courses";
    }

}
