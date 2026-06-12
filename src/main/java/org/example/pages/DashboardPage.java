package org.example.pages;

import locators.DashboardLocator;
import org.example.SymonBasePage;
import org.openqa.selenium.WebDriver;

public class DashboardPage extends SymonBasePage {
    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    public void clickNavbarDevice()throws InterruptedException{
        click(DashboardLocator.DeviceNavbar);
    }

    public String getUrl() {
        return driver.getCurrentUrl();
    }
}
