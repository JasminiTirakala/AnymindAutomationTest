package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Random;

public class DepositPage extends BaseClass {

    @FindBy(xpath = "//button" )
    WebElement depositBtn;

    @FindBy(xpath = "//input" )
    WebElement amount;

    public DepositPage() {
        PageFactory.initElements(driver,this);
    }

    public void clickOnDepositButton(){
       depositBtn.click();
    }

    public void enterAmountForDeposit(double depositAmount){
        amount.sendKeys(""+depositAmount);
    }


}
