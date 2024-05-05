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
import pages.ForgotPage;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class ForgotPageTest  {
    private WebDriver driver;
    private ForgotPage forgotPage;

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
        forgotPage=new ForgotPage(driver);
    }

    @AfterEach
    public void tearDown(){
        driver.quit();
    }

    @Test
    public void verifySuccessfulFindLoginInfo() {
        System.out.println("hello");
        String excelFile="forgot.xlsx";
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
                String ssn = formatter.formatCellValue(row.getCell(6));

                driver.findElement(forgotPage.forgotLogintLocator).click();


                // Find the username and password input fields and enter the credentials
                driver.findElement(forgotPage.firstNameLocator).sendKeys(firstname);
                driver.findElement(forgotPage.lastNameLocator).sendKeys(lastname);
                driver.findElement(forgotPage.addressLocator).sendKeys(address);
                driver.findElement(forgotPage.cityLocator).sendKeys(city);
                driver.findElement(forgotPage.stateLocator).sendKeys(state);
                driver.findElement(forgotPage.zipLocator).sendKeys(zip);
                driver.findElement(forgotPage.ssnLocator).sendKeys(ssn);


                // Find and click the register button
                driver.findElement(forgotPage.findButtonLocator).click();
                String element=driver.findElement(forgotPage.findSuccessfulLocator).getText();
                Assertions.assertEquals("Your login information was located successfully. You are now logged in.",element);
                driver.findElement(forgotPage.logoutButtonLocator).click();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void verifyEmptyFieldInFindLoginInfo(){
        driver.findElement(forgotPage.forgotLogintLocator).click();
        driver.findElement(forgotPage.findButtonLocator).click();
        String element=driver.findElement(forgotPage.errorTextLocator).getText();
        Assertions.assertEquals("First name is required.",element);

    }



}