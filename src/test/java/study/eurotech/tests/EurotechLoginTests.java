package study.eurotech.tests;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import study.eurotech.pages.DashboardPage;
import study.eurotech.pages.LoginPage;
import study.eurotech.utils.ConfigurationReader;

import static io.qameta.allure.SeverityLevel.CRITICAL;

public class EurotechLoginTests extends TestBase {
    @Test(testName = "Проверка входа в систему")
    @Description("Проверка входа в систему")
    @Owner("Team #1")
    @Severity(CRITICAL)
    @Link(name = "Website", url = "https://dev.example.com/")
    @Issue("AUTH-123")
    @TmsLink("TMS-456")
    @Epic("Web interface")
    @Feature("Essential features")
    @Story("Authentication")
    public void loginTest() {
        openUrl(ConfigurationReader.get("url") + "login");
        LoginPage loginPage = new LoginPage(getContext());

        DashboardPage dashboardPage = loginPage.login();

        Assert.assertEquals(dashboardPage.getHeaderText(), "Dashboard");
    }
}
