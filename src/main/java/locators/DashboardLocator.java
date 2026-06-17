package locators;

import org.openqa.selenium.By;

public class DashboardLocator {

    public static final By btnDevicesNavbar = By.xpath("/html[1]/body[1]/div[2]/nav[1]/div[1]/div[2]/a[2]");
    public static final By txtTotalDevices = By.xpath("/html[1]/body[1]/div[2]/main[1]/div[1]/div[1]/div[1]/div[1]/div[1]/span[2]");
    public static final By txtDeviceStatusOnline = By.xpath("/html[1]/body[1]/div[2]/main[1]/div[1]/div[1]/div[1]/div[1]/div[2]/span[2]");
    public static final By txtDeviceStatusOffline = By.xpath("/html[1]/body[1]/div[2]/main[1]/div[1]/div[1]/div[1]/div[1]/div[3]/span[2]");
    public static final By txtDeviceStatusPending = By.xpath("/html[1]/body[1]/div[2]/main[1]/div[1]/div[1]/div[1]/div[1]/div[4]/span[2]");

    public static final By btnAddDevice = By.xpath("/html[1]/body[1]/div[2]/main[1]/div[1]/div[1]/header[1]/button[1]");
    public static final By filterOS = By.xpath("/html[1]/body[1]/div[2]/main[1]/div[1]/div[1]/div[2]/div[1]/select[1]");
    public static final By inputSearch = By.xpath("/html[1]/body[1]/div[2]/main[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/input[1]");

    public static final By inputDeviceName = By.id("add-device-hostname-input");
    public static final By inputDeviceEmail = By.id("add-device-email-input");
    public static final By btnSubmitAdd = By.id("add-device-submit-btn");

    public static final By txtSuccessMessage = By.id("success-alert-modal-title");

    // TC-PEAK
    public static By textPeakRAM = By.id("peak-ram-value");
    public static By textPeakCPU = By.id("peak-cpu-value");
    public static By textPeakStorage = By.id("peak-disk-value");
    public static By daysFilter = By.id("dashboard-period-days");
}
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
