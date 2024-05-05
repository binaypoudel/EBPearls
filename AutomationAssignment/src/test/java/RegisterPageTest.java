import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import pages.LoginPage;
import pages.RegisterPage;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class RegisterPageTest  {
    private WebDriver driver;
    private RegisterPage registerPage;

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

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.get("https://parabank.parasoft.com/parabank/index.htm");
        driver.manage().window().maximize();
        registerPage=new RegisterPage(driver);
    }

    @AfterEach
    public void tearDown(){
        driver.quit();
    }

    @Test
    public void verifySuccessfulRegistar() {
        System.out.println("hello");
        String excelFile="register.xlsx";
        try (FileInputStream fis = new FileInputStream(excelFile)) {
            XSSFWorkbook workbook = new XSSFWorkbook(fis);
            XSSFSheet sheet = workbook.getSheetAt(0);

            // Loop through each row in the spreadsheet
            for (Row row : sheet) {
                DataFormatter formatter = new DataFormatter();
                String firstname = formatter.formatCellValue(row.getCell(0));
                String lastname = formatter.formatCellValue(row.getCell(1));
                String address = formatter.formatCellValue(row.getCell(2));
                String city = formatter.formatCellValue(row.getCell(3));
                String state = formatter.formatCellValue(row.getCell(4));
                String zip = formatter.formatCellValue(row.getCell(5));
                String phone = formatter.formatCellValue(row.getCell(6));
                String ssn = formatter.formatCellValue(row.getCell(7));
                String username = formatter.formatCellValue(row.getCell(8));
                String password = formatter.formatCellValue(row.getCell(9));
                String confirmPassword = formatter.formatCellValue(row.getCell(10));

                driver.findElement(registerPage.registerLocator).click();


                // Find the username and password input fields and enter the credentials
                driver.findElement(registerPage.firstNameLocator).sendKeys(firstname);
                driver.findElement(registerPage.lastNameLocator).sendKeys(lastname);
                driver.findElement(registerPage.addressLocator).sendKeys(address);
                driver.findElement(registerPage.cityLocator).sendKeys(city);
                driver.findElement(registerPage.stateLocator).sendKeys(state);
                driver.findElement(registerPage.zipLocator).sendKeys(zip);
                driver.findElement(registerPage.phoneLocator).sendKeys(phone);
                driver.findElement(registerPage.ssnLocator).sendKeys(ssn);
                driver.findElement(registerPage.usernameLocator).sendKeys(username);
                driver.findElement(registerPage.passwordLocator).sendKeys(password);
                driver.findElement(registerPage.confirmPasswordLocator).sendKeys(confirmPassword);

                // Find and click the register button
                driver.findElement(registerPage.registerButtonLocator).click();
                String element=driver.findElement(registerPage.welcomeTextLocator).getText();
                Assertions.assertEquals("Welcome",element);
                driver.findElement(registerPage.logoutButtonLocator).click();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}