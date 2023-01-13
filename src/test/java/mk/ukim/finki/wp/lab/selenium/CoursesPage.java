package mk.ukim.finki.wp.lab.selenium;

import lombok.Getter;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

@Getter
public class CoursesPage extends AbstractPage {

    @FindBy(css = "tr[class=course]")
    private List<WebElement> courseRows;

    @FindBy(css = ".delete-course")
    private List<WebElement> deleteButtons;

    @FindBy(css = ".edit-course")
    private List<WebElement> editButtons;

    @FindBy(css = ".add-student")
    private List<WebElement> addStudentButtons;

    @FindBy(css = ".add-course")
    private List<WebElement> addCourseButton;


    public CoursesPage(WebDriver driver) {
        super(driver);
    }

    public static CoursesPage to(WebDriver driver) {
        get(driver, "/courses");
        return PageFactory.initElements(driver, CoursesPage.class);
    }


    public void assertElements(int coursesNumber, int deleteBtn, int editBtn, int addCourseBtn, int addStudentBtn) {
        Assert.assertEquals("rows do not match", courseRows.size(), coursesNumber);
        Assert.assertEquals("editBtn do not match", editButtons.size(), editBtn);
        Assert.assertEquals("deleteBtn do not match", deleteButtons.size(), deleteBtn);
        Assert.assertEquals("addCourse do not match", addCourseButton.size(), addCourseBtn);
        Assert.assertEquals("addCourse do not match", addStudentButtons.size(), addStudentBtn);
    }


}
