package locators;

import org.openqa.selenium.By;

public class DashboardLocator {
    public static final By DeviceNavbar = By.linkText("Devices");
    public static final By ButtonCSvPerformance = By.xpath("//button[contains(text(),'Download CSV')]");
    public static final By DaysButtonFilter = By.xpath("//a[normalize-space()='Days']");
    public static final By OneDayColumnFilter = By.xpath("//body/div/main/div/div/div/div/div[6]");
}
