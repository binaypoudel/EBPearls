import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import pages.LoginPage;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class LoginPageTest  {
    private WebDriver driver;
    final String correctUserName="ebpearlspara";
    final String correctPassword="ebpearls@2024";
    private LoginPage loginPage;

    @BeforeEach
    public void setUp() throws IOException {
        Properties prop=new Properties();
        File file=new File("config.properties");
        FileInputStream fis=new FileInputStream(file);
        prop.load(fis);
        String browserName=prop.getProperty("browser");

        switch (browserName) {
            case "chrome" -> driver = new ChromeDriver();
            case "firefox" -> driver = new FirefoxDriver();
            case "edge" -> driver = new EdgeDriver();
            case null, default -> System.out.println("Invalid Browser");
        }

       //driver= new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.get("https://parabank.parasoft.com/parabank/index.htm");
        driver.manage().window().maximize();
        loginPage=new LoginPage(driver);
    }

    @AfterEach
    public void tearDown(){
        driver.quit();
    }

    @Test
    public void verifyLoginWithEmptyUsernameAndPassword() {
        loginPage.login("", "");
        String element=driver.findElement(loginPage.errorMessageLocator).getText();
        Assertions.assertEquals("Please enter a username and password.",element);
    }
    @Test
    public void verifyLoginWithCorrectUsernameAndPassword() {
        loginPage.login(correctUserName, correctPassword);
        String element=driver.findElement(loginPage.welcomeTextLocator).getText();
        Assertions.assertEquals("Welcome",element);
    }
    @Test
    public void verifyLoginWithCorrectUsernameAndIncorrectPassword() {
        loginPage.login("ebpearl", "abc");
        String element=driver.findElement(loginPage.internalErrorTextLocator).getText();
        Assertions.assertEquals("An internal error has occurred and has been logged.",element);
    }


}