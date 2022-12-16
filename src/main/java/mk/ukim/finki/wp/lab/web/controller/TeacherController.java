package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Teacher;
import mk.ukim.finki.wp.lab.service.TeacherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/teachers")
public class TeacherController {
    TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping
    public String getTeachers(Model model){
        List<Teacher> teachers = teacherService.findAll();
        model.addAttribute("teachers", teachers);

        return "listTeachers";
    }

    @GetMapping("/add-teacher")
    public String addTeacher()
    {
        return "add-teacher";
    }

    @PostMapping("/add")
    public String saveTeacher(@RequestParam(required = false) String teacherId,
                              @RequestParam String name,
                              @RequestParam String surname) {
        if(teacherId != null) {
            Teacher t = teacherService.findById(Long.parseLong(teacherId)).get();
            t.getTeacherFullName().setName(name);
            t.getTeacherFullName().setSurname(surname);
            teacherService.save(t);
        }
        else {
            teacherService.addTeacher(name, surname);
        }
        return "redirect:/teachers/";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteTeacher(@PathVariable String id) {
        teacherService.deleteTeacher(Long.parseLong(id));
        return "redirect:/teachers/";
    }

    @GetMapping("/edit/{id}")
    public String editTeachers(@PathVariable String id, Model model) {
        Teacher toEdit = teacherService.findById(Long.parseLong(id)).get();
        model.addAttribute("teacher", toEdit);
//        catch(TeacherNotFoundException ex)
//        {
//            return "redirect:/teachers?error=" + ex.getMessage();
//        }
        return "add-teacher";
    }



}
