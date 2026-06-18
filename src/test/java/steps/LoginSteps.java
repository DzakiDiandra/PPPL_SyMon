package steps;

import locators.LoginLocator;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import io.cucumber.java.en.*;
import org.example.pages.LoginPage;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.Assert;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.Collections;

public class LoginSteps {

    private static WebDriver driver;
    private LoginPage loginPage;

    // -------------------------------------------------------
    // Background
    // -------------------------------------------------------

    @Given("aplikasi SyMon dapat diakses di browser")
    public void aplikasiSyMonDapatDiaksesDBrowser() {
        // Pengecekan: Jika driver belum pernah dibuat (Skenario Pertama)
        if (driver == null) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--guest");
            options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
            options.setExperimentalOption("useAutomationExtension", false);

            options.addArguments("--disable-blink-features=AutomationControlled");
            options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36");

            driver = new ChromeDriver(options);
            driver.manage().window().maximize();
            driver.get("https://si-mondhog.m-faizarrofi.workers.dev/");
        } else {
            // JANGAN PAKSA driver.get() jika user sudah dalam posisi login!
            // Cukup cek, jika url saat ini masih di halaman login/oauth, baru kita arahkan ke URL utama.
            String currentUrl = driver.getCurrentUrl();
            if (currentUrl.contains("login") || currentUrl.equals("about:blank")) {
                driver.get("https://si-mondhog.m-faizarrofi.workers.dev/");
            }
        }

