package study.eurotech.pages;

import study.eurotech.context.TestContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class NavigationComponent extends BasePage {

    @FindBy(css = "#navbar-menu-h1 > a")
    public WebElement title;
    @FindBy(css = "#navbar-menu-list2-item2")
    public WebElement allPosts;

    public NavigationComponent(TestContext context) {
        super(context);
        context.wait.until(ExpectedConditions.visibilityOf(title));

    }

    public AllPostsPage openAllPosts() {
        allPosts.click();
        return new AllPostsPage(context);
    }
}
