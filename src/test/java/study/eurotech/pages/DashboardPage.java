package study.eurotech.pages;

import study.eurotech.context.TestContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DashboardPage extends BasePage {

    @FindBy(css = "#dashboard-h1")
    WebElement dashBoardHeader;

    public DashboardPage(TestContext context) {
        super(context);
        context.wait.until(ExpectedConditions.urlToBe("http://eurotech.study/dashboard"));
        context.wait.until(ExpectedConditions.visibilityOf(dashBoardHeader));
    }

    public String getHeaderText() {
        return dashBoardHeader.getText();
    }
}