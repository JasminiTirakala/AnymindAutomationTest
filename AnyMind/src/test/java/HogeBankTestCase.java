import factory.ConstantData;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;

public class HogeBankTestCase extends BaseClass {

    SignupPage signupPage;
    TransactionHomePage transactionHomePage;
    WithdrawPage withdrawPage;
    DepositPage depositPage;
    double amountToWithdraw = 1000;
    double amountToDeposit = 1000;

    public HogeBankTestCase() {
        super();
    }


    @BeforeMethod
    public void setUp() throws InterruptedException {
        System.out.println("Test execution started");
        initializeDriver();
        signupPage = new SignupPage();
        transactionHomePage = new TransactionHomePage();
        withdrawPage = new WithdrawPage();
        depositPage = new DepositPage();
    }

    @Test(priority = 0)
    public void verifyPageTitle() {
        String pageTitle = signupPage.getPageTitle();
        Assert.assertEquals(pageTitle, "React App");
        System.out.println("Page title is : "+pageTitle);
    }

    @Test(priority = 1)
    public void userNameBlank() throws InterruptedException {
        signupPage.enterCredentials("","P@ssword01");
        signupPage.clickOnSignupButton();
        signupPage.enterCredentials("","P@ssword01");
        signupPage.clickOnSignupButton();
        String actErrorMsg = signupPage.getErrorMessage();
        Thread.sleep(3000);
        Assert.assertEquals(actErrorMsg, ConstantData.errorMsgUserNameBlank);
        System.out.println("Error message when user name is blank : "+actErrorMsg);
    }

    @Test(priority = 2)
    public void userNameWithWhiteSpace() throws InterruptedException {
        signupPage.enterCredentials("user 01","P@ssword01");
        signupPage.clickOnSignupButton();
        signupPage.enterCredentials("user 01","P@ssword01");
        signupPage.clickOnSignupButton();
        String actErrorMsg = signupPage.getErrorMessage();
        Thread.sleep(3000);
        Assert.assertEquals(actErrorMsg, ConstantData.errorMsgUserNameWhiteSpace);
        System.out.println("Error message when user name has white spaces : "+actErrorMsg);
    }

    @Test(priority = 3)
    public void pwdLessThan8Characters() throws InterruptedException {
        signupPage.enterCredentials("user01","P@sswor");
        signupPage.clickOnSignupButton();
        signupPage.enterCredentials("user01","P@sswor");
        signupPage.clickOnSignupButton();
        String actErrorMsg = signupPage.getErrorMessage();
        Thread.sleep(3000);
        Assert.assertEquals(actErrorMsg, ConstantData.errorMsgPwdLessChar);
        System.out.println("Error message when password is less than 8 characters : "+actErrorMsg);
    }

    @Test(priority = 4)
    public void pwdMoreThan32Characters() throws InterruptedException {
        signupPage.enterCredentials("user01","012345678901234567890123456789012");
        signupPage.clickOnSignupButton();
        signupPage.enterCredentials("user01","012345678901234567890123456789012");
        signupPage.clickOnSignupButton();
        String actErrorMsg = signupPage.getErrorMessage();
        Thread.sleep(3000);
        Assert.assertEquals(actErrorMsg, ConstantData.errorMsgPwdMoreChar);
        System.out.println("Error message when password is more than 32 characters : "+actErrorMsg);
    }

    @Test(priority = 5)
    public void pwdWithNoNumbers() throws InterruptedException {
        signupPage.enterCredentials("user01","P@sswordp");
        signupPage.clickOnSignupButton();
        signupPage.enterCredentials("user01","P@sswordp");
        signupPage.clickOnSignupButton();
        String actErrorMsg = signupPage.getErrorMessage();
        Thread.sleep(3000);
        Assert.assertEquals(actErrorMsg, ConstantData.errorMsgPwdNum);
        System.out.println("Error message when password does not contain numbers : "+actErrorMsg);
    }

    @Test(priority = 5)
    public void pwdWithNoUppercase() throws InterruptedException {
        signupPage.enterCredentials("user01","p@ssword01");
        signupPage.clickOnSignupButton();
        signupPage.enterCredentials("user01","p@ssword01");
        signupPage.clickOnSignupButton();
        String actErrorMsg = signupPage.getErrorMessage();
        Thread.sleep(3000);
        Assert.assertEquals(actErrorMsg, ConstantData.errorMsgPwdUppercase);
        System.out.println("Error message when password does not upper case : "+actErrorMsg);
    }

    @Test(priority = 6)
    public void withdrawMoney() throws InterruptedException {
        signupPage.enterCredentials("user02","P@ssword01");
        signupPage.clickOnSignupButton();
        signupPage.enterCredentials("user02","P@ssword01");
        signupPage.clickOnSignupButton();
        double balanceBeforeWithdrawal = transactionHomePage.checkBalance();
        transactionHomePage.navigateToWithdrawPage();
        withdrawPage.enterAmountToWithdraw(amountToWithdraw);
        Thread.sleep(500);
        withdrawPage.clickOnWithdrawButton();
        Thread.sleep(10000);
        double balanceAfterWithdrawal = transactionHomePage.checkBalance();
        double finalBalanceWithTransactionFee = balanceBeforeWithdrawal - (amountToWithdraw + (int)(amountToWithdraw * 0.30));
        Assert.assertEquals(balanceAfterWithdrawal, finalBalanceWithTransactionFee);
        System.out.println("Final balance updated after withdrawal in 10 seconds with transaction fee 30%");
        transactionHomePage.logout();
    }

    @Test(priority = 7)
    public void depositMoney() throws InterruptedException {
        signupPage.enterCredentials("user02","P@ssword01");
        signupPage.clickOnSignupButton();
        signupPage.enterCredentials("user02","P@ssword01");
        signupPage.clickOnSignupButton();
        double balanceBeforeDeposit = transactionHomePage.checkBalance();
        transactionHomePage.navigateToDepositPage();
        depositPage.enterAmountForDeposit(amountToDeposit);
        Thread.sleep(500);
        depositPage.clickOnDepositButton();
        Thread.sleep(10000);
        double balanceAfterDeposit = transactionHomePage.checkBalance();
        double finalBalanceWithTransactionFee = balanceBeforeDeposit + (amountToDeposit - (int)(amountToDeposit * 0.30));
        Assert.assertEquals(balanceAfterDeposit, finalBalanceWithTransactionFee);
        System.out.println("Final balance updated after deposit in 10 seconds with transaction fee 30%");
        transactionHomePage.logout();
    }

    @AfterMethod
    public void afterEach(){
        driver.quit();
        System.out.println("Test execution completed");
    }
}
