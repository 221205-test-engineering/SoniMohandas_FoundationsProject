package dev.steps;

import dev.runners.BugCatchRunner;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class LoginPositiveImpn{
    public static WebDriver driver = BugCatchRunner.driver;

    @Given("The employee is on the login page")
    public void the_employee_is_on_the_login_page() throws Throwable{
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://bugcatcher-dan.coe.revaturelabs.com/?dev=13");
        System.out.println("I am on the login page");
    }
    @When("The employee types {string} into username input")
    public void the_employee_inputs_username(String username) throws Throwable{
       driver.findElement(By.xpath("//input[@name='username']")).sendKeys(username);
    }
    @When("The employee types {string} into password input")
    public void the_employee_inputs_password(String password) throws Throwable{
        driver.findElement(By.xpath("//input[@name='pass']")).sendKeys(password);
    }
    @When("The employee clicks on the login button")
    public void the_employee_clicks_login_button() throws Throwable{
        driver.findElement(By.tagName("button")).click();

    }
    @Then("the employee should be on the {string} page")
    public void the_employee_should_be_on_the_page(String home) throws Throwable {
        String title = driver.findElement(By.xpath("/html/body/div/h1")).getText();
        System.out.println(title);
        assertEquals(home + " Home", title);
        Thread.sleep(3000);
    }
    @Then("The employee should see their name {string} {string} on the home page")
    public void the_employee_should_see_their_name_on_the_home_page(String fname, String lname) throws Throwable {
        String str = driver.findElement(By.xpath("/html/body/div/nav/p")).getText();
        System.out.println(str);
        String name = str.substring(str.indexOf(" ")+1);
        assertEquals(name, fname+" " +lname);
    }
}
