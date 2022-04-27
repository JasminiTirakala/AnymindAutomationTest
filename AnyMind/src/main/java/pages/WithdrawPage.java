package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WithdrawPage extends BaseClass {

    private Double transactionAmount;

    @FindBy(xpath = "//button" )
    WebElement withdrawBtn;

    @FindBy(xpath = "//input" )
    WebElement amount;

    public WithdrawPage() {
        PageFactory.initElements(driver,this);
    }

    public void enterAmountToWithdraw(double depositAmount){
        amount.sendKeys(""+depositAmount);
    }
    public void clickOnWithdrawButton(){
        withdrawBtn.click();
    }


}
