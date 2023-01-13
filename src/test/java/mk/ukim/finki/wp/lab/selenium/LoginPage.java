package mk.ukim.finki.wp.lab.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends AbstractPage{

    private WebElement username;

    private WebElement password;

    private WebElement submit;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public static LoginPage openLogin(WebDriver driver){
        get(driver, "/login");
        System.out.println(driver.getCurrentUrl());
        return PageFactory.initElements(driver, LoginPage.class);
    }

    public static CoursesPage doLogin(WebDriver driver, LoginPage loginPage, String username, String password){

        loginPage.getDriver().findElement(By.id("username")).sendKeys("admin");
        loginPage.getDriver().findElement(By.id("password")).sendKeys("123");
        loginPage.getDriver().findElement(By.id("password")).sendKeys(Keys.ENTER);
//        loginPage.username.sendKeys(username);
//        loginPage.password.sendKeys(password);
//        loginPage.submit.click();
        System.out.println(driver.getCurrentUrl());

        return PageFactory.initElements(driver, CoursesPage.class);
    }


}
