import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage {
    private final By bunsButton = By.xpath(".//span[text()='Булки']");
    private final By saucesButton = By.xpath(".//span[text()='Соусы']") ;
    private final By fillingsButton = By.xpath("/html/body/div/div/main/section[1]/div[1]/div[3]");
    private WebDriver webDriver;

    public MainPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    @Step("Переход к разделу 'Булки'")
    public void goToBunsPage() {
        webDriver.findElement(bunsButton).click();
    }

    @Step("Получение класса раздела 'Булки'")
    public void getBunsClass() {
        webDriver.findElement(bunsButton).getAttribute("class");
    }

    @Step("Переход к разделу 'Соусы'")
    public void goToSaucesPage() {
        webDriver.findElement(saucesButton).click();
    }

    @Step("Переход к разделу 'Начинки'")
    public void goToFillingsPage() {
        webDriver.findElement(fillingsButton).click();
    }


}
