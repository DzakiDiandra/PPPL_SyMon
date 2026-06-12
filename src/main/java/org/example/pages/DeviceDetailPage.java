package org.example.pages;

import locators.DeviceDetailLocator;
import org.example.SymonBasePage;
import org.openqa.selenium.WebDriver;

public class DeviceDetailPage extends SymonBasePage {
    public DeviceDetailPage(WebDriver driver) {
        super(driver);
    }
    public String getPageUrl() throws InterruptedException{
        waitForElement(DeviceDetailLocator.Header);
        return driver.getCurrentUrl();
    }


}
