package org.example.pages;

import locators.LoginLocator;
import org.example.SymonBasePage;
import org.openqa.selenium.WebDriver;

public class LoginPage extends SymonBasePage {
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void clickButtonLogin() throws InterruptedException {
        click(LoginLocator.LoginBtn);
    }

    public void inputEmail(String email) throws InterruptedException{
        waitForElement(LoginLocator.InputEmail).clear();
        waitForElement(LoginLocator.InputEmail).sendKeys(email);
    }

    public void inputUsername(String username) throws InterruptedException{
        waitForElement(LoginLocator.InputUsernameSSO).clear();
        waitForElement(LoginLocator.InputUsernameSSO).sendKeys(username);
    }

    public void inputPassword(String password) throws InterruptedException{
        waitForElement(LoginLocator.InputPasswordSSO).clear();
        waitForElement(LoginLocator.InputPasswordSSO).sendKeys(password);
    }

    public void submitEmail() throws InterruptedException{
        click(LoginLocator.NextButton);
    }

    public void submitSSO() throws InterruptedException{
        click(LoginLocator.LoginSSO);
    }

    public void lanjutkanButton() throws InterruptedException{
        System.out.println(driver.getCurrentUrl());
        click(LoginLocator.ContinueButton);
    }
}
