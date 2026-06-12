package steps;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Hooks {

    public static WebDriver driver;

    @Before
    public void setup() {
        WebDriverManager.chromedriver().setup();
        
        ChromeOptions options = new ChromeOptions();
        
        // Check if we should run headless (default: false untuk manual testing)
        // Set environment variable HEADLESS=true untuk run headless
        String headlessMode = System.getenv("HEADLESS");
        if (headlessMode == null || headlessMode.isEmpty()) {
            headlessMode = "false"; // Default: visible browser
        }
        
        if (headlessMode.equals("true")) {
            options.addArguments("--headless=new");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
        }
        
        // Disable notifications dan popups
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }

    @After
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

