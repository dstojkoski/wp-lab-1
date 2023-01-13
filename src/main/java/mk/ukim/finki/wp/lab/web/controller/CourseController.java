package mk.ukim.finki.wp.lab.web.controller;


import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.model.Teacher;
import mk.ukim.finki.wp.lab.model.enumerations.Type;
import mk.ukim.finki.wp.lab.service.CourseService;
import mk.ukim.finki.wp.lab.service.GradeService;
import mk.ukim.finki.wp.lab.service.TeacherService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller // Vrakja view
// @RestController kreira RESTful API, ne vrakja view, tuku vrakja podatoci (primer JSON)
// Klientskata aplikacija (React, Angular) pravi povici do toa API
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;
    private final TeacherService teacherService;
    private final GradeService gradeService;

    public CourseController(CourseService courseService, TeacherService teacherService, GradeService gradeService) {
        this.courseService = courseService;
        this.teacherService = teacherService;
        this.gradeService = gradeService;
    }

    @GetMapping
    public String courses(@RequestParam(required = false) String error, Model model)  {
        if(error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }

        List<Course> courses = courseService.listAll();
        model.addAttribute("courses", courses);
        model.addAttribute("bodyContent", "listCourses");
        return "master-template";
    }

    @GetMapping("/add-student/{id}")
    public String addStudent(@PathVariable Long id, HttpServletRequest req){
        req.getSession().setAttribute("courseId", id);

        return "redirect:/students/AddStudent";
    }

    @GetMapping("/edit-form/{id}")
    public String getEditCoursePage(@PathVariable Long id, Model model) {
        if(this.courseService.findById(id).isPresent()){
            Course course = this.courseService.findById(id).get();
            List<Teacher> teachers = this.teacherService.findAll();

            model.addAttribute("course", course);
            model.addAttribute("teachers", teachers);
            model.addAttribute("types", Type.values());
            model.addAttribute("bodyContent", "add-course");
            return "master-template";
        }
        return "redirect:/courses?error=CourseNotFound";
    }


    @GetMapping("/access_denied")
    public String getAccessDeniedPage(Model model) {
        model.addAttribute("bodyContent", "access_denied");
        return "master-template";
    }

    @GetMapping("/{id}")
    public String getCourseInfo(@PathVariable Long id, HttpServletRequest req, Model model){
        if(this.courseService.findById(id).isPresent()){
            Course c = this.courseService.findById(id).get();
            Map<String, Character> studentGradeMap = gradeService.mappedGrades(c);

            model.addAttribute("courseSummary", c);
            model.addAttribute("grades", studentGradeMap);
            model.addAttribute("bodyContent", "studentsInCourse");
            req.getSession().setAttribute("courseId", id);
            return "master-template";
        }
        return "redirect:/courses?error=CourseNotFound";
    }

    @GetMapping("/add-form")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getAddCoursePage(Model model) {
        List<Teacher> teachers = this.teacherService.findAll();

        model.addAttribute("teachers", teachers);
        model.addAttribute("types", Type.values());
        model.addAttribute("bodyContent", "add-course");
        return "master-template";
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
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
