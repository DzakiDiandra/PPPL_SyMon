package org.example.pages;

import locators.DevicesLocator;
import org.example.SymonBasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DevicePage extends SymonBasePage {
    public DevicePage (WebDriver driver){
        super(driver);
    }

    public void openDeviceDetail(String deviceName) throws InterruptedException {
        By locator = By.xpath(
                "//h3[normalize-space()='" + deviceName + "']" +
                        "/ancestor::div[contains(@class,'bg-white')]" +
                        "//a[contains(@href,'/devices/')]"
        );

        waitForElement(locator).click();

        wait.until(
                ExpectedConditions.urlContains("/devices/")
        );
    }

    public void searchDevice(String nama)throws InterruptedException{
        WebElement search = driver.findElement(DevicesLocator.SearchBar);
        search.clear();
        search.sendKeys(nama);
    }

    public void seenDeviceOnStatus(String nama, String status) throws InterruptedException {
        By locator = By.xpath(
                "//h3[normalize-space()='" + nama + "']" +
                        "/ancestor::div[contains(@class,'bg-white')]" +
                        "//a/span[normalize-space()='" + status.toLowerCase() + "']"
        );
        waitForElement(locator);
    }

    public void seenDeviceName(String nama) throws InterruptedException{
        By locator = By.xpath(
                "//h3[normalize-space()='" + nama + "']"
        );
        waitForElement(locator);
    }

    public String getUrl() throws InterruptedException{
        waitForElement(DevicesLocator.Header);
        return driver.getCurrentUrl();
    }
}
