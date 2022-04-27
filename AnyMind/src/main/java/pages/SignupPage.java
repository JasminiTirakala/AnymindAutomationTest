package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import util.TestUtil;


public class SignupPage extends BaseClass {

    @FindBy(xpath = "//input[1]" )
    WebElement userName;

    @FindBy(xpath = "//input[2]" )
    WebElement password;

    @FindBy(xpath = "//button[2]" )
    WebElement loginButton;

    @FindBy(xpath = "//button[contains(text(),'SIGNUP')]" )
    WebElement signupButton;

    @FindBy(xpath = "//span" )
    WebElement errorMsg;

    @FindBy(xpath = "//tr[1]/td[2]" )
    WebElement balance;


    public SignupPage() {
        PageFactory.initElements(driver,this);
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public void enterCredentials(String un, String pwd) {
        userName.sendKeys(un);
        password.sendKeys(pwd);
    }

    public void clickOnSignupButton() {
        signupButton.click();
    }

    public String getPortfolioPage() {
        return driver.getCurrentUrl();
    }

    public String getBalance() {
        return balance.getText();
    }

    public String getErrorMessage() {
        return errorMsg.getText();

    }


}
