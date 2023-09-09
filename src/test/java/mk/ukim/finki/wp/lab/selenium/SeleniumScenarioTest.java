//package mk.ukim.finki.wp.lab.selenium;
//
//import mk.ukim.finki.wp.lab.model.Course;
//import mk.ukim.finki.wp.lab.model.Teacher;
//import mk.ukim.finki.wp.lab.model.TeacherFullName;
//import mk.ukim.finki.wp.lab.model.enumerations.Type;
//import mk.ukim.finki.wp.lab.service.CourseService;
//import mk.ukim.finki.wp.lab.service.TeacherService;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.openqa.selenium.htmlunit.HtmlUnitDriver;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.ActiveProfiles;
//
//@ActiveProfiles("test")
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
//public class SeleniumScenarioTest {
//
//
//    @Autowired
//    CourseService courseService;
//
//    @Autowired
//    TeacherService teacherService;
//
//    private static Teacher t1;
//    private static Course c1;
//
//    private static String user = "user";
//    private static String admin = "admin";
//
//    private static boolean dataInitialized = false;
//
//    private HtmlUnitDriver driver;
//
//    private void initData(){
//        if(!dataInitialized){
//            t1 = teacherService.save(new Teacher(new TeacherFullName("Riste", "Stojanov")));
//            //courseService.addCourse("Web Programming", "Desc", t1.getId(), Type.WINTER);
//            dataInitialized = true;
//        }
//    }
//
//    @BeforeEach
//    private void setup(){
//        this.driver = new HtmlUnitDriver(true);
//        initData();
//    }
//
//
//
//    @AfterEach
//    public void destroy(){
//        if(this.driver!=null){
//            this.driver.close();
//        }
//    }
//
//    @Test
//    public void testScenario() throws Exception{
//
//        CoursesPage coursesPage =  CoursesPage.to(this.driver);
//        coursesPage.assertElements(0,0,0,0,0);
//        LoginPage loginPage = LoginPage.openLogin(this.driver);
//
//        LoginPage.doLogin(this.driver, loginPage, "admin", "123");
//        coursesPage.assertElements(0,0,0,1, 0);
//
//        coursesPage = AddOrEditCourse.addCourse(this.driver, "Web Programming", "WP", t1.getTeacherFullName().getName() + " " + t1.getTeacherFullName().getSurname());
//        coursesPage.assertElements(1,1,1,1, 1);
//
//        coursesPage = AddOrEditCourse.addCourse(this.driver, "Robotics", "Rob", "Riste Stojanov");
//        coursesPage.assertElements(2,2,2,1, 2);
//
//
//
//    }
//
//
//}
