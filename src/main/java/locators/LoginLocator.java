package locators;

import org.openqa.selenium.By;

public class LoginLocator {

    // -------------------------------------------------------
    // LOGIN
    // -------------------------------------------------------
    public static final By LoginBtn         = By.xpath("//button[normalize-space()='Login with UGM Email']");
    public static final By InputEmail       = By.id("identifierId");
    public static final By InputUsernameSSO = By.id("username");
    public static final By InputPasswordSSO = By.id("password");
    public static final By LoginSSO         = By.name("submit");
    public static final By ContinueButton   = By.xpath("//button[contains(.,'Continue')]");
    public static final By NextButton       = By.id("identifierNext");

    // -------------------------------------------------------
    // TC-SUP-01: Tampilan halaman Login
    // -------------------------------------------------------
    public static final By GambarPC       = By.className("object-contain");
    public static final By DeskripsiSyMon = By.id("login-description");

    // -------------------------------------------------------
    // TC-SUP-03: Login Failed
    // -------------------------------------------------------
    public static final By LoginFailedPage = By.id("login-failed-panel");

    // -------------------------------------------------------
    // TC-SUP-04: Switch Account
    // -------------------------------------------------------
    public static final By SwitchAccountBtn = By.id("switch-account-button");

    // -------------------------------------------------------
    // TC-SUP-02 / TC-SUP-04: Dashboard setelah login berhasil
    // -------------------------------------------------------
    public static final By DashboardPage = By.id("dashboard-page-weeks");

    // -------------------------------------------------------
    // TC-SUP-05: Halaman Profil
    // -------------------------------------------------------
    public static final By NavbarProfileBtn = By.id("main-nav-profile-link");
    public static final By ProfilePage      = By.id("profile-page");
    public static final By ProfileFoto      = By.xpath("/html[1]/body[1]/div[2]/main[1]/div[1]/div[1]/div[2]/div[1]/div[1]/img[1]");
    public static final By ProfileNama      = By.id("profile-name-value");
    public static final By ProfileEmail     = By.id("profile-email-value");
    public static final By ProfileRole      = By.id("profile-role-value");
    public static final By ProfileFooter    = By.id("site-footer");
    public static final By TombolLogOut     = By.xpath("/html[1]/body[1]/div[2]/main[1]/div[1]/div[1]/div[1]/div[2]/button[2]/span[1]");

    // -------------------------------------------------------
    // TC-SUP-06: Logout
    // -------------------------------------------------------
    public static final By PopupKonfirmasi = By.id("profile-logout-modal-card");
    public static final By TombolYes       = By.id("profile-logout-confirm");
    public static final By HalamanLogin    = By.id("login-panel");

    // -------------------------------------------------------
    // TC-RM-01: Role Middleware
    // -------------------------------------------------------
    public static final By AdminFeature     = By.xpath("//*[contains(text(),'Add Admin') or contains(@class,'admin-menu') or contains(@href,'admin')]");
    public static final By RestrictedAccess = By.xpath("//*[contains(text(),'403') or contains(text(),'Forbidden') or contains(text(),'Unauthorized')]");

    // -------------------------------------------------------
    // TC-ADM-01: Tombol Add Admin & Modal
    // -------------------------------------------------------
    public static final By TombolAddAdmin  = By.id("profile-add-admin-button");
    public static final By ModalAddAdmin   = By.id("add-admin-form");
    public static final By InputEmailAdmin = By.id("add-admin-email-input");
    public static final By TombolAdd       = By.id("add-admin-submit");

    // -------------------------------------------------------
    // TC-ADM-02: Admin baru di daftar user
    // -------------------------------------------------------
    public static final By AdminBaruDiDaftar = By.xpath("//table//tr[contains(.,'newadmin@mail.ugm.ac.id')] | //*[contains(@class,'user-list')]//*[contains(text(),'newadmin@mail.ugm.ac.id')]");

    // -------------------------------------------------------
    // TC-ADM-03: Pesan error email kosong
    // -------------------------------------------------------
    public static final By PesanErrorEmailRequired = By.id("add-admin-email-error");

    // -------------------------------------------------------
    // TC-ADM-04: Pesan error format email tidak valid
    // -------------------------------------------------------
    public static final By PesanErrorFormatEmail = By.id("add-admin-email-error");

    // -------------------------------------------------------
    // TC-ADM-05: Notifikasi sukses
    // -------------------------------------------------------
    public static final By NotifikasiSukses = By.id("add-admin-success-card");
    public static final By CloseButton = By.id("add-admin-success-close");
}