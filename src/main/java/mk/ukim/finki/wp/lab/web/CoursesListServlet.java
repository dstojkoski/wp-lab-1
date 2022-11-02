package mk.ukim.finki.wp.lab.web;

import mk.ukim.finki.wp.lab.service.CourseService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name="courses", urlPatterns = "/listCourses")
public class CoursesListServlet extends HttpServlet {

    private final CourseService courseService;
    public CoursesListServlet(CourseService courseService) {
        this.courseService = courseService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();

        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset=\"utf-8\">");
        out.println("<title>Welcome and Choose a Course</title>");
        out.println("<style type=\"text/css\">");
        out.println("body {");
        out.println(" width: 800px;");
        out.println("margin: auto;");
        out.println("}");
        out.println("</style>");
        out.println("<body>");
        out.println("<header>");
        out.println("<h1>Courses List</h1>");
        out.println("</header>");
        out.println("<main>");
        out.println("<h2>Choose course:</h2>");

        courseService.listAll().stream().forEach(s -> out.format("<input type=\"radio\" name=\"courseId\" value=\"%d\"> %s<br/>\n",  s.getCourseId(),s.getName()));
        out.println("<br/>");
        out.println("<a href=\"/АddStudent\">Submit</a>");
        out.println("</main>");
        out.println("</body>");
        out.println("</html");

        out.flush();
    }
}
