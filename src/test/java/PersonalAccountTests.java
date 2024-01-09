import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class PersonalAccountTests {
    private WebDriver webDriver;
    private PersonalAccountPage personalAccountPage;

    @Before
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        webDriver = new ChromeDriver(options);
        webDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    @Test
    @DisplayName("Переход в личный кабинет")
    @Description("Логин пользователя и переход в личный кабинет")
    public void goToPersonalAccountTest() {
        personalAccountPage = new PersonalAccountPage(webDriver);
        webDriver.get("https://stellarburgers.nomoreparties.site");
        personalAccountPage.goToLoginPage();
        personalAccountPage.LoggedIn("annaponomareva@mail.ru", "123456");
        personalAccountPage.goToPersonalAccount();
        Assert.assertEquals(true, personalAccountPage.isOrderHistoryIsVisible());

    }

    @Test
    @DisplayName("Переход из личного кабинета в Конструктор через кнопку Конструктор")
    @Description("Логин пользователя, переход в личный кабинет, переход в Конструктор")
    public void goFromPersonalAccountToConstructorTest() {
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
        webDriver.quit();
    }
}
