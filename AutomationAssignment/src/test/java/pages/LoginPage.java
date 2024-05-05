package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class LoginPage {
    final  WebDriver driver;
    public By usernameLocator=By.xpath("//body/div[@id='mainPanel']/div[@id='bodyPanel']/div[@id='leftPanel']/div[@id='loginPanel']/form[1]/div[1]/input[1]");
    public  By passwordLocator=By.xpath("//body/div[@id='mainPanel']/div[@id='bodyPanel']/div[@id='leftPanel']/div[@id='loginPanel']/form[1]/div[2]/input[1]");
    public  By loginLocator=By.xpath("//body/div[@id='mainPanel']/div[@id='bodyPanel']/div[@id='leftPanel']/div[@id='loginPanel']/form[1]/div[3]/input[1]");
    public  By errorMessageLocator=By.xpath("//p[contains(text(),'Please enter a username and password.')]");
    public  By welcomeTextLocator=By.xpath("//b[contains(text(),'Welcome')]");
    public  By internalErrorTextLocator=By.xpath("//p[contains(text(),'An internal error has occurred and has been logged')]");









    public LoginPage(WebDriver driver){
        this.driver=driver;
    }

    public void enterUsername(String username) {
        driver.findElement(usernameLocator).clear();

        driver.findElement(usernameLocator).sendKeys(username);
    }

    // Method to enter password
    public void enterPassword(String password) {
        driver.findElement(passwordLocator).clear();
        driver.findElement(passwordLocator).sendKeys(password);
    }

    // Method to perform login
    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        driver.findElement(loginLocator).click();
    }


}




