package dev.steps;

import dev.runners.BugCatchRunner;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

public class DefectNegativeReporting{
    public static WebDriver driver = BugCatchRunner.driver;
    @Given("The employee is on the Defect Reporter Page")
    public void the_employee_is_on_the_defect_reporter_page(){
        driver.get("https://bugcatcher-dan.coe.revaturelabs.com/?dev=13");
        driver.findElement(By.xpath("//input[@name='username']")).sendKeys("ryeGuy");
        driver.findElement(By.xpath("//input[@name='pass']")).sendKeys("coolbeans");
        driver.findElement(By.tagName("button")).click();
        driver.findElement(By.xpath("//div[1]/nav/a[3]")).click();
    }
    @When("The employee selects todays date")
    public void the_employee_selects_todays_date(){
        //Dependencies DateFormat and Date
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date today = Calendar.getInstance().getTime();
        String date = dateFormat.format(today);
        driver.findElement(By.name("dateReported")).sendKeys("date");
        System.out.println(date);
    }

    @When("The employee types in description with")
    public void the_employee_types_in_description_with(String docString){
        driver.findElement(By.name("desc")).sendKeys(docString);
    }

    @When("The employee types in steps with")
    public void the_employee_types_in_steps_with(String docString){
        driver.findElement(By.name("stepsToReproduce")).sendKeys(docString);
    }

    @When("The employee selects high priority")
    public void the_employee_selects_high_priority(){
        driver.findElement(By.name("priority")).sendKeys("High");
    }
    @When("The employee selects low severity")
    public void the_employee_selects_low_severity(){
        driver.findElement(By.name("severity")).sendKeys("Low");
    }
    @When("The employee clicks the report button")
    public void the_employee_clicks_the_report_button(){
        driver.findElement(By.xpath("//button[@type='submit']")).submit();
    }
    @Then("No confirmation dialog appears")
    public void no_confirmation_dialog_appears(){
        String alertDialog = driver.switchTo().alert().getText();
        assertNotEquals(alertDialog, "No confirmation");
        System.out.println(alertDialog);
        driver.switchTo().alert().accept();
    }
}
