package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private final WebDriver driver;

    private final By emailInput = By.id("email");
    private final By passwordInput = By.id("password");
    private final By loginButton = By.id("login-button");
    private final By loginError = By.id("login-error");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterEmail(String email){
        driver.findElement(emailInput).sendKeys(email);
    }

    public void enterPassword(String psw){
        driver.findElement(passwordInput).sendKeys(psw);
    }

    public void clickLogin(){
        driver.findElement(loginButton).click();
    }

    public void login(String email,String psw){
        enterEmail(email);
        enterPassword(psw);
        clickLogin();
    }

    public boolean isLoginErrorDisplayed(){
        return driver.findElement(loginError).isDisplayed();
    }
}
