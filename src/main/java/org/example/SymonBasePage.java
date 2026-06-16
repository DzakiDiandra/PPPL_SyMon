package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SymonBasePage {
    public WebDriver driver;
    WebDriverWait wait;
    public SymonBasePage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public WebElement waitForElement (By by) throws InterruptedException {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public void click(By by) throws InterruptedException{
        waitForElement(by).click();
    }
}
