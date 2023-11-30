package study.eurotech.pages;

import study.eurotech.utils.ConfigurationReader;
import study.eurotech.context.TestContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {

    @FindBy(css = "#rcc-confirm-button")
    public WebElement confirmButton;
    @FindBy(css = "#loginpage-input-email")
    public WebElement email;
    @FindBy(css = "#loginpage-form-pw-input")
    public WebElement password;
    @FindBy(css = "#loginpage-form-btn")
    public WebElement loginButton;

    public LoginPage(TestContext context) {
        super(context);
        context.wait.until(ExpectedConditions.urlToBe("http://eurotech.study/login"));
    }

    public DashboardPage login() {
        context.wait.until(ExpectedConditions.visibilityOf(confirmButton));
        confirmButton.click();
        email.sendKeys(ConfigurationReader.get("userName"));
        password.sendKeys(ConfigurationReader.get("userPassword"));
        loginButton.click();

        return new DashboardPage(context);
    }
}
