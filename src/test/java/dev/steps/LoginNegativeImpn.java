package dev.steps;

import dev.runners.BugCatchRunner;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.time.Duration;

public class LoginNegativeImpn {
    public static WebDriver driver = BugCatchRunner.driver;

    @When("The employee types in {string} into the username input")
    public void the_employee_inputs_username(String username) throws InterruptedException {
        driver.findElement(By.xpath("//input[@name='username']")).sendKeys(username);
        Thread.sleep(2000);
    }

    @When("The employee types in {string} into the password input")
    public void employee_inputs_password(String password){
        driver.findElement(By.xpath("//input[@name='pass']")).sendKeys(password);
    }
    @Then("The employee should see an alert saying they have the wrong password")
    public void employee_should_alert_as_entered_wrong_password() throws InterruptedException {
        driver.switchTo().alert().getText()
                .contains("they have the wrong password");
        System.out.println(driver.switchTo().alert().getText());
        driver.switchTo().alert().accept();
        Thread.sleep(2000);
    }

    @Then("The employee should see an alert saying no user with that username found")
    public void employee_should_alert_no_user_with_that_username(){
        driver.switchTo()
                .alert().getText().contains("no user with that username found");
        System.out.println(driver.switchTo().alert().getText());
        driver.switchTo().alert().accept();
    }
}

