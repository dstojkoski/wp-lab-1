package mk.ukim.finki.wp.lab.web.servlet;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Grade;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.repository.jpa.GradeRepository;
import mk.ukim.finki.wp.lab.service.CourseService;
import mk.ukim.finki.wp.lab.service.GradeService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet(name = "studentEnrollment", urlPatterns = "/StudentEnrollmentSummary")
public class StudentEnrollmentSummary extends HttpServlet {

    CourseService courseService;
    GradeService gradeService;
    SpringTemplateEngine springTemplateEngine;

    public StudentEnrollmentSummary(CourseService courseService, SpringTemplateEngine springTemplateEngine, GradeService gradeService) {
        this.courseService = courseService;
        this.springTemplateEngine = springTemplateEngine;
        this.gradeService = gradeService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        if (req.getSession().getAttribute("courseId") == null) {
//            resp.sendRedirect("/listCourses");
//            return;
//        }
        WebContext context = new WebContext(req, resp, req.getServletContext());
//        Long id = Long.parseLong(String.valueOf(req.getSession().getAttribute("courseId")));

        Long id = Long.valueOf(req.getParameter("id"));
        Course c = courseService.listAll().stream().filter(s -> s.getCourseId() == id).findFirst().get();

        Map<String, Character> studentGradeMap = gradeService.mappedGrades(c);
        context.setVariable("courseSummary", c);
        context.setVariable("grades", studentGradeMap);
        springTemplateEngine.process("studentsInCourse", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req, resp, req.getServletContext());
        Long id = Long.parseLong(String.valueOf(req.getSession().getAttribute("courseId")));

        String username = req.getParameter("student");
        Course c = courseService.addStudentInCourse(username, id);
        Map<String, Character> studentGradeMap = gradeService.mappedGrades(c);
        context.setVariable("courseSummary", c);
        context.setVariable("grades", studentGradeMap);
        //context.setVariable("studentsInCourse", c.getStudents());
        //req.getSession().invalidate();
        resp.sendRedirect("/courses/" + id);
    }
}
