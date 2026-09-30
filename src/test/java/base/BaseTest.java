package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    protected WebDriver webDriver;

    public WebDriver getWebDriver() {
        return webDriver;
    }

    @BeforeMethod
    public void setUp(){
        webDriver=new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.get("http://localhost:5173/login");

    }

    @AfterMethod
    public void tearDown(){
        webDriver.quit();
    }
}
