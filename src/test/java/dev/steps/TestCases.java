package dev.steps;

import dev.runners.BugCatchRunner;
import io.cucumber.java.en.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Set;

import static org.junit.Assert.*;

public class TestCases{
    public static WebDriver driver = BugCatchRunner.driver;
    public static String caseId;

    public static WebElement details;
    public static WebElement closed;
    public static String description;


    //Scenario Add A Test Case
    @Given("The tester is on the test case dashboard")
    public void the_tester_is_on_the_test_case_dashboard(){
        driver.get("https://bugcatcher-dan.coe.revaturelabs.com/?dev=13");
        driver.findElement(By.xpath("//input[@name='username']")).sendKeys("ryeGuy");
        driver.findElement(By.xpath("//input[@name='pass']")).sendKeys("coolbeans");
        driver.findElement(By.tagName("button")).click();
        driver.findElement(By.linkText("Test Cases")).click();
        String dashboard = driver.findElement(By.xpath("/html/body/div[1]/h1")).getText();
        assertEquals(dashboard, "Test Case Dashboard");
    }
    @When("The tester types Description into input with")
    public void the_tester_types_description_into_input_with(String docString){
        driver.findElement(By.name("desc")).sendKeys(docString);
    }
    @When("The tester types steps into input with")
    public void the_tester_types_steps_into_input_with(String docString){
        driver.findElement(By.name("steps")).sendKeys(docString);
    }
    @When("The tester presses the submit button")
    public void the_tester_presses_the_submit_button(){
        driver.findElement(By.xpath("//button[@type='submit']")).submit();
    }
    @Then("The test case should appear at the bottom of the table")
    public void the_test_case_should_appear_at_the_bottom_of_the_table(){
        String lastRow = driver.findElement(By
                .xpath("//div[1]/table/tbody/tr[last()]/td[1]"))
                .getText();
        assertNotNull(lastRow);
        System.out.println("Last added row: "+lastRow);
    }
    @Then("The test case result should say UNEXECUTED")
    public void the_test_case_result_should_say_unexecuted() {
        String exec = new WebDriverWait(driver, Duration
              .ofSeconds(10)).until(ExpectedConditions
              .visibilityOfElementLocated(By
              .xpath("/div[1]/table/tbody/tr[last()]/td[3]")))
              .getText();
            System.out.println(exec);
            assertEquals(exec, "UNEXECUTED");
    }
    @When("The tester presses on details")
    public void the_tester_presses_on_details(){
        WebElement testDetails = new WebDriverWait(driver, Duration.ofSeconds(15))
                        .until(ExpectedConditions.elementToBeClickable(By
                        .xpath("//tbody/tr[last()]/td[4]/button")));
        details = testDetails;
                testDetails.click();
    }
    @Then("A test case modal should appear showing the case ID")
    public void a_test_case_modal_should_appear_showing_the_case_id(){
        String Id = driver.findElement(By
                .xpath("//div[3]//div/h3")).getText();
        assertFalse(Id.isEmpty());
        System.out.println("Case ID: "+Id);
        caseId = Id;
    }
    @Then("The performed by field should say No One")
    public void the_performed_field_should_say_no_one(){
        String field = driver.findElement(By
                .xpath("//div[3]//div/p[6]")).getText();
        assertEquals("No One", field);
    }
    @When("The tester presses the close button")
    public void the_tester_presses_the_close_button(){
        String closed = driver.findElement(By.xpath("//button[text()='Close']")).getText();
        driver.findElement(By.xpath("//button[text()='Close']")).click();

    }
    @Then("The Modal Should be closed")
    public void the_modal_should_be_closed() throws InterruptedException {
        assertEquals(closed, null);
    }

//     Scenario Edit Existing Case

