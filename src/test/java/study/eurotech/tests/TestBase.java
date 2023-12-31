package study.eurotech.tests;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import study.eurotech.context.TestContext;
import study.eurotech.utils.ConfigurationReader;
import study.eurotech.utils.DriverFactory;

import java.io.ByteArrayInputStream;
import java.time.Duration;

public class TestBase {
    private ThreadLocal<TestContext> context = new ThreadLocal<>();

    public TestContext getContext(){
        return context.get();
    }

    @BeforeMethod
    public void beforeMethod() {
        context.set(new TestContext());
        getContext().driver = DriverFactory.get();
        getContext().wait = new WebDriverWait(getContext().driver, Duration.ofSeconds(Long.parseLong(ConfigurationReader.get("timeout"))));
        getContext().actions = new Actions(getContext().driver);
    }

    @AfterMethod
    public void afterMethod(ITestResult result) {
        if (!result.isSuccess()) {
            try {
                TakesScreenshot takesScreenshot = (TakesScreenshot) getContext().driver;
                byte[] screenshot = takesScreenshot.getScreenshotAs(OutputType.BYTES);

                Allure.addAttachment("Скриншот в момент падения тестов", new ByteArrayInputStream(screenshot));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (getContext().driver != null) {
            getContext().driver.quit();
        }
    }

    @Step("Открыть адрес: [{relatedPath}]")
    public void openUrl(String path) {
        getContext().driver.get(path);
    }
}
