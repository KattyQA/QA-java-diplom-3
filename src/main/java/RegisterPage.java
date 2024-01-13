import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class RegisterPage {

    private final By registerBottom = By.xpath(".//*[text()= 'Зарегистрироваться']");
    private final By nameRegisterField = By.xpath("/html/body/div/div/main/div/form/fieldset[1]/div/div/input");
    private final By emailRegisterField = By.xpath("/html/body/div/div/main/div/form/fieldset[2]/div/div/input");
    private final By passwordRegisterField = By.xpath("/html/body/div/div/main/div/form/fieldset[3]/div/div/input");
    private final By titleEnter = By.xpath(".//*[text()= 'Вход']");
    private final By passwordError = By.xpath(".//*[text()= 'Некорректный пароль']");
    private WebDriver webDriver;


    public RegisterPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    @Step("Переход на страницу регистрации")
    public void goToRegistrPage() {
        webDriver.findElement(registerBottom).click();
    }

    @Step("Регистрация пользователя")
    public void registerUser(String name, String email, String password) {
        webDriver.findElement(nameRegisterField).sendKeys(name);
        webDriver.findElement(emailRegisterField).sendKeys(email);
        webDriver.findElement(passwordRegisterField).sendKeys(password);
        webDriver.findElement(registerBottom).click();

    }

    public boolean isTitleEnterVisible() {
        try {
            webDriver.findElement(titleEnter);
            return true;
        } catch (NoSuchElementException exception) {
            return false;
        }
    }

    public boolean isPasswordErrorVisible() {
        try {
            webDriver.findElement(passwordError);
            return true;
        } catch (NoSuchElementException exception) {
            return false;
        }
    }


}

