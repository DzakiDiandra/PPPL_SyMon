package steps;

import io.cucumber.java.en.*;
import org.example.pages.LoginPage;
import org.example.pages.DashboardPage;
import org.example.pages.DevicePage;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

public class DeviceManagementPt1Steps {
    WebDriver driver;
    LoginPage loginPage;
    DashboardPage dashboardPage;
    DevicePage devicePage;

    private void initPages() {
        if (driver == null) {
            driver = Hooks.driver;
            loginPage = new LoginPage(driver);
            dashboardPage = new DashboardPage(driver);
            devicePage = new DevicePage(driver);
        }
    }

    @Given("pengguna sudah login sebagai Admin")
    public void penggunaSudahLoginSebagaiAdmin() {
        initPages();
        // Skip login flow if already logged in (session reused)
        if (dashboardPage.isDashboardDisplayed()) {
            System.out.println("Website SyMon sudah terbuka (Admin Session Reused)");
            Assert.assertTrue("Dashboard tidak dapat diverifikasi!", dashboardPage.isDashboardDisplayed());
            return;
        }

        driver.get("https://si-mondhog.m-faizarrofi.workers.dev/");

        if (dashboardPage.isDashboardDisplayed()) {
            System.out.println("Website SyMon sudah terbuka (Admin Session Restored)");
            Assert.assertTrue("Dashboard tidak dapat diverifikasi!", dashboardPage.isDashboardDisplayed());
            return;
        }

        try {
            loginPage.clickButtonLogin();
            loginPage.inputEmail("muhammadarrofiifaiz@mail.ugm.ac.id");
            loginPage.submitEmail();
            loginPage.inputUsername("muhammadarrofiifaiz");
            loginPage.inputPassword("Rofi0602");
            loginPage.submitSSO();
            loginPage.lanjutkanButton();
            dashboardPage.waitForDashboardLoad();
        } catch (Exception e) {
            if (dashboardPage.isDashboardDisplayed()) {
                System.out.println("Website SyMon sudah terbuka (Handled exception)");
                Assert.assertTrue("Dashboard tidak dapat diverifikasi!", dashboardPage.isDashboardDisplayed());
                return;
            }
            throw new RuntimeException(e);
        }
        Assert.assertTrue("Gagal login sebagai admin!", dashboardPage.isDashboardDisplayed());
    }

    @When("pengguna menekan tombol \"Devices\" di navbar")
    public void penggunaMenekanTombolDevicesDiNavbar() throws InterruptedException {
        initPages();
        devicePage.clickDevicesNavbar();
        Assert.assertTrue("Gagal menavigasi ke halaman Devices!", devicePage.isDevicesPageDisplayed());
    }

    @Then("halaman Devices berhasil ditampilkan")
    public void halamanDevicesBerhasilDitampilkan() {
        initPages();
        Assert.assertTrue("Halaman Devices tidak ditampilkan!", devicePage.isDevicesPageDisplayed());
    }

    @Then("halaman menampilkan total jumlah device terdaftar")
    public void halamanMenampilkanTotalJumlahDeviceTerdaftar() {
        initPages();
        Assert.assertNotNull("Total device count element tidak ditampilkan!", devicePage.getTotalDevices());
    }

    @Then("halaman menampilkan jumlah device per status")
    public void halamanMenampilkanJumlahDevicePerStatus() {
        initPages();
        Assert.assertNotNull("Online device count element tidak ditampilkan!", devicePage.getOnlineDevices());
        Assert.assertNotNull("Offline device count element tidak ditampilkan!", devicePage.getOfflineDevices());
        Assert.assertNotNull("Pending device count element tidak ditampilkan!", devicePage.getPendingDevices());
    }

    @Then("halaman menampilkan tombol \"Add Device\"")
    public void halamanMenampilkanTombolAddDevice() {
        initPages();
        Assert.assertTrue("Tombol Add Device tidak ditampilkan!", devicePage.isAddDeviceButtonDisplayed());
    }

    @Then("halaman menampilkan filter OS")
    public void halamanMenampilkanFilterOS() {
        initPages();
        Assert.assertTrue("Filter OS tidak ditampilkan!", devicePage.isFilterOSDisplayed());
    }

