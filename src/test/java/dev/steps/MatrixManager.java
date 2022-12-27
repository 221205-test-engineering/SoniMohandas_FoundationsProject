package dev.steps;

import dev.runners.BugCatchRunner;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class MatrixManager{
    public static WebDriver driver = BugCatchRunner.driver;

    @When("The manager chooses to create a new matrix")
    public void the_manager_chooses_to_create_new_matrix(){
        driver.get("https://bugcatcher-dan.coe.revaturelabs.com/?dev=13");
        driver.findElement(By
                .xpath("//input[@name='username']")).sendKeys("g8tor");
        driver.findElement(By
                .xpath("//input[@name='pass']")).sendKeys("chomp!");
        driver.findElement(By.tagName("button")).click();
        driver.findElement(By.xpath("//div/button")).click();
    }
    @When("The manager creates a title for the matrix")
    public void the_manager_create_title_for_the_matrix(){
        driver.findElement(By.xpath("//div/input")).sendKeys("New Matrix Title");
    }
    @When("The manager adds requirements to the matrix")
    public void the_manager_adds_requirments_to_the_matrix(){
        driver.findElement(By.xpath("//div/fieldset/table/tbody/tr/td[1]/input"))
                .sendKeys("First matrix story");
        Select priority = new Select(driver.findElement(By
                .xpath("//div/fieldset/table/tbody/tr/td[2]/select")));
        priority.getOptions().get(1);
        driver.findElement(By
                        .xpath("//div/fieldset/table/tbody/tr/td[3]/input"))
                .sendKeys("Do it in two days");
        driver.findElement(By.xpath("//div/fieldset/button")).click(); // Adds requirements to the matrix
    }
    @When("The manager saves the matrix")
    public void the_manager_saves_the_matrix(){
        driver.findElement(By.xpath("//div/button")).click(); // Saves the matrix
    }
    @Then("An alert with a success message should appear")
    public void an_alert_with_success_should_appear(){
        String alert = driver.switchTo().alert().getText();
        String al = alert.substring(alert.lastIndexOf(" ")+1);
        assertEquals(al, "created");
        assertNotNull(driver.switchTo().alert().getText());
        driver.switchTo().alert().accept();
    }
    @Given("The manager is on the matrix homepage")
    public void the_manager_is_on_the_matrix_homepage(){
        driver.findElement(By.xpath("//div/nav/a[1]")).click();
        String matrics = driver.findElement(By.xpath("//div/h1")).getText();
        assertEquals(matrics, "Matrices");
    }
    @Given("The manager has selected the matrix")
    public void the_manager_selected_the_matrix(){
        driver.findElement(By.xpath("//div/ul/li[last()]/div/span/button")).click();
        driver.findElement(By.xpath("//div/ul/li[last()]//div/table/tbody/tr/td[6]/button")).click();
    }
    @When("The manager adds a defect")
    public void the_manager_adds_a_defect(){
        driver.findElement(By.xpath("//div/ul/li[last()]//div/ul[2]/li/input")).clear();
        driver.findElement(By
                        .xpath("//div/ul/li[last()]//div/ul[2]/li/input"))
                .sendKeys("901");
    }
    @When("The manager confirms their changes")
    public void the_manager_confirms_changes(){
        driver.findElement(By
                .xpath("//div/ul/li[last()]//div/ul[2]/li/button")).click();
    }
    @Then("Then the matrix should be saved")
    public void the_matrix_should_saved(){
        driver.findElement(By
                .xpath("//div/ul/li[last()]//div/button")).click();
        driver.switchTo().alert().accept();
    }
    @When("The manager adds a test case")
    public void the_manager_adds_test_case(){
        WebElement input = driver.findElement(By.xpath("//div/ul/li[last()]//div/ul[1]/li[2]/input"));
        input.clear();
        WebElement value = driver.findElement(By.xpath("//div/ul/li[last()]//div/datalist[1]/option[1]"));
        String text = value.getAttribute("innerHTML");
        input.sendKeys(text);

    }
}
