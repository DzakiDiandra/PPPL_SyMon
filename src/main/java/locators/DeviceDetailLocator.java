package locators;

import org.openqa.selenium.By;

public class DeviceDetailLocator {
    public static final By Header = By.xpath("//span[@class='text--secondary font-semibold truncate max-w-");
    public static final By ButtonFilter = By.tagName("select");
    public static final By ButtonDownloadCsv = By.xpath("//button[normalize-space()='Download CSV']");
    public static final By ButtonDelete = By.xpath("//span[@class='text--secondary font-semibold truncate max-w-");
    public static final By PerformanceSummary = By.xpath("//h2[normalize-space()='Performance Summary']");
    public static final By RAMChartContainer = By.id("recharts-area-_r_0_");
    public static final By CPUChartContainer = By.id("recharts-area-_r_2_");
    public static final By DiskChartContainer = By.id("recharts-area-_r_1_");
    public static final By TimestampLogDevice = By.xpath("/html[1]/body[1]/div[2]/main[1]/div[1]/div[2]/div[1]/div[3]/div[1]/div[2]/div[4]");
}
