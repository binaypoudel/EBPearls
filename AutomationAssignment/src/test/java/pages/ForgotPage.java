package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class ForgotPage {
    final  WebDriver driver;
    public By forgotLogintLocator=By.xpath("//a[contains(text(),'Forgot login info?')]");
    public  By firstNameLocator=By.xpath("//input[@id='firstName']");
    public  By lastNameLocator=By.xpath("//input[@id='lastName']");
    public  By addressLocator=By.xpath("//input[@id='address.street']");
    public  By cityLocator=By.xpath("//input[@id='address.city']");
    public  By stateLocator=By.xpath("//input[@id='address.state']");
    public  By zipLocator=By.xpath("//input[@id='address.zipCode']");

    public  By ssnLocator=By.xpath("//input[@id='ssn']");

    public  By findButtonLocator=By.xpath("//tbody/tr[8]/td[2]/input[1]");
    public  By errorTextLocator=By.xpath("//span[@id='firstName.errors']");

    public  By findSuccessfulLocator=By.xpath("//p[contains(text(),'Your login information was located successfully. Y')]");
    public  By logoutButtonLocator=By.xpath("//a[contains(text(),'Log Out')]");


    public ForgotPage(WebDriver driver){
        this.driver=driver;
    }

}




