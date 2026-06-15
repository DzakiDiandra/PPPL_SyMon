package org.example.pages;

import locators.DeviceDetailLocator;
import org.example.SymonBasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.time.Duration;

public class DeviceDetailPage extends SymonBasePage {
    public DeviceDetailPage(WebDriver driver) {
        super(driver);
    }
    public String getPageUrl() throws InterruptedException{
        waitForElement(DeviceDetailLocator.Header);
        return driver.getCurrentUrl();
    }
    public boolean isFilterVisible() throws InterruptedException {
        return waitForElement(
                DeviceDetailLocator.TimeSeriesDropdown
        ).isDisplayed();
    }
    public boolean isDownloadButtonVisible()
            throws InterruptedException {

        return waitForElement(
                DeviceDetailLocator.ButtonDownloadCsv
        ).isDisplayed();
    }
    public boolean isPerformanceSummaryVisible()
            throws InterruptedException {

        return waitForElement(
                DeviceDetailLocator.PerformanceSummary
        ).isDisplayed();
    }
    public boolean isDeleteButtonVisible()
            throws InterruptedException {

        return waitForElement(
                DeviceDetailLocator.ButtonDelete
        ).isDisplayed();
    }
    public boolean isRamChartVisible()
            throws InterruptedException {

        return waitForElement(
                DeviceDetailLocator.RAMChartContainer
        ).isDisplayed();
    }
    public boolean isCpuChartVisible()
            throws InterruptedException {

        return waitForElement(
                DeviceDetailLocator.CPUChartContainer
        ).isDisplayed();
    }
    public boolean isDiskChartVisible()
            throws InterruptedException {

        return waitForElement(
                DeviceDetailLocator.DiskChartContainer
        ).isDisplayed();
    }
    public boolean isLogTableVisible()
            throws InterruptedException {

        return waitForElement(
                DeviceDetailLocator.TimestampLogDevice
        ).isDisplayed();
    }

    public String getStatusMessage(String status)
            throws InterruptedException {

        switch (status.toLowerCase()) {

            case "offline":
                return waitForElement(
                        DeviceDetailLocator.ContainerOfflineInformation
                ).getText();

            case "pending":
                return waitForElement(
                        DeviceDetailLocator.ContainerPendingInformation
                ).getText();

            default:
                throw new IllegalArgumentException(
                        "Status tidak dikenali: " + status
                );
        }
    }

    public void clickDownloadCsv()
            throws InterruptedException {
        scrollIntoView(
                DeviceDetailLocator.ButtonDownloadCsv
        );
        wait.until(ExpectedConditions.elementToBeClickable(DeviceDetailLocator.ButtonDownloadCsv));
        click(DeviceDetailLocator.ButtonDownloadCsv);
    }


    public void clickEditButton()
            throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(DeviceDetailLocator.EditButton));
        click(DeviceDetailLocator.EditButton);
    }
    public void inputDeviceName(String deviceName)
            throws InterruptedException {

        waitForElement(
                DeviceDetailLocator.InputEditDevice
        ).clear();

        waitForElement(
                DeviceDetailLocator.InputEditDevice
        ).sendKeys(deviceName);
    }
    public void clickSaveButton()
            throws InterruptedException {

        click(DeviceDetailLocator.SaveButton);
        wait.until(ExpectedConditions.visibilityOfElementLocated(DeviceDetailLocator.DeviceName));
    }
    public String getDeviceName(String name)
            throws InterruptedException {
        wait.until(ExpectedConditions.textToBe(DeviceDetailLocator.DeviceName,name));
        return waitForElement(
                DeviceDetailLocator.DeviceName
        ).getText().trim();
    }
    public void clickDeleteButton()
            throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(DeviceDetailLocator.ButtonDelete));
        click(DeviceDetailLocator.ButtonDelete);
    }
    public boolean isDeleteConfirmationVisible()
            throws InterruptedException {

        return waitForElement(
                DeviceDetailLocator.ConfirmDelete
        ).isDisplayed();
    }
    public void clickConfirmDelete()
            throws InterruptedException {

        click(DeviceDetailLocator.ConfirmDelete);
    }
    public boolean waitUntilRedirectedToDevices() throws InterruptedException {
        Thread.sleep(Duration.ofSeconds(4));
        return wait.until(driver ->
                driver.getCurrentUrl()
                        .equals("https://si-mondhog.m-faizarrofi.workers.dev/devices")
        );
    }
    public int getTotalDataPoints() {
        String path =
                driver.findElement(
                        DeviceDetailLocator.RAMChartLine
                ).getAttribute("d");

        return path.split("C").length;
    }
    public void selectTimeSeriesFilter(String filter)
            throws InterruptedException {
        Select dropdown =
                new Select(
                        waitForElement(
                                DeviceDetailLocator.TimeSeriesDropdown
                        )
                );
        dropdown.selectByVisibleText(filter);
        Thread.sleep(2000);
    }
}