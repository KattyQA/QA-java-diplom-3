import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class PersonalAccountPage {

    private final By enterAccountBottom = By.xpath(".//*[text()= 'Войти в аккаунт']");
    private final By emailField = By.xpath("/html/body/div/div/main/div/form/fieldset[1]/div/div/input");
    private final By passwordField = By.xpath("/html/body/div/div/main/div/form/fieldset[2]/div/div/input");
    private final By enterBottom = By.xpath(".//*[text()= 'Войти']");
    private final By personalAccountBottom = By.xpath(".//*[text()= 'Личный Кабинет']");
    private final By orderHistory = By.xpath(".//*[text()= 'История заказов']");
    private final By constructorBottom = By.xpath(".//*[text()= 'Конструктор']");
    private final By mainPageTitle = By.xpath(".//*[text()= 'Соберите бургер']");
    private final By mainLogo = By.xpath("/html/body/div/div/header/nav/div");
    private final By logoutBottom = By.xpath(".//*[text()= 'Выход']");
    private final By titleEnter = By.xpath(".//*[text()= 'Вход']");
    private WebDriver webDriver;

    public PersonalAccountPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    @Step("Переход на страницу логина")
    public void goToLoginPage() {
        webDriver.findElement(enterAccountBottom).click();
    }

    @Step("Логин пользователя")
    public void LoggedIn(String email, String password) {
        webDriver.findElement(emailField).sendKeys(email);
        webDriver.findElement(passwordField).sendKeys(password);
        webDriver.findElement(enterBottom).click();

    }

    @Step("Переход в личный кабинет")
    public void goToPersonalAccount() {
        webDriver.findElement(personalAccountBottom).click();
    }

    @Step("Переход в конструктор через кнопку 'Конструктор'")
    public void goToConstructor() {
        webDriver.findElement(constructorBottom).click();
    }

    @Step("Переход в конструктор через логотип")
    public void goToConstuctorThrowLogo() {
        webDriver.findElement(mainLogo).click();
    }

    @Step("Выход из аккаунта")
    public void logout() {
        webDriver.findElement(logoutBottom).click();
    }

    public boolean isOrderHistoryIsVisible() {
        try {
            webDriver.findElement(orderHistory);
            return true;
        } catch (NoSuchElementException exception) {
            return false;
        }
    }

    public boolean isMainPageTitleIsVisible() {
        try {
            webDriver.findElement(mainPageTitle);
            return true;
        } catch (NoSuchElementException exception) {
            return false;
        }
    }

    public boolean isTitleEnterVisible() {
        try {
            webDriver.findElement(titleEnter);
            return true;
        } catch (NoSuchElementException exception) {
            return false;
        }
    }
}
