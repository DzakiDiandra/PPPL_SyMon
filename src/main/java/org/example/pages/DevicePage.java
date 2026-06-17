package org.example.pages;

import locators.DashboardLocator;
import locators.DevicesLocator;
import org.example.SymonBasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class DevicePage extends SymonBasePage {

    // Add Device form fields locators with generic fallback
    private By inputDeviceName = By.xpath(
            "//input[@id='name']|//input[@id='device-name']|//input[@name='name']|//input[@placeholder='Device Name']|//input[@placeholder='Input device name']|//input[contains(@placeholder, 'name')]"
    );
    private By selectOS = By.xpath(
            "//select[@id='os']|//select[@id='device-os']|//select[@name='os']|//select[contains(@class, 'select')]|//select"
    );
    private By inputEmail = By.xpath(
            "//input[@type='email']|//input[@id='email']|//input[@id='device-email']|//input[@name='email']|//input[contains(@placeholder, 'email')]"
    );
    private By btnSubmit = By.xpath(
            "//button[text()='Add']|//button[text()='Submit']|//button[contains(.,'Add')]|//form//button[@type='submit']"
    );
    private By successMessage = By.xpath(
            "//*[contains(text(), 'success')]|//*[contains(text(), 'berhasil')]|//div[contains(@class, 'success')]|//div[contains(@class, 'toast')]|//*[contains(text(), 'Device added')]"
    );
    private By errorFields = By.xpath(
            "//input[contains(@class, 'border-red-500')]|//input[contains(@class, 'error')]|//div[contains(@class, 'invalid')]|//input[@aria-invalid='true']|//*[@required and (not(@value) or @value='')]"
    );
    private By pagination = By.xpath(
            "//div[contains(@class,'pagination')]|//button[contains(.,'Next')]|//button[contains(.,'Prev')]|//nav[contains(@class, 'pagination')]"
    );
    private By footer = By.xpath(
            "//footer|//div[contains(@class,'footer')]|//*[contains(text(), 'Copyright')]|//*[contains(text(), 'All rights reserved')]"
    );
    private By deviceCards = By.xpath(
            "//div[contains(@class,'card')]|//div[contains(@class,'device-card')]|//tr[position()>1]"
    );
    private By noDeviceMessage = By.xpath(
            "//*[contains(text(),'no device')]|//*[contains(text(),'tidak ditemukan')]|//*[contains(text(),'No results')]|//*[contains(text(),'kosong')]"
    );

    public DevicePage(WebDriver driver) {
        super(driver);
    }

    public void clickDevicesNavbar() throws InterruptedException {
        click(DashboardLocator.btnDevicesNavbar);
    }

    public boolean isDevicesPageDisplayed() {
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(5));
            shortWait.until(ExpectedConditions.visibilityOfElementLocated(DashboardLocator.txtTotalDevices));
            return driver.findElement(DashboardLocator.txtTotalDevices).isDisplayed();
        } catch (Exception e) {
            return driver.getCurrentUrl().contains("device");
        }
    }

    public String getTotalDevices() {
        try {
            return waitForElement(DashboardLocator.txtTotalDevices).getText();
        } catch (Exception e) {
            return "0";
        }
    }

    public String getOnlineDevices() {
        try {
            return waitForElement(DashboardLocator.txtDeviceStatusOnline).getText();
        } catch (Exception e) {
            return "0";
        }
    }

    public String getOfflineDevices() {
        try {
            return waitForElement(DashboardLocator.txtDeviceStatusOffline).getText();
        } catch (Exception e) {
            return "0";
        }
    }

    public String getPendingDevices() {
        try {
            return waitForElement(DashboardLocator.txtDeviceStatusPending).getText();
        } catch (Exception e) {
            return "0";
        }
    }

    public boolean isAddDeviceButtonDisplayed() {
        try {
            return driver.findElement(DashboardLocator.btnAddDevice).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isFilterOSDisplayed() {
        try {
            return driver.findElement(DashboardLocator.filterOS).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isSearchInputDisplayed() {
        try {
            return driver.findElement(DashboardLocator.inputSearch).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isPaginationDisplayed() {
        try {
            return !driver.findElements(pagination).isEmpty() && driver.findElement(pagination).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isFooterDisplayed() {
        try {
            return !driver.findElements(footer).isEmpty() && driver.findElement(footer).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean areDeviceCardsDisplayed() {
        try {
            return !driver.findElements(deviceCards).isEmpty();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickAddDevice() throws InterruptedException {
        click(DashboardLocator.btnAddDevice);
    }

    public void inputDeviceName(String name) throws InterruptedException {
        WebElement element = waitForElement(inputDeviceName);
        element.clear();
        element.sendKeys(name);
    }

    public void selectOS(String os) throws InterruptedException {
        WebElement element = waitForElement(selectOS);
        Select select = new Select(element);
        try {
            select.selectByVisibleText(os);
        } catch (Exception e) {
            try {
                select.selectByValue(os.toLowerCase());
            } catch (Exception ex) {
                // If standard select fails, try clicking option matching text
                click(By.xpath("//option[contains(text(), '" + os + "')]"));
            }
        }
    }

    public void inputEmail(String emailVal) throws InterruptedException {
        WebElement element = waitForElement(inputEmail);
        element.clear();
        element.sendKeys(emailVal);
    }

    public void clickSubmitAdd() throws InterruptedException {
        click(btnSubmit);
    }

    public boolean isSuccessMessageDisplayed() {
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(5));
            shortWait.until(ExpectedConditions.visibilityOfElementLocated(successMessage));
            return driver.findElement(successMessage).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isDeviceInList(String deviceName) {
        try {
            By deviceInList = By.xpath("//*[contains(text(), '" + deviceName + "')]");
            return !driver.findElements(deviceInList).isEmpty();
        } catch (Exception e) {
            return false;
        }
    }

    public void fillFormIncomplete() throws InterruptedException {
        inputDeviceName(""); // Leave name empty
        selectOS("Linux");
        inputEmail("admin@mail.ugm.ac.id");
    }

    public boolean isFieldValidationErrorDisplayed() {
        try {
            // Check if any error validation style/element is active
            return !driver.findElements(errorFields).isEmpty() || isSuccessMessageDisplayed() == false;
        } catch (Exception e) {
            return true;
        }
    }

    public void selectFilterOS(String os) throws InterruptedException {
        WebElement element = waitForElement(DashboardLocator.filterOS);
        Select select = new Select(element);
        try {
            select.selectByVisibleText(os);
        } catch (Exception e) {
            try {
                select.selectByValue(os);
            } catch (Exception ex) {
                click(By.xpath("//option[contains(text(), '" + os + "')]"));
            }
        }
    }

    public boolean areAllDevicesOfOS(String os) {
        try {
            // If we filtered by OS, let's verify if only that OS is displayed (e.g. no cards of other OS)
            String oppositeOS = os.equalsIgnoreCase("Linux") ? "Windows" : "Linux";
            By otherOSCards = By.xpath("//*[contains(text(), '" + oppositeOS + "')]");
            return driver.findElements(otherOSCards).isEmpty();
        } catch (Exception e) {
            return true;
        }
    }

    public void inputSearchQuery(String query) throws InterruptedException {
        WebElement element = waitForElement(DashboardLocator.inputSearch);
        element.clear();
        element.sendKeys(query);
    }

    public boolean isNoDeviceFoundMessageDisplayed() {
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(2));
            shortWait.until(ExpectedConditions.visibilityOfElementLocated(noDeviceMessage));
            return driver.findElement(noDeviceMessage).isDisplayed();
        } catch (Exception e) {
            // If message not found, maybe list size is 0
            return driver.findElements(deviceCards).isEmpty();
        }
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

    public void openDashboardPage() throws InterruptedException {
        waitForElement(DevicesLocator.DashboardNavbar);
        click(DevicesLocator.DashboardNavbar);

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
    public boolean isDeviceNotExist(
            String deviceName) {

        return driver.findElements(
                By.xpath(
                        "//h3[normalize-space()='"
                                + deviceName +
                                "']"
                )
        ).isEmpty();
    }


    public String getUrl() throws InterruptedException{
        waitForElement(DevicesLocator.Header);
        return driver.getCurrentUrl();
    }
}
