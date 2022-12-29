package dev.steps;

import dev.runners.BugCatchRunner;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

import static org.junit.Assert.*;

public class DefectStatus{
    public static WebDriver driver = BugCatchRunner.driver;
    Actions action = new Actions(driver);
//    WebElement password;
    @Given("The tester is on the Home Page")
    public void the_tester_is_on_the_home_page(){
        driver.get("https://bugcatcher-dan.coe.revaturelabs.com/?dev=13");
        driver.findElement(By.xpath("//input[@name='username']")).sendKeys("ryeGuy");
        driver.findElement(By.xpath("//input[@name='pass']")).sendKeys("coolbeans");
        driver.findElement(By.tagName("button")).click();
        String tester = driver.findElement(By.xpath("//div[1]/h1")).getText();
        assertEquals("Tester Home", tester);
        System.out.println("The user logged in as "+tester);
    }
    @Then("The tester can see only defects assigned to them")
    public void the_tester_can_see_only_defects_assigned_to_them(){
        // List of web element selects from My defects and asserted that it is not empty
        List<WebElement>  myDefects = driver.findElements(By
                .xpath("//div[1]/h3[1]")).stream().toList();
        assertFalse(myDefects.isEmpty());
    }
    @When("The tester changes the defect to any status")
    public void the_tester_changes_the_defect_to_any_status() throws InterruptedException {
        // Actions chain is built to click where the mouse currently is, it works for collapsible text content
        driver.findElement(By.xpath("//div[1]/ul/li[1]/div/span/p/b[2]")).click();
        driver.findElement(By.xpath("//button[text()='Change Status']")).click();
//        (new Actions(driver))
//                .moveToElement(driver.findElement(By
//                .xpath("//button[text()='Change Status']")))
//                .click()
//                .build()
//                .perform();

    }
    @Then("The tester should see the defect has a different status")
    public void the_tester_should_see_the_defect_has_a_different_status(){
        // List of web element selected from the expanded status change and made sure that it is not empty
        List<WebElement> statusList = driver.findElements(By.xpath("//div[1]/ul/li[1]//div[1]//div/button")).stream().toList();
        for(int i=0; i<statusList.size(); i++){
            System.out.println(statusList.get(i).getText());
        }
        assertFalse(statusList.isEmpty());
    }
}
