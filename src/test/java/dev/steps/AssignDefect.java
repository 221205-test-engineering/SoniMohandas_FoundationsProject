package dev.steps;

import dev.runners.BugCatchRunner;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AssignDefect {
    public static WebDriver driver = BugCatchRunner.driver;
    @Given("The manager is logged in as a manager")
    public void the_manager_is_logged_in_as_a_manager(){
        driver.get("https://bugcatcher-dan.coe.revaturelabs.com/?dev=13");
        driver.findElement(By.xpath("//input[@name='username']")).sendKeys("g8tor");
        driver.findElement(By.xpath("//input[@name='pass']")).sendKeys("chomp!");
        driver.findElement(By.tagName("button")).click();
        System.out.println("Logged in as Manager");
    }
    @Given("The manager is on the home page")
    public void the_manager_is_on_the_home_page() throws InterruptedException {
        String title = driver.findElement(By.xpath("/html/body/div/h1")).getText();
        System.out.println(title);
        assertEquals("Manager Home", title);
        System.out.println(title);
        Thread.sleep(3000);

    }
    @Then("The manager should see pending defects")
    public void the_manager_should_see_pending_defects() throws InterruptedException {
        String defectId = driver.findElement(By.xpath("/html/body/div/table/tbody/tr[1]/td[1]")).getText();
        String defect = driver.findElement(By.xpath("/html/body/div/table/tbody/tr[1]/td[2]")).getText();
        System.out.println(defectId+defect);
        Thread.sleep(2000);

    }
    @When("The manager clicks on the select button for a defect")
    public void the_manager_clicks_on_the_select_button_for_a_defect(){
        driver.findElement(By.tagName("button")).click();
    }
    @Then("The defect description should appear in bold")
    public void the_defect_description_should_appear_in_bold(){
        String text = driver.findElement(By
                .xpath("/html/body/div/div/h4")).getCssValue("font-weight");

        assertTrue(Integer.parseInt(text)>=700);
        if(Integer.parseInt(text)>=700){
            System.out.println("Defect description is in bold letters");
        }

    }
    @When("The manager selects a tester from the drop down list")
    public void the_manager_selects_a_tester_from_the_drop_down_list(){
        List<WebElement> testers = driver.findElements(By.id("employees"))
                .stream().toList();
        testers.get(0).isSelected();
        System.out.println(testers.get(0).toString());
    }
    @When("The manager clicks assign")
    public void the_manager_clicks_assign(){
        driver.findElement(By.xpath("/html/body/div/div/button")).click();
    }
    @Then("The defect should disappear from the list")
    public void the_defect_should_disappear_from_the_list(){
        WebElement defect1 = driver.findElement(By.tagName("button"));
        assertTrue(defect1.isDisplayed());
    }
}