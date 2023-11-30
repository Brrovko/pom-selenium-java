package study.eurotech.tests;

import study.eurotech.utils.ConfigurationReader;
import study.eurotech.context.TestContext;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import study.eurotech.utils.DriverFactory;

import java.time.Duration;

public class TestBase {
    TestContext context;

    @BeforeMethod
    public void beforeMethod() {
        context = new TestContext();
        context.driver = DriverFactory.get();
        context.wait = new WebDriverWait(context.driver, Duration.ofSeconds(Long.parseLong(ConfigurationReader.get("timeout"))));
        context.actions = new Actions(context.driver);
    }

    @AfterMethod
    public void afterMethod() {
        if (context.driver != null) {
            context.driver.quit();
        }
    }
}
