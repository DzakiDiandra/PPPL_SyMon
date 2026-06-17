package locators;

import org.openqa.selenium.By;

public class DeviceDetailLocator {
    public static final By Header = By.id("device-spec-info-card");
    public static final By ButtonFilter = By.id("device-time-series-dropdown");
    public static final By DeviceName = By.id("device-spec-hostname");
    public static final By ButtonDownloadCsv = By.id("device-download-csv-btn");
    public static final By ButtonDelete = By.id("delete-machine-btn");
    public static final By PerformanceSummary = By.id("performance-summary-cpu-card");
    public static final By RAMChartContainer = By.xpath("(//*[name()='svg'][@role='application'])[1]");
    public static final By CPUChartContainer = By.xpath("(//*[name()='svg'][@role='application'])[3]");
    public static final By DiskChartContainer = By.xpath("(//*[name()='svg'][@role='application'])[2]");
    public static final By TimestampLogDevice = By.cssSelector("div[id='device-activity-log-row-0'] div:nth-child(4)");
    public static final By DotGraphic = By.cssSelector(".recharts-dot");
    public static final By RAMChartLine =
            By.cssSelector(
                    "#device-ram-chart-plot .recharts-area-curve"
            );
    public static final By StatusDevice = By.id("device-spec-status");
    public static final By EditButton = By.id("device-spec-edit-btn");
    public static final By InputEditDevice = By.id("device-spec-name-input");
    public static final By SaveButton = By.id("device-spec-save-btn");
    public static final By ConfirmDelete = By.id("delete-confirmation-confirm-btn");
    public static final By TimeSeriesDropdown = By.id("device-time-series-dropdown");
    public static final By ContainerPendingInformation = By.xpath("//p[normalize-space()='Device Pending']");
    public static final By ContainerOfflineInformation = By.xpath("//p[normalize-space()='Device Offline']");

}
