package tests;

import base.BaseTest;
import listeners.ScreenshotListener;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;
import org.testng.annotations.Listeners;

import java.time.Duration;

@Listeners(ScreenshotListener.class)
public class LoginTest extends BaseTest {

    private WebDriverWait wait;

    @DataProvider(name = "invalidLoginData")
    public Object[][] invalidLoginData () {
        return new Object[][]{
                {"melikee@testt.com","wrongpsw"},
                {"wrong@test.com","1234567"},
                {"wrong@test.com","abcd"}
        };
    }

    @DataProvider(name = "invalidEmailData")
    public Object[][] invalidEmailData(){
        return new Object[][]{
                {"melike"},
                {"melike@test"},
                {"melike.com"},
                {"@test.com"},
                {"melike test@test.com"}
        };
    }

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
        Assert.assertTrue(false);
    }

    @Test(dataProvider = "invalidLoginData")
    public void invalidLoginTest(String email,String psw){
        LoginPage loginPage=new LoginPage(webDriver);
        loginPage.login(email,psw);


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

    @Test(dataProvider = "invalidEmailData")
    public void invalidEmailFormatTest(String email){
        LoginPage loginPage=new LoginPage(webDriver);
        loginPage.enterEmail(email);
        loginPage.enterPassword("1234567");
        loginPage.clickLogin();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login-error")));

        Assert.assertEquals(loginPage.getLoginErrorMessage(),
                "Please enter a valid email address.");
    }

}
