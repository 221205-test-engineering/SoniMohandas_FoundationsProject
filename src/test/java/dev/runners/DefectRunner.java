//package dev.runners;
//
//import io.cucumber.junit.Cucumber;
//import io.cucumber.junit.CucumberOptions;
//import io.github.bonigarcia.wdm.WebDriverManager;
//import org.junit.AfterClass;
//import org.junit.BeforeClass;
//import org.junit.runner.RunWith;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
//
//@RunWith(Cucumber.class)
//@CucumberOptions(features = "src/test/java/dev/features/defects", glue="dev/steps/defects")
//public class DefectRunner {
//    public static WebDriver driver;
//
//    @BeforeClass
//    public static void setup(){
//        WebDriverManager.firefoxdriver().setup();
//        driver = new FirefoxDriver();
//    }
//    @AfterClass
//    public static void teardown(){
//        driver.quit();
//    }
//}
