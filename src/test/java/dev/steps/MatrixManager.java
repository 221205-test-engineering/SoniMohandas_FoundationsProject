package dev.steps;

import dev.runners.BugCatchRunner;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class MatrixManager{
    public static WebDriver driver = BugCatchRunner.driver;

    @When("The manager chooses to create a new matrix")
    public void the_manager_chooses_to_create_new_matrix(){
        driver.findElement(By.xpath("//div/button")).click();
    }
    @When("The manager creates a title for the matrix")
    public void the_manager_create_title_for_the_matrix(){
        driver.findElement(By.name("title")).sendKeys("New Matrix Title");
    }
    @When("The manager adds requirements to the matrix")
    public void the_manager_adds_requirments_to_the_matrix(){
        driver.findElement(By.xpath("//input[@placeholder='User Story']"))
                .sendKeys("First matrix story");
        Select priority = new Select(driver.findElement(By
                .xpath("//div/fieldset/table/tbody/tr/td[2]/select")));
        priority.getOptions().get(1);
        driver.findElement(By
                        .xpath("//input[@placeholder='Note']"))
                .sendKeys("Do it in two days");
        driver.findElement(By.xpath("//button[text()='Add Requirement']")).click(); // Adds requirements to the matrix
    }
    @When("The manager saves the matrix")
    public void the_manager_saves_the_matrix(){
        driver.findElement(By.xpath("//button[text()='Create Matrix']")).click(); // Saves the matrix
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
        driver.findElement(By.linkText("Matrices")).click();
        String matrics = driver.findElement(By.xpath("//div/h1")).getText();
        assertEquals(matrics, "Matrices");
    }
    @Given("The manager has selected the matrix")
    public void the_manager_selected_the_matrix(){
        driver.findElement(By.xpath("//button/parent::*")).click();

    }
    @When("The manager adds a defect")
    public void the_manager_adds_a_defect(){
        driver.findElement(By.xpath("//div/ul/li[1]//div/table/tbody/tr[1]/td[6]/button")).click();
        WebElement defectId = new WebDriverWait(driver, Duration
                .ofSeconds(10)).until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//div[@class='Collapsible__contentInner']//input[@list = 'defectlist']")));
        defectId.clear();
        defectId.sendKeys("901");
    }
    @When("The manager confirms their changes")
    public void the_manager_confirms_changes(){
        driver.findElement(By
                .xpath("//div[@class='Collapsible__contentInner']/ul[2]/li[last()]/button")).click();

    }
    @Then("Then the matrix should be saved")
    public void the_matrix_should_saved(){
        new WebDriverWait(driver, Duration
                .ofSeconds(10)).until(ExpectedConditions
                .elementToBeClickable(By
                .xpath("//div[@class='Collapsible__contentInner']/button"))).click();
        assertEquals(driver.switchTo().alert().getText(), "Matrix Saved");
        driver.switchTo().alert().accept();
    }
    @When("The manager adds a test case")
    public void the_manager_adds_test_case(){
        WebElement input = new WebDriverWait(driver, Duration
                .ofSeconds(20)).until(ExpectedConditions
                .presenceOfElementLocated(By.xpath("//div[@class='Collapsible__contentInner']/ul[1]/li[last()]/input")));
        input.clear();
        input.sendKeys("802");
        new WebDriverWait(driver, Duration
                .ofSeconds(10)).until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//div[@class='Collapsible__contentInner']/ul[1]/li[last()]/button"))).click();
    }
}
