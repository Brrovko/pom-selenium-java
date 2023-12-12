package study.eurotech.tests;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import study.eurotech.pages.*;
import study.eurotech.utils.ConfigurationReader;

import java.util.List;

import static io.qameta.allure.SeverityLevel.CRITICAL;

public class EurotechTests extends TestBase {



    @Test
    @Description("Проверка количества записей")
    @Owner("Team #1")
    @Epic("Web interface")
    @Feature("Essential features")
    @Story("Authentication")
    public void postsCountTest() {

        getContext().driver.get(ConfigurationReader.get("url") + "login");
        LoginPage loginPage = new LoginPage(getContext());
        NavigationComponent navigationPage = new NavigationComponent(getContext());

        loginPage.login();
        AllPostsPage allPostsPage = navigationPage.openAllPosts();

        Assert.assertEquals(allPostsPage.getHeaderText(), "Posts");

        Assert.assertEquals((long) allPostsPage.getPosts().size(), 283);

    }

    @Test
    @Description("Получение информации о первом посте")
    @Owner("Team #1")
    @Epic("Web interface")
    @Feature("Essential features")
    @Story("Authentication")
    public void postsTextTest() {

        getContext().driver.get(ConfigurationReader.get("url") + "login");
        LoginPage loginPage = new LoginPage(getContext());
        NavigationComponent navigationPage = new NavigationComponent(getContext());

        loginPage.login();
        AllPostsPage allPostsPage = navigationPage.openAllPosts();

        Assert.assertEquals(allPostsPage.getHeaderText(), "Posts");

        PostComponent firstPost = allPostsPage.getPosts().get(0);

        Assert.assertEquals(firstPost.getPostTitle(), "QA Enginer");
        Assert.assertEquals(firstPost.getPostText(), "Would you consider Selenium as a tool or a library?");
    }

    @Test
    @Owner("Team #1")
    @Epic("Web interface")
    @Feature("New features")
    @Story("Search")
    public void postsFilterTest() {

        getContext().driver.get(ConfigurationReader.get("url") + "login");
        LoginPage loginPage = new LoginPage(getContext());
        NavigationComponent navigationPage = new NavigationComponent(getContext());

        loginPage.login();
        AllPostsPage allPostsPage = navigationPage.openAllPosts();

        PostComponent post = allPostsPage.getPost(postComponent -> postComponent.getPostTitle().equals("JDBC"));

        Assert.assertEquals(post.getPostDate(), "2023/11/24");
    }

    @Test
    @Owner("Team #1")
    @Epic("Web interface")
    @Feature("New features")
    @Story("Search")
    public void searchPostTest() {
        openUrl(ConfigurationReader.get("url") + "login");

        LoginPage loginPage = new LoginPage(getContext());
        NavigationComponent navigationPage = new NavigationComponent(getContext());

        loginPage.login();
        AllPostsPage allPostsPage = navigationPage.openAllPosts();

        allPostsPage.search("JDBC");

        List<PostComponent> searchResult = allPostsPage.getPosts();

        Assert.assertEquals((long) searchResult.size(), 1);
        Assert.assertEquals(searchResult.get(0).getPostTitle(), "JDBC");
    }
}