package org.example.pages;

import locators.DashboardLocator;
import org.example.SymonBasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DashboardPage extends SymonBasePage {
    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    public void clickNavbarDevice()throws InterruptedException{
        click(DashboardLocator.DeviceNavbar);
    }

    public String getUrl() throws InterruptedException {
        waitForElement(DashboardLocator.Header);
        return driver.getCurrentUrl();
    }

    public void clickDownloadCsvLogEvent()
            throws InterruptedException {

        scrollIntoView(
                DashboardLocator.DownloadCsvEvent
        );

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        DashboardLocator.DownloadCsvEvent
                )
        );

        click(
                DashboardLocator.DownloadCsvEvent
        );
    }
    public void scrollToLogEvent()
            throws InterruptedException {
        scrollIntoView(
                DashboardLocator.TableLogEvent
        );
    }
    public boolean isLogEventTableVisible()
            throws InterruptedException {

        return waitForElement(
                DashboardLocator.TableLogEvent
        ).isDisplayed();
    }

    public boolean hasEventType(String eventType) {

        return driver.findElements(
                        DashboardLocator.LogEventRows
                ).stream()
                .anyMatch(
                        row -> row.getText()
                                .toLowerCase()
                                .contains(
                                        eventType.toLowerCase()
                                )
                );
    }

    public boolean allRowsHaveDate() {
        return driver.findElements(
                        DashboardLocator.LogEventRows
                ).stream()
                .allMatch(
                        row -> row.getText()
                                .split("\\R")[0]
                                .matches("\\d{2}/\\d{2}/\\d{4}")
                );
    }

    public String getFirstLogEventRowText()
            throws InterruptedException {
        return waitForElement(
                DashboardLocator.LogEventRow1
        ).getText();
    }

    public void selectWeeksFilter()
            throws InterruptedException {

        click(
                DashboardLocator.FilterDay
        );

        Thread.sleep(1500);
    }
}
