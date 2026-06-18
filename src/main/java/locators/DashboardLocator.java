package locators;

import org.openqa.selenium.By;

public class DashboardLocator {

    // Tombol untuk navigasi ke halaman Devices
    public static final By btnDevicesNavbar = By.xpath("/html[1]/body[1]/div[2]/nav[1]/div[1]/div[2]/a[2]");

    // Elemen informasi/metrik di halaman Devices
    public static final By txtTotalDevices = By.xpath("/html[1]/body[1]/div[2]/main[1]/div[1]/div[1]/div[1]/div[1]/div[1]/span[2]");
    public static final By txtDeviceStatusOnline = By.xpath("/html[1]/body[1]/div[2]/main[1]/div[1]/div[1]/div[1]/div[1]/div[2]/span[2]");
    public static final By txtDeviceStatusOffline = By.xpath("/html[1]/body[1]/div[2]/main[1]/div[1]/div[1]/div[1]/div[1]/div[3]/span[2]");
    public static final By txtDeviceStatusPending = By.xpath("/html[1]/body[1]/div[2]/main[1]/div[1]/div[1]/div[1]/div[1]/div[4]/span[2]");

    // Elemen interaktif dan kontrol di halaman
    public static final By btnAddDevice = By.xpath("/html[1]/body[1]/div[2]/main[1]/div[1]/div[1]/header[1]/button[1]");
    public static final By filterOS = By.xpath("/html[1]/body[1]/div[2]/main[1]/div[1]/div[1]/div[2]/div[1]/select[1]");
    public static final By inputSearch = By.xpath("/html[1]/body[1]/div[2]/main[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/input[1]");

    // --- Elemen Form Tambah Device (Modal/Popup) ---
    public static final By inputDeviceName = By.id("add-device-hostname-input");
    public static By getOSLocatorById(String osName) {
        // Kita buat case-insensitive atau sesuaikan dengan pattern ID di HTML (misal di-lowercase)
        String formattedOS = osName.toLowerCase();

        // Hasilnya akan mencari elemen dengan id="os-linux", id="os-windows", dll.
        return By.id("add-device-os-buttons-" + formattedOS);
    }
    public static final By inputDeviceEmail = By.id("add-device-email-input");
    public static final By btnSubmitAdd = By.id("add-device-submit-btn");

    // --- Elemen Verifikasi Sukses ---
    // Toast/Alert pesan sukses
    public static final By txtSuccessMessage = By.id("success-alert-modal-title");
    public static final By btnCloseModal = By.id("success-alert-modal-close-button");
    // Baris pertama atau data baru di tabel/list device untuk verifikasi nama
    public static By getDeviceHeaderByName(String deviceName) {
        // Mencari tag h3 yang teksnya sesuai dengan nama device setelah di-trim (normalize-space)
        return By.xpath("//h3[normalize-space()='" + deviceName + "']");
    }



    // === TAMBAHAN BARU UNTUK TC-PEAK ===
    // Sesuaikan selector ini dengan id/class asli di komponen Performance Summary lu jirr
    public static By textPeakRAM = By.id("peak-ram-value");
    public static By textPeakCPU = By.id("peak-cpu-value");
    public static By textPeakStorage = By.id("peak-disk-value");
    //    public static By dropdownTimeFilter = By.id("time-filter-dropdown");
    public static final By daysFilter = By.id("dashboard-period-days");

    public static final By DeviceNavbar = By.linkText("Devices");
    public static final By Header = By.id("dashboard-summary-title");
    public static final By ButtonCSvPerformance = By.xpath("//button[contains(text(),'Download CSV')]");
    public static final By DaysButtonFilter = By.xpath("//a[normalize-space()='Days']");
    public static final By OneDayColumnFilter = By.xpath("//body/div/main/div/div/div/div/div[6]");
    public static final By DownloadCsvEvent = By.id("download-events-csv-btn");
    public static final By LogEventRows = By.cssSelector("[id^='event-log-row-']");
    public static final By LogEventRow1 = By.id("event-log-row-0");
    public static final By LogEventRow2 = By.id("event-log-row-1");
    public static final By TableLogEvent = By.id("dashboard-event-logs-card");
    public static final By FilterDay = By.id("dashboard-period-days");
    public static final By FilterWeeks = By.id("dashboard-period-weeks");

    // Locators moved from DashboardPage.java
    public static final By dashboardContainer = By.xpath(
            "//div[contains(@class, 'dashboard')]|//div[@id='dashboard']|//div[@id='main-content']"
    );
    public static final By dashboardTitle = By.xpath(
            "//h1[contains(text(), 'Dashboard')]|//h1[contains(text(), 'dashboard')]|//h2[contains(text(), 'Dashboard')]"
    );
    public static final By ramChartContainer = By.id("ram-average-chart");
    public static final By cpuChartContainer = By.id("cpu-average-chart");
    public static final By storageChartContainer = By.id("harddisk-average-chart");
    public static final By allCharts = By.xpath("//*[@id='ram-average-chart' or @id='cpu-average-chart' or @id='harddisk-average-chart']|//div[contains(@class, 'chart')]|//canvas");

