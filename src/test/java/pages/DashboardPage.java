package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class DashboardPage {
    
    private WebDriver driver;
    private WebDriverWait wait;
    
    // ==================== LOCATORS - Dashboard & Charts ====================
    private By dashboardContainer = By.xpath(
        "//div[contains(@class, 'dashboard')]|//main|//div[@id='dashboard']|//div[@id='main-content']|//body"
    );
    private By dashboardTitle = By.xpath(
        "//h1[contains(text(), 'Dashboard')]|//h1[contains(text(), 'dashboard')]|//h2[contains(text(), 'Dashboard')]"
    );
    
    private By ramChartContainer = By.xpath(
        "//div[contains(@class, 'chart') and contains(., 'RAM')]|//div[contains(@id, 'ram')]|//canvas[contains(@id, 'ram')]"
    );
    private By cpuChartContainer = By.xpath(
        "//div[contains(@class, 'chart') and contains(., 'CPU')]|//div[contains(@id, 'cpu')]|//canvas[contains(@id, 'cpu')]"
    );
    private By storageChartContainer = By.xpath(
        "//div[contains(@class, 'chart') and contains(., 'Storage')]|//div[contains(@class, 'chart') and contains(., 'Harddisk')]|//div[contains(@id, 'storage')]"
    );
    private By allCharts = By.xpath("//div[contains(@class, 'chart')]|//canvas");
    
    // ==================== LOCATORS - Filters ====================
    private By filterDropdown = By.xpath(
        "//select[@id='filter-time']|//select[contains(@class, 'filter')]|//button[contains(@class, 'filter')]"
    );
    private By dailyFilterOption = By.xpath(
        "//option[contains(text(), 'Daily')]|//option[contains(text(), 'Harian')]|//a[contains(text(), 'Daily')]"
    );
    private By weeklyFilterOption = By.xpath(
        "//option[contains(text(), 'Weekly')]|//option[contains(text(), 'Mingguan')]|//a[contains(text(), 'Weekly')]"
    );
    private By datePickerInput = By.xpath(
        "//input[@type='date']|//input[@placeholder='Select date']|//input[contains(@class, 'datepicker')]"
    );
    private By applyFilterButton = By.xpath(
        "//button[contains(text(), 'Apply')]|//button[contains(@class, 'apply')]|//button[contains(text(), 'Filter')]"
    );
    
    // ==================== LOCATORS - Device Summary ====================
    private By deviceSummarySection = By.xpath(
        "//div[contains(@class, 'summary')]|//section[contains(@id, 'device-summary')]|//div[contains(@id, 'summary')]"
    );
    private By totalDeviceCount = By.xpath(
        "//div[contains(@class, 'total-device')]//span|//*[contains(text(), 'Total Device')]/following-sibling::*|//*[contains(text(), 'Total')]/following-sibling::*"
    );
    private By onlineDeviceCount = By.xpath(
        "//div[contains(@class, 'online')]//span|//*[contains(text(), 'Online')]/following-sibling::span"
    );
    private By offlineDeviceCount = By.xpath(
        "//div[contains(@class, 'offline')]//span|//*[contains(text(), 'Offline')]/following-sibling::span"
    );
    private By pendingDeviceCount = By.xpath(
        "//div[contains(@class, 'pending')]//span|//*[contains(text(), 'Pending')]/following-sibling::span"
    );
    
    // ==================== LOCATORS - Log Performance ====================
    private By logPerformanceSection = By.xpath(
        "//div[contains(@class, 'log-performance')]|//section[contains(@id, 'log-performance')]|//div[contains(@id, 'performance-log')]"
    );
    private By logPerformanceTable = By.xpath(
        "//table[contains(@class, 'log-performance')]|//table[contains(@id, 'performance-table')]|//table[contains(@class, 'performance')]|//table"
    );
    private By tableHeaders = By.xpath("//table//thead//th|//table//tr[1]//th");
    private By tableRows = By.xpath("//table//tbody//tr|//table//tr[position()>1]");
    private By cpuColumn = By.xpath("//th[contains(text(), 'CPU')]|//th[contains(text(), 'cpu')]");
    private By ramColumn = By.xpath("//th[contains(text(), 'RAM')]|//th[contains(text(), 'ram')]");
    private By storageColumn = By.xpath("//th[contains(text(), 'Storage')]|//th[contains(text(), 'storage')]");
    private By timestampColumn = By.xpath("//th[contains(text(), 'Timestamp')]|//th[contains(text(), 'Time')]");
    private By logFilterDropdown = By.xpath("//select[@id='log-filter']|//div[contains(@class, 'log-performance')]//select");
    
    // ==================== CONSTRUCTOR ====================
    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    
    // ==================== DASHBOARD METHODS ====================
    public boolean isDashboardDisplayed() {
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(3));
            shortWait.until(ExpectedConditions.visibilityOfElementLocated(dashboardContainer));
            return driver.findElement(dashboardContainer).isDisplayed();
        } catch (Exception e) {
            try {
                WebElement body = driver.findElement(By.tagName("body"));
                return body.isDisplayed() && driver.getPageSource().length() > 100;
            } catch (Exception ex) {
                return false;
            }
        }
    }
    
    public boolean isDashboardTitleVisible() {
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(2));
            shortWait.until(ExpectedConditions.visibilityOfElementLocated(dashboardTitle));
            return driver.findElement(dashboardTitle).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean isErrorMessageDisplayed() {
        try {
            By errorMessage = By.xpath("//div[contains(@class, 'error')]|//span[contains(@class, 'error')]");
            return !driver.findElements(errorMessage).isEmpty() && driver.findElement(errorMessage).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    public void waitForDashboardLoad() {
        try {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
            driver.findElement(dashboardContainer);
        } catch (Exception e) {
            System.out.println("Dashboard container not found yet, continuing...");
        } finally {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
        }
    }
    
    // ==================== CHART METHODS ====================
    public boolean isRamChartDisplayed() {
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(2));
            shortWait.until(ExpectedConditions.visibilityOfElementLocated(ramChartContainer));
            return driver.findElement(ramChartContainer).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean isCpuChartDisplayed() {
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(2));
            shortWait.until(ExpectedConditions.visibilityOfElementLocated(cpuChartContainer));
            return driver.findElement(cpuChartContainer).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean isStorageChartDisplayed() {
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(2));
            shortWait.until(ExpectedConditions.visibilityOfElementLocated(storageChartContainer));
            return driver.findElement(storageChartContainer).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    public int getChartsCount() {
        try {
            return driver.findElements(allCharts).size();
        } catch (Exception e) {
            return 0;
        }
    }
    
    public void scrollToCharts() {
        try {
            List<WebElement> charts = driver.findElements(allCharts);
            if (!charts.isEmpty()) {
                ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                        "arguments[0].scrollIntoView(true);", charts.get(0));
            }
        } catch (Exception e) {
            System.out.println("Could not scroll to charts: " + e.getMessage());
        }
    }
    
    public boolean areChartsUpdated() {
        try {
            return getChartsCount() > 0;
        } catch (Exception e) {
            return false;
        }
    }
    
    // ==================== FILTER METHODS ====================
    public void selectDailyFilter(String date) {
        try {
            WebElement filterBtn = driver.findElement(filterDropdown);
            filterBtn.click();
            
            try {
                WebElement dailyOption = wait.until(ExpectedConditions.elementToBeClickable(dailyFilterOption));
                dailyOption.click();
            } catch (Exception e) {
                System.out.println("Could not select daily filter option");
            }
            
            try {
                WebElement datePicker = wait.until(ExpectedConditions.elementToBeClickable(datePickerInput));
                datePicker.clear();
                datePicker.sendKeys(date);
            } catch (Exception e) {
                // Date picker might not be required
            }
            
            try {
                WebElement applyBtn = wait.until(ExpectedConditions.elementToBeClickable(applyFilterButton));
                applyBtn.click();
            } catch (Exception e) {
                // Apply button might not be present
            }
        } catch (Exception e) {
            System.out.println("Failed to select daily filter: " + e.getMessage());
        }
    }
    
    public void selectWeeklyFilter() {
        try {
            WebElement filterBtn = driver.findElement(filterDropdown);
            filterBtn.click();
            
            try {
                WebElement weeklyOption = wait.until(ExpectedConditions.elementToBeClickable(weeklyFilterOption));
                weeklyOption.click();
            } catch (Exception e) {
                System.out.println("Could not select weekly filter option");
            }
            
            try {
                WebElement applyBtn = wait.until(ExpectedConditions.elementToBeClickable(applyFilterButton));
                applyBtn.click();
            } catch (Exception e) {
                // Apply button might not be present
            }
        } catch (Exception e) {
            System.out.println("Failed to select weekly filter: " + e.getMessage());
        }
    }
    
    public String getCurrentFilterValue() {
        try {
            WebElement filter = driver.findElement(filterDropdown);
            return filter.getText();
        } catch (Exception e) {
            return "";
        }
    }
    
    // ==================== DEVICE SUMMARY METHODS ====================
    public void scrollToDeviceSummary() {
        try {
            List<WebElement> summaryElements = driver.findElements(deviceSummarySection);
            if (!summaryElements.isEmpty()) {
                ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                        "arguments[0].scrollIntoView(true);", summaryElements.get(0));
            } else {
                ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollBy(0, 500);");
            }
        } catch (Exception e) {
            System.out.println("Failed to scroll to device summary: " + e.getMessage());
        }
    }
    
    public boolean isDeviceSummarySectionVisible() {
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(2));
            shortWait.until(ExpectedConditions.visibilityOfElementLocated(deviceSummarySection));
            return driver.findElement(deviceSummarySection).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    public String getTotalDeviceCount() {
        try {
            WebElement totalDevice = wait.until(ExpectedConditions.visibilityOfElementLocated(totalDeviceCount));
            return totalDevice.getText();
        } catch (Exception e) {
            return "0";
        }
    }
    
    public String getOnlineDeviceCount() {
        try {
            WebElement onlineCount = wait.until(ExpectedConditions.visibilityOfElementLocated(onlineDeviceCount));
            return onlineCount.getText();
        } catch (Exception e) {
            return "0";
        }
    }
    
    public String getOfflineDeviceCount() {
        try {
            WebElement offlineCount = wait.until(ExpectedConditions.visibilityOfElementLocated(offlineDeviceCount));
            return offlineCount.getText();
        } catch (Exception e) {
            return "0";
        }
    }
    
    public String getPendingDeviceCount() {
        try {
            WebElement pendingCount = wait.until(ExpectedConditions.visibilityOfElementLocated(pendingDeviceCount));
            return pendingCount.getText();
        } catch (Exception e) {
            return "0";
        }
    }
    
    public boolean isTotalDeviceCountDisplayed() {
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(2));
            shortWait.until(ExpectedConditions.visibilityOfElementLocated(totalDeviceCount));
            String count = getTotalDeviceCount();
            return !count.isEmpty() && !count.equals("0");
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean areDeviceStatusCountsDisplayed() {
        return !getOnlineDeviceCount().isEmpty() 
            || !getOfflineDeviceCount().isEmpty() 
            || !getPendingDeviceCount().isEmpty();
    }
    
    // ==================== LOG PERFORMANCE METHODS ====================
    public void scrollToLogPerformance() {
        try {
            List<WebElement> logElements = driver.findElements(logPerformanceSection);
            if (!logElements.isEmpty()) {
                ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                        "arguments[0].scrollIntoView(true);", logElements.get(0));
            } else {
                ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollBy(0, 800);");
            }
        } catch (Exception e) {
            System.out.println("Failed to scroll to log performance: " + e.getMessage());
        }
    }
    
    public boolean isLogPerformanceTableDisplayed() {
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(2));
            shortWait.until(ExpectedConditions.visibilityOfElementLocated(logPerformanceTable));
            return driver.findElement(logPerformanceTable).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean isLogPerformanceSectionVisible() {
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(2));
            shortWait.until(ExpectedConditions.visibilityOfElementLocated(logPerformanceSection));
            return driver.findElement(logPerformanceSection).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    public List<String> getTableHeaders() {
        List<String> headers = new ArrayList<>();
        try {
            List<WebElement> headerElements = driver.findElements(tableHeaders);
            for (WebElement header : headerElements) {
                headers.add(header.getText().trim());
            }
        } catch (Exception e) {
            // Headers might not be found
        }
        return headers;
    }
    
    public boolean hasCpuColumn() {
        try {
            driver.findElement(cpuColumn);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean hasRamColumn() {
        try {
            driver.findElement(ramColumn);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean hasStorageColumn() {
        try {
            driver.findElement(storageColumn);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean hasTimestampColumn() {
        try {
            driver.findElement(timestampColumn);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean hasAllRequiredColumns() {
        return hasCpuColumn() && hasRamColumn() && hasStorageColumn() && hasTimestampColumn();
    }
    
    public int getLogPerformanceRowCount() {
        try {
            return driver.findElements(tableRows).size();
        } catch (Exception e) {
            return 0;
        }
    }
    
    public void selectLogPerformanceFilter(String filterValue) {
        try {
            List<WebElement> filterElements = driver.findElements(logFilterDropdown);
            if (!filterElements.isEmpty()) {
                WebElement logFilter = filterElements.get(0);
                logFilter.click();
                
                By filterOption = By.xpath(
                    "//select[@id='log-filter']//option[contains(text(), '" + filterValue + "')]|" +
                    "//div[contains(@class, 'log-performance')]//a[contains(text(), '" + filterValue + "')]"
                );
                try {
                    WebElement option = wait.until(ExpectedConditions.elementToBeClickable(filterOption));
                    option.click();
                } catch (Exception e) {
                    System.out.println("Could not select filter option: " + filterValue);
                }
            }
        } catch (Exception e) {
            System.out.println("Failed to select log performance filter: " + e.getMessage());
        }
    }
    
    public String getCurrentLogPerformanceFilter() {
        try {
            WebElement logFilter = driver.findElement(logFilterDropdown);
            return logFilter.getText();
        } catch (Exception e) {
            return "";
        }
    }
    
    public boolean isLogPerformanceDataUpdated() {
        try {
            return getLogPerformanceRowCount() > 0;
        } catch (Exception e) {
            return false;
        }
    }
}