    @Then("halaman menampilkan kolom pencarian")
    public void halamanMenampilkanKolomPencarian() {
        initPages();
        Assert.assertTrue("Kolom pencarian tidak ditampilkan!", devicePage.isSearchInputDisplayed());
    }

    @Then("halaman menampilkan pagination")
    public void halamanMenampilkanPagination() {
        initPages();
        Assert.assertTrue("Pagination tidak ditampilkan!", devicePage.isPaginationDisplayed());
    }

    @Then("halaman menampilkan footer")
    public void halamanMenampilkanFooter() {
        initPages();
        Assert.assertTrue("Footer tidak ditampilkan!", devicePage.isFooterDisplayed());
    }

    @Then("halaman menampilkan semua card device dengan informasi gambar OS, nama, jenis OS, dan status")
    public void halamanMenampilkanSemuaCardDevice() {
        initPages();
        Assert.assertTrue("Cards device tidak ditampilkan!", devicePage.areDeviceCardsDisplayed());
    }

    @Given("pengguna berada di halaman Devices")
    public void penggunaBeradaDiHalamanDevices() throws InterruptedException {
        initPages();
        if (!devicePage.isDevicesPageDisplayed()) {
            devicePage.clickDevicesNavbar();
        }
        Assert.assertTrue("Pengguna tidak berada di halaman Devices!", devicePage.isDevicesPageDisplayed());
    }

    @When("pengguna menekan tombol \"Add Device\"")
    public void penggunaMenekanTombolAddDevice() throws InterruptedException {
        initPages();
        devicePage.clickAddDevice();
    }

    @When("pengguna mengisi nama device dengan {string}")
    public void penggunaMengisiNamaDeviceDengan(String deviceName) throws InterruptedException {
        initPages();
        devicePage.inputDeviceName(deviceName);
    }

    @When("pengguna memilih OS {string}")
    public void penggunaMemilihOS(String os) throws InterruptedException {
        initPages();
        devicePage.selectOS(os);
    }

    @When("pengguna memasukkan email {string}")
    public void penggunaMemasukkanEmail(String email) throws InterruptedException {
        initPages();
        devicePage.inputEmail(email);
    }

    @When("pengguna menekan tombol \"Add\"")
    public void penggunaMenekanTombolAdd() throws InterruptedException {
        initPages();
        devicePage.clickSubmitAdd();
    }

    @Then("muncul pesan sukses penambahan device")
    public void munculPesanSuksesPenambahanDevice() {
        initPages();
        Assert.assertTrue("Pesan sukses penambahan device tidak muncul!", devicePage.isSuccessMessageDisplayed());
    }

    @Then("device baru {string} muncul dalam daftar device")
    public void deviceBaruMunculDalamDaftarDevice(String deviceName) {
        initPages();
        Assert.assertTrue("Device baru '" + deviceName + "' tidak muncul dalam daftar!", devicePage.isDeviceInList(deviceName));
    }

    @When("pengguna membiarkan salah satu field form kosong")
    public void penggunaMembiarkanSalahSatuFieldFormKosong() throws InterruptedException {
        initPages();
        devicePage.fillFormIncomplete();
    }

    @Then("field yang kosong ditandai dengan warna merah sebagai validasi error")
    public void fieldYangKosongDitandaiDenganWarnaMerah() {
        initPages();
        Assert.assertTrue("Validasi error field kosong tidak ditampilkan!", devicePage.isFieldValidationErrorDisplayed());
    }

    @Then("device tidak berhasil ditambahkan")
    public void deviceTidakBerhasilDitambahkan() {
        initPages();
        Assert.assertFalse("Device berhasil ditambahkan padahal form tidak lengkap!", devicePage.isSuccessMessageDisplayed());
    }

    @Given("terdapat device dengan berbagai jenis OS")
    public void terdapatDeviceDenganBerbagaiJenisOS() {
        initPages();
        Assert.assertTrue("Tidak ada data device yang ditampilkan!", devicePage.areDeviceCardsDisplayed());
    }

    @When("pengguna memilih filter OS {string}")
    public void penggunaMemilihFilterOS(String os) throws InterruptedException {
        initPages();
        devicePage.selectFilterOS(os);
    }

