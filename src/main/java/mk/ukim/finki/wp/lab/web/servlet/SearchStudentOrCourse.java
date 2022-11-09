package mk.ukim.finki.wp.lab.web.servlet;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.service.CourseService;
import mk.ukim.finki.wp.lab.service.StudentService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name="searchStudent", urlPatterns = "/search")
public class SearchStudentOrCourse extends HttpServlet {

    private final StudentService studentService;
    private final CourseService courseService;
    private final SpringTemplateEngine springTemplateEngine;

    public SearchStudentOrCourse(StudentService studentService, CourseService courseService, SpringTemplateEngine springTemplateEngine) {
        this.studentService = studentService;
        this.courseService = courseService;
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("students", studentService.listAll());
        context.setVariable("courses", courseService.listAll());

        this.springTemplateEngine.process("searchStudentsOrCourses.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req, resp, req.getServletContext());
        String s = req.getParameter("search");

        List<Student> st = studentService.searchByNameOrSurname(s);
        //List<Course>  course = courseService.listByText(s);

        context.setVariable("foundStudents", st);
        context.setVariable("students", st);
       // context.setVariable("students", studentService.listAll());
        context.setVariable("courses", courseService.listAll());

        this.springTemplateEngine.process("searchStudentsOrCourses.html", context, resp.getWriter());

    }
}
