package stepdefs;

import io.cucumber.java.BeforeAll;
import io.cucumber.java.AfterAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import org.example.pages.DashboardPage;
import org.example.pages.LoginPage;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DeviceManagementSteps {
    static WebDriver driver;
    static LoginPage loginPage;
    static DashboardPage dashboardPage;

    // =========================================================================
    // HOOKS: MEMBUKA BROWSER HANYA SEKALI DI AWAL EKSEKUSI PENGUJIAN
    // =========================================================================
    @BeforeAll
    public static void setDriver(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--guest");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);

        driver.get("https://si-mondhog.m-faizarrofi.workers.dev/");
    }

    @AfterAll
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    // =========================================================================
    // STEP DEFINITIONS DENGAN PROTEKSI MULTI-SCENARIO
    // =========================================================================

    @Given("pengguna sudah login sebagai Admin")
    public void penggunaSudahLoginSebagaiAdmin() {
        String currentUrl = driver.getCurrentUrl();

        if (!currentUrl.contains("/dashboard") && !currentUrl.contains("/device")) {
            System.out.println("Browser mendeteksi belum login. Memulai proses SSO...");
            try {
                loginPage.clickButtonLogin();
                loginPage.inputEmail("muhammadzidanalhilali@mail.ugm.ac.id");
                loginPage.submitEmail();
                loginPage.inputUsername("muhammadzidanalhilali");
                loginPage.inputPassword("Zidan123@");
                loginPage.submitSSO();
                loginPage.lanjutkanButton();
            } catch (Exception e) {
                throw new RuntimeException("Gagal melakukan proses login awal!", e);
            }
        } else {
            System.out.println("Pengguna sudah dalam posisi login, langkah SSO dilewati secara otomatis.");
        }
    }

    @Given("pengguna berada di halaman dashboard")
    public void penggunaBeradaDiHalamanDashboard() {
        if (!driver.getCurrentUrl().contains("/dashboard")) {
            driver.get("https://si-mondhog.m-faizarrofi.workers.dev/dashboard");
        }

        org.openqa.selenium.support.ui.WebDriverWait wait = new org.openqa.selenium.support.ui.WebDriverWait(driver, java.time.Duration.ofSeconds(15));
        wait.until(org.openqa.selenium.support.ui.ExpectedConditions.urlContains("/dashboard"));

        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue("Pengguna tidak berada di halaman Dashboard! URL saat ini: " + currentUrl, currentUrl.contains("/dashboard"));
    }

    @Given("pengguna berada di halaman Devices")
    public void penggunaBeradaDiHalamanDevices() {
        if (!driver.getCurrentUrl().contains("/devices")) {
            dashboardPage.clickDevicesNavbar();
        }

        boolean isDisplayed = dashboardPage.isDevicesPageDisplayed();
        Assert.assertTrue("Gagal memastikan pengguna berada di halaman Devices!", isDisplayed);
    }

    // =========================================================================
    // TC-DS-01: TAMPILAN HALAMAN DEVICE
    // =========================================================================
    @When("pengguna menekan tombol {string} di navbar")
    public void penggunaMenekanTombolDiNavbar(String buttonName) {
        if (buttonName.equalsIgnoreCase("Devices")) {
            dashboardPage.clickDevicesNavbar();
        } else {
            throw new IllegalArgumentException("Tombol navbar " + buttonName + " belum ditangani.");
        }
    }

    @Then("halaman Devices berhasil ditampilkan")
    public void halamanDevicesBerhasilDitampilkan() {
        boolean isDisplayed = dashboardPage.isDevicesPageDisplayed();
        Assert.assertTrue("Gagal menampilkan halaman Devices!", isDisplayed);
    }

    @And("halaman menampilkan total jumlah device terdaftar")
    public void halamanMenampilkanTotalJumlahDeviceTerdaftar() {
        String totalDevicesText = dashboardPage.getTotalDevicesText();
        Assert.assertNotNull("Elemen total device tidak ditemukan!", totalDevicesText);
        Assert.assertFalse("Teks total device kosong!", totalDevicesText.trim().isEmpty());
        System.out.println("Total Devices: " + totalDevicesText);
    }

    @And("halaman menampilkan jumlah device per status")
    public void halamanMenampilkanJumlahDevicePerStatus() {
        String onlineCount = dashboardPage.getOnlineDevicesCount();
        String offlineCount = dashboardPage.getOfflineDevicesCount();
        String pendingCount = dashboardPage.getPendingDevicesCount();

        Assert.assertFalse("Counter Online kosong!", onlineCount.trim().isEmpty());
        Assert.assertFalse("Counter Offline kosong!", offlineCount.trim().isEmpty());
        Assert.assertFalse("Counter Pending kosong!", pendingCount.trim().isEmpty());
    }

    @And("halaman menampilkan tombol {string}")
    public void halamanMenampilkanTombol(String buttonName) {
        if (buttonName.equalsIgnoreCase("Add Device")) {
            Assert.assertTrue("Tombol Add Device tidak ditampilkan!", dashboardPage.isAddDeviceButtonVisible());
        } else {
            throw new IllegalArgumentException("Tombol " + buttonName + " belum ditangani.");
        }
    }

    @And("halaman menampilkan filter OS")
    public void halamanMenampilkanFilterOS() {
        Assert.assertTrue("Dropdown filter OS tidak ditampilkan!", dashboardPage.isFilterOSVisible());
    }

    @And("halaman menampilkan kolom pencarian")
    public void halamanMenampilkanKolomPencarian() {
        Assert.assertTrue("Input search tidak ditampilkan!", dashboardPage.isSearchInputVisible());
    }

    // =========================================================================
    // TC-DS-02 & TC-DS-03: MODUL TAMBAH DEVICE
    // =========================================================================
    @When("pengguna menekan tombol {string}")
    public void penggunaMenekanTombol(String buttonName) {
        if (buttonName.equalsIgnoreCase("Add Device")) {
            dashboardPage.clickAddDevice();
        } else if (buttonName.equalsIgnoreCase("Add")) {
            dashboardPage.clickSubmitAddDevice();
        } else {
            throw new IllegalArgumentException("Tombol '" + buttonName + "' belum ditangani.");
        }
    }

    @And("pengguna membiarkan salah satu field form kosong")
    public void penggunaMembiarkanSalahSatuFieldFormKosong() {
        dashboardPage.leaveFieldsEmpty();
    }

    @Then("field yang kosong ditandai dengan warna merah sebagai validasi error")
    public void fieldYangKosongDitandaiDenganWarnaMerahSebagaiValidasiError() {
        String borderColor = dashboardPage.getInputValidationColor("nama device");

        Assert.assertTrue("Field tidak ditandai dengan warna merah sebagai validasi error!",
                borderColor.contains("255") ||
                        borderColor.contains("252") ||
                        borderColor.contains("239") ||
                        borderColor.contains("220"));
    }

    @And("pengguna mengisi nama device dengan {string}")
    public void penggunaMengisiNamaDeviceDengan(String deviceName) {
        dashboardPage.inputDeviceName(deviceName);
    }

    @And("pengguna memilih OS {string}")
    public void penggunaMemilihOS(String osName) {
        dashboardPage.selectOS(osName);
    }

    @And("pengguna memasukkan email {string}")
    public void penggunaMemasukkanEmail(String email) {
        dashboardPage.inputDeviceEmail(email);
    }

    @Then("muncul pesan sukses penambahan device")
    public void munculPesanSuksesPenambahanDevice() {
        boolean isSuccess = dashboardPage.isSuccessMessageDisplayed();
        Assert.assertTrue("Pesan sukses penambahan device tidak ditampilkan!", isSuccess);

        org.openqa.selenium.support.ui.WebDriverWait wait = new org.openqa.selenium.support.ui.WebDriverWait(driver, java.time.Duration.ofSeconds(5));
        wait.until(org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable(org.openqa.selenium.By.id("success-alert-modal-close-button"))).click();

        try { Thread.sleep(500); } catch (InterruptedException e) { e.printStackTrace(); }
    }

    @And("device baru {string} muncul dalam daftar device")
    public void deviceBaruMunculDalamDaftarDevice(String deviceName) {
        boolean isDeviceVisible = dashboardPage.isNewDeviceVisibleInList(deviceName);
        Assert.assertTrue("Device '" + deviceName + "' tidak ditemukan dalam daftar!", isDeviceVisible);
    }

    // =========================================================================
    // TC-DS-04 & TC-DS-05: MODUL FILTER OS
    // =========================================================================
    @And("terdapat device dengan berbagai jenis OS")
    public void terdapatDeviceDenganBerbagaiJenisOS() {
        String totalDevices = dashboardPage.getTotalDevicesText();
        Assert.assertFalse("Daftar device kosong, tidak dapat melakukan pengujian filter OS!", totalDevices.equals("0"));
    }

    @When("pengguna memilih filter OS {string}")
    public void penggunaMemilihFilterOS(String osName) {
        dashboardPage.selectFilterOS(osName);
        try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }
    }

    @Then("halaman hanya menampilkan device dengan OS {string}")
    public void halamanHanyaMenampilkanDeviceDenganOS(String expectedOS) {
        verifyOSFiltering(expectedOS);
    }

    @Then("halaman menampilkan semua device dengan OS {string}")
    public void halamanMenampilkanSemuaDeviceDenganOS(String expectedOS) {
        verifyOSFiltering(expectedOS);
    }

    private void verifyOSFiltering(String expectedOS) {
        java.util.List<String> displayedOSList = dashboardPage.getDisplayedDevicesOSList();
        Assert.assertFalse("Daftar kosong setelah filter diterapkan!", displayedOSList.isEmpty());

        for (String actualOS : displayedOSList) {
            Assert.assertTrue("Ditemukan OS '" + actualOS + "' yang tidak sesuai dengan filter '" + expectedOS + "'!",
                    actualOS.equalsIgnoreCase(expectedOS));
        }
    }

    @And("tidak ada device dengan OS {string} yang terdaftar")
    public void tidakAdaDeviceDenganOSYangTerdaftar(String osName) {
        System.out.println("Pre-kondisi: Tidak ada device dengan OS " + osName);
    }

    @Then("halaman menampilkan pesan bahwa tidak ada device ditemukan")
    public void halamanMenampilkanPesanBahwaTidakAdaDeviceDitemukan() {
        org.openqa.selenium.By pesanKosongLocator = org.openqa.selenium.By.xpath("/html[1]/body[1]/div[2]/main[1]/div[1]/div[1]/div[3]/div[1]/p[1]");
        org.openqa.selenium.support.ui.WebDriverWait wait = new org.openqa.selenium.support.ui.WebDriverWait(driver, java.time.Duration.ofSeconds(10));

        boolean isPesanMuncul = wait.until(org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated(pesanKosongLocator)).isDisplayed();
        Assert.assertTrue("Pesan bahwa device tidak ditemukan gagal ditampilkan!", isPesanMuncul);
    }

    // =========================================================================
    // TC-DS-06 & TC-DS-07: MODUL SEARCH DEVICE
    // =========================================================================
    @And("terdapat device dengan nama {string}")
    public void terdapatDeviceDenganNama(String deviceName) {
        System.out.println("Pre-kondisi: Device '" + deviceName + "' terdaftar.");
    }

    @When("pengguna mengetikkan {string} pada kolom pencarian")
    public void penggunaMengetikkanPadaKolomPencarian(String deviceName) {
        org.openqa.selenium.WebElement searchInput = driver.findElement(locators.DashboardLocator.inputSearch);
        searchInput.clear();
        searchInput.sendKeys(deviceName);
        try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }
    }

    @Then("halaman menampilkan device dengan nama {string}")
    public void halamanMenampilkanDeviceDenganNama(String expectedName) {
        org.openqa.selenium.support.ui.WebDriverWait customWait = new org.openqa.selenium.support.ui.WebDriverWait(driver, java.time.Duration.ofSeconds(10));
        org.openqa.selenium.By deviceHeaderFlex = org.openqa.selenium.By.xpath("//main/div/div/div[3]//*[contains(text(), '" + expectedName + "')]");

        org.openqa.selenium.WebElement element = customWait.until(org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated(deviceHeaderFlex));
        Assert.assertTrue("Device '" + expectedName + "' tidak ditemukan!", element.isDisplayed());
    }

    // =========================================================================
    // MODUL IMPLEMENTASI: PEAK PERFORMANCE SUMMARY (TC-PEAK-01 s/d TC-PEAK-04)
    // =========================================================================
    @When("pengguna melihat bagian performance summary")
    public void penggunaMelihatBagianPerformanceSummary() {
        Assert.assertTrue("Bagian Peak Performance Summary tidak terlihat!",
                dashboardPage.isPerformanceSummaryVisible());
    }

    @Then("nilai peak RAM ditampilkan dengan satuan yang benar")
    public void nilaiPeakRAMDitampilkanDenganSatuanYangBenar() {
        String ramText = dashboardPage.getPeakRAMText();
        Assert.assertTrue("Format satuan RAM tidak valid! Teks: " + ramText, ramText.contains("GB") || ramText.contains("MB"));
    }

    @Then("nilai peak CPU ditampilkan dengan satuan yang benar")
    public void nilaiPeakCPUDitampilkanDenganSatuanYangBenar() {
        String cpuText = dashboardPage.getPeakCPUText();
        Assert.assertTrue("Format satuan CPU harus menggunakan 'Cores'! Teks: " + cpuText, cpuText.contains("Cores"));
    }

    @Then("nilai peak Storage ditampilkan dengan satuan yang benar")
    public void nilaiPeakStorageDitampilkanDenganSatuanYangBenar() {
        String storageText = dashboardPage.getPeakStorageText();
        Assert.assertTrue("Format satuan Storage tidak valid! Teks: " + storageText, storageText.contains("GB") || storageText.contains("TB"));
    }