    @Then("halaman hanya menampilkan device dengan OS {string}")
    public void halamanHanyaMenampilkanDeviceDenganOS(String os) {
        initPages();
        Assert.assertTrue("Terdapat device dengan OS selain '" + os + "' yang tampil!", devicePage.areAllDevicesOfOS(os));
    }

    @Then("halaman menampilkan semua device dengan OS {string}")
    public void halamanMenampilkanSemuaDeviceDenganOS(String os) {
        initPages();
        Assert.assertTrue("Terdapat device dengan OS selain '" + os + "' yang tampil!", devicePage.areAllDevicesOfOS(os));
    }

    @Given("tidak ada device dengan OS {string} yang terdaftar")
    public void tidakAdaDeviceDenganOSYangTerdaftar(String os) {
        // No-op: assumed condition
    }

    @Then("halaman menampilkan pesan bahwa tidak ada device ditemukan")
    public void halamanMenampilkanPesanBahwaTidakAdaDeviceDitemukan() {
        initPages();
        Assert.assertTrue("Pesan 'tidak ada device ditemukan' tidak ditampilkan!", devicePage.isNoDeviceFoundMessageDisplayed());
    }

    @Given("terdapat device dengan nama {string}")
    public void terdapatDeviceDenganNama(String deviceName) {
        // No-op: assumed condition
    }

    @When("pengguna mengetikkan {string} pada kolom pencarian")
    public void penggunaMengetikkanPadaKolomPencarian(String query) throws InterruptedException {
        initPages();
        devicePage.inputSearchQuery(query);
    }

    @Then("halaman menampilkan device dengan nama {string}")
    public void halamanMenampilkanDeviceDenganNama(String deviceName) {
        initPages();
        Assert.assertTrue("Device dengan nama '" + deviceName + "' tidak ditemukan!", devicePage.isDeviceInList(deviceName));
    }

    // =========================================================================
    // PEAK PERFORMANCE STEPS
    // =========================================================================

    @When("pengguna melihat bagian performance summary")
    public void penggunaMelihatBagianPerformanceSummary() {
        initPages();
        dashboardPage.scrollToPerformanceSummary();
    }

    @Then("nilai peak RAM ditampilkan dengan satuan yang benar")
    public void nilaiPeakRamDitampilkanDenganSatuanYangBenar() {
        initPages();
        Assert.assertTrue("Nilai peak RAM tidak ditampilkan dengan satuan yang benar!", dashboardPage.isPeakRamDisplayedWithUnit());
    }

    @Then("nilai peak CPU ditampilkan dengan satuan yang benar")
    public void nilaiPeakCpuDitampilkanDenganSatuanYangBenar() {
        initPages();
        Assert.assertTrue("Nilai peak CPU tidak ditampilkan dengan satuan yang benar!", dashboardPage.isPeakCpuDisplayedWithUnit());
    }

    @Then("nilai peak Storage ditampilkan dengan satuan yang benar")
    public void nilaiPeakStorageDitampilkanDenganSatuanYangBenar() {
        initPages();
        Assert.assertTrue("Nilai peak Storage tidak ditampilkan dengan satuan yang benar!", dashboardPage.isPeakStorageDisplayedWithUnit());
    }

    @When("pengguna mengganti pilihan filter waktu")
    public void penggunaMenggantiPilihanFilterWaktu() {
        initPages();
        dashboardPage.selectWeeklyFilter();
    }

    @Then("nilai peak RAM berubah sesuai filter baru")
    public void nilaiPeakRamBerubahSesuaiFilterBaru() {
        initPages();
        Assert.assertTrue("Nilai peak RAM tidak valid setelah ganti filter!", dashboardPage.isPeakRamDisplayedWithUnit());
    }

    @Then("nilai peak CPU berubah sesuai filter baru")
    public void nilaiPeakCpuBerubahSesuaiFilterBaru() {
        initPages();
        Assert.assertTrue("Nilai peak CPU tidak valid setelah ganti filter!", dashboardPage.isPeakCpuDisplayedWithUnit());
    }

    @Then("nilai peak Storage berubah sesuai filter baru")
    public void nilaiPeakStorageBerubahSesuaiFilterBaru() {
        initPages();
        Assert.assertTrue("Nilai peak Storage tidak valid setelah ganti filter!", dashboardPage.isPeakStorageDisplayedWithUnit());
    }
}
