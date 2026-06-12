package locators;

import org.openqa.selenium.By;

public class LoginLocator {
    public static final By LoginBtn = By.xpath("//button[normalize-space()='Login with UGM Email']");
    public static final By InputEmail = By.id("identifierId");
    public static final By InputUsernameSSO = By.id("username");
    public static final By InputPasswordSSO = By.id("password");
    public static final By LoginSSO = By.name("submit");
    public static final By ContinueButton = By.xpath("//button[contains(.,'Continue')]");


    public static final By NextButton = By.id("identifierNext");

}
