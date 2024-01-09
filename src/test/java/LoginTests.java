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

public class LoginTests {

    private WebDriver webDriver;
    private LoginPage loginPage;

    @Before
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        webDriver = new ChromeDriver(options);
        webDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    @Test
    @DisplayName("Логин пользователя через кнопку войти в аккаунт")
    @Description("Успешный логин пользователя через кнопку войти в аккаунт")
    public void loginThrowEnterAcoountBottomTest() {
        loginPage = new LoginPage(webDriver);
        webDriver.get("https://stellarburgers.nomoreparties.site");
        loginPage.goToLoginPage();
        loginPage.LoggedIn("annaponomareva@mail.ru", "123456");
        Assert.assertEquals(true, loginPage.isMainPageTitleIsVisible());

    }

    @Test
    @DisplayName("Логин пользователя через кнопку личного кабинета")
    @Description("Успешный логин пользователя через кнопку личного кабинета")
    public void loginThrowEnterPersonalAcoountBottomTest() {
        loginPage = new LoginPage(webDriver);
        webDriver.get("https://stellarburgers.nomoreparties.site");
        loginPage.goToPersonalAccount();
        loginPage.LoggedIn("annaponomareva@mail.ru", "123456");
        Assert.assertEquals(true, loginPage.isMainPageTitleIsVisible());

    }

    @Test
    @DisplayName("Логин пользователя через кнопку в форме регистрации")
    @Description("Успешный логин пользователя через кнопку в форме регистрации")
    public void loginThrowEnterRegistrBottomTest() {
        loginPage = new LoginPage(webDriver);
        webDriver.get("https://stellarburgers.nomoreparties.site");
        loginPage.goToPersonalAccount();
        loginPage.goToRegistrPage();
        loginPage.goToLoginPageFromRegistrRestorePasswordPage();
        loginPage.LoggedIn("annaponomareva@mail.ru", "123456");
        Assert.assertEquals(true, loginPage.isMainPageTitleIsVisible());

    }

    @Test
    @DisplayName("Логин пользователя через кнопку в форме восстановления пароля")
    @Description("Успешный логин пользователя через кнопку в форме восстановления пароля")
    public void loginThrowEnterRestorePasswordBottomTest() {
        loginPage = new LoginPage(webDriver);
        webDriver.get("https://stellarburgers.nomoreparties.site");
        loginPage.goToPersonalAccount();
        loginPage.goToRestorePasswordPage();
        loginPage.goToLoginPageFromRegistrRestorePasswordPage();
        loginPage.LoggedIn("annaponomareva@mail.ru", "123456");
        Assert.assertEquals(true, loginPage.isMainPageTitleIsVisible());

    }

    @After
    public void teardown() {
        webDriver.quit();
    }


}
