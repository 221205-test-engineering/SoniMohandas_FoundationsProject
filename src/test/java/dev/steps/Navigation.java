package dev.steps;

import dev.runners.BugCatchRunner;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import io.cucumber.java.en.Given;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class Navigation {
    public static WebDriver driver = BugCatchRunner.driver;
    @Given("The manager should see links for Matrices, Test Cases, Defect Reporting and Defect Overview")
    public void the_manager_should_see_all_the_links(){
        List<WebElement> navLink= Collections.singletonList(driver.findElement(By.tagName("a")));
        for(WebElement nav : navLink){
            System.out.println(nav.getAttribute("href"));
        }
    }
    @When("The manager clicks on Matrices")
    public void the_manager_clicks_on_matrices(){
        driver.findElement(By.xpath("//div/nav/a[1]")).click();
    }
    @Then("The title of the page should be Matrix Page")
    public void the_title_of_the_page_matrix_page(){
        String titleMat = driver.findElement(By.xpath("//div/h1")).getText();
        titleMat.equals("Matrix Page");
    }
    @When("The manager clicks the browser back button")
    public void the_manager_clicks_the_browser_back_button(){
        driver.navigate().back();
    }
    @Then("The manager should be on the home page and the title of page is Home")
    public void the_manager_should_be_on_the_home_page(){
        String title = driver.findElement(By.xpath("//div/h1")).getText();
        title.equals("Home");
    }
    @When("The manager clicks on Test Cases")
    public void the_manager_clicks_on_test_cases(){
        driver.findElement(By.xpath("//div/nav/a[2]")).click();
    }

    // All Links Viable
    @When("The manager clicks on {string}")
    public void the_manager_clicks_on_links(String link){
        driver.findElement(By.linkText(link)).click();
    }
//    @Then("The title of page should be different {string}")
//    public void the_title_of_the_page_should_be(String title){
//        String getTitle = driver.findElement(By.tagName("h1")).getText();
//        assertNotEquals(title, getTitle);
//    }
    @Then("The title of page should be {string}")
    public void the_title_of_page_should_be(String string) {
        String getTitle = driver.findElement(By.tagName("h1")).getText();
        assertEquals(string, getTitle);
    }

}
