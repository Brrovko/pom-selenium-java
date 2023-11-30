package study.eurotech.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import study.eurotech.pages.*;
import study.eurotech.utils.ConfigurationReader;

import java.util.List;

public class EurotechTests extends TestBase {

    @Test
    public void loginTest() {

        context.driver.get(ConfigurationReader.get("url") + "login");
        LoginPage loginPage = new LoginPage(context);

        DashboardPage dashboardPage = loginPage.login();

        Assert.assertEquals(dashboardPage.getHeaderText(), "Dashboard");
    }

    @Test
    public void postsCountTest() {

        context.driver.get(ConfigurationReader.get("url") + "login");
        LoginPage loginPage = new LoginPage(context);
        NavigationComponent navigationPage = new NavigationComponent(context);

        loginPage.login();
        AllPostsPage allPostsPage = navigationPage.openAllPosts();

        Assert.assertEquals(allPostsPage.getHeaderText(), "Posts");

        Assert.assertEquals((long) allPostsPage.getPosts().size(), 283);

    }

    @Test
    public void postsTextTest() {

        context.driver.get(ConfigurationReader.get("url") + "login");
        LoginPage loginPage = new LoginPage(context);
        NavigationComponent navigationPage = new NavigationComponent(context);

        loginPage.login();
        AllPostsPage allPostsPage = navigationPage.openAllPosts();

        Assert.assertEquals(allPostsPage.getHeaderText(), "Posts");

        PostComponent firstPost = allPostsPage.getPosts().get(0);

        Assert.assertEquals(firstPost.getPostTitle(), "QA Enginer");
        Assert.assertEquals(firstPost.getPostText(), "Would you consider Selenium as a tool or a library?");
    }

    @Test
    public void postsFilterTest() {

        context.driver.get(ConfigurationReader.get("url") + "login");
        LoginPage loginPage = new LoginPage(context);
        NavigationComponent navigationPage = new NavigationComponent(context);

        loginPage.login();
        AllPostsPage allPostsPage = navigationPage.openAllPosts();

        PostComponent post = allPostsPage.getPost(postComponent -> postComponent.getPostTitle().equals("JDBC"));

        Assert.assertEquals(post.getPostDate(), "2023/11/24");
    }

    @Test
    public void searchPostTest() {

        context.driver.get(ConfigurationReader.get("url") + "login");
        LoginPage loginPage = new LoginPage(context);
        NavigationComponent navigationPage = new NavigationComponent(context);

        loginPage.login();
        AllPostsPage allPostsPage = navigationPage.openAllPosts();

        allPostsPage.search("JDBC");

        List<PostComponent> searchResult = allPostsPage.getPosts();

        Assert.assertEquals((long) searchResult.size(), 1);
        Assert.assertEquals(searchResult.get(0).getPostTitle(), "JDBC");
    }
}