import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

import static org.apache.http.HttpStatus.SC_ACCEPTED;
import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.Assert.assertEquals;

public class LoginTests {
    private WebDriver webDriver;
    private LoginPage loginPage;
    private String email;
    private String password;
    private String name;
    private static final String BASE_URL = "https://stellarburgers.nomoreparties.site";
    private String fullToken;
    private User user;
    private UserClient userClient;
    private Response response;

    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URL;
        user = UserGenerator.randomUser();
        userClient = new UserClient();
        response = userClient.create(user);

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        webDriver = new ChromeDriver(options);
        webDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    @Test
    @DisplayName("Логин пользователя через кнопку войти в аккаунт")
    @Description("Успешный логин пользователя через кнопку войти в аккаунт")
    public void loginThrowEnterAcoountBottomTest() {
        fullToken = response.path("accessToken");
        email= user.getEmail();
        password = user.getPassword();
        name = user.getName();
        assertEquals("Неверный статус код", SC_OK, response.statusCode());
        loginPage = new LoginPage(webDriver);
        webDriver.get("https://stellarburgers.nomoreparties.site");
        loginPage.goToLoginPage();
        loginPage.LoggedIn(email, password);
        Assert.assertEquals(true, loginPage.isMainPageTitleIsVisible());
    }

    @Test
    @DisplayName("Логин пользователя через кнопку личного кабинета")
    @Description("Успешный логин пользователя через кнопку личного кабинета")
    public void loginThrowEnterPersonalAcoountBottomTest() {
        fullToken = response.path("accessToken");
        email= user.getEmail();
        password = user.getPassword();
        name = user.getName();
        loginPage = new LoginPage(webDriver);
        webDriver.get("https://stellarburgers.nomoreparties.site");
        loginPage.goToPersonalAccount();
        loginPage.LoggedIn(email, password);
        Assert.assertEquals(true, loginPage.isMainPageTitleIsVisible());
    }

    @Test
    @DisplayName("Логин пользователя через кнопку в форме регистрации")
    @Description("Успешный логин пользователя через кнопку в форме регистрации")
    public void loginThrowEnterRegistrBottomTest() {
        fullToken = response.path("accessToken");
        email= user.getEmail();
        password = user.getPassword();
        name = user.getName();
        loginPage = new LoginPage(webDriver);
        webDriver.get("https://stellarburgers.nomoreparties.site");
        loginPage.goToPersonalAccount();
        loginPage.goToRegistrPage();
        loginPage.goToLoginPageFromRegistrRestorePasswordPage();
        loginPage.LoggedIn(email, password);
        Assert.assertEquals(true, loginPage.isMainPageTitleIsVisible());
    }

    @Test
    @DisplayName("Логин пользователя через кнопку в форме восстановления пароля")
    @Description("Успешный логин пользователя через кнопку в форме восстановления пароля")
    public void loginThrowEnterRestorePasswordBottomTest() {
        fullToken = response.path("accessToken");
        email= user.getEmail();
        password = user.getPassword();
        name = user.getName();
        loginPage = new LoginPage(webDriver);
        webDriver.get("https://stellarburgers.nomoreparties.site");
        loginPage.goToPersonalAccount();
        loginPage.goToRestorePasswordPage();
        loginPage.goToLoginPageFromRegistrRestorePasswordPage();
        loginPage.LoggedIn(email, password);
        Assert.assertEquals(true, loginPage.isMainPageTitleIsVisible());
    }

    @After
    public void teardown() {
        UserClient userClient = new UserClient();
        Response delete = userClient.delete(fullToken);
        assertEquals("Неверный статус код", SC_ACCEPTED, delete.statusCode());
        webDriver.quit();
    }
}
