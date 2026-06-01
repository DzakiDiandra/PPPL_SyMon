package steps;

import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.example.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DeviceManagementSteps {
    WebDriver driver;
    LoginPage loginPage;
    @Before("@device")
    public void setDriver(){
        driver = new ChromeDriver();
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
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Given("pengguna berada di halaman Devices")
    public void penggunaBeradaDiHalamanDevices() {
        // TODO
    }

    @Given("pengguna membuka halaman detail device {string}")
    public void penggunaMembukaHalamanDetailDevice(String deviceName) {
        // TODO
    }

    @Given("terdapat device {string} dengan status {string}")
    public void terdapatDeviceDenganStatus(String deviceName, String status) {
        // TODO
    }

    @Given("pengguna membuka halaman detail device {string} dengan status {string}")
    public void penggunaMembukaHalamanDetailDeviceDenganStatus(
            String deviceName,
            String status) {
        // TODO
    }

    // ======================================================
    // WHEN
    // ======================================================

    @When("pengguna memilih filter Time Series {string}")
    public void penggunaMemilihFilterTimeSeries(String filter) {
        // TODO
    }

    @When("pengguna memilih device {string} dari daftar")
    public void penggunaMemilihDeviceDariDaftar(String deviceName) {
        // TODO
    }

    @When("pengguna menekan tombol {string} di samping filter Time Series")
    public void penggunaMenekanTombolDiSampingFilterTimeSeries(
            String buttonName) {
        // TODO
    }

    @When("pengguna menekan tombol {string} di dekat nama device")
    public void penggunaMenekanTombolDiDekatNamaDevice(
            String buttonName) {
        // TODO
    }

    @When("pengguna memasukkan nama device baru {string}")
    public void penggunaMemasukkanNamaDeviceBaru(
            String newDeviceName) {
        // TODO
    }

    @When("pengguna mengkonfirmasi perubahan")
    public void penggunaMengkonfirmasiPerubahan() {
        // TODO
    }

    @When("pengguna menekan tombol {string}")
    public void penggunaMenekanTombol(
            String buttonName) {
        // TODO
    }

    @When("pengguna menekan tombol {string} pada pop-up konfirmasi")
    public void penggunaMenekanTombolPadaPopupKonfirmasi(
            String buttonName) {
        // TODO
    }

    // ======================================================
    // THEN
    // ======================================================

    @Then("grafik performa menampilkan data sesuai rentang waktu {string}")
    public void grafikPerformaMenampilkanDataSesuaiRentangWaktu(
            String filter) {
        // TODO
    }

    @Then("halaman detail device ditampilkan")
    public void halamanDetailDeviceDitampilkan() {
        // TODO
    }

    @Then("halaman menampilkan sistem overview")
    public void halamanMenampilkanSistemOverview() {
        // TODO
    }

    @Then("halaman menampilkan filter time series")
    public void halamanMenampilkanFilterTimeSeries() {
        // TODO
    }

    @Then("halaman menampilkan tombol {string}")
    public void halamanMenampilkanTombol(
            String buttonName) {
        // TODO
    }

    @Then("halaman menampilkan performance summary")
    public void halamanMenampilkanPerformanceSummary() {
        // TODO
    }

    @Then("halaman menampilkan tombol delete")
    public void halamanMenampilkanTombolDelete() {
        // TODO
    }

    @Then("grafik RAM, CPU, dan Disk ditampilkan dalam keadaan aktif")
    public void grafikRamCpuDanDiskDitampilkanDalamKeadaanAktif() {
        // TODO
    }

    @Then("tabel logs ditampilkan dalam keadaan aktif")
    public void tabelLogsDitampilkanDalamKeadaanAktif() {
        // TODO
    }

    @Then("grafik dan tabel logs ditampilkan dalam keadaan disable \\(abu-abu\\)")
    public void grafikDanTabelLogsDitampilkanDalamKeadaanDisable() {
        // TODO
    }

    @Then("proses download langsung berjalan")
    public void prosesDownloadLangsungBerjalan() {
        // TODO
    }

    @Then("muncul konfirmasi ketika download selesai")
    public void munculKonfirmasiKetikaDownloadSelesai() {
        // TODO
    }

    @Then("file CSV tersimpan di perangkat pengguna")
    public void fileCsvTersimpanDiPerangkatPengguna() {
        // TODO
    }

    @Then("nama device langsung berganti menjadi {string}")
    public void namaDeviceLangsungBergantiMenjadi(
            String newDeviceName) {
        // TODO
    }

    @Then("muncul pop-up konfirmasi penghapusan")
    public void munculPopupKonfirmasiPenghapusan() {
        // TODO
    }

    @Then("muncul notifikasi bahwa device berhasil dihapus")
    public void munculNotifikasiBahwaDeviceBerhasilDihapus() {
        // TODO
    }

    @Then("pengguna diarahkan kembali ke halaman Devices")
    public void penggunaDiarahkanKembaliKeHalamanDevices() {
        // TODO
    }

    @Then("device {string} tidak lagi muncul dalam daftar")
    public void deviceTidakLagiMunculDalamDaftar(
            String deviceName) {
        // TODO
    }
}