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

public class PersonalAccountTests {
    private WebDriver webDriver;
    private PersonalAccountPage personalAccountPage;
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
    @DisplayName("Переход в личный кабинет")
    @Description("Логин пользователя и переход в личный кабинет")
    public void goToPersonalAccountTest() {
        fullToken = response.path("accessToken");
        email= user.getEmail();
        password = user.getPassword();
        name = user.getName();
        assertEquals("Неверный статус код", SC_OK, response.statusCode());

        personalAccountPage = new PersonalAccountPage(webDriver);
        webDriver.get("https://stellarburgers.nomoreparties.site");
        personalAccountPage.goToLoginPage();
        personalAccountPage.LoggedIn(email, password);
        personalAccountPage.goToPersonalAccount();
        Assert.assertEquals(true, personalAccountPage.isOrderHistoryIsVisible());

    }

    @Test
    @DisplayName("Переход из личного кабинета в Конструктор через кнопку Конструктор")
    @Description("Логин пользователя, переход в личный кабинет, переход в Конструктор")
    public void goFromPersonalAccountToConstructorTest() {
        fullToken = response.path("accessToken");
        email= user.getEmail();
        password = user.getPassword();
        name = user.getName();
        assertEquals("Неверный статус код", SC_OK, response.statusCode());

        personalAccountPage = new PersonalAccountPage(webDriver);
        webDriver.get("https://stellarburgers.nomoreparties.site");
        personalAccountPage.goToLoginPage();
        personalAccountPage.LoggedIn("annaponomareva@mail.ru", "123456");
        personalAccountPage.goToPersonalAccount();
        personalAccountPage.goToConstructor();
        Assert.assertEquals(true, personalAccountPage.isMainPageTitleIsVisible());

    }

    @Test
    @DisplayName("Переход из личного кабинета в Конструктор через логотип")
    @Description("Логин пользователя, переход в личный кабинет, переход в Конструктор через логотип")
    public void goFromPersonalAccountToConstructorThrowLogoTest() {
        fullToken = response.path("accessToken");
        email= user.getEmail();
        password = user.getPassword();
        name = user.getName();
        assertEquals("Неверный статус код", SC_OK, response.statusCode());

        personalAccountPage = new PersonalAccountPage(webDriver);
        webDriver.get("https://stellarburgers.nomoreparties.site");
        personalAccountPage.goToLoginPage();
        personalAccountPage.LoggedIn("annaponomareva@mail.ru", "123456");
        personalAccountPage.goToPersonalAccount();
        personalAccountPage.goToConstuctorThrowLogo();
        Assert.assertEquals(true, personalAccountPage.isMainPageTitleIsVisible());

    }

    @Test
    @DisplayName("Выход")
    @Description("Выход из аккаунта")
    public void logoutTest() {
        fullToken = response.path("accessToken");
        email= user.getEmail();
        password = user.getPassword();
        name = user.getName();
        assertEquals("Неверный статус код", SC_OK, response.statusCode());

        personalAccountPage = new PersonalAccountPage(webDriver);
        webDriver.get("https://stellarburgers.nomoreparties.site");
        personalAccountPage.goToLoginPage();
        personalAccountPage.LoggedIn("annaponomareva@mail.ru", "123456");
        personalAccountPage.goToPersonalAccount();
        personalAccountPage.logout();
        Assert.assertEquals(true, personalAccountPage.isTitleEnterVisible());

    }

    @After
    public void teardown() {
        UserClient userClient = new UserClient();
        Response delete = userClient.delete(fullToken);
        assertEquals("Неверный статус код", SC_ACCEPTED, delete.statusCode());
        webDriver.quit();
    }
}
