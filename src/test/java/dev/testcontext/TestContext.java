package dev.testcontext;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TestContext {
    public static WebDriver driver;
    public void TextContext(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public String getPage(){
        String url = "https://bugcatcher-dan.coe.revaturelabs.com/?dev=13";
        return url;
    }
    public WebElement userName(){
        WebElement username = driver.findElement(By.xpath("//input[@name='username']"));
        return username;
    }
    @FindBy (xpath = "//input[@name='username']")
    public WebElement username;

    @FindBy(xpath = "//input[@name='pass']")
    public WebElement password;
    @FindBy(tagName = "button")
    public WebElement login;
}
