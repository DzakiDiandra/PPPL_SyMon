package steps;
import static org.junit.jupiter.api.Assertions.*;

import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.example.SymonBasePage;
import org.example.pages.DashboardPage;
import org.example.pages.DeviceDetailPage;
import org.example.pages.DevicePage;
import org.example.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class DeviceManagementSteps {
    WebDriver driver;
    LoginPage loginPage;
    DashboardPage dashboardPage;
    DevicePage devicePage;
    DeviceDetailPage deviceDetailPage;
    private int dataPointBefore;
    private int dataPointAfter;
    private final String downloadFolder =
            System.getProperty("user.dir") + "\\downloads";
    private String logBeforeFilter;
    private String logAfterFilter;

    @Before("@device")
    public void setDriver(){
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<>();

        prefs.put(
                "download.default_directory",
                downloadFolder
        );

        prefs.put(
                "download.prompt_for_download",
                false
        );

        prefs.put(
                "download.directory_upgrade",
                true
        );

        prefs.put(
                "profile.default_content_settings.popups",
                0
        );

        options.setExperimentalOption(
                "prefs",
                prefs
        );
        options.addArguments("--guest");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://si-mondhog.m-faizarrofi.workers.dev/");
    }

    @Given("pengguna sudah login sebagai Admin")
    public void penggunaSudahLoginSebagaiAdmin() {
        loginPage = new LoginPage(driver);
        try {
            loginPage.clickButtonLogin();
            loginPage.inputEmail("muhammadarrofiifaiz@mail.ugm.ac.id");
            loginPage.submitEmail();
            loginPage.inputUsername("muhammadarrofiifaiz");
            loginPage.inputPassword("Rofi0602");
            loginPage.submitSSO();
            loginPage.lanjutkanButton();
            dashboardPage = new DashboardPage(driver);
            dashboardPage.clickNavbarDevice();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Given("pengguna berada di halaman Devices")
    public void penggunaBeradaDiHalamanDevices() {
        try {
            devicePage = new DevicePage(driver);
            assertEquals(devicePage.getUrl(), "https://si-mondhog.m-faizarrofi.workers.dev/devices");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Given("pengguna membuka halaman detail device {string}")
    public void penggunaMembukaHalamanDetailDevice(String deviceName) {
        try {
            devicePage.openDeviceDetail(deviceName);
            deviceDetailPage = new DeviceDetailPage(driver);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Given("terdapat device {string} dengan status {string}")
    public void terdapatDeviceDenganStatus(String deviceName, String status) {
        try {
            devicePage.seenDeviceOnStatus(deviceName, status);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Given("pengguna membuka halaman detail device {string} dengan status {string}")
    public void penggunaMembukaHalamanDetailDeviceDenganStatus(
            String deviceName,
            String status) {
        try {
            devicePage.openDeviceDetail(deviceName);
            deviceDetailPage = new DeviceDetailPage(driver);
            assertTrue(deviceDetailPage.getPageUrl().contains("https://si-mondhog.m-faizarrofi.workers.dev/devices"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Given("pengguna berada di halaman dashboard")
    public void penggunaBeradaDiHalamanDashboard() {
        try {
            devicePage.openDashboardPage();

            dashboardPage = new DashboardPage(driver);

            assertEquals(
                    "https://si-mondhog.m-faizarrofi.workers.dev/dashboard",
                    dashboardPage.getUrl()
            );

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Given("tabel log event telah ditampilkan")
    public void tabelLogEventTelahDitampilkan() {
        try {
            dashboardPage.scrollToLogEvent();
            assertTrue(
                    dashboardPage
                            .isLogEventTableVisible()
            );

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // ======================================================
    // WHEN
    // ======================================================

    @When("pengguna memilih filter Time Series {string}")
    public void penggunaMemilihFilterTimeSeries(String filter) {
        try {

            dataPointBefore = deviceDetailPage.getTotalDataPoints();
            deviceDetailPage.selectTimeSeriesFilter(filter);

            Thread.sleep(2000);

            dataPointAfter =
                    deviceDetailPage.getTotalDataPoints();

            System.out.println(
                    "Before = " + dataPointBefore
            );

            System.out.println(
                    "After = " + dataPointAfter
            );

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @When("pengguna memilih device {string} dari daftar")
    public void penggunaMemilihDeviceDariDaftar(String deviceName) {
        try {
            devicePage.openDeviceDetail(deviceName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @When("pengguna menekan tombol {string} di samping filter Time Series")
    public void penggunaMenekanTombolDiSampingFilterTimeSeries(
            String buttonName) {
        try {
            switch (buttonName) {
                case "Download CSV":
                    deviceDetailPage.clickDownloadCsv();
                    SymonBasePage.saveDownloadedFile();
                    break;
                default:
                    fail("Button tidak dikenali: " + buttonName);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @When("pengguna menekan tombol {string} di dekat nama device")
    public void penggunaMenekanTombolDiDekatNamaDevice(String buttonName) {
        try {
            if(buttonName.equals("Edit")) {
                deviceDetailPage.clickEditButton();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @When("pengguna memasukkan nama device baru {string}")
    public void penggunaMemasukkanNamaDeviceBaru(String newDeviceName) {
        try {
            deviceDetailPage.inputDeviceName(newDeviceName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @When("pengguna mengkonfirmasi perubahan")
    public void penggunaMengkonfirmasiPerubahan() {
        try {
            deviceDetailPage.clickSaveButton();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @When("pengguna mengganti filter waktu pada log event")
    public void penggunaMenggantiFilterWaktuPadaLogEvent() {

        try {
            logBeforeFilter =
                    dashboardPage
                            .getFirstLogEventRowText();

            dashboardPage
                    .selectWeeksFilter();

            logAfterFilter =
                    dashboardPage
                            .getFirstLogEventRowText();
            System.out.println(logAfterFilter);
            System.out.println(logBeforeFilter);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @When("pengguna menekan tombol {string}")
    public void penggunaMenekanTombol(
            String buttonName) {
        try {
            if(buttonName.equals("Delete")) {
                deviceDetailPage.clickDeleteButton();
            }
            if (buttonName.equals("Download CSV")){
                dashboardPage.clickDownloadCsvLogEvent();
                SymonBasePage.saveDownloadedFile();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @When("pengguna menekan tombol {string} pada pop-up konfirmasi")
    public void penggunaMenekanTombolPadaPopupKonfirmasi(
            String buttonName) {
        try {

            if(buttonName.equals("Delete")) {

                deviceDetailPage.clickConfirmDelete();

            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @When("pengguna scroll ke bagian log event")
    public void penggunaScrollKeBagianLogEvent() {
        try {
            dashboardPage
                    .scrollToLogEvent();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // ======================================================
    // THEN
    // ======================================================

    @Then("grafik performa menampilkan data sesuai rentang waktu {string}")
    public void grafikPerformaMenampilkanDataSesuaiRentangWaktu(
            String filter) {
        if (filter.equals("1 Hours")){
            assertTrue(dataPointBefore == dataPointAfter);
        } else {
            assertTrue(dataPointAfter < dataPointBefore);
        }

        System.out.println(
                filter + " = " + dataPointAfter
        );
    }

    @Then("halaman detail device ditampilkan")
    public void halamanDetailDeviceDitampilkan() {
        try {
            deviceDetailPage = new DeviceDetailPage(driver);
            assertTrue(deviceDetailPage.getPageUrl().contains("https://si-mondhog.m-faizarrofi.workers.dev/devices"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Then("halaman menampilkan filter time series")
    public void halamanMenampilkanFilterTimeSeries() {
        try {
            assertTrue(
                    deviceDetailPage.isFilterVisible()
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Then("halaman menampilkan tombol {string}")
    public void halamanMenampilkanTombol(
            String buttonName) {
        try {
            switch (buttonName) {
                case "Download CSV":
                    assertTrue(
                            deviceDetailPage
                                    .isDownloadButtonVisible()
                    );
                    break;
                default:
                    fail(
                            "Button belum diimplementasikan: "
                                    + buttonName
                    );
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Then("halaman menampilkan performance summary")
    public void halamanMenampilkanPerformanceSummary() {
        try {
            assertTrue(deviceDetailPage.isPerformanceSummaryVisible());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Then("halaman menampilkan tombol delete")
    public void halamanMenampilkanTombolDelete() {
        try {
            assertTrue(deviceDetailPage.isDeleteButtonVisible());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Then("grafik RAM, CPU, dan Disk ditampilkan dalam keadaan aktif")
    public void grafikRamCpuDanDiskDitampilkanDalamKeadaanAktif() {
        try {
            assertAll(
                    () -> assertTrue(deviceDetailPage.isCpuChartVisible()),
                    () -> assertTrue(deviceDetailPage.isDiskChartVisible()),
                    () -> assertTrue(deviceDetailPage.isRamChartVisible())
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Then("tabel logs ditampilkan dalam keadaan aktif")
    public void tabelLogsDitampilkanDalamKeadaanAktif() {
        try {
            assertTrue(deviceDetailPage.isLogTableVisible());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Then("grafik dan tabel logs ditampilkan dalam keadaan {string}")
    public void grafikDanTabelLogsDitampilkanDalamKeadaan(String status) {
        try {
            assertEquals("Device " + status, deviceDetailPage.getStatusMessage(status) );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Then("tabel menampilkan event bertipe {string} dan {string}")
    public void tabelMenampilkanEventBertipeDan(
            String event1,
            String event2) {

        assertAll(
                () -> assertTrue(
                        dashboardPage
                                .hasEventType(event1)
                ),
                () -> assertTrue(
                        dashboardPage
                                .hasEventType(event2)
                )
        );
    }

    @Then("setiap event memuat informasi waktu kejadian")
    public void setiapEventMemuatInformasiWaktuKejadian() {

        assertTrue(
                dashboardPage
                        .allRowsHaveDate()
        );
    }

    @Then("proses download langsung berjalan")
    public void prosesDownloadLangsungBerjalan() {
        try {
            assertTrue(SymonBasePage.waitUntilFileDownloaded("Details"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Then("proses download file CSV langsung berjalan")
    public void prosesDownloadFileCSVLangsungBerjalan() {
        try {
            assertTrue(SymonBasePage.waitUntilFileDownloaded("Events"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Then("data pada tabel log event berubah sesuai filter yang dipilih")
    public void dataPadaTabelLogEventBerubahSesuaiFilterYangDipilih() {

        assertNotEquals(
                logBeforeFilter,
                logAfterFilter
        );
    }


    @Then("nama device langsung berganti menjadi {string}")
    public void namaDeviceLangsungBergantiMenjadi(
            String newDeviceName) {
        try {
            assertEquals(
                    newDeviceName,
                    deviceDetailPage.getDeviceName(newDeviceName)
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Then("muncul pop-up konfirmasi penghapusan")
    public void munculPopupKonfirmasiPenghapusan() {
        try {
            assertTrue(
                    deviceDetailPage.isDeleteConfirmationVisible());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Then("muncul notifikasi bahwa device berhasil dihapus")
    public void munculNotifikasiBahwaDeviceBerhasilDihapus() {
        try {

            assertTrue(
                    deviceDetailPage
                            .waitUntilRedirectedToDevices()
            );

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Then("tabel log event berhasil ditampilkan")
    public void tabelLogEventBerhasilDitampilkan() {
        try {
            assertTrue(
                    dashboardPage
                            .isLogEventTableVisible()
            );

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Then("device {string} tidak lagi muncul dalam daftar")
    public void deviceTidakLagiMunculDalamDaftar(
            String deviceName) {
        try {
            assertTrue(
                    devicePage.isDeviceNotExist(
                            deviceName
                    )
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}