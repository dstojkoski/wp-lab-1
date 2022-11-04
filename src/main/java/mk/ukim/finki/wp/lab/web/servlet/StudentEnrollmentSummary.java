package mk.ukim.finki.wp.lab.web.servlet;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.service.CourseService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "studentEnrollment", urlPatterns = "/StudentEnrollmentSummary")
public class StudentEnrollmentSummary extends HttpServlet {

    CourseService courseService;
    SpringTemplateEngine springTemplateEngine;

    public StudentEnrollmentSummary(CourseService courseService, SpringTemplateEngine springTemplateEngine) {
        this.courseService = courseService;
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        if (req.getSession().getAttribute("courseId") == null) {
//            resp.sendRedirect("/listCourses");
//            return;
//        }
        WebContext context = new WebContext(req, resp, req.getServletContext());
        Long id = Long.parseLong((String) req.getSession().getAttribute("courseId"));

        Course c = courseService.listAll().stream().filter(s -> s.getCourseId() == id).findFirst().get();
        context.setVariable("courseSummary", c);
        springTemplateEngine.process("studentsInCourse.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req, resp, req.getServletContext());
        Long id = Long.parseLong((String) req.getSession().getAttribute("courseId"));

        String username = req.getParameter("student");
        Course c = courseService.addStudentInCourse(username, id);

        context.setVariable("courseSummary", c);
        //context.setVariable("studentsInCourse", c.getStudents());

        springTemplateEngine.process("studentsInCourse.html", context, resp.getWriter());
    }
}