//    private String initialRam;
//    private String initialCpu;
//    private String initialStorage;

    // =========================================================================
    // TC-PEAK-04: FILTER TIME BASED (DAYS VS WEEKS)
    // =========================================================================
//    @When("pengguna mengganti pilihan filter waktu")
//    public void penggunaMenggantiPilihanFilterWaktu() {
//        initialRam = dashboardPage.getPeakRAMText();
//        initialCpu = dashboardPage.getPeakCPUText();
//        initialStorage = dashboardPage.getPeakStorageText();
//
//        dashboardPage.clickDaysFilter();
//
//        try { Thread.sleep(1500); } catch (InterruptedException e) { e.printStackTrace(); }
//    }
//
//    @Then("nilai peak RAM berubah sesuai filter baru")
//    public void nilaiPeakRAMBerubahSesuaiFilterBaru() {
//        String currentRam = dashboardPage.getPeakRAMText();
//        Assert.assertNotEquals("Nilai RAM tidak mengalami perubahan setelah filter diganti!", initialRam, currentRam);
//    }
//
//    @And("nilai peak CPU berubah sesuai filter baru")
//    public void nilaiPeakCPUBerubahSesuaiFilterBaru() {
//        String currentCpu = dashboardPage.getPeakCPUText();
//        Assert.assertNotEquals("Nilai CPU tidak mengalami perubahan setelah filter diganti!", initialCpu, currentCpu);
//    }
//
//    @And("nilai peak Storage berubah sesuai filter baru")
//    public void nilaiPeakStorageBerubahSesuaiFilterBaru() {
//        String currentStorage = dashboardPage.getPeakStorageText();
//        Assert.assertNotEquals("Nilai Storage tidak mengalami perubahan setelah filter diganti!", initialStorage, currentStorage);
//    }
}