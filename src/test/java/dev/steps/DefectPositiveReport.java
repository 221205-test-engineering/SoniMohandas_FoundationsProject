package dev.steps;
import dev.runners.BugCatchRunner;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DefectPositiveReport{
    public static WebDriver driver = BugCatchRunner.driver;
    @When("The employee selects {string} priority")
    public void the_employee_selects_medium_priority(String priority){
        driver.get("https://bugcatcher-dan.coe.revaturelabs.com/?dev=13");
        driver.findElement(By.xpath("//input[@name='username']")).sendKeys("ryeGuy");
        driver.findElement(By.xpath("//input[@name='pass']")).sendKeys("coolbeans");
        driver.findElement(By.tagName("button")).click();
        driver.findElement(By.xpath("//div[1]/nav/a[3]")).click();
        driver.findElement(By.xpath("//div[1]/form/input[2]")).sendKeys(priority);
    }
    @When("The employee selects {string} severity")
    public void the_employee_selects_medium_severity(String severity){
        driver.findElement(By.xpath("//div[1]/form/input[3]")).sendKeys(severity);
    }
    @Then("There should be a confirmation box")
    public void there_should_be_a_confirmation_box(){
        driver.switchTo().alert().getText().contains("Confirm Bug Report");
    }
    @When("The employee clicks Ok")
    public void the_employee_clicks_ok(){
        driver.switchTo().alert().accept();
    }
    @Then("A modal should appear with a defect ID")
    public void a_modal_should_appear_with_a_defectID(){
        String defectId = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By
                .xpath("//div[3]//div/h4"))).getText();
        System.out.println(defectId);
    }
    @When("The employee clicks close")
    public void the_employee_clicks_close(){
        driver.findElement(By.xpath("//div[3]//div/button")).click();
    }
    @Then("The modal should disappear")
    public void the_modal_should_disappear(){
        Actions action = new Actions(driver);
        action.sendKeys(Keys.ESCAPE);
    }

}