    public static final By filterDropdown = By.xpath(
            "//select[@id='filter-time']|//select[contains(@class, 'filter')]|//button[contains(@class, 'filter')]"
    );
    public static final By dailyFilterOption = By.id("dashboard-period-days");
    public static final By weeklyFilterOption = By.id("dashboard-period-weeks");
    public static final By datePickerInput = By.xpath(
            "//input[@type='date']|//input[@placeholder='Select date']|//input[contains(@class, 'datepicker')]"
    );
    public static final By applyFilterButton = By.xpath(
            "//button[contains(text(), 'Apply')]|//button[contains(@class, 'apply')]|//button[contains(text(), 'Filter')]"
    );

    public static final By deviceSummarySection = By.xpath(
            "//div[contains(@class, 'summary')]|//section[contains(@id, 'device-summary')]|//div[contains(@id, 'summary')]"
    );
    public static final By totalDeviceCount = By.id("device-stats-total-card");
    public static final By onlineDeviceCount = By.id("device-stats-online-card");
    public static final By offlineDeviceCount = By.id("device-stats-offline-card");
    public static final By pendingDeviceCount = By.id("device-stats-pending-card");

    public static final By logPerformanceSection = By.xpath(
            "/html[1]/body[1]/div[2]/main[1]/div[1]/div[1]/div[1]/div[1]/div[5]|//div[contains(@class, 'log-performance')]|//section[contains(@id, 'log-performance')]|//div[contains(@id, 'performance-log')]"
    );
    public static final By logPerformanceTable = By.xpath(
            "/html[1]/body[1]/div[2]/main[1]/div[1]/div[1]/div[1]/div[1]/div[5]/div[1]/div[1]/div[2]|//table[contains(@class, 'log-performance')]|//table[contains(@id, 'performance-table')]|//table[contains(@class, 'performance')]|//table"
    );
    public static final By tableHeaders = By.xpath("//table//thead//th|//table//tr[1]//th");
    public static final By tableRows = By.xpath(
            "/html[1]/body[1]/div[2]/main[1]/div[1]/div[1]/div[1]/div[1]/div[5]/div[1]/div[1]/div[2]/div[1]/div|//table//tbody//tr|//table//tr[position()>1]"
    );
    public static final By cpuColumn = By.xpath(
            "/html[1]/body[1]/div[2]/main[1]/div[1]/div[1]/div[1]/div[1]/div[5]/div[1]/div[1]/div[2]/div[1]/div[1]/div[4]"
    );
    public static final By ramColumn = By.xpath(
            "/html[1]/body[1]/div[2]/main[1]/div[1]/div[1]/div[1]/div[1]/div[5]/div[1]/div[1]/div[2]/div[1]/div[1]/div[5]"
    );
    public static final By storageColumn = By.xpath(
            "/html[1]/body[1]/div[2]/main[1]/div[1]/div[1]/div[1]/div[1]/div[5]/div[1]/div[1]/div[2]/div[1]/div[1]/div[6]"
    );
    public static final By timestampColumn = By.xpath(
            "/html[1]/body[1]/div[2]/main[1]/div[1]/div[1]/div[1]/div[1]/div[5]/div[1]/div[1]/div[2]/div[1]/div[1]/div[7]"
    );
    public static final By logFilterDropdown = By.xpath("//select[@id='log-filter']|//div[contains(@class, 'log-performance')]//select");

    public static final By peakRamValue = By.xpath(
            "//*[contains(text(), 'Peak RAM')]/following-sibling::*|//*[contains(text(), 'RAM Peak')]/following-sibling::*|//div[contains(@class, 'peak-ram')]|//span[contains(@id, 'peak-ram')]|//div[contains(text(), 'Peak RAM')]/..//span|//div[contains(text(), 'Peak RAM')]/..//div|//h3[contains(text(), 'Peak RAM')]/..//p"
    );
    public static final By peakCpuValue = By.xpath(
            "//*[contains(text(), 'Peak CPU')]/following-sibling::*|//*[contains(text(), 'CPU Peak')]/following-sibling::*|//div[contains(@class, 'peak-cpu')]|//span[contains(@id, 'peak-cpu')]|//div[contains(text(), 'Peak CPU')]/..//span|//div[contains(text(), 'Peak CPU')]/..//div|//h3[contains(text(), 'Peak CPU')]/..//p"
    );
    public static final By peakStorageValue = By.xpath(
            "//*[contains(text(), 'Peak Storage')]/following-sibling::*|//*[contains(text(), 'Peak Harddisk')]/following-sibling::*|//div[contains(@class, 'peak-storage')]|//span[contains(@id, 'peak-storage')]|//div[contains(text(), 'Peak Storage')]/..//span|//div[contains(text(), 'Peak Storage')]/..//div|//h3[contains(text(), 'Peak Storage')]/..//p"
    );
    public static final By performanceSummarySection = By.xpath(
            "//div[contains(@class, 'summary')]|//div[contains(@class, 'performance')]|//section[contains(@id, 'performance-summary')]|//div[contains(@id, 'summary')]"
    );
}
