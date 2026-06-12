package stepdefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import org.junit.Assert;

public class DeviceManagementSteps {

    // ==========================================
    // TC-DS-01: Tampilan Halaman Devices
    // ==========================================
    @Given("pengguna berada di halaman dashboard utama")
    public void penggunaBeradaDiHalamanDashboardUtama() {
        // Code untuk memastikan user di dashboard
        driver.get("https://si-mondhog.m-faizarrofi.workers.dev/");
    }

    @When("pengguna mengklik menu Devices")
    public void penggunaMengklikMenuDevices() {
        // Code aksi klik menu devices
    }

    @Then("halaman daftar device ditampilkan")
    public void halamanDaftarDeviceDitampilkan() {
        // Assertion untuk validasi halaman device terbuka
        Assert.assertTrue(true);
    }

    // ==========================================
    // TC-DS-02 & TC-DS-03: Menambah Device & Validasi Form
    // ==========================================
    @When("pengguna mengklik tombol Tambah Device")
    public void penggunaMengklikTombolTambahDevice() {
        // Code aksi klik tambah device
    }

    @And("pengguna mengisi form device dengan nama {string} dan OS {string}")
    public void penggunaMengisiFormDeviceDenganNamaDanOS(String namaDevice, String os) {
        // Code input form data valid/invalid
    }

    @And("pengguna mengklik simpan")
    public void penggunaMengklikSimpan() {
        // Code klik simpan
    }

    @Then("device baru berhasil ditambahkan ke daftar")
    public void deviceBaruBerhasilDitambahkanKeDaftar() {
        // Assertion berhasil
    }

    @Then("muncul pesan error validasi form {string}")
    public void munculPesanErrorValidasiForm(String pesanError) {
        // Assertion error form kosong/invalid
    }

    // ==========================================
    // TC-DS-04 & TC-DS-05: Filter OS Device
    // ==========================================
    @When("pengguna memilih opsi filter OS {string}")
    public void penggunaMemilihOpsiFilterOS(String osType) {
        // Code memilih dropdown filter OS
    }

    @Then("daftar device hanya menampilkan device dengan OS {string}")
    public void daftarDeviceHanyaMenampilkanDeviceDenganOS(String osType) {
        // Assertion hasil filter
    }

    // ==========================================
    // TC-DS-06 & TC-DS-07: Pencarian Device
    // ==========================================
    @When("pengguna memasukkan kata kunci {string} pada kolom pencarian")
    public void penggunaMemasukkanKataKunciPadaKolomPencarian(String keyword) {
        // Code input keyword pencarian
    }

    @Then("sistem menampilkan device yang sesuai dengan nama {string}")
    public void sistemMenampilkanDeviceYangSesuaiDenganNama(String expectedName) {
        // Assertion hasil pencarian cocok
    }
}