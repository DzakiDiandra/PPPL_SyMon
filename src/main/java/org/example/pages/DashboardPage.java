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

    // Constructor
    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        // Set explicit wait 10 detik biar ga gampang flaky pas nunggu elemen muncul
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // 1. When pengguna menekan tombol "Devices" di navbar
    public void clickDevicesNavbar() {
        wait.until(ExpectedConditions.elementToBeClickable(DashboardLocator.btnDevicesNavbar)).click();
    }

    // 2. Then halaman Devices berhasil ditampilkan
    // Kita validasi berdasarkan munculnya salah satu elemen utama di halaman tersebut (misal: tombol Add Device)
    public boolean isDevicesPageDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(DashboardLocator.btnAddDevice)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // 3. And halaman menampilkan total jumlah device terdaftar
    public String getTotalDevicesText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(DashboardLocator.txtTotalDevices)).getText();
    }

    // 4. And halaman menampilkan jumlah device per status (Online, Offline, Pending)
    public String getOnlineDevicesCount() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(DashboardLocator.txtDeviceStatusOnline)).getText();
    }

    public String getOfflineDevicesCount() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(DashboardLocator.txtDeviceStatusOffline)).getText();
    }

    public String getPendingDevicesCount() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(DashboardLocator.txtDeviceStatusPending)).getText();
    }

    // 5. And halaman menampilkan tombol "Add Device"
    public boolean isAddDeviceButtonVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(DashboardLocator.btnAddDevice)).isDisplayed();
    }

    // 6. And halaman menampilkan filter OS
    public boolean isFilterOSVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(DashboardLocator.filterOS)).isDisplayed();
    }

    // 7. And halaman menampilkan kolom pencarian
    public boolean isSearchInputVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(DashboardLocator.inputSearch)).isDisplayed();
    }

    // KEDUA

    public void clickAddDevice() {
        wait.until(ExpectedConditions.elementToBeClickable(DashboardLocator.btnAddDevice)).click();
    }

    // 2. And pengguna mengisi nama device dengan "Server Lab A"
    public void inputDeviceName(String deviceName) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(DashboardLocator.inputDeviceName));
        element.clear();
        element.sendKeys(deviceName);
    }

    // 3. And pengguna memilih OS "Linux" (Menggunakan tombol dinamis)
    public void selectOS(String osName) {
        // Memanggil string XPath dinamis dari locator yang dicocokkan dengan teks OS
        By dynamicOSButton = By.xpath("//*[normalize-space()='" + osName + "']");
        wait.until(ExpectedConditions.elementToBeClickable(dynamicOSButton)).click();
    }

    // 4. And pengguna memasukkan email "admin@mail.ugm.ac.id"
    public void inputDeviceEmail(String email) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(DashboardLocator.inputDeviceEmail));
        element.clear();
        element.sendKeys(email);
    }

    // 5. And pengguna menekan tombol "Add"
    public void clickSubmitAddDevice() {
        wait.until(ExpectedConditions.elementToBeClickable(DashboardLocator.btnSubmitAdd)).click();
    }

    // 6. Then muncul pesan sukses penambahan device
    public boolean isSuccessMessageDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(DashboardLocator.txtSuccessMessage)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // 7. And device baru "Server Lab A" muncul dalam daftar device (Menggunakan strategi h3 dinamis lu)
    public boolean isNewDeviceVisibleInList(String deviceName) {
        try {
            // Tembak langsung h3 berdasarkan nama device yang dikirim dari BDD step definition
            By dynamicDeviceHeader = By.xpath("//h3[normalize-space()='" + deviceName + "']");
            return wait.until(ExpectedConditions.visibilityOfElementLocated(dynamicDeviceHeader)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // =========================================================================
    // TAMBAHAN METHOD UNTUK SCENARIO: GAGAL MENAMBAH DEVICE (FORM KOSONG)
    // =========================================================================

    // 1. And pengguna membiarkan salah satu field form kosong
    public void leaveFieldsEmpty() {
        // Clear field menggunakan locator asli lu jirr
        wait.until(ExpectedConditions.visibilityOfElementLocated(DashboardLocator.inputDeviceName)).clear();
        wait.until(ExpectedConditions.visibilityOfElementLocated(DashboardLocator.inputDeviceEmail)).clear();
    }

    // 2. Ambil warna border field buat dicek pas error merah
    public String getInputValidationColor(String fieldName) {
        WebElement element;
        if (fieldName.equalsIgnoreCase("nama device")) {
            element = wait.until(ExpectedConditions.visibilityOfElementLocated(DashboardLocator.inputDeviceName));
        } else {
            element = wait.until(ExpectedConditions.visibilityOfElementLocated(DashboardLocator.inputDeviceEmail));
        }
        return element.getCssValue("border-color");
    }

    //KETIGAAA
    // 1. Memilih opsi dropdown filter
    public void selectFilterOS(String osValue) {
        WebElement dropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(DashboardLocator.filterOS));
        org.openqa.selenium.support.ui.Select select = new org.openqa.selenium.support.ui.Select(dropdown);
        select.selectByVisibleText(osValue);
    }

    public java.util.List<String> getDisplayedDevicesOSList() {
        // BERDASARKAN XPATH ASLI LU: /html[1]/body[1]/div[2]/main[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[2]/span[1]
        // div[3] adalah container list device. div[1]/div[1] itu baris/card device pertama.
        // Kita ganti index barisnya jadi '//' biar Selenium ngambil SEMUA card device yang muncul!
        By osElementsLocator = By.xpath("//main/div/div/div[3]/div/div/div[2]/span[1]");

        try {
            // Tunggu sampai minimal ada 1 elemen badge OS render di layar
            wait.until(ExpectedConditions.presenceOfElementLocated(osElementsLocator));
        } catch (Exception e) {
            return new java.util.ArrayList<>();
        }

        java.util.List<WebElement> elements = driver.findElements(osElementsLocator);
        java.util.List<String> osTexts = new java.util.ArrayList<>();

        for (WebElement element : elements) {
            String text = element.getText().trim();
            // Kita filter tipis-tipis di sini jirr, mastiin yang diambil beneran nama OS (bukan PENDING/ONLINE/OFFLINE)
            if (text.equalsIgnoreCase("Linux") || text.equalsIgnoreCase("Windows") || text.equalsIgnoreCase("macOS")) {
                osTexts.add(text);
            }
        }
        return osTexts;
    }

    // =========================================================================
    // TAMBAHAN METHOD UNTUK SCENARIO: FILTER HASIL KOSONG (TC-DS-05)
    // =========================================================================

    // Then halaman menampilkan pesan bahwa tidak ada device ditemukan
    public boolean isNoDeviceMessageDisplayed() {
        // BERDASARKAN XPATH ASLI LU: /html[1]/body[1]/div[2]/main[1]/div[1]/div[1]/div[3]/div[1]/p[1]
        // Kita ubah jadi Relative XPath biar dinamis tapi tetep 100% akurat nembak p[1] di dalam div[3]
        By noDeviceMessageLocator = By.xpath("//main/div/div/div[3]/div/p[1]");

        try {
            // Tunggu maksimal 10 detik (ngikutin explicit wait constructor lu) sampai teks pemberitahuan muncul
            return wait.until(ExpectedConditions.visibilityOfElementLocated(noDeviceMessageLocator)).isDisplayed();
        } catch (Exception e) {
            System.out.println("Jirr, tag <p> pesan kosongnya kagak nongol di layar!");
            return false;
        }
    }

    // =========================================================================
    // TAMBAHAN METHOD UNTUK SCENARIO: PENCARIAN DEVICE (TC-DS-06)
    // =========================================================================


    // Method buat ngetik di kolom search
    public void searchDevice(String deviceName) {
        WebElement searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(DashboardLocator.inputSearch));
        searchInput.clear();
        searchInput.sendKeys(deviceName);
    }

    // Method buat ngambil list nama device yang lagi tampil di layar
    public java.util.List<String> getDisplayedDeviceNamesList() {
        // Berdasarkan absolute XPath lu kemarin, nama device (header card) biasanya ada di tag h3 sebelum status/OS
        By deviceNameLocator = By.xpath("//main/div/div/div[3]/div/div/h3");

        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(deviceNameLocator));
        } catch (Exception e) {
            return new java.util.ArrayList<>();
        }

        java.util.List<WebElement> elements = driver.findElements(deviceNameLocator);
        java.util.List<String> names = new java.util.ArrayList<>();
        for (WebElement element : elements) {
            names.add(element.getText().trim());
        }
        return names;
    }



    // === TAMBAHAN METHOD BARU DI DALAM CLASS DASHBOARDPAGE ===

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
        // Panggil langsung dari file lokator lu yang dipisah tadi, brok
        org.openqa.selenium.WebElement daysBtn = wait.until(
                org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable(
                        DashboardLocator.daysFilter
                )
        );
        daysBtn.click();
    }
}