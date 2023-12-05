package study.eurotech.pages;

import io.qameta.allure.Step;
import study.eurotech.context.TestContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.function.Predicate;

public class AllPostsPage extends BasePage {


    @FindBy(css = "#posts-heading")
    WebElement allPostsHeader;

    @FindAll({@FindBy(css = "#post-item-container")})
    List<WebElement> postContainers;

    @FindBy(css = "#posts-search-input")
    WebElement searchInput;

    @FindBy(css = "#posts-search-btn")
    WebElement searchButton;

    @FindBy(css = "#posts-search-results-heading")
    WebElement searchResultHeading;

    public AllPostsPage(TestContext context) {
        super(context);
        context.wait.until(ExpectedConditions.urlToBe("http://eurotech.study/posts"));
        context.wait.until(ExpectedConditions.visibilityOf(allPostsHeader));
    }

    public List<PostComponent> getPosts() {
        return postContainers.stream().map(e -> new PostComponent(context, e)).toList();
    }

    public String getHeaderText() {
        return allPostsHeader.getText();
    }

    public PostComponent getPost(Predicate<PostComponent> condition) {
        return getPosts()
                .stream()
                .filter(condition)
                .findFirst()
                .orElseThrow();
    }

    @Step("Выполнить поисковой запрос: [{text}]")
    public AllPostsPage search(String text) {
        searchInput.sendKeys(text);
        searchButton.click();
        context.wait.until(ExpectedConditions.visibilityOf(searchResultHeading));
        return this;
    }
}
