package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TransactionHomePage extends BaseClass {

    @FindBy(xpath = "//a[1]" )
    WebElement depositBtn;

    @FindBy(xpath = "//a[2]" )
    WebElement withdrawBtn;

    @FindBy(xpath = "//button" )
    WebElement logoutBtn;

    @FindBy(xpath = "//td[contains(text(), 'Balance')]/following-sibling::td" )
    WebElement currentBalance;


    public TransactionHomePage() {
        PageFactory.initElements(driver,this);
    }

    public void navigateToDepositPage(){
        depositBtn.click();
    }
    public void navigateToWithdrawPage(){
        withdrawBtn.click();
    }

    public void logout(){
        logoutBtn.click();
    }

    public double checkBalance() {
       return Double.parseDouble(currentBalance.getText());
    }


}
