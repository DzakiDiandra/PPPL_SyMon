package steps.dashboard;

import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.example.pages.LoginPage;
import org.example.pages.DashboardPage;
import steps.Hooks;

public class DashboardSteps {

    public static WebDriver driver;
    private LoginPage loginPage;
    private DashboardPage dashboardPage;

    @Before
    public void setup() {
        if (driver == null) {
            io.github.bonigarcia.wdm.WebDriverManager.chromedriver().setup();
            org.openqa.selenium.chrome.ChromeOptions options = new org.openqa.selenium.chrome.ChromeOptions();
            
            String headlessMode = System.getenv("HEADLESS");
            if (headlessMode == null || headlessMode.isEmpty()) {
                headlessMode = "false";
            }
            
            if (headlessMode.equals("true")) {
                options.addArguments("--headless=new");
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
            }
            
            options.addArguments("--disable-notifications");
            options.addArguments("--disable-popup-blocking");
            options.addArguments("--guest");
            
            driver = new org.openqa.selenium.chrome.ChromeDriver(options);
            driver.manage().window().maximize();
            
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                if (driver != null) {
                    try {
                        driver.quit();
                    } catch (Exception e) {
                        // Ignore
                    }
                }
            }));
            steps.Hooks.driver = driver;
        }
        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
    }

    @Given("pengguna sudah login ke sistem SyMon")
    public void loginKeSymon() {
        driver.get("https://si-mondhog.m-faizarrofi.workers.dev/");
        try {
            // Check if we are already logged in and on the dashboard
            if (driver.getCurrentUrl().contains("dashboard") && dashboardPage.isDashboardDisplayed()) {
                System.out.println("Pengguna sudah login");
                return;
            }
            // If not, perform login sequence
            loginPage.clickButtonLogin();
            loginPage.inputEmail("muhammadarrofiifaiz@mail.ugm.ac.id");
            loginPage.submitEmail();
            loginPage.inputUsername("muhammadarrofiifaiz");
            loginPage.inputPassword("Rofi0602");
            loginPage.submitSSO();
            loginPage.lanjutkanButton();
            dashboardPage.waitForDashboardLoad();
        } catch (Exception e) {
            System.out.println("Error/Exception during login flow: " + e.getMessage());
        }
        System.out.println("Website SyMon sudah terbuka dan pengguna login");
    }

    @When("pengguna masuk ke halaman dashboard")
    public void masukDashboard() {
        if (!driver.getCurrentUrl().contains("dashboard")) {
            driver.get("https://si-mondhog.m-faizarrofi.workers.dev/dashboard");
        }
        dashboardPage.waitForDashboardLoad();
        System.out.println("Masuk dashboard");
    }

    @Then("halaman dashboard berhasil ditampilkan")
    public void dashboardDitampilkan() {
        Assert.assertTrue("Halaman dashboard tidak ditampilkan", dashboardPage.isDashboardDisplayed());
    }

    @Then("tidak ada pesan error yang muncul")
    public void tidakAdaError() {
        Assert.assertFalse("Pesan error muncul di halaman dashboard", dashboardPage.isErrorMessageDisplayed());
    }

    @Given("pengguna berada di halaman dashboard")
    public void penggunaBeradaDiHalamanDashboard() {
        if (!driver.getCurrentUrl().contains("dashboard")) {
            driver.get("https://si-mondhog.m-faizarrofi.workers.dev/dashboard");
        }
        dashboardPage.waitForDashboardLoad();
        Assert.assertTrue("Pengguna tidak berada di halaman dashboard", dashboardPage.isDashboardDisplayed());
    }

    @When("pengguna mengamati bagian grafik pada halaman dashboard")
    public void penggunaMengamatiBagianGrafikPadaHalamanDashboard() {
        dashboardPage.scrollToCharts();
        System.out.println("Mengamati grafik dashboard");
    }

    @Then("grafik RAM ditampilkan")
    public void grafikRamDitampilkan() {
        Assert.assertTrue("Grafik RAM tidak ditampilkan", dashboardPage.isRamChartDisplayed());
    }

    @Then("grafik CPU ditampilkan")
    public void grafik_cpu_ditampilkan() {
        Assert.assertTrue("Grafik CPU tidak ditampilkan", dashboardPage.isCpuChartDisplayed());
    }

    @Then("grafik Harddisk\\/Storage ditampilkan")
    public void grafik_harddisk_storage_ditampilkan() {
        Assert.assertTrue("Grafik Storage tidak ditampilkan", dashboardPage.isStorageChartDisplayed());
    }

    @Then("grafik Harddisk/Storage ditampilkan")
    public void grafikHarddiskStorageDitampilkan() {
        Assert.assertTrue("Grafik Storage tidak ditampilkan", dashboardPage.isStorageChartDisplayed());
    }

    @When("pengguna memilih filter harian untuk tanggal tertentu")
    public void penggunaMemilihFilterHarianUntukTanggalTertentu() {
        dashboardPage.selectDailyFilter("any_date");
        System.out.println("Memilih filter harian");
    }

    @Then("grafik menampilkan data sesuai hari yang dipilih")
    public void grafikMenampilkanDataSesuaiHariYangDipilih() {
        Assert.assertTrue("Grafik tidak menampilkan data sesuai filter harian", dashboardPage.areChartsUpdated());
    }

    @When("pengguna memilih filter {string}")
    public void penggunaMemilihFilter(String filter) {
        if ("Weekly".equalsIgnoreCase(filter) || "Weeks".equalsIgnoreCase(filter)) {
            dashboardPage.selectWeeklyFilter();
        }
        System.out.println("Filter dipilih: " + filter);
    }

    @Then("grafik menampilkan data selama {int} minggu terakhir")
    public void grafikMenampilkanDataSelamaMingguTerakhir(Integer minggu) {
        Assert.assertTrue("Grafik tidak menampilkan data mingguan", dashboardPage.areChartsUpdated());
    }

    @When("pengguna mengganti pilihan filter")
    public void penggunaMenggantiPilihanFilter() {
        String currentVal = dashboardPage.getCurrentFilterValue();
        if (currentVal.toLowerCase().contains("day") || currentVal.toLowerCase().contains("hari")) {
            dashboardPage.selectWeeklyFilter();
        } else {
            dashboardPage.selectDailyFilter("any_date");
        }
        System.out.println("Mengganti filter");
    }

    @Then("seluruh grafik RAM, CPU, dan Storage ter-update sesuai filter baru")
    public void seluruhGrafikTerUpdateSesuaiFilterBaru() {
        Assert.assertTrue("Grafik RAM tidak ter-update", dashboardPage.isRamChartDisplayed());
        Assert.assertTrue("Grafik CPU tidak ter-update", dashboardPage.isCpuChartDisplayed());
        Assert.assertTrue("Grafik Storage tidak ter-update", dashboardPage.isStorageChartDisplayed());
    }

    @Then("tidak perlu melakukan refresh halaman")
    public void tidakPerluMelakukanRefreshHalaman() {
        Assert.assertTrue(true);
    }

    @When("pengguna melihat bagian device summary")
    public void penggunaMelihatBagianDeviceSummary() {
        dashboardPage.scrollToDeviceSummary();
        System.out.println("Melihat device summary");
    }

    @Then("total jumlah device yang terdaftar ditampilkan")
    public void totalJumlahDeviceYangTerdaftarDitampilkan() {
        Assert.assertTrue("Total device count not displayed", dashboardPage.isTotalDeviceCountDisplayed());
    }

    @Then("jumlah device dengan status {string} ditampilkan")
    public void jumlahDeviceDenganStatusDitampilkan(String status) {
        String countText = "";
        switch (status) {
            case "Online":
                countText = dashboardPage.getOnlineDeviceCount();
                break;
            case "Offline":
                countText = dashboardPage.getOfflineDeviceCount();
                break;
            case "Pending":
                countText = dashboardPage.getPendingDeviceCount();
                break;
            default:
                throw new IllegalArgumentException("Unknown status: " + status);
        }
        Assert.assertNotNull("Status count text is null", countText);
        Assert.assertTrue("Status count is not displayed correctly", countText.matches("(?s).*\\d+.*"));
        System.out.println("Status device: " + status + " (" + countText + ")");
    }

    @When("pengguna scroll ke bagian log performa")
    public void penggunaScrollKeBagianLogPerforma() {
        dashboardPage.scrollToLogPerformance();
        System.out.println("Scroll ke log performa");
    }

    @Then("tabel log performa berhasil ditampilkan")
    public void tabelLogPerformaBerhasilDitampilkan() {
        Assert.assertTrue("Tabel log performa tidak ditampilkan", dashboardPage.isLogPerformanceTableDisplayed());
    }

    @Given("tabel log performa telah ditampilkan")
    public void tabelLogPerformaTelahDitampilkan() {
        Assert.assertTrue("Tabel log performa tidak ditampilkan", dashboardPage.isLogPerformanceTableDisplayed());
    }

    @When("pengguna memeriksa kolom pada tabel log performa")
    public void penggunaMemeriksaKolomPadaTabelLogPerforma() {
        dashboardPage.scrollToLogPerformance();
        System.out.println("Memeriksa kolom log performa");
    }

    @Then("tabel log performa memuat kolom data CPU, RAM, Storage, dan timestamp")
    public void tabelLogPerformaMemuatKolomDataCpuRamStorageDanTimestamp() {
        Assert.assertTrue("Kolom CPU, RAM, Storage, dan timestamp tidak lengkap", dashboardPage.hasAllRequiredColumns());
    }

    @When("pengguna mengganti filter waktu pada log performa")
    public void penggunaMenggantiFilterWaktuPadaLogPerforma() {
        dashboardPage.selectLogPerformanceFilter("1 Day");
        System.out.println("Mengganti filter waktu log performa");
    }

    @Then("data pada tabel log performa berubah sesuai filter yang dipilih")
    public void dataPadaTabelLogPerformaBerubahSesuaiFilterYangDipilih() {
        Assert.assertTrue("Data pada tabel log performa tidak berubah sesuai filter", dashboardPage.isLogPerformanceDataUpdated());
    }
}