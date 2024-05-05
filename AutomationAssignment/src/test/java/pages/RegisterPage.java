package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class RegisterPage {
    final  WebDriver driver;
    public By registerLocator=By.xpath("//a[contains(text(),'Register')]");
    public  By firstNameLocator=By.xpath("//input[@id='customer.firstName']");
    public  By lastNameLocator=By.xpath("//input[@id='customer.lastName']");
    public  By addressLocator=By.xpath("//input[@id='customer.address.street']");
    public  By cityLocator=By.xpath("//input[@id='customer.address.city']");
    public  By stateLocator=By.xpath("//input[@id='customer.address.state']");
    public  By zipLocator=By.xpath("//input[@id='customer.address.zipCode']");
    public  By phoneLocator=By.xpath("//input[@id='customer.phoneNumber']");
    public  By ssnLocator=By.xpath("//input[@id='customer.ssn']");
    public  By usernameLocator=By.xpath("//input[@id='customer.username']");
    public  By passwordLocator=By.xpath("//input[@id='customer.password']");
    public  By confirmPasswordLocator=By.xpath("//input[@id='repeatedPassword']");
    public  By registerButtonLocator=By.xpath("//tbody/tr[13]/td[2]/input[1]");
    public  By welcomeTextLocator=By.xpath("//b[contains(text(),'Welcome')]");
    public  By logoutButtonLocator=By.xpath("//a[contains(text(),'Log Out')]");














    public RegisterPage(WebDriver driver){
        this.driver=driver;
    }

}




