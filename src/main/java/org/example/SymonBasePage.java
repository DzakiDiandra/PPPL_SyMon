package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.time.Duration;

public class SymonBasePage {
    public WebDriver driver;
    public Actions actions;
    public WebDriverWait wait;
    public SymonBasePage(WebDriver driver){
        this.driver = driver;
        this.actions = new Actions(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public WebElement waitForElement (By by) throws InterruptedException {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public void click(By by) throws InterruptedException{
        waitForElement(by).click();
    }

    public void scrollIntoView(By by)
            throws InterruptedException {

        WebElement element = waitForElement(by);

        ((JavascriptExecutor) driver)
                .executeScript(
                        "arguments[0].scrollIntoView({block:'center'});",
                        element
                );
    }

    public static boolean waitUntilFileDownloaded(String target) {
        File folder = new File(
                System.getProperty("user.home")
                        + "\\Downloads"
        );

        long timeout = System.currentTimeMillis() + 10000;

        while (System.currentTimeMillis() < timeout) {

            File[] files = folder.listFiles();

            if (files != null) {

                for (File file : files) {
                    if (target == "Details") {
                        if (file.getName().contains("_metrics")) {
                            return true;
                        }
                    } else if (target == "Events") {
                        if (file.getName().contains("logs-events")){
                            return true;
                        }
                    }
                }
            }

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        return false;
    }
    public static void saveDownloadedFile() throws Exception {

        Robot robot = new Robot();

        robot.delay(3000);

        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

        robot.delay(1000);
    }
}
