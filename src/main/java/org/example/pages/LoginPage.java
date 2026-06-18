package org.example.pages;

import locators.LoginLocator;
import org.example.SymonBasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends SymonBasePage {
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    // -------------------------------------------------------
    // SUDAH ADA — JANGAN DIUBAH
    // -------------------------------------------------------
    public void clickButtonLogin() throws InterruptedException {
        click(LoginLocator.LoginBtn);
    }

    public void inputEmail(String email) throws InterruptedException {
        waitForElement(LoginLocator.InputEmail).clear();
        waitForElement(LoginLocator.InputEmail).sendKeys(email);
    }

    public void inputUsername(String username) throws InterruptedException {
        waitForElement(LoginLocator.InputUsernameSSO).clear();
        waitForElement(LoginLocator.InputUsernameSSO).sendKeys(username);
    }

    public void inputPassword(String password) throws InterruptedException {
        waitForElement(LoginLocator.InputPasswordSSO).clear();
        waitForElement(LoginLocator.InputPasswordSSO).sendKeys(password);
    }

    public void submitEmail() throws InterruptedException {
        click(LoginLocator.NextButton);
    }

    public void submitSSO() throws InterruptedException {
        click(LoginLocator.LoginSSO);
    }

    public void lanjutkanButton() throws InterruptedException {
        System.out.println(driver.getCurrentUrl());
        click(LoginLocator.ContinueButton);
    }

    // -------------------------------------------------------
    // TC-SUP-01: Tampilan halaman Login
    // -------------------------------------------------------
    public boolean isLoginPageDisplayed() throws InterruptedException {
        return waitForElement(LoginLocator.LoginBtn).isDisplayed();
    }

    public boolean isGambarPCDisplayed() throws InterruptedException {
        return waitForElement(LoginLocator.GambarPC).isDisplayed();
    }

    public boolean isDeskripsiSyMonDisplayed() throws InterruptedException {
        return waitForElement(LoginLocator.DeskripsiSyMon).isDisplayed();
    }

    public String getLoginBtnText() throws InterruptedException {
        return waitForElement(LoginLocator.LoginBtn).getText();
    }

    // -------------------------------------------------------
    // TC-SUP-03: Login Failed
    // -------------------------------------------------------
    public boolean isLoginFailedPageDisplayed() throws InterruptedException {
        return waitForElement(LoginLocator.LoginFailedPage).isDisplayed();
    }

    // -------------------------------------------------------
    // TC-SUP-04: Switch Account
    // -------------------------------------------------------
    public void clickSwitchAccount() throws InterruptedException {
        click(LoginLocator.SwitchAccountBtn);
    }

    public boolean isDashboardDisplayed() throws InterruptedException {
        return waitForElement(LoginLocator.DashboardPage).isDisplayed();
    }

    // -------------------------------------------------------
    // TC-SUP-05: Halaman Profil
    // -------------------------------------------------------
    public void clickNavbarProfile() throws InterruptedException {
        click(LoginLocator.NavbarProfileBtn);
    }

    public boolean isProfilePageDisplayed() throws InterruptedException {
        return waitForElement(LoginLocator.ProfilePage).isDisplayed();
    }

    public boolean isProfileFotoDisplayed() throws InterruptedException {
        return waitForElement(LoginLocator.ProfileFoto).isDisplayed();
    }

    public boolean isProfileNamaDisplayed() throws InterruptedException {
        return waitForElement(LoginLocator.ProfileNama).isDisplayed();
    }

    public boolean isProfileEmailDisplayed() throws InterruptedException {
        return waitForElement(LoginLocator.ProfileEmail).isDisplayed();
    }

    public boolean isProfileRoleDisplayed() throws InterruptedException {
        return waitForElement(LoginLocator.ProfileRole).isDisplayed();
    }

    public boolean isProfileFooterDisplayed() throws InterruptedException {
        return waitForElement(LoginLocator.ProfileFooter).isDisplayed();
    }

    public boolean isLogOutBtnDisplayed() throws InterruptedException {
        return waitForElement(LoginLocator.TombolLogOut).isDisplayed();
    }

    // -------------------------------------------------------
    // TC-SUP-06: Logout
    // -------------------------------------------------------
    public void clickLogOut() throws InterruptedException {
        click(LoginLocator.TombolLogOut);
    }

    public boolean isPopupKonfirmasiDisplayed() throws InterruptedException {
        return waitForElement(LoginLocator.PopupKonfirmasi).isDisplayed();
    }

    public void clickTombolYes() throws InterruptedException {
        click(LoginLocator.TombolYes);
    }

    public boolean isHalamanLoginDisplayed() throws InterruptedException {
        return waitForElement(LoginLocator.HalamanLogin).isDisplayed();
    }

    // -------------------------------------------------------
    // TC-RM-01: Role Middleware
    // -------------------------------------------------------
    public boolean isAdminFeatureDisplayed() throws InterruptedException {
        return waitForElement(LoginLocator.AdminFeature).isDisplayed();
    }

    public boolean isRestrictedAccessDisplayed() {
        return !driver.findElements(LoginLocator.RestrictedAccess).isEmpty();
    }

    // -------------------------------------------------------
    // TC-ADM-01: Add Admin
    // -------------------------------------------------------
    public void clickTombolAddAdmin() throws InterruptedException {
        click(LoginLocator.TombolAddAdmin);
    }

    public boolean isModalAddAdminDisplayed() throws InterruptedException {
        return waitForElement(LoginLocator.ModalAddAdmin).isDisplayed();
    }

    public boolean isInputEmailAdminEnabled() throws InterruptedException {
        return waitForElement(LoginLocator.InputEmailAdmin).isEnabled();
    }

    // -------------------------------------------------------
    // TC-ADM-02 / TC-ADM-03 / TC-ADM-04 / TC-ADM-05
    // -------------------------------------------------------
    public void inputEmailAdmin(String email) throws InterruptedException {
        WebElement input = waitForElement(LoginLocator.InputEmailAdmin);
        input.clear();
        input.sendKeys(email);
    }

    public void clickTombolAdd() throws InterruptedException {
        click(LoginLocator.TombolAdd);
    } //tombol add admin pop up

    public void clickTombolLogOut() throws InterruptedException {
        click(LoginLocator.TombolLogOut);
    }

    public boolean isAdminBaruDiDaftarDisplayed() throws InterruptedException {
        return waitForElement(LoginLocator.AdminBaruDiDaftar).isDisplayed();
    }

    public String getAdminBaruRowText() throws InterruptedException {
        return waitForElement(LoginLocator.AdminBaruDiDaftar).getText();
    }

    public boolean isPesanErrorEmailRequiredDisplayed() throws InterruptedException {
        return waitForElement(LoginLocator.PesanErrorEmailRequired).isDisplayed();
    }

    public String getPesanErrorEmailRequiredText() throws InterruptedException {
        return waitForElement(LoginLocator.PesanErrorEmailRequired).getText();
    }

    public boolean isPesanErrorFormatEmailDisplayed() throws InterruptedException {
        return waitForElement(LoginLocator.PesanErrorFormatEmail).isDisplayed();
    }

    public boolean isNotifikasiSuksesDisplayed() throws InterruptedException {
        return waitForElement(LoginLocator.NotifikasiSukses).isDisplayed();
    }

    public void CloseButtonSuccess() throws InterruptedException {
        click(LoginLocator.CloseButton);
    }

    public boolean isModalAddAdminClosed() {
        return driver.findElements(LoginLocator.ModalAddAdmin).isEmpty()
                || !driver.findElement(LoginLocator.ModalAddAdmin).isDisplayed();
    }

}