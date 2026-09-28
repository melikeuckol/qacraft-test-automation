package tests;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;

import java.time.Duration;

public class LoginTest extends BaseTest {

    private WebDriverWait wait;

    @BeforeMethod
    public void setUpWait(){
        wait=new WebDriverWait(webDriver,Duration.ofSeconds(10));
    }

    @Test
    public void validLoginTest(){
        LoginPage loginPage=new LoginPage(webDriver);
        loginPage.login("melikee@testt.com","1234567");


        wait.until(ExpectedConditions.urlContains("/dashboard"));

        Assert.assertTrue(
                webDriver.getCurrentUrl().contains("/dashboard")
        );
    }

    @Test
    public void invalidLoginTest(){
        LoginPage loginPage=new LoginPage(webDriver);
        loginPage.login("melikee@testt.com","abcd");


        wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("login-error"))
        );

        Assert.assertTrue(loginPage.isLoginErrorDisplayed());
    }

    @Test
    public void emptyEmailTest(){
        LoginPage loginPage=new LoginPage(webDriver);

        loginPage.enterPassword("1234567");
        loginPage.clickLogin();

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("login-error"))
        );

        Assert.assertEquals(
                loginPage.getLoginErrorMessage(),"Email is required."
        );

    }

    @Test
    public void emptyPasswordTest(){
        LoginPage loginPage=new LoginPage(webDriver);
        loginPage.enterEmail("melikee@testt.com");
        loginPage.clickLogin();

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("login-error"))
        );

        Assert.assertEquals(loginPage.getLoginErrorMessage(),
                "Password is required.");
    }

    @Test
    public void invalidEmailFormatTest(){
        LoginPage loginPage=new LoginPage(webDriver);
        loginPage.enterEmail("melike@test");
        loginPage.enterPassword("1234567");
        loginPage.clickLogin();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login-error")));

        Assert.assertEquals(loginPage.getLoginErrorMessage(),
                "Please enter a valid email address.");
    }

}
