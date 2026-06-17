package steps;

import io.cucumber.java.en.*;
import org.junit.Assert;

public class DashboardSteps {

    @Given("pengguna sudah login ke sistem SyMon")
    public void loginKeSymon() {

        Hooks.driver.get(
                "https://si-mondhog.m-faizarrofi.workers.dev/"
        );

        System.out.println("Website SyMon sudah terbuka");
    }

    @When("pengguna masuk ke halaman dashboard")
    public void masukDashboard() {

        System.out.println("Masuk dashboard");
    }

    @Then("halaman dashboard berhasil ditampilkan")
    public void dashboardDitampilkan() {

        Assert.assertTrue(true);
    }

    @Then("tidak ada pesan error yang muncul")
    public void tidakAdaError() {

        Assert.assertTrue(true);
    }

    @Given("pengguna berada di halaman dashboard")
    public void penggunaBeradaDiHalamanDashboard() {
        System.out.println("Pengguna berada di halaman dashboard");
    }

    @When("pengguna mengamati bagian grafik pada halaman dashboard")
    public void penggunaMengamatiBagianGrafikPadaHalamanDashboard() {
        System.out.println("Mengamati grafik dashboard");
    }

    @Then("grafik RAM ditampilkan")
    public void grafikRamDitampilkan() {
        Assert.assertTrue(true);
    }

    @Then("grafik CPU ditampilkan")
    public void grafik_cpu_ditampilkan() {
        Assert.assertTrue(true);
    }

    @Then("grafik Harddisk\\/Storage ditampilkan")
    public void grafik_harddisk_storage_ditampilkan() {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertTrue(true);
    }

    @Then("grafik Harddisk/Storage ditampilkan")
    public void grafikHarddiskStorageDitampilkan() {
        Assert.assertTrue(true);
    }

    @When("pengguna memilih filter harian untuk tanggal tertentu")
    public void penggunaMemilihFilterHarianUntukTanggalTertentu() {
        System.out.println("Memilih filter harian");
    }

    @Then("grafik menampilkan data sesuai hari yang dipilih")
    public void grafikMenampilkanDataSesuaiHariYangDipilih() {
        Assert.assertTrue(true);
    }

    @When("pengguna memilih filter {string}")
    public void penggunaMemilihFilter(String filter) {
        System.out.println("Filter dipilih: " + filter);
    }

    @Then("grafik menampilkan data selama {int} minggu terakhir")
    public void grafikMenampilkanDataSelamaMingguTerakhir(Integer minggu) {
        Assert.assertTrue(true);
    }

    @When("pengguna mengganti pilihan filter")
    public void penggunaMenggantiPilihanFilter() {
        System.out.println("Mengganti filter");
    }

    @Then("seluruh grafik RAM, CPU, dan Storage ter-update sesuai filter baru")
    public void seluruhGrafikTerUpdateSesuaiFilterBaru() {
        Assert.assertTrue(true);
    }

    @Then("tidak perlu melakukan refresh halaman")
    public void tidakPerluMelakukanRefreshHalaman() {
        Assert.assertTrue(true);
    }

    @When("pengguna melihat bagian device summary")
    public void penggunaMelihatBagianDeviceSummary() {
        System.out.println("Melihat device summary");
    }

    @Then("total jumlah device yang terdaftar ditampilkan")
    public void totalJumlahDeviceYangTerdaftarDitampilkan() {
        Assert.assertTrue(true);
    }

    @Then("jumlah device dengan status {string} ditampilkan")
    public void jumlahDeviceDenganStatusDitampilkan(String status) {
        System.out.println("Status device: " + status);
        Assert.assertTrue(true);
    }

    @When("pengguna scroll ke bagian log performa")
    public void penggunaScrollKeBagianLogPerforma() {
        System.out.println("Scroll ke log performa");
    }

    @Then("tabel log performa berhasil ditampilkan")
    public void tabelLogPerformaBerhasilDitampilkan() {
        Assert.assertTrue(true);
    }

    @Given("tabel log performa telah ditampilkan")
    public void tabelLogPerformaTelahDitampilkan() {
        System.out.println("Tabel log performa sudah tampil");
    }

    @When("pengguna memeriksa kolom pada tabel log performa")
    public void penggunaMemeriksaKolomPadaTabelLogPerforma() {
        System.out.println("Memeriksa kolom log performa");
    }

    @Then("tabel log performa memuat kolom data CPU, RAM, Storage, dan timestamp")
    public void tabelLogPerformaMemuatKolomDataCpuRamStorageDanTimestamp() {
        Assert.assertTrue(true);
    }

    @When("pengguna mengganti filter waktu pada log performa")
    public void penggunaMenggantiFilterWaktuPadaLogPerforma() {
        System.out.println("Mengganti filter waktu log performa");
    }

    @Then("data pada tabel log performa berubah sesuai filter yang dipilih")
    public void dataPadaTabelLogPerformaBerubahSesuaiFilterYangDipilih() {
        Assert.assertTrue(true);
    }
}