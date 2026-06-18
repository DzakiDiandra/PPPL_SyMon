package runner;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class InspectSite {
    public static void main(String[] args) {
        System.out.println("Starting inspection...");
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless"); // Run in headless mode so it doesn't need UI on the server
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        WebDriver driver = new ChromeDriver(options);
        try {
            driver.get("https://si-mondhog.m-faizarrofi.workers.dev/");
            System.out.println("Page Title: " + driver.getTitle());
            System.out.println("Current URL: " + driver.getCurrentUrl());

            // Click the Login button
            WebElement loginButton = driver.findElement(By.xpath("//button[contains(text(), 'Login with UGM Email')]"));
            System.out.println("Clicking login button...");
            loginButton.click();

            // Wait and inspect redirect
            Thread.sleep(5000);
            System.out.println("URL after login click: " + driver.getCurrentUrl());
            System.out.println("Page source length: " + driver.getPageSource().length());

            // Let's print out all interactive elements on the page now
            System.out.println("--- BUTTONS ---");
            List<WebElement> buttons = driver.findElements(By.tagName("button"));
            for (WebElement b : buttons) {
                System.out.println("Button: Text='" + b.getText() + "', ID='" + b.getAttribute("id") + "', Class='" + b.getAttribute("class") + "'");
            }

            System.out.println("--- LINKS ---");
            List<WebElement> links = driver.findElements(By.tagName("a"));
            for (WebElement l : links) {
                System.out.println("Link: Text='" + l.getText() + "', href='" + l.getAttribute("href") + "', Class='" + l.getAttribute("class") + "'");
            }

            System.out.println("--- INPUTS ---");
            List<WebElement> inputs = driver.findElements(By.tagName("input"));
            for (WebElement i : inputs) {
                System.out.println("Input: ID='" + i.getAttribute("id") + "', Name='" + i.getAttribute("name") + "', Type='" + i.getAttribute("type") + "', Class='" + i.getAttribute("class") + "'");
            }

            // Print the body text to see if there is any dashboard content
            System.out.println("--- BODY SNIPPET ---");
            String bodyText = driver.findElement(By.tagName("body")).getText();
            System.out.println(bodyText.substring(0, Math.min(bodyText.length(), 2000)));

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
            System.out.println("Inspection finished.");
        }
    }
}