        loginPage = new LoginPage(driver);
    }

    // -------------------------------------------------------
    // TC-SUP-01: Tampilan halaman Login
    // -------------------------------------------------------

    @When("pengguna membuka halaman utama aplikasi SyMon")
    public void penggunaMembukahalamanUtamaAplikasiSyMon() {
        // Sudah dilakukan di Background (driver.get), tidak perlu aksi tambahan
    }

    @Then("halaman login ditampilkan")
    public void halamanLoginDitampilkan() throws InterruptedException {
        Assert.assertTrue("Halaman login tidak ditampilkan",
                loginPage.isLoginPageDisplayed());
    }

    @And("halaman menampilkan gambar PC di tengah")
    public void halamanMenampilkanGambarPCDiTengah() throws InterruptedException {
        Assert.assertTrue("Gambar PC tidak ditampilkan",
                loginPage.isGambarPCDisplayed());
    }

    @And("halaman menampilkan deskripsi singkat tentang SyMon")
    public void halamanMenampilkanDeskripsiSingkatTentangSyMon() throws InterruptedException {
        Assert.assertTrue("Deskripsi SyMon tidak ditampilkan",
                loginPage.isDeskripsiSyMonDisplayed());
    }

    @And("halaman menampilkan tombol {string}")
    public void halamanMenampilkanTombol(String labelTombol) throws InterruptedException {
        String actual = loginPage.getLoginBtnText();
        Assert.assertEquals("Teks tombol tidak sesuai", labelTombol, actual.trim());
    }

    // -------------------------------------------------------
    // METHOD DINAIMS UNTUK SEMUA INTERAKSI TOMBOL (SWITCH ACCOUNT, LOGOUT, ADD ADMIN, DLL)
    // -------------------------------------------------------

    @When("pengguna menekan tombol {string}")
    public void penggunaMenukanTombol(String labelTombol) throws InterruptedException {
        switch (labelTombol) {
            case "Login with UGM Email":
                loginPage.clickButtonLogin();
                break;
            case "Switch Account":
                loginPage.clickSwitchAccount();
                break;
            case "Log Out":
                loginPage.clickLogOut();
                break;
            case "Add Admin":
                loginPage.clickTombolAddAdmin();
                break;
            case "Add":
                loginPage.clickTombolAdd();
                break;
            case "Yes":
                loginPage.clickTombolYes();
                break;
            default:
                throw new IllegalArgumentException("Tombol tidak dikenal: " + labelTombol);
        }
    }

    // -------------------------------------------------------
    // TC-SUP-02 & TC-SUP-03: Interaksi di Halaman Login
    // -------------------------------------------------------

    @Given("pengguna berada di halaman login")
    public void penggunaBeradaDiHalamanLogin() throws InterruptedException {
        Assert.assertTrue("Halaman login tidak ditampilkan",
                loginPage.isLoginPageDisplayed());
    }

    @And("pengguna memilih akun email dengan domain selain {string}")
    public void penggunaMemilihAkunEmailDenganDomainSelain(String domain) throws InterruptedException {
        loginPage.inputEmail("testuser@gmail.com");
        loginPage.submitEmail();
    }

    @Then("login gagal dilakukan")
    public void loginGagalDilakukan() {
        // Verifikasi dilakukan di step berikutnya
    }

    @And("pengguna diarahkan ke halaman {string}")
    public void penggunaDiarahkanKeHalaman(String namaHalaman) throws InterruptedException {
        switch (namaHalaman) {
            case "Login Failed":
                Assert.assertTrue("Halaman Login Failed tidak ditampilkan",
                        loginPage.isLoginFailedPageDisplayed());
                break;
            case "dashboard sesuai role":
                Assert.assertTrue("Halaman Dashboard tidak ditampilkan",
                        loginPage.isDashboardDisplayed());
                break;
            default:
                throw new IllegalArgumentException("Halaman tidak dikenal: " + namaHalaman);
        }
    }

    // -------------------------------------------------------
    // TC-SUP-02 & TC-SUP-04: Login Berhasil dengan Email UGM
    // -------------------------------------------------------

    @Given("pengguna berada di halaman {string}")
    public void penggunaBeradaDiHalaman(String namaHalaman) throws InterruptedException {
        switch (namaHalaman) {
            case "Login Failed":
                Assert.assertTrue("Halaman Login Failed tidak ditampilkan",
                        loginPage.isLoginFailedPageDisplayed());
                break;
            default:
                throw new IllegalArgumentException("Halaman tidak dikenal: " + namaHalaman);
        }
    }

    @And("pengguna memilih akun email dengan domain {string}")
    public void penggunaMemilihAkunEmailDenganDomain(String domain) throws InterruptedException {
        if (domain.equals("@mail.ugm.ac.id")) {
            loginPage.inputEmail("mdzakidiandraputra@mail.ugm.ac.id");
            loginPage.submitEmail();
            loginPage.inputUsername("mdzakidiandraputra");
            loginPage.inputPassword("lup4Sandi");
            loginPage.submitSSO();
            loginPage.lanjutkanButton();
        }
    }

    @Then("login berhasil dilakukan")
    public void loginBerhasilDilakukan() {
        // Verifikasi dilakukan di step berikutnya
    }

    @And("pengguna diarahkan ke halaman dashboard sesuai role")
    public void penggunaDiarahkanKeHalamanDashboardSesuaiRole() throws InterruptedException {
        Assert.assertTrue("Halaman Dashboard tidak ditampilkan",
                loginPage.isDashboardDisplayed());
    }

    // -------------------------------------------------------
    // TC-SUP-05: Tampilan halaman Profile
    // -------------------------------------------------------

    @Given("pengguna sudah login dan berada di halaman dashboard")
    public void penggunaSudahLoginDanBeradaDiHalamanDashboard() throws InterruptedException {
        Assert.assertTrue("Pengguna tidak berada di halaman dashboard",
                loginPage.isDashboardDisplayed());
    }

    @When("pengguna menekan foto profil atau nama di pojok kanan atas navbar")
    public void penggunaMenukanFotoProfilAtauNamaDiPojokKananAtasNavbar() throws InterruptedException {
        loginPage.clickNavbarProfile();
    }

    @Then("pengguna diarahkan ke halaman profil")
    public void penggunaDiarahkanKeHalamanProfil() throws InterruptedException {
        Assert.assertTrue("Halaman profil tidak ditampilkan",
                loginPage.isProfilePageDisplayed());
    }

    @And("halaman profil menampilkan foto pengguna")
    public void halamanProfilMenampilkanFotoPengguna() throws InterruptedException {
        Assert.assertTrue("Foto pengguna tidak ditampilkan",
                loginPage.isProfileFotoDisplayed());
    }

    @And("halaman profil menampilkan nama pengguna")
    public void halamanProfilMenampilkanNamaPengguna() throws InterruptedException {
        Assert.assertTrue("Nama pengguna tidak ditampilkan",
                loginPage.isProfileNamaDisplayed());
    }

    @And("halaman profil menampilkan email pengguna")
    public void halamanProfilMenampilkanEmailPengguna() throws InterruptedException {
        Assert.assertTrue("Email pengguna tidak ditampilkan",
                loginPage.isProfileEmailDisplayed());
    }

    @And("halaman profil menampilkan role pengguna")
    public void halamanProfilMenampilkanRolePengguna() throws InterruptedException {
        Assert.assertTrue("Role pengguna tidak ditampilkan",
                loginPage.isProfileRoleDisplayed());
    }

    @And("halaman profil menampilkan footer")
    public void halamanProfilMenampilkanFooter() throws InterruptedException {
        Assert.assertTrue("Footer tidak ditampilkan",
                loginPage.isProfileFooterDisplayed());
    }

    @And("halaman profil menampilkan tombol {string}")
    public void halamanProfilMenampilkanTombol(String labelTombol) throws InterruptedException {
        if ("Log Out".equals(labelTombol)) {
            Assert.assertTrue("Tombol Log Out tidak ditampilkan",
                    loginPage.isLogOutBtnDisplayed());
        }
    }

    // -------------------------------------------------------
    // TC-RM-01: Akses fitur sesuai role
    // -------------------------------------------------------

    @Given("pengguna dengan role {string} sudah melakukan login")
    public void penggunaDenganRoleSudahMelakukanLogin(String role) throws InterruptedException {
        loginPage.clickButtonLogin();
        if ("Admin".equalsIgnoreCase(role)) {
            loginPage.inputEmail("mdzakidiandraputra@mail.ugm.ac.id");
            loginPage.submitEmail();
            loginPage.inputUsername("mdzakidiandraputra");
            loginPage.inputPassword("lup4Sandi");
            loginPage.submitSSO();
            loginPage.lanjutkanButton();
        } else if ("User".equalsIgnoreCase(role)) {
            loginPage.inputEmail("mdzakidiandraputra@mail.ugm.ac.id");
            loginPage.submitEmail();
            loginPage.inputUsername("mdzakidiandraputra");
            loginPage.inputPassword("lup4Sandi");
            loginPage.submitSSO();
            loginPage.lanjutkanButton();
        }
    }

    @When("sistem memverifikasi role pengguna melalui backend")
    public void sistemMemverifikasiRolePenggunaMelaluiBackend() {
        // Otomatis terverifikasi backend
    }

    @Then("pengguna mendapatkan hak akses sesuai role {string}")
    public void penggunaMendapatkanHakAksesSesuaiRole(String role) throws InterruptedException {
        if ("Admin".equalsIgnoreCase(role)) {
            Assert.assertTrue("Fitur Admin tidak tersedia untuk role Admin",
                    loginPage.isAdminFeatureDisplayed());
        } else if ("User".equalsIgnoreCase(role)) {
            Assert.assertTrue("Halaman Dashboard tidak ditampilkan untuk role User",
                    loginPage.isDashboardDisplayed());
        }
    }

    @And("fitur yang dibatasi untuk role lain tidak dapat diakses")
    public void fiturYangDibatasiUntukRoleLainTidakDapatDiakses() {
        Assert.assertFalse("Muncul halaman restricted access yang tidak seharusnya",
                loginPage.isRestrictedAccessDisplayed());
    }

    // -------------------------------------------------------
    // TC-ADM-01: Admin mengakses fitur Add Admin
    // -------------------------------------------------------

    @Given("pengguna sudah login sebagai Admin")
    public void penggunaBeradaDiHalamanProfil() throws InterruptedException {
        // Mengingat flow satu window bertahap, pastikan berada di halaman profil / dashboard admin
        Assert.assertTrue("Halaman profil tidak ditampilkan",
                loginPage.isProfilePageDisplayed());
    }

    @Then("modal atau form {string} muncul dan dapat diisi")
    public void modalAtauFormMunculDanDapatDiisi(String namaModal) throws InterruptedException {
        Assert.assertTrue("Modal Add Admin tidak ditampilkan",
                loginPage.isModalAddAdminDisplayed());
        Assert.assertTrue("Field email pada modal tidak dapat diisi",
                loginPage.isInputEmailAdminEnabled());
    }

    // -------------------------------------------------------
    // TC-ADM-03: Gagal tambah admin karena email kosong
    // -------------------------------------------------------

    @Given("modal Add Admin sudah terbuka")
    public void modalAddAdminSudahTerbuka() throws InterruptedException {
        // Jika belum terbuka otomatis dari test case sebelumnya, kita trigger klik
        if(!loginPage.isModalAddAdminDisplayed()) {
            loginPage.clickTombolAddAdmin();
        }
        Assert.assertTrue("Modal Add Admin tidak terbuka",
                loginPage.isModalAddAdminDisplayed());
    }

    @When("pengguna tidak mengisi field email")
    public void penggunaTidakMengisiFieldEmail() {
        // Dibiarkan kosong
    }

    @Then("muncul pesan error {string}")
    public void munculPesanError(String pesanError) throws InterruptedException {
        if ("Email required".equals(pesanError) || "Email required!".equals(pesanError)) {
            Assert.assertTrue("Pesan error 'Email required' tidak muncul",
                    loginPage.isPesanErrorEmailRequiredDisplayed());
            Assert.assertTrue("Teks pesan error tidak mengandung 'Required'",
                    loginPage.getPesanErrorEmailRequiredText().toLowerCase().contains("required"));
        }
    }

    // -------------------------------------------------------
    // TC-ADM-04: Gagal tambah admin karena format email tidak valid
    // -------------------------------------------------------

    @When("pengguna mengisi email dengan format tidak valid {string}")
    public void penggunaMengisiEmailDenganFormatTidakValid(String emailTidakValid) throws InterruptedException {
        loginPage.inputEmailAdmin(emailTidakValid);
    }

    @Then("muncul pesan validasi error format email")
    public void munculPesanValidasiErrorFormatEmail() throws InterruptedException {
        Assert.assertTrue("Pesan error format email tidak muncul",
                loginPage.isPesanErrorFormatEmailDisplayed());
    }

    // -------------------------------------------------------
    // TC-ADM-02: Berhasil menambah admin dengan email UGM valid
    // -------------------------------------------------------

    @When("pengguna mengisi email {string}")
    public void penggunaMengisiEmail(String email) throws InterruptedException {
        loginPage.inputEmailAdmin(email);
    }

    @Then("notif sukses muncul di layar")
    public void notifSuksesMunculDiLayar() throws InterruptedException {
        Assert.assertTrue("Notifikasi sukses tidak muncul",
                loginPage.isNotifikasiSuksesDisplayed());
    }
    // -------------------------------------------------------
    // TC-ADM-05: Notifikasi sukses setelah tambah admin
    // -------------------------------------------------------

    @Given("notifikasi sukses muncul di layar")
    public void notifikasiSuksesMunculDiLayar() throws InterruptedException {
        Assert.assertTrue("Notifikasi sukses tidak muncul",
                loginPage.isNotifikasiSuksesDisplayed());
    }

    @When("pengguna menekan tombol tutup notifikasi sukses")
    public void penggunaMenutupNotifikasiSukses() throws InterruptedException {
        // Panggil penutup notifikasi jika ada di base page Anda
        loginPage.CloseButtonSuccess();
    }

    @And("modal Add Admin tertutup")
    public void modalAddAdminTertutup() {
        Assert.assertTrue("Modal Add Admin masih terbuka setelah berhasil menambah admin",
                loginPage.isModalAddAdminClosed());
    }

    // -------------------------------------------------------
    // TC-SUP-06: Logout
    // -------------------------------------------------------

    @Given("pengguna sudah login dan berada di halaman profil")
    public void penggunaSudahLoginDanBeradaDiHalamanProfil() throws InterruptedException {
        Assert.assertTrue("Pengguna tidak berada di halaman profil",
                loginPage.isProfilePageDisplayed());
    }

    @Then("muncul pop-up konfirmasi logout")
    public void munculPopUpKonfirmasiLogout() throws InterruptedException {
        Assert.assertTrue("Pop-up konfirmasi logout tidak muncul",
                loginPage.isPopupKonfirmasiDisplayed());
    }

    @And("pengguna menekan tombol {string} pada pop-up")
    public void penggunaMenekanTombolPadaPopUp(String labelTombol) throws InterruptedException {
        if ("Yes".equals(labelTombol)) {
            loginPage.clickTombolYes();
        }
    }

    @Then("pengguna diarahkan kembali ke halaman Login")
    public void penggunaDiarahkanKembaliKeHalamanLogin() throws InterruptedException {
        Assert.assertTrue("Pengguna tidak diarahkan ke halaman Login",
                loginPage.isHalamanLoginDisplayed());
    }

    @And("sesi pengguna telah dihapus")
    public void sesiPenggunaTelahDihapus() throws InterruptedException {
        Assert.assertTrue("Sesi pengguna belum dihapus, halaman login tidak muncul",
                loginPage.isHalamanLoginDisplayed());
    }

    // -------------------------------------------------------
    // SIKLUS AKHIR HOOK: TUTUP BROWSER SETELAH RUNNER SELESAI
    // -------------------------------------------------------
    @io.cucumber.java.AfterAll
    public static void closeBrowser() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}