import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    private final By enterAccountBottom = By.xpath(".//*[text()= 'Войти в аккаунт']");
    private final By emailField = By.xpath("/html/body/div/div/main/div/form/fieldset[1]/div/div/input");
    private final By passwordField = By.xpath("/html/body/div/div/main/div/form/fieldset[2]/div/div/input");
    private final By enterBottom = By.xpath(".//*[text()= 'Войти']");
    private final By mainPageTitle = By.xpath(".//*[text()= 'Соберите бургер']");
    private final By personalAccountBottom = By.xpath(".//*[text()= 'Личный Кабинет']");
    private final By registerBottom = By.xpath(".//*[text()= 'Зарегистрироваться']");
    private final By restorePasswordBottom = By.xpath(".//*[text()= 'Восстановить пароль']");
    private WebDriver webDriver;


    public LoginPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    @Step("Переход на страницу логина")
    public void goToLoginPage() {
        webDriver.findElement(enterAccountBottom).click();
    }

    @Step("Переход на страницу регистрации")
    public void goToRegistrPage() {
        webDriver.findElement(registerBottom).click();
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

    @Step("Переход со страницы регистрации/восстановления пароля на страницу логина через кнопку 'Войти'")
    public void goToLoginPageFromRegistrRestorePasswordPage() {
        webDriver.findElement(enterBottom).click();
    }

    @Step("Переход на страницу восстановления пароля")
    public void goToRestorePasswordPage() {
        webDriver.findElement(restorePasswordBottom).click();
    }

    public boolean isMainPageTitleIsVisible() {
        try {
            webDriver.findElement(mainPageTitle);
            return true;
        } catch (NoSuchElementException exception) {
            return false;
        }
    }

}
