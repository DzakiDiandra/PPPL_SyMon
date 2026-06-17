package org.example.pages;

import locators.DashboardLocator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;


public class DashboardPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void clickDevicesNavbar() {
        wait.until(ExpectedConditions.elementToBeClickable(DashboardLocator.btnDevicesNavbar)).click();
    }

    public boolean isDevicesPageDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(DashboardLocator.btnAddDevice)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getTotalDevicesText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(DashboardLocator.txtTotalDevices)).getText();
    }

    public String getOnlineDevicesCount() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(DashboardLocator.txtDeviceStatusOnline)).getText();
    }

    public String getOfflineDevicesCount() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(DashboardLocator.txtDeviceStatusOffline)).getText();
    }

    public String getPendingDevicesCount() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(DashboardLocator.txtDeviceStatusPending)).getText();
    }

    public boolean isAddDeviceButtonVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(DashboardLocator.btnAddDevice)).isDisplayed();
    }

    public boolean isFilterOSVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(DashboardLocator.filterOS)).isDisplayed();
    }

    public boolean isSearchInputVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(DashboardLocator.inputSearch)).isDisplayed();
    }

    public void clickAddDevice() {
        wait.until(ExpectedConditions.elementToBeClickable(DashboardLocator.btnAddDevice)).click();
    }

    public void inputDeviceName(String deviceName) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(DashboardLocator.inputDeviceName));
        element.clear();
        element.sendKeys(deviceName);
    }

    public void selectOS(String osName) {
        By dynamicOSButton = By.xpath("//*[normalize-space()='" + osName + "']");
        wait.until(ExpectedConditions.elementToBeClickable(dynamicOSButton)).click();
    }

    public void inputDeviceEmail(String email) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(DashboardLocator.inputDeviceEmail));
        element.clear();
        element.sendKeys(email);
    }

    public void clickSubmitAddDevice() {
        wait.until(ExpectedConditions.elementToBeClickable(DashboardLocator.btnSubmitAdd)).click();
    }

    public boolean isSuccessMessageDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(DashboardLocator.txtSuccessMessage)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isNewDeviceVisibleInList(String deviceName) {
        try {
            By dynamicDeviceHeader = By.xpath("//h3[normalize-space()='" + deviceName + "']");
            return wait.until(ExpectedConditions.visibilityOfElementLocated(dynamicDeviceHeader)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void leaveFieldsEmpty() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(DashboardLocator.inputDeviceName)).clear();
        wait.until(ExpectedConditions.visibilityOfElementLocated(DashboardLocator.inputDeviceEmail)).clear();
    }

    public String getInputValidationColor(String fieldName) {
        WebElement element;
        if (fieldName.equalsIgnoreCase("nama device")) {
            element = wait.until(ExpectedConditions.visibilityOfElementLocated(DashboardLocator.inputDeviceName));
        } else {
            element = wait.until(ExpectedConditions.visibilityOfElementLocated(DashboardLocator.inputDeviceEmail));
        }
        return element.getCssValue("border-color");
    }

    public void selectFilterOS(String osValue) {
        WebElement dropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(DashboardLocator.filterOS));
        org.openqa.selenium.support.ui.Select select = new org.openqa.selenium.support.ui.Select(dropdown);
        select.selectByVisibleText(osValue);
    }

    public java.util.List<String> getDisplayedDevicesOSList() {
        By osElementsLocator = By.xpath("//main/div/div/div[3]/div/div/div[2]/span[1]");

        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(osElementsLocator));
        } catch (Exception e) {
            return new java.util.ArrayList<>();
        }

        java.util.List<WebElement> elements = driver.findElements(osElementsLocator);
        java.util.List<String> osTexts = new java.util.ArrayList<>();

        for (WebElement element : elements) {
            String text = element.getText().trim();
            if (text.equalsIgnoreCase("Linux") || text.equalsIgnoreCase("Windows") || text.equalsIgnoreCase("macOS")) {
                osTexts.add(text);
            }
        }
        return osTexts;
    }

    public void searchDevice(String deviceName) {
        WebElement searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(DashboardLocator.inputSearch));
        searchInput.clear();
        searchInput.sendKeys(deviceName);
    }

    public boolean isPerformanceSummaryVisible() {
        // Mastiin element summary-nya emang ada di layar dashboard
        return driver.findElement(locators.DashboardLocator.textPeakRAM).isDisplayed();
    }

    public String getPeakRAMText() {
        return driver.findElement(locators.DashboardLocator.textPeakRAM).getText();
    }

    public String getPeakCPUText() {
        return driver.findElement(locators.DashboardLocator.textPeakCPU).getText();
    }

    public String getPeakStorageText() {
        return driver.findElement(locators.DashboardLocator.textPeakStorage).getText();
    }

    public void clickDaysFilter() {
        org.openqa.selenium.WebElement daysBtn = wait.until(
                org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable(
                        DashboardLocator.daysFilter
                )
        );
        daysBtn.click();
    }
}