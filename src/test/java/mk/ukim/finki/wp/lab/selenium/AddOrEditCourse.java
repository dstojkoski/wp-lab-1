package mk.ukim.finki.wp.lab.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class AddOrEditCourse extends AbstractPage {
    private WebElement name;
    private WebElement description;
    private WebElement teacherId;
    private WebElement submit;

    public AddOrEditCourse(WebDriver driver) {
        super(driver);
    }

    public static CoursesPage addCourse(WebDriver driver, String name, String description, String teacher) {
        get(driver, "courses/add-form");
        AddOrEditCourse addOrEditCourse = PageFactory.initElements(driver, AddOrEditCourse.class);

        addOrEditCourse.name.sendKeys(name);
        addOrEditCourse.description.sendKeys(description);

        //driver.findElement(By.name("teacherId")).click();
        addOrEditCourse.teacherId.click();
        {
            WebElement dropdown = driver.findElement(By.name("teacherId"));
            dropdown.findElement(By.xpath("//option[. = 'Riste Stojanov']")).click();
        }
        addOrEditCourse.submit.click();
        return PageFactory.initElements(driver, CoursesPage.class);
    }
}