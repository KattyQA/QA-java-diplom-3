import com.github.javafaker.Faker;
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

public class RegisterTests {

    private WebDriver webDriver;
    private RegisterPage registerPage;

    @Before
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        webDriver = new ChromeDriver(options);
        webDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    @Test
    @DisplayName("Регистрация пользователя")
    @Description("Успешная регистрация пользователя, переход на страницу логина после регистрации")
    public void registerTest() {
        Faker faker = new Faker();
        registerPage = new RegisterPage(webDriver);
        webDriver.get("https://stellarburgers.nomoreparties.site/login");
        registerPage.goToRegistrPage();
        registerPage.registerUser(faker.name().name(), faker.internet().emailAddress(), faker.letterify("????????"));
        Assert.assertEquals(true, registerPage.isTitleEnterVisible());
    }

    @Test
    @DisplayName("Регистрация пользователя с невалидным паролем")
    @Description("Ошибка при вводе пароля меньше 6 символов")
    public void registerPasswordErrorTest() {
        registerPage = new RegisterPage(webDriver);
        webDriver.get("https://stellarburgers.nomoreparties.site/login");
        registerPage.goToRegistrPage();
        registerPage.registerUser("Анна", "annaponomareva@mail.ru", "1234");
        Assert.assertEquals(true, registerPage.isPasswordErrorVisible());
    }


    @After
    public void teardown() {
        webDriver.quit();
    }

}


