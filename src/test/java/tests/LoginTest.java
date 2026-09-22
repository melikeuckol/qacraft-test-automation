package tests;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

import java.time.Duration;

public class LoginTest extends BaseTest {

    @Test
    public void validLoginTest(){
        LoginPage loginPage=new LoginPage(webDriver);
        loginPage.login("melikee@testt.com","1234567");

        WebDriverWait wait=new WebDriverWait(
                webDriver,
                Duration.ofSeconds(10)
        );
        wait.until(ExpectedConditions.urlContains("/dashboard"));


        Assert.assertTrue(
                webDriver.getCurrentUrl().contains("/dashboard")
        );
    }

    @Test
    public void invalidLoginTest(){
        LoginPage loginPage=new LoginPage(webDriver);
        loginPage.login("melikee@testt.com","abcd");

        WebDriverWait wait=new WebDriverWait(
                webDriver,
                Duration.ofSeconds(5)
        );

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("login-error"))
        );

        Assert.assertTrue(loginPage.isLoginErrorDisplayed());
    }

}
