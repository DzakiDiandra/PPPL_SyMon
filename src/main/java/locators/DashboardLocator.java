package locators;

import org.openqa.selenium.By;

public class DashboardLocator {
    public static final By DeviceNavbar = By.linkText("Devices");
    public static final By Header = By.id("dashboard-summary-title");
    public static final By ButtonCSvPerformance = By.xpath("//button[contains(text(),'Download CSV')]");
    public static final By DaysButtonFilter = By.xpath("//a[normalize-space()='Days']");
    public static final By OneDayColumnFilter = By.xpath("//body/div/main/div/div/div/div/div[6]");
    public static final By DownloadCsvEvent = By.id("download-events-csv-btn");
    public static final By LogEventRows =
            By.cssSelector("[id^='event-log-row-']");
    public static final By LogEventRow1 = By.id("event-log-row-0");
    public static final By LogEventRow2 = By.id("event-log-row-1");
    public static final By TableLogEvent = By.id("dashboard-event-logs-card");
    public static final By FilterDay = By.id("dashboard-period-days");
    public static final By FilterWeeks = By.id("dashboard-period-weeks");
}