    @When("The Tester clicks on edit within the modal")
    public void the_tester_clicks_on_edit_within_the_modal() throws InterruptedException {
//        driver.findElement(By.xpath("//div[1]/table/tbody/tr[last()]/td[4]/button")).click();
        driver.findElement(By.xpath("//div[3]//div/button[2]/a")).click();
    }
    @Then("The Tester should be on the case editor for that case")
    public void the_tester_should_be_on_the_case_editor_page(){
        String case_id = caseId.substring(caseId.indexOf(" ")+1);
        String editId = driver.findElement(By.xpath("//div/h1")).getText();
        String edit_id = editId.substring(editId.indexOf(" ")+4);
        assertEquals(case_id, edit_id);
    }
    @When("The tester clicks on the edit button")
    public void the_tester_clicks_on_the_edit_button(){
        driver.findElement(By.xpath("//div/button")).click();
    }
    @When("The tester types description in the textarea")
    public void the_tester_types_description_in_the_textarea(String text){
        driver.findElement(By.xpath("//div/fieldset[1]/textarea[1]")).sendKeys(text);
    }
    @When("The tester types steps in the textarea")
    public void the_tester_types_steps_in_the_textarea(String text){
        driver.findElement(By.xpath("//div/fieldset[1]/textarea[2]")).sendKeys(text);
    }
    @When("The tester clicks on the automated check mark")
    public void the_tester_clicks_on_the_automated_check_mark(){
        driver.findElement(By.xpath("//input[@type='checkbox']")).click();
    }
    @When("The tester selects ryeGuy for performed from drop down")
    public void the_tester_selects_ryeGuy_for_performed_from_dropdown(){
        Select guy = new Select(driver.findElement(By.xpath("//div/fieldset[1]/select")));
        guy.selectByVisibleText("ryeGuy");
        System.out.println(guy.getAllSelectedOptions().get(0));
    }
    @When("The tester selects FAIL for test result from drop down")
    public void the_tester_selects_fail_for_the_result_from_dropdown(){
        Select option = new Select(driver.findElement(By.xpath("//div/fieldset[2]/select")));
        option.selectByVisibleText("FAIL");
    }
    @When("The tester types summary in the text area")
    public void the_tester_types_summary_in_the_textarea(String text){
        driver.findElement(By.xpath("//fieldset[2]/textarea")).sendKeys(text);
    }
    @When("The tester clicks save on test case page")
    public void the_tester_clicks_save_on_test_case_page(){
        driver.findElement(By.xpath("//button[text()='Save']")).click();
    }
    @Then("A confirmation prompt should appear")
    public void a_confirmation_prompt_should_appear(){
        String confirm = driver.switchTo().alert().getText();
        assertEquals(confirm, "Are you sure you want to update the test case?");
    }
    @When("The tester clicks on Ok")
    public void the_tester_clicks_ok(){
        driver.switchTo().alert().accept();
    }
    @Then("An alert says the test case has been saved")
    public void an_alert_says_the_test_case_has_been_saved() throws InterruptedException {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        boolean testCase = alert.getText().contains("Test Case has been Saved"); // Saved in boolean variable
        System.out.println(testCase);
        assertTrue(testCase);  // Checked whether it is true of false
        driver.switchTo().alert().accept();

    }

    // Scenario Reset Test Case

    @When("The tester selects cavalier89 for performed from drop down")
    public void the_tester_selects_cavalier89_for_performed_from_drop_down(){
        driver.getCurrentUrl();
        Select selectTester = new Select(driver.findElement(By.xpath("//div/fieldset[1]/select")));
        selectTester.selectByVisibleText("cavalier89"); // Selenium Select and visibility text function used

    }
    @When("The tester selects FLAKY for test result from drop down")
    public void the_tester_selects_flaky_for_test_result_from_drop_down(){
        Select option = new Select(driver.findElement(By.xpath("//div/fieldset[2]/select")));
        option.selectByVisibleText("FLAKY");
    }
    @When("The tester clicks on the reset button")
    public void the_tester_clicks_on_the_reset_button(){
        String desc = driver.findElement(By.xpath("//div/fieldset[1]/textarea[1]")).getText();
        description = desc;
        driver.findElement(By.xpath("//div/fieldset[1]/textarea[1]")).sendKeys("Hello");
        driver.findElement(By.xpath("//button[text()='Reset']")).click();
    }
    @Then("The fields should be populated to their old values")
    public void the_fields_should_be_populated_to_their_old_values(){
        String text = driver.findElement(By.xpath("//div/fieldset[1]/textarea[1]")).getText();
        assertEquals(description, text);  // Before reset text was saved in a String variable and after reset
                                            // values were compared.
    }
}
